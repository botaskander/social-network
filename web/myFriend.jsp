<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.csse.db.DBManager" %>
<%@ page import="java.nio.file.attribute.UserDefinedFileAttributeView" %><%--
  Created by IntelliJ IDEA.
  User: бота
  Date: 11.10.2020
  Time: 22:46
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

    </style>
    <style>
        <%@include file="views/css/style.css"%>
        body{
            margin-top:20px;
            background:#eee;
        }
        a {
            color: #f96332;
        }
        .m-t-5{
            margin-top: 5px;
        }
        .card {
            background: #fff;
            margin-bottom: 30px;
            transition: .5s;
            border: 0;
            border-radius: .1875rem;
            display: inline-block;
            position: relative;
            width: 100%;
            box-shadow: none;
        }
        .card .body {
            font-size: 14px;
            color: #424242;
            padding: 20px;
            font-weight: 400;
        }
        .profile-page .profile-header {
            position: relative
        }

        .profile-page .profile-header .profile-image img {
            border-radius: 50%;
            width: 140px;
            border: 3px solid #fff;
            box-shadow: 0 3px 6px rgba(0, 0, 0, 0.16), 0 3px 6px rgba(0, 0, 0, 0.23)
        }

        .profile-page .profile-header .social-icon a {
            margin: 0 5px
        }

        .profile-page .profile-sub-header {
            min-height: 60px;
            width: 100%
        }

        .profile-page .profile-sub-header ul.box-list {
            display: inline-table;
            table-layout: fixed;
            width: 100%;
            background: #eee
        }

        .profile-page .profile-sub-header ul.box-list li {
            border-right: 1px solid #e0e0e0;
            display: table-cell;
            list-style: none
        }

        .profile-page .profile-sub-header ul.box-list li:last-child {
            border-right: none
        }

        .profile-page .profile-sub-header ul.box-list li a {
            display: block;
            padding: 15px 0;
            color: #424242
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
            font-size: 10px;
            color: rgba(255,255,255,0.6);
        }
    </style>
</head>
<body>
<%@include file="views/template/nav.jsp"%>
<% Users user= (Users)request.getAttribute("user"); %>
<% ArrayList<Users> usersArrayList=(ArrayList<Users>) request.getAttribute("users");%>
<% ArrayList<Users>  friends=(ArrayList<Users>) request.getAttribute("friends");%>
<%ArrayList <Users> requestFriend=(ArrayList<Users>) request.getAttribute("request_Friend");%>
<%ArrayList<Users> filtredUsers=(ArrayList<Users>) request.getAttribute("filtredUsers");%>
<%String search=(String)request.getAttribute("search");%>
<% ArrayList<Users> request_id=(ArrayList<Users>) request.getAttribute("request_id");%>
<% ArrayList<Users> friend_id=(ArrayList<Users>) request.getAttribute("friend_id");%>
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

        <div class="container-fluid">
            <div class="row justify-content-center">
                   <div class="col-12 col-md-10 col-lg-8" >
                       <form class="card card-sm" method="get" action="${pageContext.request.contextPath}/myFriend">
                           <div class="card-body row no-gutters align-items-center">
                               <div class="col-auto">
                                   <i class="fa fa-fw fa-search h2 text-body"></i>
                               </div>

                               <div class="col">
                                   <input class="form-control form-control-lg form-control-borderless" name = "search" type="search" placeholder="Search...">
                               </div>

                               <div class="col-auto">
                                   <button class="btn btn-lg btn-success" type="submit">Search</button>
                               </div>
                           </div>
                       </form>
                   </div>
            </div>
            <%
                if(filtredUsers!= null){

            %>
            <div class="container-fluid">
                <h2>
                    Search result for: "<%=search%>"
                </h2>
            </div>
            <div class="container-fluid">
                <%
                    for (Users filtred:filtredUsers
                    ) {


                %>
                <div class="container">

                    <div class="d-flex bd-highlight">
                        <div class="img_cont">
                            <% if (filtred.getPicture_url()==null){%>
                            <img  class="rounded-circle user_img" src="https://longsshotokan.com/wp-content/uploads/2017/04/default-image-620x600.jpg" alt="" >

                            <%} else{%>
                            <img  class="rounded-circle user_img" id="myImg"  src="<%=filtred.getPicture_url()%>" onerror="this.onerror=null; this.src='https://longsshotokan.com/wp-content/uploads/2017/04/default-image-620x600.jpg'" >
                            <%}%>

                        </div>
                        <div class="user_info">
                            <span style="color: #1b1e21"><%=filtred.getFull_name()%></span>
                            <h6  style="color: #1b1e21"><%=filtred.getAge()%> years old</h6>
                            <div style="float: right">


                                <% if (friend_id.contains(filtred.getId())){%>
                                <a data-toggle="modal"  href="#Edit<%=filtred.getId()%>">
                                    <button type="button" class="btn btn-primary"><i class="fa fa-fw fa-paper-plane-o h2 text-body"></i>
                                        Send Message</button>
                                </a>
                                <%}else if (request_id.contains(filtred.getId())){%>
                                <button type="button" class="btn btn-outline-info"><i class="fa fa-fw fa-check h2 text-body"></i>Request Sent</button>
                                <%
                                }else{      %>
                                <form action="/sendRequest" method="post">
                                    <input type="hidden" name="friend_id" value="<%=filtred.getId()%>">
                                    <button type="submit" class="btn btn-outline-info"><i class="fa fa-fw  fa-plus-square-o h2 text-body"></i>Add to Friends</button>
                                </form>


                                <%}%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="Edit<%=filtred.getId()%>">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel1">Send Message to <%=filtred.getFull_name()%></h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form action="/sendMessage" method="post">
                            <div class="modal-body">
                                <textarea name="text_message">  </textarea>
                                <input type="hidden" name="friend_id" value="<%=filtred.getId()%>">
                            </div>
                            <div class="modal-footer">

                                <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                <button type="submit" class="btn btn-success">Send</button>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
            <%
                } else{
            %>


            <%if(!requestFriend.isEmpty()) {%>
            <div class="row">
                <div class="card">
                    <h5 class="card-header">You have new requests</h5>
                    <div class="col-xl-6 col-lg-7 col-md-12">
                        <div class="card profile-header">
                            <div class="body">
                                <% for(Users u:requestFriend){%>
                                <div class="row">
                                    <div class="col-lg-4 col-md-4 col-12">
                                        <div class="profile-image float-md-right"> <img src="<%=u.getPicture_url()%>"  style="width: 100px;" alt="Avatar"> </div>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-12">

                                        <h4 class="m-t-0 m-b-0"><a href="/friendProfile?id=<%=u.getId()%>"><strong><%=u.getFull_name()%></strong></a> </h4>
                                        <span class="job_post"></span>
                                        <p><%=u.getAge()%> years old </p>
                                        <div>
                                            <form action="/rejectFriend" method="post">
                                                <button type="submit" class="btn btn-outline-primary" name="friend_id" value="<%=u.getId()%>">Reject</button>

                                            </form>
                                            <form action="/addFriend" method="post">
                                                <button type="submit" class="btn btn-outline-primary" name="friend_id" value="<%=u.getId()%>">Confirm </button>
                                            </form>
                                        </div>

                                    </div>
                                </div>

                                <%}%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%}%>

            <div class="row">
                <% for(Users u:friends){ %>
                <div class="row">
                    <div class="col-lg-4 col-md-4 col-12">
                        <div class="profile-image float-md-right"> <img src="<%=u.getPicture_url()%>" alt="Avatar" width="100px;"> </div>
                    </div>
                    <div class="col-lg-8 col-md-8 col-12">
                        <h4 class="m-t-0 m-b-0"><a href="/friendProfile?id=<%=u.getId()%>"><strong><%=u.getFull_name()%></strong></a></h4>
                        <span class="job_post"></span>
                        <p><%=u.getAge()%> years old </p>
                        <div>
                            <a data-toggle="modal"  href="#Edit<%=u.getId()%>">
                                <button type="button" class="btn btn-primary"><i class="fa fa-fw fa-paper-plane-o h2 text-body"></i>
                                    Send Message</button>
                            </a>
                        </div>

                    </div>
                </div>
                <div class="modal fade" id="Edit<%=u.getId()%>">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header">
                                <h5 class="modal-title">Send Message to <%=u.getFull_name()%></h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form action="/sendMessage" method="post">
                                <div class="modal-body">
                                    <textarea name="text_message">  </textarea>
                                    <input type="hidden" name="friend_id" value="<%=u.getId()%>">
                                </div>
                                <div class="modal-footer">

                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">CANCEL</button>
                                    <button type="submit" class="btn btn-success">Send</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
            <%}%>
        </div>
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
