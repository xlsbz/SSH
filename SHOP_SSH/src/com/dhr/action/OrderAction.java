package com.dhr.action;

import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.dhr.domain.Order;
import com.dhr.domain.OrderItem;
import com.dhr.domain.User;
import com.dhr.service.IOrderService;
import com.dhr.util.Cart;
import com.dhr.util.CartItem;
import com.dhr.util.Constant;
import com.dhr.util.PageBean;
import com.dhr.util.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单项action
 * @author Mr DU
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	private static final long serialVersionUID = 1L;
	//注入模型驱动
	private Order order = new Order();
	@Override
	public Order getModel() {
		return order;
	}
	
	//注入service
	private IOrderService orderService;
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	//接收页数
	private int pageNumber;
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * 将订单写到数据库里
	 * @return
	 */
	public String saveOrder() {
		//封装order数据
		order.setOrdertime(new Date());
		order.setState(Constant.NO_Pay);
		//获取购物车的数据和登录的用户
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user==null) {
			this.addActionError("您还没有登录，请登录后在购物！");
			return "msg";
		}
		if(cart==null) {
			this.addActionError("您还没有购物，请购物后在下单！");
			return "msg";
		}
		order.setUser(user);
		order.setTotal(cart.getTotal());
		//遍历购物项
		OrderItem orderItem = null;
		for (CartItem ci : cart.getCartItems()) {
			orderItem = new OrderItem();
			orderItem.setCount(ci.getCount());
			orderItem.setProduct(ci.getProduct());
			orderItem.setSubtotal(ci.getSubTotal());
			orderItem.setOrder(order);
			//把每个订单项添加到订单里
			order.getOrderItems().add(orderItem);
		}
		//调用service方法
		orderService.saveOrder(order);
		//添加到订单后清除购物车的数据
		cart.clearCart();
		return "orderInfo";
	}
	
	/**
	 * 分页查看我的订单
	 * @return
	 */
	public String myOrder() {
		//1.获取用户ID
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		//2.调用service
		PageBean<Order> pageBean = orderService.findByPageOrder(user,pageNumber);
		//3.保存到值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "orderList";
	}
	
	/**
	 * 去付款
	 * @return
	 */
	public String payMendOrder() {
		order = orderService.payMendOrder(order.getOid());
		return "orderInfo";
	}
	
	/**
	 * 确认收货
	 * @return
	 */
	public String sureOrder() {
		order = orderService.payMendOrder(order.getOid());
		order.setState(4);
		orderService.updateOrder(order);
		return "sureOrder";
	}
	/**
	 * 支付功能
	 * @param req
	 * @param res
	 * @return
	 */
	public String payOrder(){
		Order o = orderService.payMendOrder(order.getOid());
		o.setAddr(order.getAddr());
		o.setName(order.getName());
		o.setOid(order.getOid());
		o.setPhone(order.getPhone());
		orderService.updateOrder(o);
		
		// 组织发送支付公司需要哪些数据
		String pd_FrpId = ServletActionContext.getRequest().getParameter("pd_FrpId");
		String p0_Cmd = "Buy";
		String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
		String p2_Order = order.getOid().toString();
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		// 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
		// 第三方支付可以访问网址
		String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("responseURL");
		String p9_SAF = "";
		String pa_MP = "";
		String pr_NeedResponse = "1";
		// 加密hmac 需要密钥
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);
	
		//发送给第三方
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		try {
			ServletActionContext.getResponse().sendRedirect((sb.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NONE;
	}
	/**
	 * 回调信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String callback() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_Trxtime = request.getParameter("ru_Trxtime");
		// 身份校验 --- 判断是不是支付公司通知你
		String hmac = request.getParameter("hmac");
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
				"keyValue");

		// 自己对上面数据进行加密 --- 比较支付公司发过来hamc
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if (isValid) {
			// 响应数据有效
			if (r9_BType.equals("1")) {
				// 浏览器重定向
				this.addActionMessage("您的订单号为:"+r6_Order+",金额为:"+r3_Amt+"已经支付成功,等待发货~~");
				
			} else if (r9_BType.equals("2")) {
				// 服务器点对点 --- 支付公司通知你
				// 修改订单状态 为已付款
				// 回复支付公司
				response.getWriter().print("success");
			}
			
			//修改订单状态
			Order o2 = orderService.payMendOrder(Integer.parseInt(r6_Order));
			o2.setState(2);
			orderService.updateOrder(o2);
		} else {
			// 数据无效
			System.out.println("数据被篡改！");
		}
		return "pay";
	}
}
