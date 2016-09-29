package mo.oa.io.mo.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import mo.oa.io.mo.Entities.MessageEntitys;

/**
 * Created by max-code on 2016/9/26.
 */

public class MsgListModel {
    @SerializedName("flagStr")
    public String flagStr;
    @SerializedName("totalItem")
    public int totalItem;
    @SerializedName("msgListTwo")
    public List<MessageEntitys> list;

}
