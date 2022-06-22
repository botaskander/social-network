package kz.csse.Servlets;

import kz.csse.db.Chat;
import kz.csse.db.DBManager;
import kz.csse.db.Message;
import kz.csse.db.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/chatList")
public class ChatListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users currentUsers=(Users)request.getSession().getAttribute("CURRENT_USER");
        ArrayList<Users> friends= DBManager.getAllFriendsbyUser(currentUsers.getId());
        ArrayList<Chat> chats =DBManager.getAllChatsbyUser(currentUsers.getId());
        String name = request.getParameter("search");
        ArrayList<Users> filtredUsers = null;
        ArrayList<Message> messages=DBManager.getAllMessageswthoutId();
        if(currentUsers!=null){
            request.setAttribute("age", currentUsers.getAge());
            request.setAttribute("user",currentUsers);
            request.setAttribute("friends",friends);
            request.setAttribute("chats",chats);


            request.setAttribute("messages",messages);

            if(name != null){
                filtredUsers = DBManager.getUserbyName(name , currentUsers.getId());

                request.setAttribute("filtredUsers", filtredUsers);
                request.setAttribute("search" , name);

            }

            request.getRequestDispatcher("/Chat.jsp").forward(request,response);
        }
        else {
            response.sendRedirect("/login");
        }
    }
}
