<%@ page import="java.util.List" %>
<%@ page import="com.learning.spring.models.Department" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Schedule service</title>
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
            <div class="col-sm-4">
                <br>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#new">
                    View schedule
                </button>
                <br>
            </div>
        </div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Department code</th>
                <th>Short name</th>
                <th>Name</th>
                <th>Chief</th>
                <th>Click to view more information</th>
            </tr>
            </thead>
            <tbody id="myTable">
            <%
                List<Department> list = ((List<Department>) request.getAttribute("departments"));
                for (Department dept : list) {

            %>
            <tr>
                <th><%=dept.getCode()%>
                </th>
                <th><%=dept.getShortN()%>
                </th>
                <th><%=dept.getName()%>
                </th>
                <th><%=dept.getChief()%>
                </th>
                <th><a class="link-secondary" href="/service/groups?deptId=<%=dept.getCode()%>"
                >View</a>
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
                    <h4 class="modal-title">Form for schedule</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form method="POST" action="/service/schedule"
                          class="needs-validation" novalidate>
                        <div class="form-group">
                            <label for="deptId" class="form-label">Enter department code: </label>
                            <input type="number" id="deptId" name="deptId" class="form-control" required
                                   min="1" max="100"/>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill this field correctly.</div>
                        </div>
                        <div class="form-group">
                            <label for="course" class="form-label">Enter your course: </label>
                            <input type="number" id="course" name="course" class="form-control" required
                                   min="1" max="6"/>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill this field correctly.</div>
                        </div>
                        <div class="form-group">
                            <label for="stream" class="form-label">Enter your stream: </label>
                            <input type="number" id="stream" name="stream" class="form-control" required
                                   min="1" max="20"/>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill this field correctly.</div>
                        </div>
                        <div class="form-group">
                            <label for="groupCode" class="form-label">Enter cod your group: </label>
                            <input type="number" id="groupCode" name="groupCode" class="form-control" required
                                   min="1" max="100"/>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill this field correctly.</div>
                        </div>

                        <div class="form-group">
                            <label for="subGroupCode" class="form-label">Enter cod your sub group: </label>
                            <input type="number" id="subGroupCode" name="subGroupCode" class="form-control" required
                                   min="1" max="10"/>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill this field correctly.</div>
                        </div>

                        <button type="submit" class="btn btn-primary">Find</button>
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
w