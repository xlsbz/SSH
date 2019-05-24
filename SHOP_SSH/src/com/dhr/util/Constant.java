package com.dhr.util;
/**
 * 常量
 * @author Mr DU
 *
 */
public class Constant {
	public final static Integer STATE_SUCCESS = 1;//已激活
	public final static Integer STATE_NOACTIVE = 0;//未激活
	public final static String ADDRESS = "server@mrdu.com";//商城官方邮箱
	public final static String PASSWORD = "123";//官方邮箱密码
	
	//商品
	public final static Integer IS_HOT = 1;//热门
	public final static Integer Page_Size = 12;//每页显示的数据
	
	//订单
	public final static Integer NO_Pay = 1;//未付款
	public final static Integer YES_Pay = 2;//已付款 待发货
	public final static Integer FOR_Goods = 3;//已发货 待收货
	public final static Integer SUCCESS = 4;//已完成
	
	
}
