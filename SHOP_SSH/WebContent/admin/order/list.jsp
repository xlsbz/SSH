<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
	</HEAD>
	<script type="text/javascript">
			//创建ajax
			function orderAjax(oid){
				//获取按钮
				var btn = document.getElementById("but"+oid).value;
				//获取div
				var content = document.getElementById("div"+oid);
				if(btn=="点击查看"){
					document.getElementById("but"+oid).value  = "关闭详情";
					// 1.创建异步交互对象
					var xhr = createXmlHttp();
					// 2.设置监听
					xhr.onreadystatechange = function(){
						if(xhr.readyState == 4){
							if(xhr.status == 200){
								content.innerHTML = xhr.responseText;
							}
						}
					}
					// 3.打开连接
					xhr.open("GET","${pageContext.request.contextPath}/adminOrder_findOrderItems.action?time="+new Date().getTime()+"&oid="+oid,true);
					// 4.发送
					xhr.send(null);
				}else{
					content.innerHTML = "";
					document.getElementById("but"+oid).value="点击查看";
				}
				
			}
			
			function createXmlHttp(){
				   var xmlHttp;
				   try{ // Firefox, Opera 8.0+, Safari
				        xmlHttp=new XMLHttpRequest();
				    }
				    catch (e){
					   try{// Internet Explorer
					         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
					      }
					    catch (e){
					      try{
					         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
					      }
					      catch (e){}
					      }
				    }
					return xmlHttp;
				 }
		</script>
	<body>
		<br>
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>订单列表</strong>
						</TD>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="3%">
										序号
									</td>
									<td align="center" width="5%">
										收货人
									</td>
									<td align="center" width="5%">
										收货地址
									</td>
									<td align="center" width="5%">
										联系人
									</td>
									<td align="center" width="5%">
										下单时间
									</td>
									<td align="center" width="5%">
										订单金额
									</td>
									<td width="5%" align="center">
										交易状态
									</td>
									<td width="15%" align="center">
										查看订单详情
									</td>
								</tr>
								<s:iterator var="o" value="pageBean.list" status="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												<s:property value="#status.count"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												<s:property value="#o.name"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												<s:property value="#o.addr"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
												<s:property value="#o.user.username"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="5%">
													<s:property value="#o.ordertime"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="6%">
												<s:property value="#o.total"/>
											</td>
											<td align="center" style="HEIGHT: 22px">
												<s:if test="#o.state==1">未付款</s:if>
												<s:if test="#o.state==2">
													<a href="${pageContext.request.contextPath }/adminOrder_goGoods.action?oid=<s:property value="#o.oid"/>">去发货</a>
												</s:if>
												<s:if test="#o.state==3">待收货</s:if>
												<s:if test="#o.state==4">完成交易</s:if>
											</td>
									
											<td align="center" style="HEIGHT: 22px">
												<input type="button" id="but<s:property value="#o.oid"/>" onclick="orderAjax(<s:property value='#o.oid'/>)" value="点击查看">
												<div id="div<s:property value="#o.oid"/>"></div>
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
					<a class="firstPage" href="${pageContext.request.contextPath }/adminOrder_findAllOrder.action?pageNumber=1"/>首页</a>
					<!-- 判断是否为首页 -->
					<s:if test="pageBean.pageNumber!=1">
						<a class="previousPage" href="${pageContext.request.contextPath }/adminOrder_findAllOrder.action?pageNumber=<s:property value="pageBean.pageNumber-1"/>">上一页</a>
					</s:if>
					<s:else>
						<span class="previousPage"><a href="#">&nbsp;</a></span>
					</s:else>
					
						
					<!-- 判断是否为尾页 -->
					<s:if test="pageBean.pageNumber!=pageBean.totalPage">
						<a class="nextPage" href="${pageContext.request.contextPath }/adminOrder_findAllOrder.action?pageNumber=<s:property value="pageBean.pageNumber+1"/>">下一页</a>
					</s:if>
					<s:else>
						<span class="nextPage"><a href="#">&nbsp;</a></span>
					</s:else>
					<!-- 最后一页 -->
					<a class="lastPage" href="${pageContext.request.contextPath }/adminOrder_findAllOrder.action?pageNumber=<s:property value="pageBean.totalPage"/>">尾页</a>
				
	</body>
</HTML>

