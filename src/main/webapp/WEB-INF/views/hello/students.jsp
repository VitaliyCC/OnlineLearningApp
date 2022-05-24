<%@ page import="java.util.List" %>
<%@ page import="com.learning.spring.models.Student" %>
<%@ page import="com.learning.spring.models.Subject" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>I`m student</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/templates/navUser.jsp"/>
<%
    Student student = ((Student) request.getAttribute("student"));
%>
<div class="container">
    <div class="container">
        <div class="row">
            <div class="col-sm-7">
                <h2>Search in the table</h2>
                <p>Search for symbols in all columns:</p>
                <input class="form-control" id="myInput" type="text" placeholder="Search..">
                <br>
            </div>
            <div class="col-sm-3">
                <br>
                <br>
                <br>
                <button type="submit" class="btn btn-primary">
                    <a style="color: aliceblue" class="link-secondary" href="/operation/review?id=<%=student.getStudentId()%>"
                    >Review for my reports</a></button>
            </div>
            <div class="col-sm-3">
                <br>
                <br>
                <br>
            </div>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Subject</th>
            <th>Academic semestr</th>
            <th>Max grade</th>
            <th>Your progres</th>
            <th>View task for this subject</th>
        </tr>
        </thead>
        <tbody id="myTable">
        <%
            List<Subject> list = student.getSubjectList();
            for (Subject subject : list) {

        %>
        <tr>
            <th><a class="link-secondary" href="/operation/subject/show?id=<%= subject.getSubjectID()%>"
            ><%=subject.getSubjectName()%>
            </a>
            </th>
            <th><%=subject.getSemester()%>
            </th>
            <th><%=subject.getMaxGrade()%>
            </th>
            <th><%=subject.getProgress()%>
            </th>
            <th><a class="link-secondary"
                   href="/operation/task?idS=<%= subject.getSubjectID()%>&idSt=<%= student.getStudentId()%>&idT=-1"
            >Show tasks</a>
            </th>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>

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

<script>
    // Disable form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Get the forms we want to add validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
</body>
</html>
