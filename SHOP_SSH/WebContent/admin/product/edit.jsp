<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
	</HEAD>
	
	<body>
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/adminProduct_updateProduct.action" method="post" enctype="multipart/form-data">
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="6"
						height="26">
						<strong><STRONG>修改商品</STRONG>
						</strong>
					</td>
				</tr>
				<input type="hidden" value="model.pid"/>
				<tr>
					<td width="100%" align="center" bgColor="#f5fafe" class="ta_01">
						商城名称：<input type="text" name="pname" value="<s:property value="model.pname"/>">
					</td>
				</tr>
				<tr>
					<td width="100%"  align="center" bgColor="#f5fafe" class="ta_01">
						市场价格：<input type="text" name="market_price" value="<s:property value="model.market_price"/>">
					</td>
				</tr>
				<tr>
					<td width="100%"  align="center" bgColor="#f5fafe" class="ta_01">
						商城价格：<input type="text" name="shop_price" value="<s:property value="model.shop_price"/>">
					</td>
				</tr>
				<tr>
					<td width="100%"  align="center" bgColor="#f5fafe" class="ta_01">
						商品图片：<input type="file" name="upload">
					</td>
				</tr>
				<tr>
					<td width="100%"  align="center" bgColor="#f5fafe" class="ta_01">
						商品介绍：<textarea rows="5" cols="26" name="pdesc" ><s:property value="model.pdesc"/></textarea>
					</td>
				</tr>
				<tr>
					<td width="100%"  align="center" bgColor="#f5fafe" class="ta_01">
						是否热门：
						<select name="is_hot">
							<option value="1" <s:if test="#model.is_hot==1">selected</s:if>>是</option>
							<option value="0">否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="100%"  align="center" bgColor="#f5fafe" class="ta_01">
						商品所属二级分类：
						<select name="categorySecond.csid">
							<!-- 遍历二级分类 -->
							<s:iterator value="categorysceonds" var="cs">
								<option value="<s:property value="#cs.csid"/>" <s:if test="model.categorySecond.csid==#cs.csid">selected</s:if>><s:property value="#cs.csname"/></option>
							</s:iterator>
						</select>
					</td>
				</tr>
			
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
							&#30830;&#23450;
						</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>