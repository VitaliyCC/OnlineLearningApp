<%@ page import="com.learning.spring.models.Student" %>
<%@ page import="com.learning.spring.models.Report" %>
<%@ page import="com.learning.spring.models.Review" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>New review</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/templates/navUser.jsp"/>
<%Review review = ((Review) request.getAttribute("review"));%>
<div class="container">
    <form method="POST" action="/operation/review/add"
          class="needs-validation" novalidate>
        <div class="mb-3 mt-3">
            <label for="grade" class="form-label">Enter grade for this report: </label>
            <input type="number" name="grade" id="grade" class="form-control"
                   required min="0" maxlength="10"/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="mb-3">
            <label for="timeReview" class="form-label">Send time: </label>
            <input type="date" name="timeReview" id="timeReview" class="form-control ui-state-disabled" value="<%=review.getTimeReview()%>"
                   readonly/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="mb-3">
            <label for="reportId" class="form-label">Your id: </label>
            <input type="number" name="reportId" id="reportId" class="form-control" value="<%=review.getReportId()%>"
                   readonly/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="mb-3">
            <label for="teacherId" class="form-label">Task name: </label>
            <input type="number" name="teacherId"  id="teacherId" class="form-control" value="<%=review.getTeacherId()%>"
                   readonly/> <!--fix-->
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>

        <button type="submit" class="btn btn-primary">Create review</button>
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