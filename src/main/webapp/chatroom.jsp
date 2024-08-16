<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javabean.ChatroomBean" %>
<%@ page import="java.util.List" %>
<%
List<ChatroomBean> chatroom = (List<ChatroomBean>)session.getAttribute("chathistory");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JavaChat</title>
</head>
<body>
<form action="send" method="post">
<input type="text" name="content">
<input type="submit" value="送信">
</form>
<% for(ChatroomBean chat : chatroom){ %>
<p><%= chat.getSender() %>さん：<%= chat.getContent() %></p><br>
<% System.out.println(chat.getContent()); %>
<% } %>
</body>
<%
if(request.getAttribute("error_msg") != null){
	String errorMsg = (String)request.getAttribute("error_msg");
%>
<script type="text/javascript">
// メッセージ送信に失敗した場合にアラートを出す
let errorMsg = <%= errorMsg %>
alert(errorMsg);
</script>
<% } %>
</html>