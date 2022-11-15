<%@ page import="com.learning.spring.models.ConnectingStudent" %>
<%@ page import="java.util.List" %>
<%@ page import="com.learning.spring.models.ConnectingTeacher" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Connecting person </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/templates/navAdmin.jsp"/>

<div class="container mt-3">
    <div class="row">
        <div class="col-sm-6">
            <div class="row">
                <div class="col-sm-7">
                    <h2>Search in the table</h2>
                    <p>Search for symbols in all columns:</p>
                    <input class="form-control" id="myInput1" type="text" placeholder="Search..">
                    <br>
                </div>
                <div class="col-sm-4">
                    <h2>Connecting teacher</h2>
                    <br>

                    <button type="button" id ="student" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#newStudent">
                        Add new connection student
                    </button>
                    <br>
                </div>
            </div>
            <h3>Connecting students to disciplines</h3>
            <p></p>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Student id (click to see full info)</th>
                    <th>Subject id (click to see full info)</th>
                </tr>
                </thead>
                <tbody id="myTable1">
                <%
                    List<ConnectingStudent> list = ((List<ConnectingStudent>) request.getAttribute("connectingS"));
                    for (ConnectingStudent connect : list) {
                %>
                <tr>
                    <th><a class="link-secondary"
                           href="/operation/student/show?id=<%=connect.getStudentId()%>"><%=connect.getStudentId()%>
                    </a>
                    </th>
                    <th><a class="link-secondary"
                           href="/operation/subject/show?id=<%=connect.getSubjectId()%>"><%=connect.getSubjectId()%>
                    </a>
                    </th>

                    <th><a class="link-secondary"
                           href="/operation/connectingPerson/S/edit?id=<%=connect.getId()%>"
                    >Edit</a>
                    </th>
                </tr>
                <%}%>
                </tbody>
            </table>

        </div>

        <div class=" col-sm-6">
            <div class="row">
                <div class="col-sm-7">
                    <h2>Search in the table</h2>
                    <p>Search for symbols in all columns:</p>
                    <input class="form-control" id="myInput2" type="text" placeholder="Search..">
                    <br>
                </div>
                <div class="col-sm-4">
                    <h2>Connecting teacher</h2>
                    <br>

                    <button type="button" id ="teacher" class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#newTeacher">
                        Add new connection teacher
                    </button>
                    <br>
                </div>
            </div>
            <h3>Connecting teacher to disciplines</h3>
            <p></p>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Teacher id (click to see full info)</th>
                    <th>Subject id (click to see full info)</th>
                </tr>
                </thead>
                <tbody id="myTable2">
                <%
                    List<ConnectingTeacher> list1 = ((List<ConnectingTeacher>) request.getAttribute("connectingT"));
                    for (ConnectingTeacher connect1 : list1) {
                %>
                <tr>
                    <th><a class="link-secondary"
                           href="/operation/teacher/show?id=<%=connect1.getTeacherId()%>"><%=connect1.getTeacherId()%>
                    </a>
                    </th>
                    <th><a class="link-secondary"
                           href="/operation/subject/show?id=<%=connect1.getSubjectId()%>"><%=connect1.getSubjectId()%>
                    </a>
                    </th>

                    <th><a class="link-secondary"
                           href="/operation/connectingPerson/T/edit?id=<%=connect1.getId()%>"
                    >Edit</a>
                    </th>
                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>


<!-- The Modal -->
<div class="modal" id="newStudent">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">New connecting student</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form method="POST" action="/operation/connectingPerson/S/add"
                      class="needs-validation" novalidate>
                    <div class="form-group">
                        <label for="studentId" class="form-label">Enter student id: </label>
                        <input type="number" name="studentId" id="studentId" class="form-control"
                               required min="0" max="1000"/>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill this field correctly.</div>
                    </div>
                    <div class="form-group">
                        <label for="subjectIdS" class="form-label">Enter subject id: </label>
                        <input type="number" name="subjectId" id="subjectIdS" class="form-control"
                               required min="0" max="1000"/>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill this field correctly.</div>
                    </div>

                    <button type="submit" class="btn btn-primary">Create</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- The Modal -->
<div class="modal" id="newTeacher">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">New connecting teacher</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form method="POST" action="/operation/connectingPerson/T/add"
                      class="needs-validation" novalidate>
                    <div class="form-group">
                        <label for="teacherId" class="form-label">Enter teacher id: </label>
                        <input type="number" name="teacherId" id="teacherId" class="form-control"
                               required min="0" max="1000"/>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill this field correctly.</div>
                    </div>
                    <div class="form-group">
                        <label for="subjectId" class="form-label">Enter subject id: </label>
                        <input type="number" name="subjectId" id="subjectId" class="form-control"
                               required min="0" max="1000"/>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill this field correctly.</div>
                    </div>

                    <button type="submit" class="btn btn-primary">Create</button>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/templates/footer.jsp"/>

<script>
    $(document).ready(function () {
        $("#myInput1").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable1 tr").filter(function () {
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
