<%--
  Created by IntelliJ IDEA.
  User: dongjae
  Date: 2023/07/09
  Time: 4:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <select name="selection">
        <option value="one" ${selection == "one" ? "selected" : ""}>one</option>
        <option value="two" ${selection == "two" ? "selected" : ""}>two</option>
        <option value="three" ${selection == "three" ? "selected" : ""}>three</option>
    </select>
    <br>
    <input type="text" name="text" value="${text}">
    <br>
    <button type="submit">Ok</button>
</form>
</body>
</html>
