package mo.oa.io.mo.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.*;
/**
 * Created by max-code on 2016/9/22.
 */
public class Vp_ViewPagerAdapter extends PagerAdapter {
    private List<View> list;
    private boolean islooping;

    public Vp_ViewPagerAdapter(List<View> list, boolean islooping) {
        this.list = list;
        this.islooping = islooping;
    }

    @Override
    public int getCount() {
        return list.size();
//        if(islooping){
//            //如果looping则直接返回一个最大值
//            return Integer.MAX_VALUE;
//        }else{
//            if(this.list!=null){
//                return list.size();
//            }else{
//                return 0;
//            }
//
//        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        if(islooping){
            int realPosition = position%this.list.size();
            view = this.list.get(realPosition);
            ViewGroup vg = (ViewGroup) view.getParent();
            if(vg!=null){
                vg.removeView(view);
            }
        }else {
            view = this.list.get(position);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(this.list.get(position));
//        if(!islooping){
//        }
    }
}
