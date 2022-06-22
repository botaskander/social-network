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

@WebServlet(value = "/updatePassword")
public class updatePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users currentUsers=(Users)request.getSession().getAttribute("CURRENT_USER");
        if(currentUsers!=null){
            String old_password=request.getParameter("old_password");
            String password=request.getParameter("password");
            String re_password=request.getParameter("re_password");
            String redirect="/settings?oldpassword";
            if(old_password.equals(currentUsers.getPassword())){
                redirect="/settings?passworderror";
                if(password.equals(re_password)){

                   Users newUser=new Users();
                    newUser.setPassword(password);
                    newUser.setId(currentUsers.getId());
                    if(DBManager.updatePasswordUser(newUser))
                        redirect="/settings?success";
                }

            }
            currentUsers =DBManager.getUser(currentUsers.getId());
            request.getSession().setAttribute("CURRENT_USER",currentUsers);
            response.sendRedirect(redirect);
        }
        else {
            response.sendRedirect("/login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
