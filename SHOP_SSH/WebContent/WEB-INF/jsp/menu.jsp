<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<s:if test="#session.user==null">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="${pageContext.request.contextPath }/user_toLogin.action">登录</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${pageContext.request.contextPath }/user_toRegister.action">注册</a>|
					</li>
				</s:if>
				<s:else>
					<li >
						<font color="red" size="3">欢迎您！<s:property value="#session.user.username"/></font>
					</li>
					<li>
						<a href="${pageContext.request.contextPath }/user_quit.action">[退出]</a>|
					</li>
					<li>
						<a href="${pageContext.request.contextPath }/order_myOrder.action?pageNumber=1">我的订单</a>
					</li>
				</s:else>
						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="${pageContext.request.contextPath }/cart_myCart.action">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>96008/53277764</strong>
			</div>
	</div>
	<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="${pageContext.request.contextPath }/index.action">首页</a>
						|
					</li>
					<s:iterator value="#session.category" var="cate">
						<li>
							<a href="${pageContext.request.contextPath }/product_findByCid?cid=<s:property value="#cate.cid"/>&pageNumber=1"><s:property value="#cate.cname"/></a>
							|
						</li>
					</s:iterator>
		</ul>
	</div>


</div>	