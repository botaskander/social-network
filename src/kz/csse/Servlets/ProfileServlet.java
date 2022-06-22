package kz.csse.Servlets;

import kz.csse.db.DBManager;
import kz.csse.db.Post;
import kz.csse.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(value = "/profile")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users currentUsers=(Users)request.getSession().getAttribute("CURRENT_USER");
        ArrayList<Post> posts= DBManager.getAllPosts();
        if(currentUsers!=null){
            request.setAttribute("age", currentUsers.getAge());
            request.setAttribute("user",currentUsers);
            request.setAttribute("posts",posts);

            request.getRequestDispatcher("/profile.jsp").forward(request,response);
        }
        else {
            response.sendRedirect("/login");
        }
    }
}
