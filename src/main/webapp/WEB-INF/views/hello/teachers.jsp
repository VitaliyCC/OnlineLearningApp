<%@ page import="java.util.List" %>
<%@ page import="com.learning.spring.models.Student" %>
<%@ page import="com.learning.spring.models.Subject" %>
<%@ page import="com.learning.spring.models.Teacher" %>
<%@ page import="com.learning.spring.models.Review" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>I`m teacher</title>
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
    Teacher teacher = ((Teacher) request.getAttribute("teacher"));
%>
<div class="container">
    <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <h2>Search in the table</h2>
                <p>Search for symbols in all columns:</p>
                <input class="form-control" id="myInput" type="text" placeholder="Search..">
                <br>
            </div>
            <div class="col-sm-3">
                <br>
                <br>
                <br>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#new">
                    My reviews
                </button>
            </div>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Subject</th>
            <th>Academic semestr</th>
            <th>Max grade</th>
            <th>View task for this subject</th>
        </tr>
        </thead>
        <tbody id="myTable">
        <%
            List<Subject> list = teacher.getSubjectList();
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
            <th><a class="link-secondary"
                   href="/operation/task?idS=<%= subject.getSubjectID()%>&idT=<%=teacher.getTeacherId()%>&idSt=-1"
            >Show tasks</a>
            </th>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
<!-- The Modal -->
<div class="modal" id="new">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">My reviews</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <h2>Search in the table</h2>
                <p>Search for symbols in all columns:</p>
                <input class="form-control" id="myInput2" type="text" placeholder="Search..">
                <br>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Teacher id</th>
                        <th>Report id</th>
                        <th>Grade</th>
                        <th>Time review</th>

                    </tr>
                    </thead>
                    <tbody id="myTable2">
                    <%
                        for (Review review : teacher.getReviewList()) {
                    %>
                    <tr>
                        <th>
                            <a class="link-secondary" href="/operation/teacher/show?id=<%=review.getTeacherId()%>"
                            ><%=review.getTeacherId()%>
                            </a>
                        </th>
                        <th><%=review.getReportId()%>
                        <th><%=review.getGrade()%>
                        <th><%=review.getTimeReview()%>
                        </th>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
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
    $(document).ready(function () {
        $("#myInput2").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable2 tr").filter(function () {
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
