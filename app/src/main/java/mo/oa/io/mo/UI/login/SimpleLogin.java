package mo.oa.io.mo.UI.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mo.oa.io.mo.UI.Base.BaseFragment;
import mo.oa.io.mo.R;
import mo.oa.io.mo.Utils.CheckUtils;

/**
 * Created by max-code on 2016/9/19.
 */
public class SimpleLogin extends BaseFragment{
    @Bind(R.id.login_name)
    EditText username;
    @Bind(R.id.login_pass)
    EditText pwd;
    private LoginByCheckCode loginbycheck = new LoginByCheckCode();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout,container,false);
        ButterKnife.bind(this,view);
        return view;
    }
    @OnClick(R.id.btn_user_login)
    void BtnLogin(){
        if(CheckUtils.CheckEmpty(username.getText().toString())&&CheckUtils.CheckEmpty(pwd.getText().toString())){
            showToast("接口开发中");
//            return;
//            sub = AllServices.
//                    getLoginAPI(getActivity()).simpleLogin("1",username.getText().toString(),pwd.getText().toString()).
//                    subscribeOn(Schedulers.io()).
//                    observeOn(AndroidSchedulers.mainThread()).
//                    subscribe(new Action1<String>() {
//                @Override
//                public void call(String s) {
//                    Log.e("登录成功-->",s);
//                }
//            });
        }else{
            showToast("请输入正确信息");
        }
    }

    @OnClick(R.id.login_user_by_check_code)
    void JumpToCheckLogin(){
        ReplaceTransFragment(loginbycheck,SimpleLogin.this,R.id.top_one);
    }
}
