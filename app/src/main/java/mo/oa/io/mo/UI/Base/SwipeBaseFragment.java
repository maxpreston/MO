package mo.oa.io.mo.UI.Base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import mo.oa.io.mo.R;
import mo.oa.io.mo.Widget.MultiRefreshLayout;
import mo.oa.io.mo.Widget.SwipeRefreshInterFace;
import mo.oa.io.mo.Widget.MultiRefreshLayout.CanChildScrollUpCallBack;
/**
 * Created by max-code on 2016/9/25.
 */

public abstract class SwipeBaseFragment extends Fragment implements SwipeRefreshInterFace {
    private boolean isRefresh;

    private MultiRefreshLayout multiRefreshLayout;


    @Override
    public void getRefrsh() {
        isRefresh = true;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trysetRefresh();
    }

    void trysetRefresh(){
        if(multiRefreshLayout!=null){
            multiRefreshLayout.setColorSchemeResources(R.color.blue,
                    R.color.black, R.color.white);
            multiRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    getRefrsh();
                }
            });
        }
    }

    @Override
    public void setIsRefresh(boolean isrefresh) {
        if(multiRefreshLayout==null){
            return;
        }
        if(!isrefresh){
            isRefresh = false;
            multiRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(multiRefreshLayout!=null){
                        multiRefreshLayout.setRefreshing(false);
                    }
                }
            },500);
        }else{
            multiRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void setProgressOffset(boolean scale, int start, int end) {
        multiRefreshLayout.setProgressViewOffset(scale,start,end);
    }

    @Override
    public void setCanChildScrollCallBack(MultiRefreshLayout.CanChildScrollUpCallBack canChildScrollCallBack) {
        multiRefreshLayout.setCanChildScrollUpCallBack(canChildScrollCallBack);
    }
}
