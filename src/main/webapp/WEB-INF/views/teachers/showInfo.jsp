<%@ page import="com.learning.spring.models.Teacher" %>
<%@ page import="com.learning.spring.models.Subject" %>
<%@ page import="java.util.List" %>
<%@ page import="com.learning.spring.models.Review" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Teacher information</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>

<jsp:include page="/WEB-INF/views/templates/navUser.jsp"/>

<div class="container mt-3">
    <div class="row">
        <div class="col-sm-3">
            <%Teacher teacher =((Teacher) request.getAttribute("teacher"));%>
            <h3>Name:</h3>
            <p ><%=teacher.getName()%> <%=teacher.getSurname()%> <%=teacher.getPatronymic()%></p>
            <br>
            <h3>Login information</h3>
            <p >Username - <%=teacher.getLogin()%></p></p>
            <p >Role - <%=teacher.getRole()%></p></p>
        </div>


        <div class="col-sm-8">
            <h3 class="mt-4">Subjects teaches by the teacher</h3>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Subject id (click to see full info)</th>
                    <th>Name</th>
                    <th>Semester</th>
                    <th>Max grade</th>
                </tr>
                </thead>
                <tbody>
                <%List<Subject> list = teacher.getSubjectList();
                    for (Subject subject : list) {
                %>
                <tr>
                    <th><a class="link-secondary"
                           href="/operation/subject/show?id=<%=subject.getSubjectID()%>"
                          ><%=subject.getSubjectID()%></a>
                    </th>
                    <th ><%=subject.getSubjectName()%></th>
                    <th ><%=subject.getSemester()%></th>
                    <th ><%=subject.getMaxGrade()%></th>
                </tr>
                <%}%>
                </tbody>
            </table>

        </div>
    </div>
</div>
<div class="container mt-5">
    <h2>Teacher`s review</h2>
    <div class="container">
        <p></p>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Review id (click to see full info)</th>
                <th>Teacher id (click to see full info)</th>
                <th>Report id (click to see full info)</th>
                <th>Grade</th>
                <th>Review time</th>
            </tr>
            </thead>
            <tbody>
            <%List<Review> list1 = teacher.getReviewList();
                for (Review review : list1) {
            %>
            <tr>
                <th><%=review.getReviewId()%></th>
                <th><a class="link-secondary"
                       href="/operation/teacher/show?id=<%=review.getTeacherId()%>"
                      ><%=review.getTeacherId()%></a>
                </th>
                <th><%=review.getReportId()%></th>
                <th ><%=review.getGrade()%></th>
                <th ><%=review.getTimeReview()%></th>
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


