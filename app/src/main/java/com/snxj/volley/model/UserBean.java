package com.snxj.volley.model;

import java.util.Date;

/**
 * @author Sheng XiaoJie .
 * @Date 2016/12/2
 * @describe *
 */
public class UserBean {
	private int id;
	private String name;
	private String password;
	private String sex;// 性别
	private String workcity;
	private String workunit;
	private String address;
	private String phone;
	private Date brithday;
	private String email;
	private String position;// 用户职位
	private String workexperience;// 工作经验
	private String cardphotopath;// 证件照片
	private String billBuy;// 用户购买的单子
	private String billRelease;// 用户发布的单子
	private String billCollection;// 收藏的单子
	private int integral;// 积分
	private double accountbalance;// 账户余额
	private int registerstatus;// 用户注册状态
	private int status;// 用户状态
	private int loginCount;// 连续登陆次数
	private int refereeid;// 邀请人ID
	private int inviteRewardStatus;// 邀请积分状态
	private int loginerror;// 连续登陆记录
	private int version;// 版本
	private String avatarpath; // 用户头像
	private String onWayMoney;// 预到金额
	private String cardnum;// 身份证号
	private String reviewerID;//审核人
	private int occupation;//职业
	
	public String getReviewerID() {
		return reviewerID;
	}

	public void setReviewerID(String reviewerID) {
		this.reviewerID = reviewerID;
	}

	public int getOccupation() {
		return occupation;
	}

	public void setOccupation(int occupation) {
		this.occupation = occupation;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getOnWayMoney() {
		return onWayMoney;
	}

	public void setOnWayMoney(String onWayMoney) {
		this.onWayMoney = onWayMoney;
	}

	public String getAvatarpath() {
		return avatarpath;
	}

	public void setAvatarpath(String avatarpath) {
		this.avatarpath = avatarpath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkcity() {
		return workcity;
	}

	public void setWorkcity(String workcity) {
		this.workcity = workcity;
	}

	public String getWorkunit() {
		return workunit;
	}

	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBrithday() {
		return brithday;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getWorkexperience() {
		return workexperience;
	}

	public void setWorkexperience(String workexperience) {
		this.workexperience = workexperience;
	}

	public String getCardphotopath() {
		return cardphotopath;
	}

	public void setCardphotopath(String cardphotopath) {
		this.cardphotopath = cardphotopath;
	}

	public String getBillBuy() {
		return billBuy;
	}

	public void setBillBuy(String billBuy) {
		this.billBuy = billBuy;
	}

	public String getBillRelease() {
		return billRelease;
	}

	public void setBillRelease(String billRelease) {
		this.billRelease = billRelease;
	}

	public String getBillCollection() {
		return billCollection;
	}

	public void setBillCollection(String billCollection) {
		this.billCollection = billCollection;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public double getAccountbalance() {
		return accountbalance;
	}

	public void setAccountbalance(double accountbalance) {
		this.accountbalance = accountbalance;
	}

	public int getRegisterstatus() {
		return registerstatus;
	}

	public void setRegisterstatus(int registerstatus) {
		this.registerstatus = registerstatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getRefereeid() {
		return refereeid;
	}

	public void setRefereeid(int refereeid) {
		this.refereeid = refereeid;
	}

	public int getInviteRewardStatus() {
		return inviteRewardStatus;
	}

	public void setInviteRewardStatus(int inviteRewardStatus) {
		this.inviteRewardStatus = inviteRewardStatus;
	}

	public int getLoginerror() {
		return loginerror;
	}

	public void setLoginerror(int loginerror) {
		this.loginerror = loginerror;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
