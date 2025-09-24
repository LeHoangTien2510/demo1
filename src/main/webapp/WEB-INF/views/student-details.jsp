<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 9/22/2025
  Time: 8:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Chi tiết sinh viên</h2>
<p>ID: ${student.id}</p>
<p>Họ tên: ${student.name}</p>
<p>Điểm: ${student.finalScore}</p>
<p>Hạng:
    <c:choose>
        <c:when test="${student.finalScore >= 8.0}">Giỏi</c:when>
        <c:when test="${student.finalScore >= 6.5}">Khá</c:when>
        <c:when test="${student.finalScore >= 5.0}">Trung bình</c:when>
        <c:otherwise>Yếu</c:otherwise>
    </c:choose>
</p>
<a href="${pageContext.request.contextPath}/students">← Quay lại</a>
</body>
</html>
