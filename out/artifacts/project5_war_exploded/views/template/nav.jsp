<%--
  Created by IntelliJ IDEA.
  User: бота
  Date: 03.10.2020
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="user.jsp"%>
<style>
.nav-item a{
    color: white;
}


</style>
<header class="header" id="header" style="background-color: #4267B2">
    <nav class="navbar navbar-expand-lg ">

        <a class="navbar-brand" href="#"  style="color: white">Aralasu.kz</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">


                <ul class="navbar-nav ml-auto" >
                    <%
                        if(ONLINE){


                    %>

                    <li class="nav-item">
                        <a class="nav-link" href="/profile">Feed</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/myFriend">My friends</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Groups</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/myPost">My Posts</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/chatList">Messages</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Pictures</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Videos</a>
                    </li>
                    <% }
                        else{ %>

                    <li class="nav-item">
                        <a class="nav-link" href="/register">Register</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/login">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">FAQ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">About Aralasu</a>
                    </li>
                    <%}%>
                </ul>

        </div>
    </nav>
</header>
