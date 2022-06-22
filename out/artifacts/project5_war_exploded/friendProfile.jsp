<%--
  Created by IntelliJ IDEA.
  User: бота
  Date: 12.10.2020
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kz.csse.db.Post" %><%--
  Created by IntelliJ IDEA.
  User: бота
  Date: 04.10.2020
  Time: 0:09
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
    </style>
</head>
<body>
<%@include file="views/template/nav.jsp"%>
<% Users user= (Users)request.getAttribute("user"); %>
<% Users friend_proverka=(Users) request.getAttribute("friend_proverka");%>
<% Users request_friend= (Users)request.getAttribute("request_friend"); %>
<% ArrayList<Post> posts=(ArrayList<Post>) request.getAttribute("posts");%>
<% ArrayList<Users>  friends=(ArrayList<Users>) request.getAttribute("friends");%>
<%ArrayList <Users> requestFriend=(ArrayList<Users>) request.getAttribute("request_Friend");%>
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
                        <% if(request_friend!=null) {%>
                        <form action="/addFriend" method="post">
                            <input type="hidden" name="friend_id" value="<%=user.getId()%>">
                            <button type="submit" ><i class="fa fa-fw fa-home"></i>Confirm</button>
                        </form>
                        <%} else if(friend_proverka!=null){%>
                        <form action="/removeFriend" method="post">

                                <input type="hidden" name="friend_id" value="<%=user.getId()%>">
                                <button type="submit" ><i class="fa fa-fw fa-home"></i>Remove from friend</button>
                        </form>

                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                            <i class="fa fa-fw fa-desktop"></i>
                            Send Message
                        </button>
                        <%} else {%>
                        <form action="" method="post">
                            <input type="hidden" name="friend_id" value="<%=user.getId()%>">
                            <button type="submit" ><i class="fa fa-fw fa-home"></i>Add to Friend</button>

                        </form>
                        <%}%>
                    </li>

                    <li class="list-group-item">
                        <a href="/logout" style="color: red;">
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

            <% if(posts!=null){
                for (Post p:posts){

            %>

            <div class="card">
                <div class="card-body">
                    <h5 class="card-title"><%=p.getTitle()%></h5>
                    <p class="card-text">
                        <%=p.getShort_content()%>
                    </p>
                    <a data-toggle="modal"  href="#Detail<%=p.getId()%>">
                        <button type="button" class="btn btn-primary">More-></button>
                    </a>

                </div>
                <div class="card-footer text-muted">
                    Posted on  <%=p.getMonth()%>  <%=p.getDay()%> , <%=p.getYear()%> by <p style="color: #2038ff"><%=p.getUsers().getFull_name()%></p>
                </div>
            </div>
            <%}
            }%>
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
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="/sendMessage" method="post">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Send Message to <%=user.getFull_name()%></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
            <textarea name="text_message">  </textarea>
                <input type="hidden" name="friend_id" value="<%=user.getId()%>">
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-success">Send</button>
            </div>
            </form>
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

