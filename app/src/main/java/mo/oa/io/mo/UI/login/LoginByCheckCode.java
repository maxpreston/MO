package mo.oa.io.mo.UI.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mo.oa.io.mo.UI.Base.BaseFragment;
import mo.oa.io.mo.Entities.PersonalInfo;
import mo.oa.io.mo.MainActivity;
import mo.oa.io.mo.R;
import mo.oa.io.mo.Services.AllServices;
import mo.oa.io.mo.Utils.LogUtils;
import mo.oa.io.mo.Utils.PbUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by max-code on 2016/9/19.
 */
public class LoginByCheckCode extends BaseFragment {
    //注解find
    @Bind(R.id.swrydmorzh)
    EditText username;
    @Bind(R.id.CodeNum)
    EditText codenum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loginbycheckcode_layout,container,false);
        //添加注解支持
        ButterKnife.bind(this,view);
        return view;
    }


    @OnClick(R.id.login_checkcode_btnlogin)
    public void sendCode(){
        if(TextUtils.isEmpty(codenum.getText().toString())||TextUtils.isEmpty(username.getText().toString())){
            showToast("请输入正确信息");
            return ;
        }else{
            sendCodeToWeb(username.getText().toString());
        }
    }

    public void sendCodeToWeb(String username){
        sub = AllServices.
                getLoginAPI(getActivity()).
                loginByCheckCode("0","1234",username).
                map(new Func1<String, Map<String,String>>() {
            @Override
            public Map<String,String> call(String s) {
                try {
                    LogUtils.E("返回信息"+s);
                    JSONObject jobj = new JSONObject(s);
                    String flagStr = jobj.getString("flagStr");
                    if(flagStr.equals("true")){
                        PersonalInfo p = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create().fromJson(jobj.getString("userInfo"),PersonalInfo.class);
                        Map<String,String> userMap = new HashMap<String, String>();
                        userMap.put(PbUtils.FLAG, "true");
                        userMap.put(PbUtils.GestureFlag, "true");
                        userMap.put(PbUtils.ACTORID, p.getActorid());
                        userMap.put(PbUtils.ORGALIAS, p.getOrgalias());
                        userMap.put(PbUtils.USERNAME, p.getUsername());
                        userMap.put(PbUtils.USERACCOUNT, p.getUseraccount());
                        userMap.put(PbUtils.USERID, p.getUserid());
                        userMap.put(PbUtils.SWJGDM, p.getSwjgdm().toString());
                        userMap.put(PbUtils.STATUS, String.valueOf(p.getStatus()));
                        userMap.put(PbUtils.USERTYPE, String.valueOf(p.getUsertype()));
                        userMap.put(PbUtils.MOBILEPHONE, p.getMobilephone());
                        userMap.put(PbUtils.EMAIL, p.getEmail());
                        userMap.put(PbUtils.XYBZ, String.valueOf(p.getXybz()));
                        userMap.put(PbUtils.LASTLOGIN, p.getLastlogindate());
                        userMap.put(PbUtils.GWUSERID, p.getGwuserid());
                        PbUtils.setLoginMessage(userMap, getActivity());
                        PbUtils.setUserType(String.valueOf(p.getUsertype()), getActivity());
                        return userMap;
                    }else{
                        return null;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    LogUtils.E(e.getMessage());
                }
                return null;
            }
        }).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<Map<String,String>>() {
            @Override
            public void call(Map<String,String> personalInfo) {
                if (personalInfo == null) {
                    showToast("登录失败");
                } else {
                    showToast("登录成功");
                    LogUtils.E("登录成功");
                    PbUtils.setLoginMessage(personalInfo,getActivity());
                    Intent in = new Intent(mAct,MainActivity.class);
                    in.putExtra("person",personalInfo.get(PbUtils.USERNAME));
                    startActivity(in);
                    mAct.finish();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                showToast("异常了-->"+throwable.getMessage());
                LogUtils.E(throwable.getMessage());
            }
        });
        AddComSub(sub);
    }

    @OnClick(R.id.codelogin_back)
    void getBack(){
            getBackBtn();
    }
}
