<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE HTML >
<html>
<head>
<title>机电设备管理</title>
</head>
<body> <!-- 转发到真正的页面 -->
		<%-- 这里用<jsp:forword>  不能通过--%>
	 <c:redirect url="/pages_layout_index.action"/> 
</body>
</html>
