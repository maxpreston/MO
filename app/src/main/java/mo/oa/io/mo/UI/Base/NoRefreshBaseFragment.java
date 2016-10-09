package mo.oa.io.mo.UI.Base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import butterknife.Bind;
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

public abstract class NoRefreshBaseFragment extends Fragment {

    public abstract int addLayoutView();

    public static Activity smAct;
    private MainActivity main;

    private CompositeSubscription scompositeSubscription;
    public Subscription sub;

    public CompositeSubscription getCompositeSubscription() {
        if(scompositeSubscription == null){
            scompositeSubscription = new CompositeSubscription();
        }
        return scompositeSubscription;
    }

    //解绑
    public void Unsubscrib(){
        if(this.scompositeSubscription!=null){
            LogUtils.E("解绑了");
            this.scompositeSubscription.unsubscribe();
        }
    }

    //添加subscription到compositionsub
    public void AddComSub(Subscription sub){
        if(this.scompositeSubscription==null){
            this.scompositeSubscription = new CompositeSubscription();
        }
        scompositeSubscription.add(sub);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        smAct = activity;
    }

    public MainActivity getMain(){
        if(main==null){
            main = (MainActivity) getActivity();
        }
        return main;
    }

    //显示隐藏Fragment（hide/show方式）
    public static void TransFragment(Fragment target, Fragment Current, int layoutID){
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
        Toast.makeText(smAct, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Unsubscrib();
    }

    public void LoadError(Throwable e){
        LogUtils.E(e.getMessage());
    }

}