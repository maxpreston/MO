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

import com.jakewharton.rxbinding.view.RxViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import mo.oa.io.mo.Adapter.FragmentAdapter;
import mo.oa.io.mo.UI.Base.BaseActivity;
import mo.oa.io.mo.UI.Base.ToolBarActvity;
import mo.oa.io.mo.UI.contactor.Fragment_Contactor;
import mo.oa.io.mo.UI.homemenu.Fragment_HomeMenu;
import mo.oa.io.mo.UI.message.Fragment_Message;
import mo.oa.io.mo.UI.personalcenter.Fragment_PersonalCenter;
import mo.oa.io.mo.UI.pubboard.Fragment_PubBoard;
import mo.oa.io.mo.Utils.NoTitle;
import mo.oa.io.mo.Widget.TabSelectorLayout;

public class MainActivity extends ToolBarActvity {
    @Bind(R.id.main_viewPager)
    ViewPager vp;
    List<Fragment> list;
    @Bind(R.id.main_selectTab)
    TabSelectorLayout tabSelectorLayout;

    private FragmentAdapter fad;
    public View v;
    private Fragment_Message fragment_message;
    private Fragment_PubBoard fragment_pubBoard;
    private Fragment_HomeMenu fragment_homeMenu;
    private Fragment_Contactor fragment_contactor;
    private Fragment_PersonalCenter fragment_personalCenter;
    @Bind(R.id.base_toolbar)
    Toolbar toolbar;

    @Override
    protected int provideLayoutid() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        NoTitle.getTitle().setStatusView(this,R.color.touming);

        ButterKnife.bind(this);
//        setToolbar();
        fragment_message = new Fragment_Message();
        fragment_pubBoard = new Fragment_PubBoard();
        fragment_homeMenu = new Fragment_HomeMenu();
        fragment_contactor = new Fragment_Contactor();
        fragment_personalCenter = new Fragment_PersonalCenter();
        init();
    }

    void setToolbar(){
        //toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setCollapsible(true);
        toolbar.setNavigationIcon(R.drawable.home);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("navigation被点击");
            }
        });
        toolbar.setSubtitle("哈哈");
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
        list.add(fragment_message);
        list.add(fragment_pubBoard);
        list.add(fragment_homeMenu);
        list.add(fragment_contactor);
        list.add(fragment_personalCenter);
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
                setTitle("my"));
        fad.setItems(list);
        vp.setOffscreenPageLimit(4);
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
