<%@ page import="com.learning.spring.models.Subject" %>
<%@ page import="java.util.List" %>
<%@ page import="com.learning.spring.models.Task" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Tasks</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/templates/navUser.jsp"/>
<div class="container">

    <div class="container">
        <div class="row">
            <div class="col-sm-7">
                <h2>Search in the table</h2>
                <p>Search for symbols in all columns:</p>
                <input class="form-control" id="myInput" type="text" placeholder="Search..">
                <br>
            </div>
            <%if((Integer)request.getAttribute("idT")!=-1){%>
            <div class="col-sm-3">
                <br>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#new">
                    Add new task
                </button>
                <br>
            </div>
            <%}%>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <%
            Subject subject = ((Subject) request.getAttribute("subject"));
        %>
        <tr>
            <th>Task name</th>
            <th>Subject id</th>
            <th>Task</th>
            <th>Max grade</th>
            </th>
        </tr>
        </thead>
        <tbody id="myTable">
        <%
            for (Task task : subject.getTaskList()) {
        %>
        <tr>
            <th><%=task.getTaskName()%>
            </th>
            <th><a class="link-secondary" href="/operation/subject/show?id=<%=subject.getSubjectID()%>"
            ><%=task.getSubjectId()%>
            </a>
            </th>
            <th><%=task.getSubject()%>
            </th>
            <th><%=task.getMaxGrade()%>
            </th>
            <th><a class="link-secondary" href="/operation/task/show?id=<%=(Integer) request.getAttribute("idT")%>&nameT=<%=String.valueOf(task.getTaskName())%>"
            >Show reports</a></th>
            <th>
                <form class="form_" method="post" action="/operation/task/delete?name=<%=task.getTaskName()%>">
                    <button type="submit" class="btn btn-outline-danger">Delete
                    </button>
                </form>
            </th>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>

<div class="modal" id="new">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">New Task</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <form method="POST" action="/operation/task/add"
                      class="needs-validation" novalidate>
                    <div class="form-group">
                        <label for="taskName" class="form-label">Enter task name: </label>
                        <input type="text" id="taskName" name="taskName" class="form-control" required
                               minlength="5"
                               maxlength="30"/>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill this field correctly.</div>
                    </div>
                    <div class="form-group">
                        <label for="subject" class="form-label">Enter link on subject: </label>
                        <input type="text" id="subject" name="subject" class="form-control" required
                               minlength="15" maxlength="50"/>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill this field correctly.</div>
                    </div>
                    <div class="form-group">
                        <label for="maxGrade" class="form-label">Enter max grade: </label>
                        <input type="number" id="maxGrade" name="maxGrade" class="form-control" required
                               min="0" maxlength="50"/>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill this field correctly.</div>
                    </div>
                    <div class="form-group">
                        <label for="subjectId" class="form-label">Subject id: </label>
                        <input type="number" id="subjectId" name="subjectId" class="form-control"
                               value="<%=subject.getSubjectID()%>" readonly/>
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

