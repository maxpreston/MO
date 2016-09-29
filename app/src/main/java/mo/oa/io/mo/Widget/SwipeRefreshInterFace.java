package mo.oa.io.mo.Widget;

/**
 * Created by max-code on 2016/9/25.
 * swipe刷新实现接口
 */
public interface SwipeRefreshInterFace {
    //刷新数据
    void getRefrsh();
    //是否可刷新
    void setIsRefresh(boolean isrefresh);
    //设置刷新progress偏移量
    void setProgressOffset(boolean scale,int start,int end);
    //refresh刷新回调
    void setCanChildScrollCallBack(MultiRefreshLayout.CanChildScrollUpCallBack canChildScrollCallBack);
}
