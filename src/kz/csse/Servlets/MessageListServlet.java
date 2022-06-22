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

@WebServlet(value = "/messageList")
public class MessageListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users currentUsers=(Users)request.getSession().getAttribute("CURRENT_USER");
        ArrayList<Users> friends= DBManager.getAllFriendsbyUser(currentUsers.getId());
        ArrayList<Chat> chats =DBManager.getAllChatsbyUser(currentUsers.getId());
        Long chat_id=Long.parseLong(request.getParameter("chat_id"));

        ArrayList<Message> messages=DBManager.getAllMessages(chat_id);
        ArrayList<Users> filtredUsers = null;

        if(currentUsers!=null){
            request.setAttribute("age", currentUsers.getAge());
            request.setAttribute("user",currentUsers);
            request.setAttribute("friends",friends);
            request.setAttribute("chats",chats);
            request.setAttribute("messages",messages);
            request.setAttribute("chat_id",chat_id);
            DBManager.upDateMessage(chat_id);
            Chat chat =DBManager.getChat(chat_id);
            chat.setIs_read(true);
            request.getRequestDispatcher("/Message.jsp").forward(request,response);
        }
        else {
            response.sendRedirect("/login");
        }
    }
}
