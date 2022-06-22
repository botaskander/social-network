<%--
  Created by IntelliJ IDEA.
  User: бота
  Date: 03.10.2020
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="views/template/lib.jsp"%>

    <style>
        <%@include file="views/css/style.css"%>
    </style>
</head>
<body>
<%@include file="views/template/nav.jsp"%>
<section id="sign_in" class="sign_in">
    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card card-signin my-5">
                    <div class="card-body">
                        <h5 class="card-title text-center">Sign In</h5>
                        <%
                            String passworderror=request.getParameter("passworderror");
                            if(passworderror!=null){
                        %>
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            Incorrect password
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <%
                            }
                        %>
                        <%
                            String emailerror=request.getParameter("emailerror");
                            if(emailerror!=null){
                        %>
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            Incorrect email
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <%
                            }
                        %>
                        <form action="/auth" method="post" class="form-signin" a>
                            <div class="form-label-group">
                                <input type="email" id="inputEmail" class="form-control" name="email" placeholder="Email address" required autofocus >
                                <label for="inputEmail">Email address</label>
                            </div>

                            <div class="form-label-group">
                                <input type="password" id="inputPassword" class="form-control"  name="password" placeholder="Password" required >
                                <label for="inputPassword">Password</label>
                            </div>

                            <button class="btn btn-lg btn-primary btn-block text-uppercase" >Sign in</button>
                            <hr class="my-4">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>
<%@include file="views/template/footer.jsp"%>
</body>
</html>

