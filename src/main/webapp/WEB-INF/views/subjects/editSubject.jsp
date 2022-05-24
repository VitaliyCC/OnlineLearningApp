<%@ page import="com.learning.spring.models.Subject" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit subject</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/templates/navAdmin.jsp"/>

<div class="container mt-3">
    <%Subject subject = ((Subject) request.getAttribute("subject"));%>
    <form method="POST" action="/operation/subject/update?id=<%=subject.getSubjectID()%>"
          class="needs-validation" novalidate>
        <div class="mb-3 mt-3">
            <label for="subjectname" class="form-label">Enter subject name: </label>
            <input type="text" name="subjectName" value="<%=subject.getSubjectName()%>" id="subjectname" class="form-control"
                   required minlength="4" maxlength="40"/>
        </div>
        <div class="mb-3">
            <label for="semester" class="form-label">Enter academic semester: </label>
            <input type="number" name="semester" value="<%=subject.getSemester()%>"  id="semester" class="form-control"
                   required min="1" max="8"/>
        </div>
        <div class="mb-3">
            <label for="maxGrade" class="form-label">Enter maximum grade for the subject: </label>
            <input type="number" name="maxGrade" id="maxGrade" value="<%=subject.getMaxGrade()%>" class="form-control"
                   required min="0" maxlength="200"/>
        </div>

        <button type="submit" class="btn btn-primary">Update subject</button>
    </form>
</div>
<div class="container mt-3">
    <br>
    <form class="form_" method="POST" action="/operation/subject/delete?id=<%=subject.getSubjectID()%>">
        <button type="submit" class="btn btn-outline-danger">Delete subject</button>
    </form>
</div>
<jsp:include page="/WEB-INF/views/templates/footer.jsp"/>
<script>
    // Disable form submissions if there are invalid fields
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            // Get the forms we want to add validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
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