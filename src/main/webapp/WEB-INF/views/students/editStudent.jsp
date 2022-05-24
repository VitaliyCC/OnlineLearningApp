<%@ page import="com.learning.spring.models.Student" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit student</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/templates/navAdmin.jsp"/>
<%Student student = ((Student) request.getAttribute("student"));%>
<div class="container mt-3">
    <form method="POST" action="/operation/student/edit?id=<%=student.getStudentId()%>"
          class="needs-validation" novalidate>
        <div class="mb-3 mt-3">
            <label for="name" class="form-label">Enter name: </label>
            <input type="text" name="name" id="name" class="form-control" value="<%=student.getName()%>" required minlength="4" maxlength="15"/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="mb-3">
            <label for="surname" class="form-label">Enter surname: </label>
            <input type="text" name="surname" id="surname" class="form-control" value="<%=student.getSurname()%>" required minlength="4"
                   maxlength="15"/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="mb-3">
            <label for="patronymic" class="form-label">Enter patronymic: </label>
            <input type="text" name="patronymic" id="patronymic" class="form-control" value="<%=student.getPatronymic()%>" required minlength="4"
                   maxlength="20"/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="mb-3">
            <label for="username" class="form-label">Enter username: </label>
            <input type="text" name="login"  id="username" class="form-control" value="<%=student.getLogin()%>"  required minlength="5"
                   maxlength="20"/> <!--fix-->
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="mb-3">
            <!--fix-show selected-->
            <label for="role" class="form-label">Enter role: </label>
            <input class="form-control" list="browsers" id="role" name="role" value="<%=student.getRole()%>"
                   required pattern="STUDENT|TEACHER|ADMIN"/> <!--fix-->
            <datalist id="browsers">
                <option VALUE="STUDENT">
                <option value="TEACHER">
                <option value="ADMIN">
            </datalist>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>

        <button type="submit" class="btn btn-primary">Update student</button>
    </form>
</div>
<div class="container mt-3">
    <br>
    <form class="form_" method="POST" action="/operation/student/delete?id=<%=student.getStudentId()%>">
        <button type="submit" class="btn btn-outline-danger">Delete student</button>
    </form>
</div>

<jsp:include page="/WEB-INF/views/templates/footer.jsp"/>

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