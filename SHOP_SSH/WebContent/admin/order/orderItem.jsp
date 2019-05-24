<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table border="1" width="100%">
	<tr>
		<td>商品名称</td>
		<td>商品数量</td>
		<td>商品图片</td>
		<td>商品小计</td>
	</tr>
	<s:iterator value="orderItems" var="oi">
		<tr>
			<td><s:property value="#oi.product.pname"/></td>
			<td><s:property value="#oi.count"/></td>
			<td><img alt="" width="40" height="45" src="${pageContext.request.contextPath }/<s:property value="#oi.product.image"/>"></td>
			<td><s:property value="#oi.subtotal"/></td>
		</tr>
	</s:iterator>
</table>