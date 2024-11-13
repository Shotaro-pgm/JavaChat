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
<p><%= userBean.getUserName() %>さん、こんにちは</p>
<a href="login" onclick="return confirm('ログアウトします。よろしいですか？')">ログアウト</a>
<h3>チャットを始める</h3>
<form action="viewchat" method="post">
<input type="text" name="recipient">
<input type="submit" value="始める">
</form>
<table>
<% for(ChatroomBean chatroom : chatroomList){ %>
<tr>
<th><a href="viewchat?recipient=<%= chatroom.getRecipient() %>"></a><%= chatroom.getRecipient() %></th>
</tr>
<% } %>
</table>
</body>
<%
if(request.getAttribute("error_msg") != null){
	String errorMsg = (String)request.getAttribute("error_msg");
%>
<script type="text/javascript">
// チャット履歴取得に失敗した場合にアラートを出す
let errorMsg = <%= errorMsg %>
alert(errorMsg);
</script>
<% } %>
</html>