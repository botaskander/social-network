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

@WebServlet(value = "/sendMessagebyChat")
public class sendMessagebyChatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users currentUsers=(Users)request.getSession().getAttribute("CURRENT_USER");
        if(currentUsers!=null){

            Long id=Long.parseLong(request.getParameter("chat_id"));
            String text_message=request.getParameter("text_message");



                Chat chat1=DBManager.getChat(id);
                System.out.println(chat1.getId());
                chat1.setLatest_message(text_message);
                DBManager.updateChat(chat1);
                Message message=new Message();
                message.setChat(chat1);
                message.setMessage_text(text_message);
                message.setRead_by_receiver(false);
                message.setSender(currentUsers);
                if(chat1.getOpponent_user().getId()!=currentUsers.getId()){
                    message.setUser(chat1.getOpponent_user());
                }
                else
                {
                    message.setUser(chat1.getUsers());
                }

                DBManager.addMessage(message);


            request.getSession().setAttribute("CURRENT_USER",currentUsers);
            response.sendRedirect("/messageList?chat_id="+chat1.getId());
        }
        else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
