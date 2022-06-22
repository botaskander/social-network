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

@WebServlet(value = "/friendProfile")
public class FriendProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users currentUsers=(Users)request.getSession().getAttribute("CURRENT_USER");

        Long id=Long.parseLong(request.getParameter("id"));
        ArrayList<Post> posts= DBManager.getPostbyUser(id);
        ArrayList<Users> friends= DBManager.getAllFriendsbyUser(currentUsers.getId());
        ArrayList<Users> requestsenders=DBManager.getAllRequestFriend(currentUsers.getId());
        Users requestsender=DBManager.getRequestSender(id,currentUsers.getId());
        Users friend_proverka=DBManager.getFriendbyFriendId(id,currentUsers.getId());

        if(currentUsers!=null){
            request.setAttribute("age", currentUsers.getAge());
            request.setAttribute("user",DBManager.getUser(id));
            request.setAttribute("friends",friends);
            request.setAttribute("request_Friends" ,requestsenders);
            request.setAttribute("posts",posts);
            request.setAttribute("request_friend",requestsender);
            request.setAttribute("friend_proverka",friend_proverka);

            request.getRequestDispatcher("/friendProfile.jsp").forward(request,response);
        }
        else {
            response.sendRedirect("/login");
        }
    }
}
