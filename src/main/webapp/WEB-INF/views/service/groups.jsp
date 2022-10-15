<%@ page import="java.util.List" %>
<%@ page import="com.learning.spring.models.Group" %>
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
        </div>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Department</th>
                <th>Course</th>
                <th>Stream</th>
                <th>Group</th>
                <th>Dept short name</th>
                <th>Name</th>
                <th>Name P</th>
            </tr>
            </thead>
            <tbody id="myTable">
            <%
                List<Group> list = ((List<Group>) request.getAttribute("groups"));
                String dep,cours,strm,grp;
                for (Group group : list) {
                    dep=group.getDep()==0?"":String.valueOf(group.getDep());
                    cours=group.getCourse()==0?"":String.valueOf(group.getCourse());
                    strm=group.getStrm()==0?"":String.valueOf(group.getStrm());
                    grp=group.getGrp()==0?"":String.valueOf(group.getGrp());

            %>
            <tr>
                <th><%=dep%></th>
                <th><%=cours%></th>
                <th><%=strm%></th>
                <th><%=grp%></th>
                <th><%=group.getDepShort()%></th>
                <th><%=group.getName()%></th>
                <th><%=group.getNameP()%></th>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
    <br>
    <br>
    <br>

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
</body>
</html>
