package mo.oa.io.mo.UI.pubboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import mo.oa.io.mo.Entities.MsgVo;
import mo.oa.io.mo.R;
import mo.oa.io.mo.UI.Base.CommBaseFragment;
import mo.oa.io.mo.UI.Base.NoRefreshBaseFragment;
import mo.oa.io.mo.Utils.OldDriverBus;
import rx.Subscription;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;

/**
 * Created by max-code on 2016/9/23.
 */

public class Fragment_PubBoard extends NoRefreshBaseFragment {
    @Override
    public int addLayoutView() {
        return R.layout.timetree_lv_item;
    }
    @Bind(R.id.msg_timetext)
    TextView msgtime;

    private Bundle intent;

    public Fragment_PubBoard() {
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(addLayoutView(),container,false);
        ButterKnife.bind(this,view);
        TakeBus();
        msgtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("点击了");
                intent = new Bundle();
                intent.putCharSequence("msg","hahaha,sb！");
                OldDriverBus.getOldDriver().sendBus(intent);
            }
        });
        return view;
    }

    void TakeBus(){
//        sub = OldDriverBus.getOldDriver().ToObserable().subscribe(new Action1<Object>() {
//            @Override
//            public void call(Object msgVo) {
//                Bundle bun = (Bundle) msgVo;
//                MsgVo msg = (MsgVo) bun.getSerializable("msgvo");
//                msgtime.setText(msg.getMsgTime());
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Unsubscrib();
    }
}
