<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page isErrorPage = "true" %>
<html>
<head>
  <title>Show Error Page</title>
</head>

<body>
<h1>Opps...</h1>
<p>Click <a href="/index"> here</a>
  to go back to the Homepage.</p>
<br>
<table width = "100%" border = "1">
  <tr valign = "top">
    <td width = "40%"><b>Error:</b></td>
    <td><%=pageContext.getException()%></td>
  </tr>

  <tr valign = "top">
    <td><b>URI:</b></td>
    <td><%=pageContext.getErrorData().getRequestURI()%></td>
  </tr>

  <tr valign = "top">
    <td><b>Status code:</b></td>
    <td><%=pageContext.getErrorData().getStatusCode()%></td>
  </tr>

  <tr valign = "top">
    <td><b>Stack trace:</b></td>
    <td>
      <%
        for (StackTraceElement traceElement : pageContext.getException().getStackTrace()) {
      %>
        <p><%=traceElement%></p>
      <%}%>
    </td>
  </tr>
</table>

</body>
</html>