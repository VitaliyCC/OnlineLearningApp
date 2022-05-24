<%@ page import="com.learning.spring.models.Subject" %>
<%@ page import="com.learning.spring.models.ConnectingStudent" %>
<%@ page import="java.util.List" %>
<%@ page import="com.learning.spring.models.ConnectingTeacher" %>
<%@ page import="com.learning.spring.models.Task" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Subject information</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>

<jsp:include page="/WEB-INF/views/templates/navUser.jsp"/>

<div class="container mt-3">
    <div class="row">
        <%Subject subject = ((Subject) request.getAttribute("subject"));%>
        <div class="col-sm-3">
            <h3>Login information</h3>
            <p >Subject name - <%=subject.getSubjectName()%></p></p>
            <p >Academic semester - <%=subject.getSemester()%></p></p>
            <p >Max grade - <%=subject.getMaxGrade()%></p></p>
        </div>


        <div class="col-sm-4">
            <h3 class="mt-4">Students who studying this discipline </h3>

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Student id (click to see full info)</th>

                </tr>
                </thead>
                <tbody>
                <%
                    List<ConnectingStudent> list = subject.getConnectingStudents();

                    for (ConnectingStudent student : list) {
                %>
                <tr>
                    <th><a class="link-secondary"
                           href="/operation/student/show?id=<%=student.getStudentId()%>">
                        <%=student.getStudentId()%></a>
                    </th>
                </tr>
                <%}%>
                </tbody>
            </table>

        </div>
        <div class="col-sm-4">
            <h3 class="mt-4">Teachers who teaches this discipline </h3>

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Teacher id (click to see full info)</th>

                </tr>
                </thead>
                <tbody>
                <%List<ConnectingTeacher> list1= subject.getConnectingTeachers();
                    for (ConnectingTeacher teacher : list1) {
                    %>
                <tr>
                    <!--<th scope="row">1</th>-->
                    <th><a class="link-secondary"
                           href="/operation/teacher/show?id=<%=teacher.getTeacherId()%>">
                        <%=teacher.getTeacherId()%></a>
                    </th>
                </tr>
                <%}%>
                </tbody>
            </table>

        </div>
    </div>
</div>
<div class="container mt-5">
    <h2>Tasks of this discipline</h2>
    <div class="container">
        <p></p>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Task name (click to see full info)</th>
                <th>Subject</th>
                <th>Max grade</th>
            </tr>
            </thead>
            <tbody>
            <%List<Task> list2 = subject.getTaskList();
                for (Task task : list2) {
            %>
            <tr>
                <!--<th scope="row">1</th>-->
                <th><a class="link-secondary"
                       href="/operation/task/show?id=-1&nameT=<%=task.getTaskName()%>"><%=task.getTaskName()%></a>
                </th>
                <th ><%=task.getSubject()%></th>
                <th ><%=task.getMaxGrade()%></th>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
</div>

<jsp:include page="/WEB-INF/views/templates/footer.jsp"/>

</body>
</html>


