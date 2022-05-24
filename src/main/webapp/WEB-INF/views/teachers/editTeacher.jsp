<%@ page import="com.learning.spring.models.Teacher" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit teacher</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/templates/navAdmin.jsp"/>
<div class="container mt-3">
<%Teacher teacher = ((Teacher) request.getAttribute("teacher"));%>
    <form method="post" action="/operation/teacher/update?id=<%=teacher.getTeacherId()%>"
          class="needs-validation" novalidate>
        <div class="mb-3 mt-3">
            <label for="name" class="form-label">Enter name: </label>
            <input type="text" name="name" id="name" value="<%=teacher.getName()%>" class="form-control" required minlength="4" maxlength="15"/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="mb-3">
            <label for="surname" class="form-label">Enter surname: </label>
            <input type="text" name="surname" id="surname" value="<%=teacher.getSurname()%>" class="form-control" required minlength="4"
                   maxlength="15"/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="mb-3">
            <label for="patronymic" class="form-label">Enter patronymic: </label>
            <input type="text" name="patronymic" id="patronymic" value="<%=teacher.getPatronymic()%>" class="form-control" required minlength="4"
                   maxlength="20"/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="form-group">
            <label for="salary" class="form-label">Enter salary: </label>
            <input type="text" name="salary" id="salary" value="<%=teacher.getSalary()%>" class="form-control" required
                   min="1000" max="100000"/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="mb-3">
            <label for="username" class="form-label">Enter username: </label>
            <input type="text" name="login" id="username" value="<%=teacher.getLogin()%>" class="form-control"  required minlength="5"
                   maxlength="20"/> <!--fix-->
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="mb-3">
            <!--fix-show selected-->
            <label for="role" class="form-label">Enter role: </label>
            <input class="form-control" list="browsers" id="role" value="<%=teacher.getRole()%>" name="role"
                   required pattern="STUDENT|TEACHER|ADMIN"/> <!--fix-->
            <datalist id="browsers">
                <option VALUE="STUDENT">
                <option value="TEACHER">
                <option value="ADMIN">
            </datalist>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>

        <button type="submit" class="btn btn-primary">Update teacher</button>
    </form>
</div>
<div class="container mt-3">
    <br>
    <form class="form_" method="post" action="/operation/teacher/delete?id=<%=teacher.getTeacherId()%>">
        <button type="submit" class="btn btn-outline-danger">Delete teacher</button>
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