<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import javabean.UserBean %>
<!DOCTYPE html>
<%
UserBean userBean = (UserBean)session.getAttribute("userBean");
%>
<html>
<head>
<meta charset="UTF-8">
<title>JavaChat</title>
</head>
<body>
<p>ボタンを押してチャットを始める</p>
<form action="login" method="post">
<input type="hidden" name="userName" value="<%= userBean.getUserName() %>">
<input type="hidden" name="userName" value="<%= userBean.getPassword() %>">
<input type="submit" value="はじめる">
</form>
</body>
</html>