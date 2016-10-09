package mo.oa.io.mo.UI.message;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mo.oa.io.mo.Adapter.TYAdapter;
import mo.oa.io.mo.Adapter.TYAdapter.ClickListener;
import mo.oa.io.mo.Entities.MessageEntitys;
import mo.oa.io.mo.Model.MsgListModel;
import mo.oa.io.mo.R;
import mo.oa.io.mo.Services.AllServices;
import mo.oa.io.mo.Statics.StaticsValue;
import mo.oa.io.mo.UI.Base.CommBaseFragment;
import mo.oa.io.mo.Utils.PbUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by max-code on 2016/9/23.
 */

public class Fragment_Message extends CommBaseFragment{

    @Bind(R.id.msg_rv)
    public RecyclerView recyclerView;
    List<MessageEntitys> list;
    private TYAdapter tyAdapter = new TYAdapter();
    private int CurrentStartItem = 1;
    private int CurrentEndItem = 15;
    private String totalCount;
    private String msgType = "";
    private boolean firstTimeTouchBottom = true;
    private String userid;
    @Override
    public int addLayoutView() {
        return R.layout.fragment_msg;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(addLayoutView(),container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //刚进入界面设置为刷新状态
        userid = PbUtils.getLoginMessageUserID(mAct);
        list = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setIsRefresh(true);
            }
        },100);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mAct);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tyAdapter);
        recyclerView.addOnScrollListener(OnBottomListener(linearLayoutManager));
        recycleViewClick();
        LoadNetData(userid,String.valueOf(CurrentStartItem),String.valueOf(CurrentEndItem),msgType);
        if(clickViewToTop!=null){

        }
    }

    RecyclerView.OnScrollListener OnBottomListener(final LinearLayoutManager linearLayoutManager){

        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                boolean isBottom = linearLayoutManager.findLastCompletelyVisibleItemPosition() >= tyAdapter.getItemCount();
                ShowSnackBar(recyclerView,"isBottom-->"+isBottom);
                if(!multiRefreshLayout.isRefreshing()&&isBottom){
                    if(!firstTimeTouchBottom){
                        if(Integer.parseInt(totalCount) == tyAdapter.getItemCount()){
                            showToast("已经加载全部条目");
                            return;
                        }
                        CurrentEndItem +=15 ;
                        LoadNetData(userid,String.valueOf(CurrentStartItem),String.valueOf(CurrentEndItem),msgType);
                    }else{
                        firstTimeTouchBottom = false;
                    }
                }
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    //默认类型
    @OnClick(R.id.msg_default_btn)
    void defaultbtnClick(){
        msgType = "";
        CurrentEndItem = 15;
        LoadNetData(userid,String.valueOf(CurrentStartItem),String.valueOf(CurrentEndItem),msgType);
    }
    //日程类型
    @OnClick(R.id.msg_rc_btn)
    void RcBtntnClick(){
        msgType = StaticsValue.RCMSG;
        CurrentEndItem = 15;
        LoadNetData(userid,String.valueOf(CurrentStartItem),String.valueOf(CurrentEndItem), msgType);
    }
    //通知类型
    @OnClick(R.id.msg_notify_btn)
    void NotifyBtnClick(){
        msgType = StaticsValue.TZMSG;
        CurrentEndItem = 15;
        LoadNetData(userid,String.valueOf(CurrentStartItem),String.valueOf(CurrentEndItem),msgType);
    }
    //审批类型
    @OnClick(R.id.msg_sp_btn)
    void SpBtnClick(){
        msgType = StaticsValue.SPMSG;
        CurrentEndItem = 15;
        LoadNetData(userid,String.valueOf(CurrentStartItem),String.valueOf(CurrentEndItem),msgType);
    }

    void LoadNetData(String userid, final String startItem, final String endItem, String msgtype){
        Log.e("userid-->",userid);
        Log.e("startItem-->",startItem);
        Log.e("enditem-->",endItem);
        Log.e("msgtype-->",msgtype);
        setIsRefresh(true);
       // showSnackBar(,"正在加载...");
        sub = AllServices.
                getMsgList(mAct).
                //此处加入防止用户手抖或者用户连续短时间请求操作造成异常(Rx设计到位)
                getMsgInfo(userid,startItem,endItem,msgtype).throttleFirst(2000,TimeUnit.MICROSECONDS).retry(2).//如果请求失败，则连续请求2次
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<MsgListModel>() {
                    @Override
                    public void call(MsgListModel backinfo) {
//                        showToast("返回信息-->"+backinfo);
                        Log.e("返回了数据-->","");
                        if (backinfo.flagStr.equals("false")) {
                            showToast("为查询到信息");
                        } else if(backinfo==null){
                            showToast("未收到服务器响应");
                        }else {
//                            try {
//                                //防止用户太调皮刷新太快
//                                Thread.sleep(200);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
                            list.clear();
                            //获取总条目
                            totalCount = String.valueOf(backinfo.totalItem);
                            list = backinfo.list;
                            tyAdapter.setItems(list);
                            setIsRefresh(false);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if(throwable instanceof TimeoutException){
                            setIsRefresh(false);
                            LoadError(throwable);
                            ShowSnackBar(recyclerView,throwable.getMessage());
                            CurrentEndItem = Integer.parseInt(endItem);
                        }else{
                            setIsRefresh(false);
                            LoadError(throwable);
                            ShowSnackBar(recyclerView,"未知异常");
                            CurrentEndItem = Integer.parseInt(endItem);
                        }

                    }
                });
        AddComSub(sub);
    }

    void ShowSnackBar(View view,String msg){
        if(msg.contains("failed to connect")){
            msg = "连接错误";
        }
        Snackbar.make(recyclerView,msg,Snackbar.LENGTH_LONG).show();
    }

    void recycleViewClick(){
        tyAdapter.setClickListener(new ClickListener() {
            @Override
            public void OnItemClickListener(int position, View view) {
                ShowSnackBar(recyclerView,"删除title-->"+list.get(position).msgVo.getMsgTitle());
                list.remove(position);
                tyAdapter.setItems(list);
            }

            @Override
            public void OnItemLongClickListener(int position, View view) {
                ShowSnackBar(recyclerView,"消息time-->"+list.get(position).msgVo.getMsgTime());
            }
        });
    }

    @Override
    public void getRefrsh() {
        CurrentEndItem = 15;
        LoadNetData(userid,String.valueOf(CurrentStartItem),String.valueOf(CurrentEndItem),msgType);
    }
}
