package mo.oa.io.mo.UI.contactor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import mo.oa.io.mo.R;
import mo.oa.io.mo.UI.Base.NoRefreshBaseFragment;

/**
 * Created by max-code on 2016/9/23.
 */

public class Fragment_Contactor extends NoRefreshBaseFragment {
    @Override
    public int addLayoutView() {
        return R.layout.timetree_lv_item;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(addLayoutView(),container,false);
        ButterKnife.bind(this,view);
        return view;
    }
}
