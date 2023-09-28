<%--
  Created by IntelliJ IDEA.
  User: dongjae
  Date: 2023/07/09
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <style>
    div.container {
      width: 400px;
      margin: 20px auto;
    }
    form div {
      margin-bottom: 10px;
    }
    label {
      display: inline-block;
      width: 70px;
      text-align: right;
    }
    input {
      width: 150px;
      padding: 4px;
    }
    select {
      width: 50px;
      padding: 4px
    }
    button {
      padding: 0.4em 2em;
      margin-top: 10px;
    }
  </style>
</head>
<body>
  <div class="container">
    <form method="post">
      <h1>select 02</h1>
      <div>
        <label>number1:</label>
        <input type="text" name="number1" value="${number1}">
      </div>

      <div>
        <label>operator</label>
        <select name="cmd">
          <option value="+" ${cmd == "+" ? "selected" : ""}>+</option>
          <option value="+" ${cmd == "-" ? "selected" : ""}>-</option>
          <option value="+" ${cmd == "*" ? "selected" : ""}>*</option>
          <option value="+" ${cmd == "/" ? "selected" : ""}>/</option>
        </select>
      </div>

      <div>
        <label>number2:</label>
        <input type="text" name="number2" value="${number2}">
      </div>

      <div>
        <button type="submit">확인</button>
        <button type="reset">취소</button>
      </div>
    </form>
    <p>
      결과 : ${result}
    </p>
  </div>
</body>
</html>
