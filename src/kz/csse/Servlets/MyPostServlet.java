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
import java.util.ArrayList;

@WebServlet(value = "/myPost")
public class MyPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users currentUsers=(Users)request.getSession().getAttribute("CURRENT_USER");

        if(currentUsers!=null){
            ArrayList<Post> posts= DBManager.getPostbyUser(currentUsers.getId());
            request.setAttribute("age", currentUsers.getAge());
            request.setAttribute("user",currentUsers);
            request.setAttribute("posts",posts);

            request.getRequestDispatcher("/MyPost.jsp").forward(request,response);
        }
        else {
            response.sendRedirect("/login");
        }
    }
}
