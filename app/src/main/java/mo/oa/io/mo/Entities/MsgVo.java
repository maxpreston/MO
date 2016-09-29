package mo.oa.io.mo.Entities;

import java.io.Serializable;
/**
 * 
 *
 * @project 省局综合办公系统（优化版）
 * @package com.css.ydoa.entity
 * @file News.java 创建时间:2016-5-31下午6:11:47
 * @title 标题（要求能简洁地表达出类的功能和职责）
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @copyright Copyright (c) 2016 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 * @module 模块: 模块名称
 * @author   张铭心
 * @reviewer 审核人
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
@SuppressWarnings("serial")
public class MsgVo implements Serializable{
	//消息来源
	private String fromUserId;
	//消息内容
	private String msgContent;
	//是否已读
	private String msgFlag;
	
	private String msgFrom;
	//消息id
	private String msgId;
	//消息时间
	private String msgTwoTime;
	//消息标题
	private String msgTitle;
	//消息类型
	private String msgType;
	//消息优先级
	private String priority;
	//userid
	private String userId;
	private String applyType;
	private String HeadIsExist;
	private String totalItem;
	private String xybz;
	private String msgTime;
	public MsgVo(String applyType,String fromUserId, String msgContent, String msgFlag,
			String msgFrom, String msgId, String msgTime, String msgTitle,
			String msgTwoTime, String msgType, String priority,String userId,String xybz) {
		super();
		this.applyType = applyType;
		this.fromUserId = fromUserId;
		this.msgContent = msgContent;
		this.msgFlag = msgFlag;
		this.msgFrom = msgFrom;
		this.msgId = msgId;
		this.msgTime = msgTime;
		this.msgTitle = msgTitle;
		this.msgTwoTime = msgTwoTime;
		this.msgType = msgType;
		this.priority = priority;
		this.userId = userId;
		this.xybz = xybz;
	}
	
	public String getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}

	public String getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(String totalItem) {
		this.totalItem = totalItem;
	}

	public String getXybz() {
		return xybz;
	}

	public void setXybz(String xybz) {
		this.xybz = xybz;
	}

	public String getApplyType() {
		return applyType;
	}

	public String getHeadIsExist() {
		if(null==HeadIsExist){
			HeadIsExist = "null";
		}
		return HeadIsExist;
	}

	public void setHeadIsExist(String headIsExist) {
		HeadIsExist = headIsExist;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public MsgVo() {
		super();
	}
	public String getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getMsgFlag() {
		return msgFlag;
	}
	public void setMsgFlag(String msgFlag) {
		this.msgFlag = msgFlag;
	}
	public String getMsgFrom() {
		return msgFrom;
	}
	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getMsgTwoTime() {
		return msgTwoTime;
	}
	public void setMsgTwoTime(String msgTwoTime) {
		this.msgTwoTime = msgTwoTime;
	}
	public String getMsgTitle() {
		return msgTitle;
	}
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
