<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JavaChat</title>
</head>
<body>
<h3>新規登録</h3>
<form action="signup" method="post">
メールアドレス<input type="text" name="userName" value="test@test.com"><br>
パスワード<input type="password" name="password" value="password"><br>
姓<input type="text" name="firstName" value="テスト">
名<input type="text" name="lastName" value="太郎"><br>
ニックネーム<input type="text" name="nickname" value="てすと">
<input type="submit" value="登録する">
</form>
</body>
</html>