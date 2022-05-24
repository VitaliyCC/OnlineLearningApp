<%@ page import="com.learning.spring.models.Subject" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Subjects</title>
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
                <h2>Operation on student</h2>
                <br>

                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#new">
                    Add new subject
                </button>
                <br>
            </div>
        </div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Subject id (click to see full info)</th>
                <th>Name</th>
                <th>Semester</th>
                <th>Max grade</th>
            </tr>
            </thead>
            <tbody id="myTable">
            <%
                List<Subject> list = ((List<Subject>) request.getAttribute("subjects"));
                for (Subject subject : list) {


            %>
            <tr>
                <!--<th scope="row">1</th>-->
                <th><a class="link-secondary"
                       href="/operation/subject/show?id=<%=subject.getSubjectID()%>"><%=subject.getSubjectID()%></a>
                </th>

                <th><%=subject.getSubjectName()%>
                </th>
                <th><%=subject.getSemester()%>
                </th>
                <th><%=subject.getMaxGrade()%>
                </th>

                <th><a class="link-secondary" href="/operation/subject/edit/?id=<%=subject.getSubjectID()%>"
                >Edit</a>
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
                    <h4 class="modal-title">New Subject</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form method="POST" action="/operation/subject/add"
                          class="needs-validation" novalidate>
                        <div class="form-group">
                            <label for="subjectName" class="form-label">Enter subject name: </label>
                            <input type="text" name="subjectName" id="subjectName" class="form-control" required
                                   minlength="4"
                                   maxlength="40"/>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill this field correctly.</div>
                        </div>
                        <div class="form-group">
                            <label for="semester" class="form-label">Enter academic semester: </label>
                            <input type="number" name="semester" id="semester" class="form-control"
                                   required min="1" max="8"/>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill this field correctly.</div>
                        </div>
                        <div class="form-group">
                            <label for="maxGrade" class="form-label">Enter maximum grade for the subject : </label>
                            <input type="number" name="maxGrade" id="maxGrade" class="form-control"
                                   required min="0" max="200"/>
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
