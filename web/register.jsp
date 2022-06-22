<%--
  Created by IntelliJ IDEA.
  User: бота
  Date: 04.10.2020
  Time: 0:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Special version of Bootstrap that only affects content wrapped in .bootstrap-iso -->
    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />

    <!--Font Awesome (added because you use icons in your prepend/append)-->
    <link rel="stylesheet" href="https://formden.com/static/cdn/font-awesome/4.4.0/css/font-awesome.min.css" />
    <%@include file="views/template/lib.jsp"%>
    <title>Title</title>
    <style>.bootstrap-iso .formden_header h2, .bootstrap-iso .formden_header p, .bootstrap-iso form{font-family: Arial, Helvetica, sans-serif; color: black}.bootstrap-iso form button, .bootstrap-iso form button:hover{color: white !important;} .asteriskField{color: red;}</style>
</head>
<body>
<%@include file="views/template/nav.jsp"%>


<div class="bootstrap-iso">
    <div class="container-fluid" style="padding-top: 100px;">
        <div class="row justify-content-center">
            <div class="row">
                <div>
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
                        String emailerror=request.getParameter("emailerror");
                        if(emailerror!=null){
                    %>
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        User exists
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
                        User added successfully
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <%
                        }
                    %>
                    <form action="/toRegister" method="post">
                        <div class="form-group ">
                            <label class="control-label requiredField" for="email">
                                Email
                                <span class="asteriskField">
                                    *
                                </span>
                            </label>
                            <input class="form-control" id="email" name="email" type="email"/>
                        </div>
                        <div class="form-group ">
                            <label class="control-label requiredField" for="subject">
                                Password
                                <span class="asteriskField">
                                            *
                               </span>
                            </label>
                            <input class="form-control" id="subject" name="password" type="password"/>
                        </div>
                        <div class="form-group ">
                            <label class="control-label requiredField" for="subject1">
                                Re-Password
                                <span class="asteriskField">
                                    *
                                </span>
                            </label>
                            <input class="form-control" id="subject1" name="re_password" type="password"/>
                        </div>
                        <div class="form-group ">
                            <label class="control-label requiredField" for="name2">
                                Full-Name
                                <span class="asteriskField">
                                                *
                                </span>
                            </label>
                            <input class="form-control" id="name2" name="full_name" type="text"/>
                        </div>
                        <div class="form-group ">
                            <label class="control-label " for="date">
                                Birth-Date
                            </label>
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar-check-o">
                                    </i>
                                </div>
                                <input class="form-control" id="date" name="birth_date" placeholder="MM/DD/YYYY" type="text"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div>
                                <button class="btn btn-primary " name="submit" type="submit">
                                    Submit
                                </button>
                            </div>
                        </div>

                    </form>

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
</body>
</html>
