<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Quản lý Đánh giá Cầu thủ</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f9f9f9; }
        h2, h3 { color: #333; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background: #fff; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #007bff; color: white; }
        form { background: #fff; padding: 20px; border: 1px solid #ddd; margin-top: 20px; width: 400px; }
        form input, form select { width: 100%; padding: 8px; margin: 5px 0 15px 0; display: inline-block; border: 1px solid #ccc; box-sizing: border-box; }
        form button { background-color: #28a745; color: white; padding: 10px 15px; border: none; cursor: pointer; width: 100%; }
        form button:hover { background-color: #218838; }
        .btn-delete { color: red; text-decoration: none; margin-left: 10px; }
        .btn-edit { color: blue; text-decoration: none; }
        .status-pass { color: green; font-weight: bold; }
    </style>
</head>
<body>

<h2>Danh sách Cầu thủ</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Tên ngắn</th>
        <th>Họ và Tên</th>
        <th>Tuổi</th>
        <th>Index ID</th>
        <th>Đánh giá</th>
        <th>Thao tác</th>
    </tr>
    <c:forEach var="p" items="${playerList}">
        <tr>
            <td>${p.playerId}</td>
            <td>${p.name}</td>
            <td>${p.fullName}</td>
            <td>${p.age}</td>
            <td>${p.indexId}</td>
            <td>
                <span class="status-pass">Đạt chuẩn</span>
            </td>
            <td>
                <a href="players?action=edit&id=${p.playerId}" class="btn-edit">Sửa</a>
                <a href="players?action=delete&id=${p.playerId}" class="btn-delete" onclick="return confirm('Bạn có chắc chắn muốn xóa cầu thủ này không?');">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>

<form action="players" method="POST">
    <c:if test="${isEdit}">
        <h3>Cập nhật Cầu thủ</h3>
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="playerId" value="${player.playerId}">

        <label>Tên ngắn (Name):</label>
        <input type="text" name="name" value="${player.name}" required>

        <label>Họ và Tên (Full Name):</label>
        <input type="text" name="fullName" value="${player.fullName}" required>

        <label>Tuổi (Age):</label>
        <input type="text" name="age" value="${player.age}" required>

        <label>Index ID (1: speed, 2: strength, 3: accurate):</label>
        <input type="number" name="indexId" value="${player.indexId}" required>

        <button type="submit" style="background-color: #ffc107; color: black;">Lưu thay đổi</button>
        <a href="players" style="display:block; text-align:center; margin-top:10px;">Hủy</a>
    </c:if>

    <c:if test="${!isEdit}">
        <h3>Thêm Cầu thủ mới</h3>
        <input type="hidden" name="action" value="add">

        <label>Tên ngắn (Name):</label>
        <input type="text" name="name" required>

        <label>Họ và Tên (Full Name):</label>
        <input type="text" name="fullName" required>

        <label>Tuổi (Age):</label>
        <input type="text" name="age" required>

        <label>Index ID (1: speed, 2: strength, 3: accurate):</label>
        <input type="number" name="indexId" required>

        <button type="submit">Thêm cầu thủ</button>
    </c:if>
</form>

</body>
</html>