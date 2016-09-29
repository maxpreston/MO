package mo.oa.io.mo.UI.Base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import butterknife.Bind;
import mo.oa.io.mo.MainActivity;
import mo.oa.io.mo.UI.login.Login_UI;
import mo.oa.io.mo.Utils.LogUtils;
import mo.oa.io.mo.Widget.MultiRefreshLayout;
import mo.oa.io.mo.Widget.SwipeRefreshInterFace;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by max-code on 2016/9/19.
 */
public class BaseFragment extends Fragment{
    private Login_UI login_ui = (Login_UI) getActivity();

    public Login_UI getLogin_ui() {
        if(login_ui==null){
            login_ui = (Login_UI) getActivity();
        }
        return login_ui;
    }

    public static Activity mAct;
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
        if(this.compositeSubscription!=null&&!this.compositeSubscription.isUnsubscribed()){
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
    public void onDestroyView() {
        super.onDestroyView();
        if(compositeSubscription!=null){
            LogUtils.E("释放CompositeSub对象");
            compositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mAct = activity;
    }

    //显示隐藏Fragment（replace方式）
    public static void ReplaceTransFragment(Fragment target,Fragment Current,int layoutID){
        FragmentTransaction ft = Current.getFragmentManager().beginTransaction();
            ft.
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).
                    replace(layoutID,target).
                    addToBackStack(null).
                    commit();
    }

    //login回退栈方法
   public void getBackBtn(){
        getLogin_ui().onBackPressed();
    }

    public static void showToast(String msg){
        Toast.makeText(mAct, msg, Toast.LENGTH_SHORT).show();
    }

}
