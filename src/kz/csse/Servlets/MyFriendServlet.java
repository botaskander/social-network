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

@WebServlet(value = "/myFriend")
public class MyFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users currentUsers=(Users)request.getSession().getAttribute("CURRENT_USER");
        ArrayList<Users> friends= DBManager.getAllFriendsbyUser(currentUsers.getId());
        ArrayList<Users> requestsender=DBManager.getAllRequestFriend(currentUsers.getId());
        String name = request.getParameter("search");
        ArrayList<Users> filtredUsers = null;
        ArrayList<Long> request_id=new ArrayList<>();
        ArrayList<Long> friend_id=new ArrayList<>();
        ArrayList<Users> request_sent= DBManager.getPodpisi_User(currentUsers.getId());
        if(currentUsers!=null){
            request.setAttribute("age", currentUsers.getAge());
            request.setAttribute("user",currentUsers);
            request.setAttribute("friends",friends);
            request.setAttribute("request_Friend" ,requestsender);

            if(name != null){
                filtredUsers = DBManager.getUserbyName(name , currentUsers.getId());

                request.setAttribute("filtredUsers", filtredUsers);
                request.setAttribute("search" , name);
                for(Users re:request_sent){
                    request_id.add(re.getId());
                }
                for(Users  fr:friends){
                    friend_id.add(fr.getId());
                }
                request.setAttribute("request_id",request_id);
                request.setAttribute("friend_id",friend_id);
            }

            request.getRequestDispatcher("/myFriend.jsp").forward(request,response);
        }
        else {
            response.sendRedirect("/login");
        }
    }
}
