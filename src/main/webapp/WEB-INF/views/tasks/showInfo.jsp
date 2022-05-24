<%@ page import="java.util.List" %>
<%@ page import="com.learning.spring.models.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Task information</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>

<jsp:include page="/WEB-INF/views/templates/navUser.jsp"/>

<div class="container">
    <div class="row">
        <%Task task = ((Task) request.getAttribute("task"));%>
        <div class="col-sm-4">
            <h3>Task information</h3>
            <p>Task name - <%=task.getTaskName()%>
            </p></p>
            <p>Task - <%=task.getSubject()%>
            </p></p>
            <p>Max grade - <%=task.getMaxGrade()%>
            </p></p>
        </div>
        <div class="col-sm-8">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Student id</th>
                    <th>Solutions</th>
                    <th>Send time</th>
                    <th>Checked</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Report report : task.getReportList()) {
                %>
                <tr>
                    <th><a class="link-secondary"
                           href="/operation/student/show?id=<%=report.getStudentId()%>">
                        <%=report.getStudentId()%>
                    </a></th>
                    <th><%=report.getSolutions()%></th>
                    <th><%=report.getSendTime()%></th>
                    <th><%=report.getChecked()%></th>
                    <th><a class="link-secondary"
                           href="/operation/review/show?id=<%=report.getReportId()%>&idT=<%=(Integer) request.getAttribute("idT")%>">
                        Create review
                    </a></th>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/views/templates/footer.jsp"/>
</body>
</html>


