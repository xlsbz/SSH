package com.dhr.domain;

/**
 * @author Mr DU 联系人实体
 * 
 *         FieldTypeComment lkm_id bigint(32) NOT NULL联系人编号(主键) lkm_name
 *         varchar(16) NULL联系人姓名 lkm_cust_id bigint(32) NOT NULL客户id lkm_gender
 *         char(1) NULL联系人性别 lkm_phone varchar(16) NULL联系人办公电话 lkm_mobile
 *         varchar(16) NULL联系人手机 lkm_email varchar(64) NULL联系人邮箱 lkm_qqvar
 *         char(16) NULL联系人qq lkm_position varchar(16) NULL联系人职位 lkm_memovar
 *         char(512) NULL联系人备注
 * 
 * 
 */
public class LinkMan {
	private Integer lkm_id;
	private String lkm_name;
	private String lkm_gender;
	private String lkm_phone;
	private String lkm_mobile;
	private String lkm_email;
	private String lkm_qq;
	private String lkm_position;
	private String lkm_memovar;

	// 配置所属的客户对象
	private Customer customer;

	public Integer getLkm_id() {
		return lkm_id;
	}

	public void setLkm_id(Integer lkm_id) {
		this.lkm_id = lkm_id;
	}

	public String getLkm_name() {
		return lkm_name;
	}

	public void setLkm_name(String lkm_name) {
		this.lkm_name = lkm_name;
	}

	public String getLkm_gender() {
		return lkm_gender;
	}

	public void setLkm_gender(String lkm_gender) {
		this.lkm_gender = lkm_gender;
	}

	public String getLkm_phone() {
		return lkm_phone;
	}

	public void setLkm_phone(String lkm_phone) {
		this.lkm_phone = lkm_phone;
	}

	public String getLkm_mobile() {
		return lkm_mobile;
	}

	public void setLkm_mobile(String lkm_mobile) {
		this.lkm_mobile = lkm_mobile;
	}

	public String getLkm_email() {
		return lkm_email;
	}

	public void setLkm_email(String lkm_email) {
		this.lkm_email = lkm_email;
	}

	public String getLkm_qq() {
		return lkm_qq;
	}

	public void setLkm_qq(String lkm_qq) {
		this.lkm_qq = lkm_qq;
	}

	public String getLkm_position() {
		return lkm_position;
	}

	public void setLkm_position(String lkm_position) {
		this.lkm_position = lkm_position;
	}

	public String getLkm_memovar() {
		return lkm_memovar;
	}

	public void setLkm_memovar(String lkm_memovar) {
		this.lkm_memovar = lkm_memovar;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "LinkMan [lkm_id=" + lkm_id + ", lkm_name=" + lkm_name + ", lkm_gender=" + lkm_gender + ", lkm_phone="
				+ lkm_phone + ", lkm_mobile=" + lkm_mobile + ", lkm_email=" + lkm_email + ", lkm_qq=" + lkm_qq
				+ ", lkm_position=" + lkm_position + ", lkm_memovar=" + lkm_memovar + ", customer=" + customer + "]";
	}

}
