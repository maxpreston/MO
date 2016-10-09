package mo.oa.io.mo.UI.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import mo.oa.io.mo.MainActivity;
import mo.oa.io.mo.R;
import mo.oa.io.mo.Utils.LogUtils;
import mo.oa.io.mo.Widget.MultiRefreshLayout;
import mo.oa.io.mo.Widget.SwipeRefreshInterFace;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by max-code on 2016/9/23.
 */

public abstract class CommBaseFragment extends Fragment implements SwipeRefreshInterFace {
    //公共freshLayout布局
    @Bind(R.id.comm_refresh_layout)
    public MultiRefreshLayout multiRefreshLayout;
    //是否刷新状态
    private boolean IsRequestDataRefresh = false;
    //提供布局View
    public abstract int addLayoutView();

    public ClickViewToTop clickViewToTop;
    public void SetFresh(){
        if(multiRefreshLayout!=null){
            multiRefreshLayout.setColorSchemeResources(R.color.red,R.color.white,R.color.blue);
            multiRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    //调用数据刷新方法
                    getRefrsh();
                }
            });
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SetFresh();
    }

    public static Activity mAct;
    private MainActivity main;

    private CompositeSubscription compositeSubscription;
    public Subscription sub;

    public CompositeSubscription getCompositeSubscription() {
        if(compositeSubscription == null){
            compositeSubscription = new CompositeSubscription();
        }
        return compositeSubscription;
    }

    //解绑
    public void Unsubscrib(){
        if(this.compositeSubscription!=null){
            LogUtils.E("解绑了");
            this.compositeSubscription.unsubscribe();
        }
    }

    //添加subscription到compositionsub
    public void AddComSub(Subscription sub){
        if(this.compositeSubscription==null){
            this.compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(sub);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mAct = activity;
        if(activity instanceof ClickViewToTop){
            clickViewToTop = (ClickViewToTop) activity;
        }else{
            throw new IllegalArgumentException("viewToTop为null");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickViewToTop = null;
    }

    public MainActivity getMain(){
        if(main==null){
            main = (MainActivity) getActivity();
        }
        return main;
    }

    //显示隐藏Fragment（hide/show方式）
    public static void TransFragment(android.support.v4.app.Fragment target, android.support.v4.app.Fragment Current, int layoutID){
        FragmentTransaction ft = Current.getFragmentManager().beginTransaction();
        if(target.isAdded()){
            ft.hide(Current).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).show(target).addToBackStack(null).commitAllowingStateLoss();
        }else{
            ft.hide(Current).add(layoutID,target).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).show(target).addToBackStack(null).commitAllowingStateLoss();
        }
    }

    //mainativity回退栈方法
    public void getMainBack(){
        main.onBackPressed();
    }

    public static void showToast(String msg){
        Toast.makeText(mAct, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Unsubscrib();
    }

    //刷新数据的方法
    @Override
    public void getRefrsh() {
        IsRequestDataRefresh = true;
    }

    //是否可刷新
    @Override
    public void setIsRefresh(boolean getRefrsh) {
        if(multiRefreshLayout==null){
            return;
        }
        if(!getRefrsh){
            IsRequestDataRefresh = false;
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

    //设置进度条刷新状态
    @Override
    public void setProgressOffset(boolean scale, int start, int end) {
        multiRefreshLayout.setProgressViewOffset(scale,start,end);
    }

    //设置子控件滑动回调
    @Override
    public void setCanChildScrollCallBack(MultiRefreshLayout.CanChildScrollUpCallBack canChildScrollCallBack) {
        multiRefreshLayout.setCanChildScrollUpCallBack(canChildScrollCallBack);
    }

    public void LoadError(Throwable e){
        LogUtils.E(e.getMessage());
    }

    public interface ClickViewToTop{
        public void clickToTop(RecyclerView view);
    }

    public void setClickViewToTop(ClickViewToTop clickViewToTop) {
        this.clickViewToTop = clickViewToTop;
    }
}
