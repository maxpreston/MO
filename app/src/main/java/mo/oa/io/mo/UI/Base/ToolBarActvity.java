package mo.oa.io.mo.UI.Base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import mo.oa.io.mo.R;

/**
 * Created by max-code on 2016/9/24.
 */

public abstract class ToolBarActvity extends BaseActivity implements CommBaseFragment.ClickViewToTop{
    private RecyclerView recyclerView;

    protected Toolbar toolbar;
    protected boolean IsHidden = false;
    abstract protected int provideLayoutid();
    //toolbarLayout点击事件
    public void ToolBarOnClick(){
        if(recyclerView!=null){
            recyclerView.smoothScrollToPosition(0);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutid());

        toolbar = (Toolbar) findViewById(R.id.base_toolbar);
        if(toolbar==null){
            throw new IllegalStateException("toolbar或appbarLayout为空");
        }
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToolBarOnClick();
            }
        });
        setSupportActionBar(toolbar);
        if(CanBack()){
            ActionBar actionBar = getSupportActionBar();
            if(actionBar!=null){
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
//        if(Build.VERSION.SDK_INT>=21){
//            appBarLayout.setElevation(10.6f);
//        }
    }
    //设置appbar透明度
//    protected void setAppBarLayoutAlpha(float alpha){
//        appBarLayout.setAlpha(alpha);
//    }

//    protected void isHideorShow(){
//        appBarLayout.animate().translationY(IsHidden?0:-appBarLayout.getHeight()).setInterpolator(new DecelerateInterpolator()).start();
//        IsHidden = !IsHidden;
//    }

    public boolean CanBack(){
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void clickToTop(RecyclerView view) {
        recyclerView = view;
    }
}
