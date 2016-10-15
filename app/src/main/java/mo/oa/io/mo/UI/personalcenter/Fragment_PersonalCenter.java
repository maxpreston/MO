package mo.oa.io.mo.UI.personalcenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import mo.oa.io.mo.Entities.MsgVo;
import mo.oa.io.mo.R;
import mo.oa.io.mo.UI.Base.CommBaseFragment;
import mo.oa.io.mo.UI.Base.NoRefreshBaseFragment;
import mo.oa.io.mo.Utils.OldDriverBus;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by max-code on 2016/9/23.
 */

public class Fragment_PersonalCenter extends NoRefreshBaseFragment {
    @Override
    public int addLayoutView() {
        return R.layout.timetree_lv_item;
    }
    private boolean isprepery;

    @Override
    public void LazyLoad() {
        if(!isprepery||!ISVISIABLE){
            return ;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(addLayoutView(),container,false);
        isprepery = true;
        ButterKnife.bind(this,view);
        LazyLoad();
        return view;
    }

    //获取网络数据
    void getRefresh(){

    }

    //初始化相关控件


    //老司机方法
    void takeBus(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
