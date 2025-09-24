<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 9/19/2025
  Time: 8:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Student</title>
</head>
<body>
<form action = "${pageContext.request.contextPath}/students/create-student" method="post">
    <h1>tên</h1>
    <input type ="text" name = "name"><br>
    <h1>điểm tổng kết</h1>
    <input type ="text" name = "final_score"><br>
    <button type = "submit"> Thêm</button>
</form>
</body>
</html>
