<%@ page import="com.learning.spring.models.Student" %>
<%@ page import="com.learning.spring.models.Report" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>New report</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/templates/navUser.jsp"/>
<%Report report = ((Report) request.getAttribute("report"));%>
<div class="container mt-3">
    <form method="POST" action="/operation/report/add"
          class="needs-validation" novalidate>
        <div class="mb-3 mt-3">
            <label for="solutions" class="form-label">Enter link on your online docement with solution: </label>
            <input type="text" name="solutions" id="solutions" class="form-control"
                   required minlength="4" maxlength="1000"/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="mb-3">
            <label for="sendTime" class="form-label">Send time: </label>
            <input type="date" name="sendTime" id="sendTime" class="form-control ui-state-disabled" value="<%=report.getSendTime()%>"
                   readonly/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="mb-3">
            <label for="studentId" class="form-label">Your id: </label>
            <input type="number" name="studentId" id="studentId" class="form-control" value="<%=report.getStudentId()%>"
                   readonly/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="mb-3">
            <label for="taskName" class="form-label">Task name: </label>
            <input type="text" name="taskName"  id="taskName" class="form-control" value="<%=report.getTaskName()%>"
                   readonly/> <!--fix-->
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>

        <button type="submit" class="btn btn-primary">Create report</button>
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