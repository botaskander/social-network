package kz.csse.Servlets;

import kz.csse.db.DBManager;
import kz.csse.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(value = "/editPicture")
public class EditPictureServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users currentUsers=(Users)request.getSession().getAttribute("CURRENT_USER");
        if(currentUsers!=null){
            String picture_url=request.getParameter("picture_url");
            System.out.println(picture_url+"bbbb");
            Users users=new Users();
            if(picture_url==""){
                picture_url="https://img2.freepng.ru/20180614/vzk/kisspng-computer-icons-anonymous-anonymity-5b2333ee0ec363.3736667315290337100605.jpg";
            }
            users.setId(currentUsers.getId());
            users.setPicture_url(picture_url);
            DBManager.editPictureUsers(users);
            currentUsers =DBManager.getUser(currentUsers.getId());
            request.getSession().setAttribute("CURRENT_USER",currentUsers);
            response.sendRedirect("/settings");
        }
        else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
