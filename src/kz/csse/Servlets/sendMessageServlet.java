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

@WebServlet(value = "/sendMessage")
public class sendMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users currentUsers=(Users)request.getSession().getAttribute("CURRENT_USER");
        if(currentUsers!=null){

            Long id=Long.parseLong(request.getParameter("friend_id"));
            String text_message=request.getParameter("text_message");
            Users friend=DBManager.getUser(id);

            if(DBManager.getCheck_Chat(currentUsers.getId(),friend.getId())!=null){
                Chat chat1=DBManager.getCheck_Chat(currentUsers.getId(),friend.getId());
                System.out.println(chat1.getId());
                chat1.setLatest_message(text_message);
                DBManager.updateChat(chat1);
                Message message=new Message();
                message.setChat(chat1);
                message.setMessage_text(text_message);
                message.setRead_by_receiver(false);
                message.setSender(currentUsers);
                message.setUser(friend);
                DBManager.addMessage(message);
            }
            else
            {
                Chat chat=new Chat();
                chat.setUsers(currentUsers);
                chat.setOpponent_user(friend);
                chat.setLatest_message(text_message);
                DBManager.createChat(chat);
                Chat chat1=DBManager.getCheck_Chat(currentUsers.getId(),friend.getId());
                Message message=new Message();
                message.setChat(chat1);
                message.setMessage_text(text_message);
                message.setRead_by_receiver(false);
                message.setSender(currentUsers);
                message.setUser(friend);
                DBManager.addMessage(message);
            }

            request.getSession().setAttribute("CURRENT_USER",currentUsers);
            response.sendRedirect("/profile");
        }
        else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
