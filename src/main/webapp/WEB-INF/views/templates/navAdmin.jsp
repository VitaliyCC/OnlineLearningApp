<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<body>
<div class="p-5 bg-primary text-white text-center">
    <h1>Admin workbench</h1>
    <p>On this page the administrator can manage the information system!</p>
</div>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link active" href="/index">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/operation/student">Work with students</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/operation/subject">Work with subjects</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/operation/admin">Work with admins</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/operation/teacher">Work with teacher</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/operation/connectingPerson">Work with connecting</a>
            </li>

            <li class="nav-item">
                <form action="/auth/logout" method="POST">
                    <button style="color: black" class="nav-link active" type="submit">Logout</button>
                </form>
            </li>
        </ul>
    </div>
</nav>
<br>
<br>
<br>
</div>
</body>
</html>