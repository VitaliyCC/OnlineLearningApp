<%@ page import="com.learning.spring.models.ConnectingTeacher" %>
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

<div class="container mt-3">
    <%ConnectingTeacher connectingT = ((ConnectingTeacher) request.getAttribute("connectingT"));%>
    <form method="post" action="/operation/connectingPerson/T/update?id=<%=connectingT.getId()%>"
          class="needs-validation" novalidate>
        <div class="mb-3 mt-3">
            <label for="studentId" class="form-label">Enter teacher id: </label>
            <input type="number" name="teacherId" value="<%=connectingT.getTeacherId()%>" id="studentId" class="form-control"
                   required min="0" max="1000"/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>
        <div class="mb-3">
            <label for="subjectId" class="form-label">Enter subject id: </label>
            <input type="number" name="subjectId" value="<%=connectingT.getSubjectId()%>" id="subjectId" class="form-control"
                   required min="0" max="1000"/>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill this field correctly.</div>
        </div>

        <button type="submit" class="btn btn-primary">Update connecting</button>
    </form>
</div>
<div class="container mt-3">
    <br>
    <form class="form_" method="post" action="/operation/connectingPerson/T/delete?id=<%=connectingT.getId()%>">
        <button type="submit" class="btn btn-outline-danger">Delete connecting</button>
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