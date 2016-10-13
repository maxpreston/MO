package mo.oa.io.mo.UI.personalcenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import mo.oa.io.mo.R;
import mo.oa.io.mo.UI.Base.CommBaseFragment;
import mo.oa.io.mo.UI.Base.NoRefreshBaseFragment;
import mo.oa.io.mo.Utils.OldDriverBus;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by max-code on 2016/9/23.
 */

public class Fragment_PersonalCenter extends NoRefreshBaseFragment {
    @Override
    public int addLayoutView() {
        return R.layout.timetree_lv_item;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(addLayoutView(),container,false);
        ButterKnife.bind(this,view);
        takeBus();
        return view;
    }
    
    void takeBus(){
        sub = OldDriverBus.getOldDriver().ToObserable().ofType(Integer.class).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                showToast(((Bundle) o).getString("msg", "无信息") + Fragment_PersonalCenter.this.getClass().getName());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                showToast("不是String类型");
            }
        }, new Action0() {
            @Override
            public void call() {
                showToast("action0");
            }
        });
    }
}
