<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>乐淘商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>

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
					<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
</div>	
</div>
	<%@ include file="menu.jsp" %>
<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
						<!-- 遍历一级二级分类 -->
					<s:iterator var="c" value="#session.category">
						<dl>
							<dt>
								<a href="${pageContext.request.contextPath }/product_findByCid.action?cid=<s:property value="#c.cid"/>&pageNumber=1"><s:property value="#c.cname"/></a>
							</dt>
								<s:iterator value="#c.categorySecond" var="cs">
									<dd>
										<a href="${pageContext.request.contextPath }/product_findBySecondCid?csid=<s:property value="#cs.csid"/>&pageNumber=1"><s:property value="#cs.csname"/></a>
									</dd>
								</s:iterator>
						</dl>
					</s:iterator>
			</div>
		</div>
		<div class="span18 last">
			<form id="productForm" action="${pageContext.request.contextPath}/image/蔬菜 - Powered By Mango Team.htm" method="get">
					
				<div id="result" class="result table clearfix">
						<ul>
							<s:iterator var="p" value="pageBean.list">
								<li>
										<a href="${ pageContext.request.contextPath }/product_info.action?pid=<s:property value="#p.pid"/>">
											<img src="${pageContext.request.contextPath}/<s:property value="#p.image"/>" width="170" height="170"  style="display: inline-block;">
											   
											<span style='color:green'>
											 <s:property value="#p.pname"/>
											</span>
											 
											<span class="price">
												商城价： ￥<s:property value="#p.shop_price"/>
											</span>
											 
										</a>
								</li>
							</s:iterator>	
								
						</ul>
				</div>
				<div class="pagination">
				<!-- 判断是一级分类的数据还是二级分类的数据 -->
				<s:if test="cid!=null">
					<!-- 第一页 -->
					<a class="firstPage" href="${pageContext.request.contextPath }/product_findByCid?cid=<s:property value="cid"/>&pageNumber=1"/>&nbsp;</a>
					<!-- 判断是否为首页 -->
					<s:if test="pageBean.pageNumber!=1">
						<a class="previousPage" href="${pageContext.request.contextPath }/product_findByCid?cid=<s:property value="cid"/>&pageNumber=<s:property value="pageBean.pageNumber-1"/>">&nbsp;</a>
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
							 	<a href="${pageContext.request.contextPath }/product_findByCid?cid=<s:property value="cid"/>&pageNumber=${i }">${i}</a>
							 </c:if>
							 <c:if test="${pageBean.pageNumber==i }">
							 	<a href="#">${i}</a>
							 </c:if>
						</c:forEach>
					<!-- 判断是否为尾页 -->
					<s:if test="pageBean.pageNumber!=pageBean.totalPage">
						<a class="nextPage" href="${pageContext.request.contextPath }/product_findByCid?cid=<s:property value="cid"/>&pageNumber=<s:property value="pageBean.pageNumber+1"/>">&nbsp;</a>
					</s:if>
					<s:else>
						<span class="nextPage"><a href="#">&nbsp;</a></span>
					</s:else>
					<!-- 最后一页 -->
					<a class="lastPage" href="${pageContext.request.contextPath }/product_findByCid?cid=<s:property value="cid"/>&pageNumber=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
				</s:if>
				
				
				<!-- 当csid不为空则是二级分类的页面数据 -->
				   <s:if test="csid!=null">
					<!-- 第一页 -->
					<a class="firstPage" href="${pageContext.request.contextPath }/product_findBySecondCid?csid=<s:property value="csid"/>&pageNumber=1"/>&nbsp;</a>
					<!-- 判断是否为首页 -->
					<s:if test="pageBean.pageNumber!=1">
						<a class="previousPage" href="${pageContext.request.contextPath }/product_findBySecondCid?csid=<s:property value="csid"/>&pageNumber=<s:property value="pageBean.pageNumber-1"/>">&nbsp;</a>
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
							 	<a href="${pageContext.request.contextPath }/product_findBySecondCid?csid=<s:property value="csid"/>&pageNumber=${i }">${i}</a>
							 </c:if>
							 <c:if test="${pageBean.pageNumber==i }">
							 	<a href="#">${i}</a>
							 </c:if>
						</c:forEach>
					<!-- 判断是否为尾页 -->
					<s:if test="pageBean.pageNumber!=pageBean.totalPage">
						<a class="nextPage" href="${pageContext.request.contextPath }/product_findBySecondCid?csid=<s:property value="csid"/>&pageNumber=<s:property value="pageBean.pageNumber+1"/>">&nbsp;</a>
					</s:if>
					<s:else>
						<span class="nextPage"><a href="#">&nbsp;</a></span>
					</s:else>
					<!-- 最后一页 -->
					<a class="lastPage" href="${pageContext.request.contextPath }/product_findBySecondCid?csid=<s:property value="csid"/>&pageNumber=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
				</s:if>
				</div>
			</form>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a >诚聘英才</a>
						|
					</li>
					<li>
						<a >法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >官网</a>
						|
					</li>
					<li>
						<a >论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright©2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>