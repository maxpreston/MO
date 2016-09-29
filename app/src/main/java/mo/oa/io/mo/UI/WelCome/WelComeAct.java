package mo.oa.io.mo.UI.WelCome;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import mo.oa.io.mo.UI.Base.BaseActivity;
import mo.oa.io.mo.R;
import mo.oa.io.mo.Utils.BitmapUtils;

/**
 * Created by max-code on 2016/9/21.
 */
public class WelComeAct extends BaseActivity {
    @Bind(R.id.welcome_img)
    ImageView bit;
    Intent in;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addLayout(R.layout.welcome_layout);
        ButterKnife.bind(this);
//        bu = BitmapUtils.getInstance(this);
//        bits = bu.compressBitmap(R.drawable.login_backg, Bitmap.Config.RGB_565);
//        bit.setImageBitmap(bits);
        in = new Intent();
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                    in.setClass(WelComeAct.this,ViewPagerAct.class);
                    startActivity(in);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
