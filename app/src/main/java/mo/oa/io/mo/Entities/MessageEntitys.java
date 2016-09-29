package mo.oa.io.mo.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@SuppressWarnings("serial")

public class MessageEntitys {
	@SerializedName("fileid")
	public String fileid;
	@SerializedName("filename")
	public String filename;
	@SerializedName("filesize")
	public String filesize;
	@SerializedName("msgVo")
	public MsgVo msgVo;
}
