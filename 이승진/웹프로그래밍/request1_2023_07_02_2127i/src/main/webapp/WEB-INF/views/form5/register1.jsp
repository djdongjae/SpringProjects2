<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register1</title>
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
            <h1>회원가입</h1>
            <div>
                <label>사용자 아이디</label>
                <input type="text" name="userId" value="${userId}">
            </div>
            <div>
                <label>이름</label>
                <input type="text" name="name" value="${name}">
            </div>
            <div>
                <label>비밀번호1</label>
                <input type="password1" name="password1">
            </div>
            <div>
                <label>비밀번호2</label>
                <input type="password2" name="password2">
            </div>
            <div>
                <label>이메일</label>
                <input type="email" name="email" value="${email}">
            </div>
            <div>
                <label>전공</label>
                <select name="departmentId">
                    <option value="1" ${departmentId==1 ? "selected" : ""}>소프트웨어공학</option>
                    <option value="2" ${departmentId==2 ? "selected" : ""}>컴퓨터공학</option>
                    <option value="3" ${departmentId==3 ? "selected" : ""}>정보통신공학</option>
                    <option value="4" ${departmentId==4 ? "selected" : ""}>인공지능전공</option>
                </select>
            </div>
            <button type="submit" class="btn">회원가입</button>
        </form>

        <c:if test="${not empty errorMsg}">
            <div class="error">${errorMsg}</div>
        </c:if>
    </div>
</body>
</html>