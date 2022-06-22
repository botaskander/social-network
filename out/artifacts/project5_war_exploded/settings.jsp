<%@ page import="kz.csse.db.Post" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: бота
  Date: 04.10.2020
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

        <title>Title</title>
        <%@include file="views/template/lib.jsp"%>
        <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!--Font Awesome (added because you use icons in your prepend/append)-->
        <link rel="stylesheet" href="https://formden.com/static/cdn/font-awesome/4.4.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <title>Title</title>
        <style>
            .container{
                display: flex;
            }
            .container-1{
                flex-grow: 2;
            }
            .container-2{
                flex-grow: 3;
            }
            .container-3{
                flex-grow: 1;
            }
            .edit{
                display: flex;
                flex-direction: column;
            }

        </style>
        <style>
            <%@include file="views/css/style.css"%>
        </style>
</head>
<body>
<%@include file="views/template/nav.jsp"%>
<% Users user= (Users)request.getAttribute("user"); %>
<% ArrayList<Post> posts=(ArrayList<Post>) request.getAttribute("posts");%>
<div class="container" style="margin-top: 30px;">
    <div class="container-1">
        <div class="container-fluid">
            <% if (user.getPicture_url()==null){%>
            <img src="https://longsshotokan.com/wp-content/uploads/2017/04/default-image-620x600.jpg" alt="" height="250" width="200">
            <%} else{%>
            <img  class="profileImg" src="<%=user.getPicture_url()%>" alt="" height="250" width="200">
            <%}%>
            <br>
            <br>
            <div class="card"style="width: 200px;">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <span><%=user.getFull_name()%> , <%=user.getAge()%> years</span>
                    </li>
                    <li class="list-group-item">
                        <a href="#" >
                            <i class="fa fa-fw fa-home"></i>
                            My Profile</a>
                    </li>
                    <li class="list-group-item">
                        <a href="/settings" >
                            <i class="fa fa-fw fa-desktop"></i>
                            Settings
                        </a>
                    </li>
                    <li class="list-group-item">
                        <a href="logout" style="color: red;">
                            <i class="fa fa-fw fa-sign-out"></i>
                            Logout
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="container-2"  style="margin-right: 30px;">
        <div class="container-fluid">
            <div class="edit">
                <div class="editProfile">
                    <h2>Edit Profile</h2>
                    <form action="/editProfile" method="post">
                        <div class="form-group">
                            <label >Email address</label>
                            <input class="form-control" type="text"  value="<%=user.getEmail()%>" readonly>

                        </div>
                        <div class="form-group">
                            <label >Full Name</label>
                            <input type="text"  name="full_name" value="<%=user.getFull_name()%>" class="form-control">
                            <small  class="form-text text-muted">Change your full name</small>
                        </div>
                        <div class="form-group">
                            <label class="control-label " for="date">
                                Birthdate
                            </label>
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar-check-o">
                                    </i>
                                </div>
                                <input class="form-control" id="date" name="birth_date" placeholder="MM/DD/YYYY" value="<%=user.getBirth_date()%>" type="text"/>

                            </div>
                            <small  class="form-text text-muted">Change your birthdate</small>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="user_id" value="<%=user.getId()%>">
                        </div>
                        <button type="submit" class="btn btn-primary">Update Profile</button>
                    </form>
                </div>
                <div class="editPicture">
                    <h2>Edit Picture</h2>
                    <form action="/editPicture" method="post">
                        <div class="form-group">
                            <label >URL</label>
                            <input type="text"  name="picture_url" value="<%=user.getPicture_url()%>">
                            <small  class="form-text text-muted">Input your url photo</small>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="user_id" value="<%=user.getId()%>">
                        </div>
                        <button type="submit" class="btn btn-primary">Update URL</button>
                    </form>
                </div>
                <div class="updatePassword">
                    <%
                        String oldpassworderror=request.getParameter("oldpassword");
                        if(oldpassworderror!=null){
                    %>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        Old password incorrect
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        }
                    %>
                    <%
                        String passworderror=request.getParameter("passworderror");
                        if(passworderror!=null){
                    %>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        Password are not same
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        }
                    %>
                    <%
                        String succes=request.getParameter("success");
                        if(succes!=null){
                    %>
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        Password updated successfully
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        }
                    %>
                    <h2>Update Password</h2>
                    <form action="/updatePassword" method="post">
                        <div class="form-group ">
                            <label class="control-label requiredField" >
                                Old password
                                <span class="asteriskField">
                                            *
                               </span>
                            </label>
                            <input class="form-control"  name="old_password" type="password"/>
                            <small  class="form-text text-muted">Input your old password</small>
                        </div>
                        <div class="form-group ">
                            <label class="control-label requiredField" for="subject">
                                Password
                                <span class="asteriskField">
                                            *
                               </span>
                            </label>
                            <input class="form-control" id="subject" name="password" type="password"/>
                            <small  class="form-text text-muted">Input your new password</small>
                        </div>
                        <div class="form-group ">
                            <label class="control-label requiredField" for="subject1">
                                Re-Password
                                <span class="asteriskField">
                                    *
                                </span>
                            </label>
                            <input class="form-control" id="subject1" name="re_password" type="password"/>
                            <small  class="form-text text-muted">Input your new password again</small>
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="user_id" value="<%=user.getId()%>">
                        </div>
                        <button type="submit" class="btn btn-primary">Update password</button>
                    </form>
                </div>

            </div>
        </div>
    </div>
    <div class="container-3">
        <div class="container-fluid">
            <div class="card text-center">
                <div class="card-header" style="background-color:#5A9AEE ">
                    Latest birthdays
                </div>
                <div class="card-body">
                    <p class="card-text">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Musa Uatbayev , tomorrow</li>
                        <li class="list-group-item">Azamat Tolegenov , 02 October </li>
                        <li class="list-group-item">Aybek Bagit , 10 October </li>
                    </ul>

                    </p>

                </div>

            </div>
            <br>
            <br>
            <div class="card text-center">
                <div class="card-header" style="background-color: #5A9AEE">
                    My Games
                </div>
                <div class="card-body">
                    <p class="card-text">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><span> <i class="fa fa-fw fa-futbol-o"></i> Football Online</span></li>
                        <li class="list-group-item"> <span> <i class="fa fa-fw fa-gamepad"></i> Ping-Pong Online</span> </li>
                        <li class="list-group-item"><span> <i class="fa fa-fw fa-car"></i> Race Online</span></li>
                    </ul>
                    </p>

                </div>

            </div>
        </div>

    </div>

</div>
<%@include file="views/template/footer.jsp"%>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

<script>
    $(document).ready(function(){
        var date_input=$('input[name="birth_date"]'); //our date input has the name "date"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'yyyy-mm-dd',
            container: container,
            todayHighlight: true,
            autoclose: true,
        })
    })
</script>
<script>

    var img = $("<img src='<%=user.getPicture_url()%>'/>");

    img.on('load', function(e){
        img = $("<img src='<%=user.getPicture_url()%>'/>");
    }).on('error', function(e) {
        $(".profileImg").attr("src","https://longsshotokan.com/wp-content/uploads/2017/04/default-image-620x600.jpg");
    });



</script>
</body>
</html>
