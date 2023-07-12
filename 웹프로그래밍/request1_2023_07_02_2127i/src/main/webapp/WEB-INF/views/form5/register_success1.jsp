<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register_Success1</title>
    <style>
        div.container {
            width: 310px;
            margin: 20px auto;
        }
        table {
            width: 300px;
            border-collapse: collapse;
        }
        table tr {
            background-color: #eee;
        }
        td {
            border: 1px solid #aaa;
            padding: 5px;
        }
        td:nth-child(2) {
            background-color: #eee;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>회원가입 성공</h1>

        <table>
            <tr>
                <td>사용자 아이디</td>
                <td>${userid}</td>
            </tr>
            <tr>
                <td>이름</td>
                <td>${name}</td>
            </tr>
            <tr>
                <td>이메일</td>
                <td>${email}</td>
            </tr>
        </table>
    </div>
</body>
</html>