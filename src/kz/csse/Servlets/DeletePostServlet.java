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

@WebServlet(value = "/deletePost")
public class DeletePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users currentUsers=(Users)request.getSession().getAttribute("CURRENT_USER");
        if(currentUsers!=null){
            Long id=Long.parseLong(request.getParameter("post_id"));

            DBManager.deletePost(id);
            currentUsers =DBManager.getUser(currentUsers.getId());
            request.getSession().setAttribute("CURRENT_USER",currentUsers);
            response.sendRedirect("/myPost");
        }
        else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
