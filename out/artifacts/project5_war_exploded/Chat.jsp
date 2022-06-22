<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.csse.db.Chat" %>
<%@ page import="kz.csse.db.Message" %><%--
  Created by IntelliJ IDEA.
  User: бота
  Date: 13.10.2020
  Time: 21:16
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
    <script src="views/tinymce/tinymce.min.js"></script>
    <link href="css/addons-pro/chat.css" rel="stylesheet">
    <!-- Chat CSS - minified-->
    <link href="css/addons-pro/chat.min.css" rel="stylesheet">

    <script>tinymce.init({selector:'textarea'});</script>

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


        .user_img{
            height: 70px;
            width: 70px;
            border:1.5px solid #f5f6fa;

        }
        .user_img_msg{
            height: 40px;
            width: 40px;
            border:1.5px solid #f5f6fa;

        }
        .img_cont{
            position: relative;
            height: 70px;
            width: 70px;
        }
        .img_cont_msg{
            height: 40px;
            width: 40px;
        }
        .online_icon{
            position: absolute;
            height: 15px;
            width:15px;
            background-color: #4cd137;
            border-radius: 50%;
            bottom: 0.2em;
            right: 0.4em;
            border:1.5px solid white;
        }
        .offline{
            background-color: #c23616 !important;
        }
        .user_info{
            margin-top: auto;
            margin-bottom: auto;
            margin-left: 15px;
        }
        .user_info span{
            font-size: 20px;
            color: white;
        }
        .user_info p{
            font-size: 15px;
            color: black;
        }

    </style>
    <style>
        <%@include file="views/css/style.css"%>
    </style>
</head>
<body>
<%@include file="views/template/nav.jsp"%>
<% Users user= (Users)request.getAttribute("user"); %>
<% ArrayList<Chat> chats=(ArrayList<Chat>) request.getAttribute("chats");%>
<%ArrayList<Message> messages=(ArrayList<Message>) request.getAttribute("messages");%>

<div class="container" style="margin-top: 30px;">
    <div class="container-1">
        <div class="container-fluid">
            <% if (user.getPicture_url()==null){%>
            <img src="https://longsshotokan.com/wp-content/uploads/2017/04/default-image-620x600.jpg" alt="" height="250" width="200">
            <%} else{%>
            <img class="profileImg" src="<%=user.getPicture_url()%>" alt="" height="250" width="200">
            <%}%>
            <br>
            <br>
            <div class="card" style="width: 200px;">
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
        <% for (Chat chat:chats){%>
        <a href="/messageList?chat_id=<%=chat.getId()%>">
            <div class="container">
                <div class="d-flex bd-highlight">
                    <div class="img_cont">
                        <%if(user.getId()!=chat.getUsers().getId()){%>
                        <img src="<%=chat.getUsers().getPicture_url()%>" class="rounded-circle user_img">
                        <%} else {%>
                        <img src="<%=chat.getOpponent_user().getPicture_url()%>" class="rounded-circle user_img">
                        <%}%>
                    </div>
                    <div class="user_info">
                        <h4>
                            <%if(user.getId()!=chat.getUsers().getId()){%>
                                <%=chat.getUsers().getFull_name()%>
                            <%} else {%>
                                <%=chat.getOpponent_user().getFull_name()%>
                            <%}%>
                        </h4>
                        <span style="color: #1b1e21"><%=chat.getLatest_message()%></span>
                        <h6  style="color: #1b1e21">At : <%=chat.getLatest_message_time()%> </h6>
                        <div style="float: right">

                        </div>
                    </div>
                </div>

            </div>

        </a>
        <%}%>
    </div>

    <div class="container-3">
        <div class="container-fluid">
            <div class="card text-center">
                <div class="card-header" style="background-color: #5A9AEE">
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
                <div class="card-header" style="background-color: #5A9AEE;">
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
