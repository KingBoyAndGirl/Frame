<%--
  Created by IntelliJ IDEA.
  User: 王团结
  Date: 2019/8/30
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>查询所有账户</h3>

<c:forEach items="${list}" var="account">
    ${account.name}
</c:forEach>

</body>
</html>
