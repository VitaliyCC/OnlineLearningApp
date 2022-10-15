<%@ page import="java.util.List" %>
<%@ page import="com.learning.spring.models.Schedule" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Schedule service</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/templates/navUser.jsp"/>

<div class="container">
    <div class="container">
        <div class="row">
            <div class="col-sm-7">
                <h2>Search in the table</h2>
                <p>Search for symbols in all columns:</p>
                <input class="form-control" id="myInput" type="text" placeholder="Search..">
                <br>
            </div>
        </div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Date</th>
                <th>Teacher</th>
                <th>Discipline</th>
                <th>Classroom</th>
                <th>Group</th>
                <th>Lecture</th>
            </tr>
            </thead>
            <tbody id="myTable">
            <%
                List<Schedule> list = ((List<Schedule>) request.getAttribute("schedule"));
                for (Schedule schedule : list) {

            %>
            <tr>
                <th><%=schedule.getTime()%>
                </th>
                <th><%=schedule.getTeacher()%>
                </th>
                <th><%=schedule.getDiscipline()%>
                </th>
                <th><%=schedule.getClassroom()%>
                </th>
                <th><%=schedule.getGroup()%>
                </th>
                <th><%=schedule.getLecture()%>
                </th>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
    <br>
    <br>
    <br>

<jsp:include page="/WEB-INF/views/templates/footer.jsp"/>
<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>
</body>
</html>
