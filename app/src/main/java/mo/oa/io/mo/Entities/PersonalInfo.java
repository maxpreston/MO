package mo.oa.io.mo.Entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 *
 * @project 省局综合办公系统移动版（优化版）
 * @package com.css.ydoa.entity
 * @file PersonalInfo.java 创建时间:2016-5-9下午3:43:39
 * @title 标题（要求能简洁地表达出类的功能和职责）
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @copyright Copyright (c) 2016 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 * @module 模块: json解析需要使用的实体类
 * @author   张铭心
 * @reviewer 审核人
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
@SuppressWarnings("serial")
public class PersonalInfo implements Serializable {
	//用户id
	private String userid;
	//税务机关代码
	private String swjgdm;
	//用户账户
	private String useraccount;
	//用户姓名
	private String username;
	//用户状态
	private int status;
	//锁定状态
	private String locked;
	//最后一次登录时间
	private Date lastlogin = new Date();
	//用户类型
	private int usertype;
	//用户手机电话
	private String mobilephone;
	//用户邮箱
	private String email;
	//用户选用标志位
	private int xybz;
	//临时转换变量
	private String lastlogindate;
	//用户设备是否改变
	private String DeviceChanged;
	//验证码
	private String checkCode;
	/**个人详情属性**/
	//办公室
	private String officeroom;
	//办公电话
	private String officephone;
	//联系人姓名
	private String relatename;
	//联系人电话
	private String relatephone;
	//兴趣爱好
	private String favortag;
	//地址
	private String address;
	//头像
	private filevo filevo;
	//actorid
	private String actorid;
	//orgalias
	private String orgalias;
	//公文系统userid
	private String gwuserid;
	
	public String getGwuserid() {
		return gwuserid;
	}


	public void setGwuserid(String gwuserid) {
		this.gwuserid = gwuserid;
	}


	/***************************************/
	public PersonalInfo() {
		super();
	}
	
	
	public PersonalInfo(String bgs,String officephone, String relatename, String relatephone,
			String favortag, String address,filevo filevo) {
		super();
		this.officeroom = bgs;
		this.officephone = officephone;
		this.relatename = relatename;
		this.relatephone = relatephone;
		this.favortag = favortag;
		this.address = address;
		this.filevo = filevo;
	}
	
	

	public PersonalInfo(String userid, String swjgdm, String username,
			String actorid) {
		super();
		this.userid = userid;
		this.swjgdm = swjgdm;
		this.username = username;
		this.actorid = actorid;
	}


	public PersonalInfo(String userid, String swjgdm, String useraccount,
			 String username, int status, String locked,
			String lastlogindate, int usertype, String mobilephone, String email,
			int xybz,String actorid,String orgalias) {
		super();
		this.userid = userid;
		this.swjgdm = swjgdm;
		this.useraccount = useraccount;
		this.username = username;
		this.status = status;
		this.locked = locked;
		this.lastlogindate = lastlogindate;
		this.usertype = usertype;
		this.mobilephone = mobilephone;
		this.email = email;
		this.xybz = xybz;
		this.actorid = actorid;
		this.orgalias = orgalias;
	}
	
	public PersonalInfo(String userid, String swjgdm, String useraccount,
			String username, int status, String locked, String lastlogindate,
			int usertype, String mobilephone, String email, int xybz) {
		super();
		this.userid = userid;
		this.swjgdm = swjgdm;
		this.useraccount = useraccount;
		this.username = username;
		this.status = status;
		this.locked = locked;
		this.lastlogindate = lastlogindate;
		this.usertype = usertype;
		this.mobilephone = mobilephone;
		this.email = email;
		this.xybz = xybz;
	}


	public PersonalInfo(String userid, String swjgdm) {
		super();
		this.userid = userid;
		this.swjgdm = swjgdm;
	}
	public PersonalInfo(String userid) {
		super();
		this.userid = userid;
	}
	public PersonalInfo(String userid, String swjgdm, String username) {
		super();
		this.userid = userid;
		this.swjgdm = swjgdm;
		this.username = username;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSwjgdm() {
		return swjgdm;
	}
	public void setSwjgdm(String swjgdm) {
		this.swjgdm = swjgdm;
	}
	public String getUseraccount() {
		return useraccount;
	}
	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	public Date getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getXybz() {
		return xybz;
	}
	public void setXybz(int xybz) {
		this.xybz = xybz;
	}
	
	public String getLastlogindate() {
		if(lastlogin!=null){
			lastlogindate = new SimpleDateFormat("yyyy-MM-dd").format(lastlogin);
		}
		return lastlogindate;
	}
	//转换日期
	public void setLastlogindate(String lastlogindate) {
		if(lastlogindate!=null){
			try {
				lastlogin = new SimpleDateFormat("yyyy-MM-dd").parse(lastlogindate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.lastlogindate = lastlogindate;
	}


	public String getDeviceChanged() {
		return DeviceChanged;
	}


	public void setDeviceChanged(String deviceChanged) {
		DeviceChanged = deviceChanged;
	}


	public String getCheckCode() {
		return checkCode;
	}


	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}


	public String getOfficeroom() {
		return officeroom;
	}


	public void setOfficeroom(String officeroom) {
		this.officeroom = officeroom;
	}


	public String getOfficephone() {
		return officephone;
	}


	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}


	public String getRelatename() {
		return relatename;
	}


	public void setRelatename(String relatename) {
		this.relatename = relatename;
	}


	public String getRelatephone() {
		return relatephone;
	}


	public void setRelatephone(String relatephone) {
		this.relatephone = relatephone;
	}


	public String getFavortag() {
		return favortag;
	}


	public void setFavortag(String favortag) {
		this.favortag = favortag;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	public String getActorid() {
		return actorid;
	}


	public void setActorid(String actorid) {
		this.actorid = actorid;
	}


	public String getOrgalias() {
		return orgalias;
	}


	public void setOrgalias(String orgalias) {
		this.orgalias = orgalias;
	}


	public filevo getFilevo() {
		return filevo;
	}


	public void setFilevo(filevo filevo) {
		this.filevo = filevo;
	}
	
	
}
