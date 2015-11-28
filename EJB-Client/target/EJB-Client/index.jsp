<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ page isELIgnored="false" %>  不再需要--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
<%
//${strFromEJB} 无效
String strFromEJB = (String)request.getSession().getAttribute("strFromEJB");
%>

EJB调用的返回值:<%=strFromEJB%>  <br>
EL:${strFromEJB}
</body>
</html>