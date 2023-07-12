<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <div class="container">
        <h1>로그인 성공</h1>

        <table>
            <tr>
                <td>사용자 아이디</td>
                <td>${ userid }</td>
            </tr>
            <tr>
                <td>자동 로그인</td>
                <td>${ autologin }</td>
            </tr>
        </table>
    </div>
</body>
</html>