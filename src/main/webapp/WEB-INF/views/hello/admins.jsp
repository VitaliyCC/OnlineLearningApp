<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page import="com.learning.spring.models.Admin" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/templates/navAdmin.jsp" />
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h3>
                <form class="form_" method="GET" action="/operation/student">
                    <button style="position: center" class="btn btn-outline-secondary" type="submit">Edit students</button>
                </form>
            </h3>
            <p>Choose to be able to manage students: create, edit...</p>
        </div>
        <div class="col-sm-4">
            <h3>
                <form class="form_" method="GET" action="/operation/teacher">
                    <button style="position: center" class="btn btn-outline-secondary" type="submit">Edit teachers</button>
                </form>
            </h3>
            <p>Choose to be able to manage teachers: create, edit...</p>
        </div>
        <div class="col-sm-4">
            <h3>
                <form class="form_" method="GET" action="/operation/admin">
                    <button style="position: center" class="btn btn-outline-secondary" type="submit">Edit admins</button>
                </form>
            </h3>
            <p>Choose to be able to manage admins: create, edit</p>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-4" style="">
            <h3>
                <form class="form_" method="GET" action="/operation/connectingPerson">
                    <button style="position: center" class="btn btn-outline-secondary" type="submit">Connecting person</button>
                </form>
            </h3>
            <p>Choose to be able to manage connecting to disciplines...</p>
        </div>

        <div class="col-sm-4">
            <h3>
                <form class="form_" method="GET" action="/operation/subject">
                    <button style="position: center" class="btn btn-outline-secondary" type="submit">Edit subjects</button>
                </form>
            </h3>
            <p>Choose to be able to manage subjects: create, edit...</p>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/templates/footer.jsp" />
</body>
</html>
