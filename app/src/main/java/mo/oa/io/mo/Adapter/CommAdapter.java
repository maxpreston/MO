package mo.oa.io.mo.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by max-code on 2016/9/23.
 */

public class CommAdapter extends PagerAdapter {
    private List<View> list;

    public CommAdapter(List<View> list) {
        this.list = list;
    }

    // 获得视图数量
        public int getCount() {
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        // 判断两个对象是否相等
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        // // 销毁子视图
        // @Override
        // public void destroyItem(ViewGroup container, int position, Object
        // object) {
        // // TODO Auto-generated method stub
        // Toast.makeText(getActivity(), "销毁-->"+position,
        // Toast.LENGTH_SHORT).show();
        // container.removeView(list.get(position));
        // }

        // 向容器中添加子视图，返回添加的子对象
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        // 获得当前对象的位置
        public int getItemPosition(Object object) {
            // 返回集合中object对象出现的下标
            return list.indexOf(object);
        }

}
