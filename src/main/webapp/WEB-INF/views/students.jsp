<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 9/19/2025
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student List</title>
</head>
<body>
<c:if test="${not empty message}">
    <div id="flash-success" style="
    padding:10px;border:1px solid #b2f5bf;border-radius:6px;
    background:#e6ffed;color:#065f46;margin:10px 0;">
    ${message}
    </div>
</c:if>
<a href = "./students/create-student">Thêm sinh viên</a>
<form action = "${pageContext.request.contextPath}/students" method="get">
    <input type ="text" name = "q"><br>
    <select name = "sort">
        <option value = "id">Id sinh viên</option>
        <option value = "name">Tên sinh viên</option>
        <option value = "final_score">Điểm tổng kết</option>
    </select>
    <select name = "dir">
        <option value = "asc">Tăng dần</option>
        <option value = "desc">Giảm dần</option>
    </select>
    <input type ="text" name = "limit">Hiển thị số sinh viên<br>
    <button type = "submit">Tìm kiếm và sắp xếp</button>
</form>
<h2>Danh sách sinh viên</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Họ tên</th>
        <th>Điểm cuối kỳ</th>
        <th>Hạng</th>
        <th>Xem chi tiết</th>
        <th>Xóa sinh viên</th>
        <th>Chỉnh sửa</th>
    </tr>
    <c:forEach var="s" items="${studentList}">
        <tr>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.finalScore}</td>
            <td>
                <c:choose>
                    <c:when test="${s.finalScore >= 8.0}">Giỏi</c:when>
                    <c:when test="${s.finalScore >= 6.5}">Khá</c:when>
                    <c:when test="${s.finalScore >= 5.0}">Trung bình</c:when>
                    <c:otherwise>Yếu</c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href = ${pageContext.request.contextPath}/students/${s.id}>Xem chi tiết</a>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/students/${s.id}/delete"
                      method="post" style="display:inline;"
                      onsubmit="return confirm('Xóa sinh viên này?');">
                    <button type="submit">Xóa</button>
                </form>
            </td>
            <td>
                <a href = "${pageContext.request.contextPath}/students/${s.id}/edit">Chỉnh sửa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
