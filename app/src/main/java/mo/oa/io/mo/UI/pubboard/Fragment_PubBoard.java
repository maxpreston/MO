package mo.oa.io.mo.UI.pubboard;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mo.oa.io.mo.Adapter.PubAdapter;
import mo.oa.io.mo.Entities.MsgVo;
import mo.oa.io.mo.Entities.NoticeEntity;
import mo.oa.io.mo.Entities.PubEntity;
import mo.oa.io.mo.R;
import mo.oa.io.mo.Services.AllServices;
import mo.oa.io.mo.UI.Base.CommBaseFragment;
import mo.oa.io.mo.UI.Base.NoRefreshBaseFragment;
import mo.oa.io.mo.Utils.LogUtils;
import mo.oa.io.mo.Utils.OldDriverBus;
import mo.oa.io.mo.Utils.PbUtils;
import mo.oa.io.mo.Widget.MultiRefreshLayout;
import mo.oa.io.mo.Widget.RecycleItemDecoration;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

/**
 * Created by max-code on 2016/9/23.
 */

public class Fragment_PubBoard extends CommBaseFragment {
    @Override
    public int addLayoutView() {
        return R.layout.fragment_pubboard;
    }
//    @Bind(R.id.msg_rv)
    public static RecyclerView recyclerView;
    private boolean isprepery;
    private List<NoticeEntity> list;
    private Bundle intent;
    private String userid;
    private PubAdapter pubAdapter = new PubAdapter();
    private boolean firstTimeTouchBottom = true;
    private boolean isTotal = false;
    private int currentstartItem = 1;
    private int currentendItem = 15;
    private int endItem;
    private int TotalItem;
    private String swjgdm ;
    public Fragment_PubBoard() {
    }

    @Override
    public void LazyLoad() {
        if(!isprepery||!ISVISIble){
            return ;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(addLayoutView(),container,false);
        isprepery = true;
//        ButterKnife.bind(this,view);
        initView(view);
        LazyLoad();
        TakeBus();
        list = new ArrayList<>();
        userid = PbUtils.getLoginMessageUserID(mAct);
        swjgdm = PbUtils.getLoginMessageSWJGDM(mAct);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mAct);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(pubAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(OnBottomListener(linearLayoutManager));
        OnClickListener();
        endItem = currentendItem;
        getRefrsh();
        if(clickViewToTop!=null){
            clickViewToTop.clickToTop(recyclerView,Fragment_PubBoard.class);
        }
        return view;
    }


    void OnClickListener(){
        pubAdapter.setClickListener(new PubAdapter.ClickListener() {
            @Override
            public void OnItemClickListener(int position, View view) {
                showToast("点击了-->"+list.get(position).getNoticeTitle());
            }

            @Override
            public void OnItemLongClickListener(int position, View view) {
                showToast("长按了-->"+list.get(position).getNoticeTitle());
            }
        });
    }

    RecyclerView.OnScrollListener OnBottomListener(final LinearLayoutManager linearLayoutManager){
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isbottom = (linearLayoutManager.findLastVisibleItemPosition()+1)>=pubAdapter.getItemCount();
                if(!multiRefreshLayout.isRefreshing()&&isbottom){
                    if(!firstTimeTouchBottom){
                        LogUtils.E("适配器总条数-->"+String.valueOf(pubAdapter.getItemCount()));
                        if(isTotal) return;
                        if(TotalItem == pubAdapter.getItemCount()){
                            showToast("已经加载全部条目");
                            isTotal = true;
                            return;
                        }
                        endItem+=15;
                        getRefrsh();
                    }else{
                        firstTimeTouchBottom = false;
                    }
                }
            }
        };
    };

    void TakeBus(){

    }

    void initView(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.pub_rv);
        multiRefreshLayout = (MultiRefreshLayout) view.findViewById(R.id.pub_refresh_layout);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Unsubscrib();
//        ButterKnife.unbind(this);
    }
    //获取数据
    @Override
    public void getRefrsh() {
        super.getRefrsh();
        Log.e("startItem-->",String.valueOf(currentstartItem));
        Log.e("endItem-->",String.valueOf(endItem));
        Log.e("userid-->",String.valueOf(userid));
        Log.e("swjgdm-->",String.valueOf(swjgdm));
        setIsRefresh(true);
        isTotal = false;
        sub = AllServices.
              getAllServices().
              getPubList(mAct).
              getPubInfo(String.valueOf(currentstartItem),String.valueOf(endItem),userid,swjgdm).
              subscribeOn(Schedulers.io()).
              observeOn(AndroidSchedulers.mainThread()).
              subscribe(new Action1<PubEntity>() {
                  @Override
                  public void call(PubEntity pubEntity) {
                      setIsRefresh(false);
                      LogUtils.E("------------------------------------");
                      LogUtils.E("flagStr-->"+pubEntity.flagStr);
                      if(pubEntity.flagStr.equals("false")){
                          showToast("未查询到信息");
                          endItem = currentendItem;
                      }else if(pubEntity==null){
                          showToast("数据查询错误,请稍候再试");
                          endItem = currentendItem;
                      }else{
                          LogUtils.E("正常");
                          TotalItem = pubEntity.totalSize;
                          LogUtils.E("公告总条数-->"+String.valueOf(pubEntity.totalSize));
                          list.clear();
                          list = pubEntity.noticeEntityList;
                          pubAdapter.setItems(pubEntity.noticeEntityList);
                      }
                  }
              }, new Action1<Throwable>() {
                  @Override
                  public void call(Throwable throwable) {
                      setIsRefresh(false);
                      LoadError(throwable);
                      showToast("数据获取错误"+throwable.getMessage()+Fragment_PubBoard.this.getClass().getName());
                      endItem = currentendItem;
                  }
              });
    }
}
