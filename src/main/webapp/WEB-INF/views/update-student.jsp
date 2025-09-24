<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 9/24/2025
  Time: 9:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Student</title>
</head>
<body>
<form action = "${pageContext.request.contextPath}/students/${s.id}/edit" method="post">
    <input type="hidden" name="id" value="${s.id}" />

    <label>Tên sinh viên</label>
    <input name="name" value="${s.name}"/><br>

    <label>Điểm tổng kết</label>
    <input name="final_score" value="${s.finalScore}" /><br>

    <button type="submit">Lưu thay đổi</button>
    <a href="${pageContext.request.contextPath}/students">Hủy</a>
</form>
</body>
</html>
