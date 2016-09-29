package mo.oa.io.mo.UI.Base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by max-code on 2016/9/19.
 */
public class BaseActivity extends AppCompatActivity {

    //设置布局
    public void addLayout(int layout){
        setContentView(layout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showToast(String msg){
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private long currentTimes;

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()!=0){
            getSupportFragmentManager().popBackStack();
        }else if((System.currentTimeMillis()-currentTimes)>2000){
            showToast("再点一次就退出啦");
            currentTimes = System.currentTimeMillis();
        }else{
            finish();
        }
    }

}
