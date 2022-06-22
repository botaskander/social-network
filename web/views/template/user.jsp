<%@ page import="kz.csse.db.Users" %><%--
  Created by IntelliJ IDEA.
  User: бота
  Date: 03.10.2020
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Users currentUser=(Users) request.getSession().getAttribute("CURRENT_USER");
boolean ONLINE=false;
if(currentUser!=null){
    ONLINE=true;
}
%>

