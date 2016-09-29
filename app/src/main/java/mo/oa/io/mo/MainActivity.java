package mo.oa.io.mo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mo.oa.io.mo.Adapter.FragmentAdapter;
import mo.oa.io.mo.UI.Base.BaseActivity;
import mo.oa.io.mo.UI.contactor.Fragment_Contactor;
import mo.oa.io.mo.UI.homemenu.Fragment_HomeMenu;
import mo.oa.io.mo.UI.message.Fragment_Message;
import mo.oa.io.mo.UI.personalcenter.Fragment_PersonalCenter;
import mo.oa.io.mo.UI.pubboard.Fragment_PubBoard;
import mo.oa.io.mo.Utils.NoTitle;
import mo.oa.io.mo.Widget.TabSelectorLayout;

public class MainActivity extends BaseActivity {
    @Bind(R.id.main_viewPager)
    ViewPager vp;
    List<Fragment> list;
    @Bind(R.id.main_selectTab)
    TabSelectorLayout tabSelectorLayout;

    private FragmentAdapter fad;
    public View v;

    @Bind(R.id.main_toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addLayout(R.layout.activity_main);
//        NoTitle.getTitle().setStatusView(this,R.color.touming);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ButterKnife.bind(this);
        setToolbar();
        init();
    }

    void setToolbar(){

        //toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Intent in = getIntent();
        String username = in.getStringExtra("person");
        showToast(username);
    }

    void init(){
        list = new ArrayList<>();
        fad = new FragmentAdapter(getSupportFragmentManager());
        list.add(new Fragment_Message());
        list.add(new Fragment_PubBoard());
        list.add(new Fragment_HomeMenu());
        list.add(new Fragment_Contactor());
        list.add(new Fragment_PersonalCenter());
        tabSelectorLayout.addTab(TabSelectorLayout.newTab().
                setNormalDrawable(getResources().getDrawable(R.drawable.msg)).
                setSelectDrawable(getResources().getDrawable(R.drawable.msg_f)).
                setTitle("消息"));
        tabSelectorLayout.addTab(TabSelectorLayout.newTab().
                setNormalDrawable(getResources().getDrawable(R.drawable.ggl)).
                setSelectDrawable(getResources().getDrawable(R.drawable.ggl_f)).
                setTitle("公告"));
        tabSelectorLayout.addTab(TabSelectorLayout.newTab().
                setNormalDrawable(getResources().getDrawable(R.drawable.home)).
                setSelectDrawable(getResources().getDrawable(R.drawable.home_f)).
                setTitle("主页"));
        tabSelectorLayout.addTab(TabSelectorLayout.newTab().
                setNormalDrawable(getResources().getDrawable(R.drawable.llr)).
                setSelectDrawable(getResources().getDrawable(R.drawable.llr_f)).
                setTitle("联系人"));
        tabSelectorLayout.addTab(TabSelectorLayout.newTab().
                setNormalDrawable(getResources().getDrawable(R.drawable.my)).
                setSelectDrawable(getResources().getDrawable(R.drawable.my_f)).
                setTitle("我的"));
        fad.setItems(list);
        vp.setAdapter(fad);
        vp.setCurrentItem(0);
        tabSelectorLayout.bindViewPager(vp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
