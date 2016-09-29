package mo.oa.io.mo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by max-code on 2016/9/26.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    public void setItems(List<Fragment> list){
        this.list = list;
        notifyDataSetChanged();
    }
}
