<%--
  Created by IntelliJ IDEA.
  User: guwen
  Date: 15-10-31
  Time: 下午3:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>欢迎</title>
</head>
<body>
<h2>Hello World!</h2>

<form action="login">
  用户名：<input id="username" name="username" type="text"/><br>
  密  码：<input id="password" name="password" type="password"/><br>
  <input type="submit">
</form>
<span>当前IP：<%=request.getRemoteAddr() %></span>
</body>
</html>
