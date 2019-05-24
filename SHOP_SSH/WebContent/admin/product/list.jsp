<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/adminProduct_toaddProduct.action";
			}
		</script>
	</HEAD>
	<body>
		<br>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>商品列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="right">
							<button type="button" id="add" name="add" value="添加" class="button_add" onclick="addProduct()">
&#28155;&#21152;
</button>

						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="5%">
										序号
									</td>
									<td align="center" width="5%">
										商品名称
									</td>
									<td align="center" width="5%">
										商品市场价格
									</td>
									<td align="center" width="5%">
										商品商城价格
									</td>
									<td align="center" width="5%">
										商品图片
									</td>
									<td align="center" width="5%">
										是否热门
									</td>
									<td align="center" width="5%">
										上传日期
									</td>
									<td width="7%" align="center">
										编辑
									</td>
									<td width="7%" align="center">
										删除
									</td>
								</tr>
								<s:iterator var="p" value="pageBean.list" status="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												<s:property value="#status.count"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												<s:property value="#p.pname"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												<s:property value="#p.market_price"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												<s:property value="#p.shop_price"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												<img src="${pageContext.request.contextPath}/<s:property value="#p.image"/>" width="50" height="50" style="CURSOR: hand">
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
													<select>
														<s:if test="#p.is_hot==1"><option value="1" selected>是</option></s:if>
														<s:else><option value="0" selected>否</option></s:else>
													</select>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												<s:property value="#p.pdate"/>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/adminProduct_toEdit.action?pid=<s:property value="#p.pid"/>">
													<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
												</a>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<a href="${pageContext.request.contextPath}/adminProduct_delProduct.action?pid=<s:property value="#p.pid"/>">
													<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
												</a>
											</td>
										</tr>
									</s:iterator>	
							</table>
						</td>
					</tr>
				</TBODY>
			</table>
			<!-- 判断是一级分类的数据还是二级分类的数据 -->
					<!-- 第一页 -->
					<a class="firstPage" href="${pageContext.request.contextPath }/adminProduct_findPageProduct.action?pageNumber=1"/>首页</a>
					<!-- 判断是否为首页 -->
					<s:if test="pageBean.pageNumber!=1">
						<a class="previousPage" href="${pageContext.request.contextPath }/adminProduct_findPageProduct.action?pageNumber=<s:property value="pageBean.pageNumber-1"/>">上一页</a>
					</s:if>
					<s:else>
						<span class="previousPage"><a href="#">&nbsp;</a></span>
					</s:else>
					
						
					<!-- 判断是否为尾页 -->
					<s:if test="pageBean.pageNumber!=pageBean.totalPage">
						<a class="nextPage" href="${pageContext.request.contextPath }/adminProduct_findPageProduct.action?pageNumber=<s:property value="pageBean.pageNumber+1"/>">下一页</a>
					</s:if>
					<s:else>
						<span class="nextPage"><a href="#">&nbsp;</a></span>
					</s:else>
					<!-- 最后一页 -->
					<a class="lastPage" href="${pageContext.request.contextPath }/adminProduct_findPageProduct.action?pageNumber=<s:property value="pageBean.totalPage"/>">尾页</a>
				
		</form>
	</body>
</HTML>

