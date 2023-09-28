<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <style>
        div.container {
            width: 400px;
            margin: 20px auto;
        }
        form div {
            margin-bottom: 20px;
        }
        label {
            display: block;
        }
        input, select {
            width: 200px;
            padding: 5px;
        }
        button {
            padding: 0.4em 1.5em;
        }
        div.error {
            margin-top: 20px;
            color: red;
        }
    </style>
</head>
<body>
<div class="container">
    <form method="post">
        <h1>학생정보</h1>
        <div>
            <label>학번</label>
            <input type="text" name="studentNo" value="${student.studentNo}">
        </div>
        <div>
            <label>이름</label>
            <input type="text" name="name" value="${student.name}">
        </div>
        <div>
            <label>이메일</label>
            <input type="text" name="email" value="${student.email}">
        </div>
        <div>
            <label>전공</label>
            <select name="departmentId">
                <option value="0" ${student.departmentId==0 ? "selected" : ""}>전공을 선택하세요</option>
                <option value="1" ${student.departmentId==1 ? "selected" : ""}>소프트웨어공학</option>
                <option value="2" ${student.departmentId==2 ? "selected" : ""}>컴퓨터공학</option>
                <option value="3" ${student.departmentId==3 ? "selected" : ""}>정보통신공학</option>
                <option value="4" ${student.departmentId==4 ? "selected" : ""}>인공지능전공</option>
            </select>
        </div>
        <button type="submit" class="btn">학생정보 등록</button>
    </form>

    <c:if test="${not empty errorMsg}">
        <div class="error">${errorMsg}</div>
    </c:if>
</div>
</body>
</html>