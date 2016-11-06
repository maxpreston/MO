package mo.oa.io.mo.UI.Base;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import mo.oa.io.mo.R;
import mo.oa.io.mo.UI.Searcher.Searcher_Activity;

/**
 * Created by max-code on 2016/9/19.
 */
public class BaseActivity extends AppCompatActivity {

    //设置布局
    public void addLayout(int layout){
        setContentView(layout);
    }
    protected boolean canback;
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showToast(String msg){
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private long currentTimes;
//
    @Override
    public void onBackPressed() {
        if(!canback){
            if((System.currentTimeMillis()-currentTimes)>2000){
                showToast("再点一次就退出啦");
                currentTimes = System.currentTimeMillis();
            }else{
                finish();
            }
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main,menu);
//        SearchManager sm = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView sv = (SearchView) menu.findItem(R.id.action_login).getActionView();
//        sv.setSearchableInfo(sm.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_login){
            startActivity(new Intent(this, Searcher_Activity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
