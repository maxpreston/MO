package mo.oa.io.mo.UI.WelCome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mo.oa.io.mo.Adapter.Vp_ViewPagerAdapter;
import mo.oa.io.mo.UI.Base.BaseActivity;
import mo.oa.io.mo.MainActivity;
import mo.oa.io.mo.R;
import mo.oa.io.mo.UI.login.Login_UI;
import mo.oa.io.mo.Utils.BitmapUtils;
import mo.oa.io.mo.Utils.PbUtils;

/**
 * Created by max-code on 2016/9/21.
 */
public class ViewPagerAct extends BaseActivity {
    private Intent in;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private BitmapUtils bu;
    @Bind(R.id.vp_layout_vp)
    ViewPager vp;
    View view1,view2,view3;
    Bitmap bit1,bit2,bit3;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    @Bind(R.id.vp_layout_dot_area)
    LinearLayout lindots;
    Button btn;
    List<View> list;
    ImageView[] dots;
    LayoutInflater infa;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addLayout(R.layout.pageract_layout);
        ButterKnife.bind(this);
        getIfFirstStart();
        infa = getLayoutInflater();
        view1 = infa.inflate(R.layout.vp_view1_layout,null);
        view2 = infa.inflate(R.layout.vp_view2_layout,null);
        view3 = infa.inflate(R.layout.vp_view3_layout,null);
        btn = (Button) view3.findViewById(R.id.view3_layout_btn);
        img1 = (ImageView) view1.findViewById(R.id.view1_layout_img);
        img2 = (ImageView) view2.findViewById(R.id.view2_layout_img);
        img3 = (ImageView) view3.findViewById(R.id.view3_layout_img);
        bu = BitmapUtils.getInstance(this);
        bit1 = bu.compressBitmap(R.drawable.login_bg1, Bitmap.Config.RGB_565);
        bit2 = bu.compressBitmap(R.drawable.login_bg2, Bitmap.Config.RGB_565);
        bit3 = bu.compressBitmap(R.drawable.login_bg3, Bitmap.Config.RGB_565);
        img1.setImageBitmap(bit1);
        img2.setImageBitmap(bit2);
        img3.setImageBitmap(bit3);
        list = new ArrayList<View>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        vp.setAdapter(new Vp_ViewPagerAdapter(list,false));
        vp.setCurrentItem(0);
        dots = new ImageView[list.size()];

        dots[0] = (ImageView) lindots.getChildAt(0);
        dots[1] = (ImageView) lindots.getChildAt(1);
        dots[2] = (ImageView) lindots.getChildAt(2);
        dots[0].setEnabled(true);
        dots[0].setEnabled(false);
        dots[0].setEnabled(false);
        vp.setOnPageChangeListener(change);
        btn.setOnClickListener(click);
    }

    //判断是否第一次启动
    void getIfFirstStart(){
        sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("firstStart",true)){//第一次启动应用
            //写入手机设备信息
            PbUtils.setPhoneMessage(PbUtils.getInfo(this),this);
            editor = sharedPreferences.edit();
            editor.putBoolean("firstStart",false);
            editor.commit();
        }else{//非第一次启动直接根据用户登录状态跳转相应界面
            in = new Intent();
            if(checkUserLoginState()){
                in.setClass(ViewPagerAct.this, MainActivity.class);
            }else{
                in.setClass(ViewPagerAct.this, Login_UI.class);
            }
            startActivity(in);
            finish();
        }
    }
    //检查用户登录状态
    boolean checkUserLoginState(){
        if("true".equals(PbUtils.getLoginMessageFLAG(this))){
            return true;
        }else{
            return false;
        }
    }
    android.support.v4.view.ViewPager.OnPageChangeListener change = new ViewPager.OnPageChangeListener() {

        private void setdot(int position){
            for (int i = 0;i<dots.length;i++){
                if(i==position){
                    dots[i].setEnabled(true);
                }else{
                    dots[i].setEnabled(false);
                }
            }
        }
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setdot(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            in = new Intent();
            in.setClass(ViewPagerAct.this,Login_UI.class);
            startActivity(in);
            finish();
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if(bu!=null){
            bu.releaseNativeBitmap();
        }
    }
}
