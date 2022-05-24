<%@ page import="java.util.List" %>
<%@ page import="com.learning.spring.models.Student" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Students</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/templates/navAdmin.jsp"/>

<div class="container">
    <div class="container">
        <div class="row">
            <div class="col-sm-7">
                <h2>Search in the table</h2>
                <p>Search for symbols in all columns:</p>
                <input class="form-control" id="myInput" type="text" placeholder="Search..">
                <br>
            </div>
            <div class="col-sm-4">
                <br>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#new">
                    Add new student
                </button>
                <br>
            </div>
        </div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Student id (click to see full info)</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Patronymic</th>
                <th>Click to edit</th>
            </tr>
            </thead>
            <tbody id="myTable">
            <%
                List<Student> list = ((List<Student>) request.getAttribute("students"));
                for (Student student : list) {

            %>
            <tr>
                <th><a class="link-secondary" href="/operation/student/show?id=<%= student.getStudentId()%>"
                ><%=student.getStudentId()%>
                </a>
                </th>
                <th><%=student.getName()%>
                </th>
                <th><%=student.getSurname()%>
                </th>
                <th><%=student.getPatronymic()%>
                </th>
                <th><a class="link-secondary" href="/operation/student/edit?id=<%= student.getStudentId()%>"
                >Edit</a>
                </th>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
    <br>
    <br>
    <br>


    <!-- The Modal -->
    <div class="modal" id="new">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">New Student</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form method="POST" action="/operation/student/add"
                          class="needs-validation" novalidate>
                        <div class="form-group">
                            <label for="name" class="form-label">Enter name: </label>
                            <input type="text" id="name" name="name" class="form-control" required
                                   minlength="4"
                                   maxlength="15"/>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill this field correctly.</div>
                        </div>
                        <div class="form-group">
                            <label for="surname" class="form-label">Enter surname: </label>
                            <input type="text" id="surname" name="surname" class="form-control" required
                                   minlength="4" maxlength="15"/>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill this field correctly.</div>
                        </div>
                        <div class="form-group">
                            <label for="patronymic" class="form-label">Enter patronymic: </label>
                            <input type="text" id="patronymic" name="patronymic" class="form-control" required
                                   minlength="4" maxlength="20"/>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill this field correctly.</div>
                        </div>
                        <div class="form-group">
                            <label for="login" class="form-label">Enter login: </label>
                            <input type="text" id="login" name="login" class="form-control" required
                                   minlength="5" maxlength="20"/>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill this field correctly.</div>
                        </div>

                        <div class="form-group">
                            <label for="name" class="form-label">Enter password: </label>
                            <input type="password" id="password" name="password" class="form-control" required
                                   minlength="5" maxlength="20"/>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill this field correctly.</div>
                        </div>

                        <button type="submit" class="btn btn-primary">Create</button>
                    </form>
                </div>
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
