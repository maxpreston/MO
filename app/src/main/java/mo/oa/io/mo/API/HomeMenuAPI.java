package mo.oa.io.mo.API;

import java.util.List;

import mo.oa.io.mo.Entities.MessageEntitys;
import mo.oa.io.mo.Model.MsgListModel;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by max-code on 2016/9/25.
 */

public interface HomeMenuAPI {
    //获取消息列表
    @POST("callService.do?URL=/mobile/service/noticeApply.do?reqCode=getMsgList")
    Observable<MsgListModel> getMsgInfo(@Query("userid") String userid, @Query("startItem") String startItem, @Query("endItem") String endItem, @Query("msgtype") String msgtype);

    @POST("callService.do?URL=/mobile/service/validateUser.do?reqCode=getUserPortraitByUseri")
    Observable getMsgUserIcon(@Query("userid") String userid);
}
