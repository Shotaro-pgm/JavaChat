<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="javabean.ChatroomBean" %>
<%@ page import="javabean.UserBean" %>
<%
UserBean userBean = (UserBean)session.getAttribute("userBean");
List<ChatroomBean> chatroomList = (List<ChatroomBean>)session.getAttribute("chatroomList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JavaChat</title>
</head>
<body>
<h1>JavaChat</h1>
<table>
<% for(ChatroomBean chatroom : chatroomList){ %>
<tr>
<th><%= chatroom.getRecipient() %></th>
</tr>
<% } %>
</table>
</body>
</html>