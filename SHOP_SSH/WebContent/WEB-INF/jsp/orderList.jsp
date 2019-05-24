<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>订单页面</title>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/product.css"
	rel="stylesheet" type="text/css" />

</head>
<body>

	<div class="container header">
		<div class="span5">
			<div class="logo">
				<a href="#">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.png" alt="校乐淘" width="150px" height="60px"/>
				</a>
			</div>
		</div>
		<div class="span9">
			<div class="headerAd">
				<img src="${pageContext.request.contextPath}/image/header.jpg"
					width="320" height="50" alt="正品保障" title="正品保障" />
			</div>
		</div>

		<%@ include file="menu.jsp"%>

	</div>

	<div class="container cart">

		<div class="span24">

			<div class="step step1">
				<ul>

					<li class="current"></li>
					<li>我的订单</li>
				</ul>
			</div>


			<table>
				<tbody>
					<s:iterator var="order" value="pageBean.list">
						<tr>
							<th colspan="5">订单编号:<s:property value="#order.oid" />&nbsp;&nbsp;&nbsp;&nbsp;订单金额:<font
								color="red"><s:property value="#order.total" />
							</font>
							&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">
								<s:if test="#order.state == 1">
									<a href="${ pageContext.request.contextPath }/order_payMendOrder.action?oid=<s:property value="#order.oid" />">付款</a>
								</s:if>
								<s:if test="#order.state == 2">
									已付款
								</s:if>
								<s:if test="#order.state == 3">
									<a href="${ pageContext.request.contextPath }/order_sureOrder.action?oid=<s:property value="#order.oid" />">确认收货</a>
								</s:if>
								<s:if test="#order.state == 4">
									交易成功
								</s:if>
							</font>
							</th>
						</tr>
						<tr>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
						<s:iterator var="orderItem" value="#order.orderItems">
							<tr>
								<td width="60"><img
									src="${ pageContext.request.contextPath }/<s:property value="#orderItem.product.image"/>" />
								</td>
								<td><s:property value="#orderItem.product.pname" /></td>
								<td><s:property value="#orderItem.product.shop_price" /></td>
								<td class="quantity" width="60"><s:property
										value="#orderItem.count" /></td>
								<td width="140"><span class="subtotal">￥<s:property
											value="#orderItem.subtotal" />
								</span></td>
							</tr>
						</s:iterator>
					</s:iterator>
					<tr>
						<th colspan="5">
								<div class="pagination">
										<!-- 第一页 -->
										<a class="firstPage" href="${pageContext.request.contextPath }/order_myOrder?pageNumber=1"/>&nbsp;</a>
										<!-- 判断是否为首页 -->
										<s:if test="pageBean.pageNumber!=1">
											<a class="previousPage" href="${pageContext.request.contextPath }/order_myOrder?pageNumber=<s:property value="pageBean.pageNumber-1"/>">&nbsp;</a>
										</s:if>
										<s:else>
											<span class="previousPage"><a href="#">&nbsp;</a></span>
										</s:else>
										
											<!-- 循环遍历中间页 -->
												<!-- 当不足3页 -->
												<c:choose>
													<c:when test="${pageBean.totalPage<=3}">
														<c:set var="start"  value="1"></c:set>
														<c:set var="end" value="${pageBean.totalPage}"></c:set>
													</c:when>
													<c:otherwise>
														<!-- 正常显示三条 -->
														<c:set var="start" value="${pageBean.pageNumber-1}"></c:set>
														<c:set var="end" value="${pageBean.pageNumber+1}"></c:set>
														<!-- 头溢出 -->
														<c:if test="${start<1 }">
															<c:set var="start" value="1"></c:set>
															<c:set var="end" value="3"></c:set>
														</c:if>
														<!-- 尾溢出 -->
														<c:if test="${end>pageBean.totalPage }">
															<c:set var="start" value="${pageBean.totalPage-2}"></c:set>
															<c:set var="end" value="${pageBean.totalPage}"></c:set>
														</c:if>
													</c:otherwise>
												</c:choose>
											<c:forEach begin="${start}" end="${end}" var="i">
												 <c:if test="${pageBean.pageNumber!=i }">
												 	<a href="${pageContext.request.contextPath }/order_myOrder?pageNumber=${i }">${i}</a>
												 </c:if>
												 <c:if test="${pageBean.pageNumber==i }">
												 	<a href="#">${i}</a>
												 </c:if>
											</c:forEach>
										<!-- 判断是否为尾页 -->
										<s:if test="pageBean.pageNumber!=pageBean.totalPage">
											<a class="nextPage" href="${pageContext.request.contextPath }/order_myOrder?pageNumber=<s:property value="pageBean.pageNumber+1"/>">&nbsp;</a>
										</s:if>
										<s:else>
											<span class="nextPage"><a href="#">&nbsp;</a></span>
										</s:else>
										<!-- 最后一页 -->
										<a class="lastPage" href="${pageContext.request.contextPath }/order_myOrder?pageNumber=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
								</div>
						</th>
					</tr>
				</tbody>
			</table>


		</div>

	</div>
	<div class="container footer">
		<div class="span24">
			<div class="footerAd">
				<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势"
					title="我们的优势" height="52" width="950">
			</div>
		</div>
		<div class="span24">
			<ul class="bottomNav">
				<li><a href="#">关于我们</a> |</li>
				<li><a href="#">联系我们</a> |</li>
				<li><a href="#">诚聘英才</a> |</li>
				<li><a href="#">法律声明</a> |</li>
				<li><a>友情链接</a> |</li>
				<li><a target="_blank">支付方式</a> |</li>
				<li><a target="_blank">配送方式</a> |</li>
				<li><a>SHOP++官网</a> |</li>
				<li><a>SHOP++论坛</a></li>
			</ul>
		</div>
		<div class="span24">
			<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
		</div>
	</div>
</body>
</html>