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

@WebServlet(value = "/editProfile")
public class EditProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users currentUsers=(Users)request.getSession().getAttribute("CURRENT_USER");
        if(currentUsers!=null){
            String full_name=request.getParameter("full_name");
            SimpleDateFormat birth= new SimpleDateFormat("yyyy-MM-dd");
            Users users=new Users();
            try {
                Date birthd=birth.parse(request.getParameter("birth_date"));
                users.setBirth_date(birthd);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            users.setId(currentUsers.getId());
            users.setFull_name(full_name);
            DBManager.updateMainInformUsers(users);
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
