package mo.oa.io.mo.UI.login;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import butterknife.ButterKnife;
import mo.oa.io.mo.UI.Base.BaseActivity;
import mo.oa.io.mo.R;

/**
 * Created by max-code on 2016/9/19.
 */
public class Login_UI extends BaseActivity {
    private SimpleLogin simpleLogin = new SimpleLogin();
    private LoginByCheckCode loginByCheckCode = new LoginByCheckCode();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addLayout(R.layout.login_motherlayout);
        ButterKnife.bind(this);
        TransFragment(simpleLogin,R.id.top_one);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void TransFragment(Fragment target, int layoutID){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(layoutID,target).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }

}
