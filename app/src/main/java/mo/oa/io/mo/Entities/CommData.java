package mo.oa.io.mo.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by max-code on 2016/9/19.
 */
public class CommData implements Serializable {

    @SerializedName("flagStr")
    public String flagStr;
    public String code;
    @SerializedName("userInfo")
    public String CommData;


}
