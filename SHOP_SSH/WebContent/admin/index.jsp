<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网上商城管理中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/general.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" />

<style type="text/css">
body {
  color: white;
}
</style>

<body style="background: #278296">
<form method="post" action="${pageContext.request.contextPath }/admin_login.action" target="_parent" name='theForm' >
  <table cellspacing="0" cellpadding="0" style="margin-top: 100px" align="center">
  <tr>
    <!-- <td><img src="images/login.png" width="178" height="256" border="0" alt="SHOP" /></td> -->
    <td style="padding-left: 50px">
      <table>
      <s:actionmessage />
      <tr>
        <td>管理员姓名：</td>
        <td><input type="text" name="username" /></td>
      </tr>
      <tr>
        <td>管理员密码：</td>
        <td><input type="password" name="password" /></td>
      </tr>
            
      <tr>
        <td colspan="2" align="right"><input type="submit" value="登录"/> <a href="../" style="color:white">返回首页</a> &raquo; <a href="get_password.php?act=forget_pwd" style="color:white">您忘记了密码吗?</a></td>
      </tr>
      <tr><s:actionerror /></tr>
      </table>
    </td>
  </tr>
  </table>
  <input type="hidden" name="act" value="signin" />
</form>

</body>