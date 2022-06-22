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

@WebServlet(value = "/toRegister")
public class ToRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String email=request.getParameter("email");
            String password=request.getParameter("password");
            String re_password=request.getParameter("re_password");
            String full_name=request.getParameter("full_name");
        SimpleDateFormat birth= new SimpleDateFormat("yyyy-MM-dd");
        Users users=new Users();
        try {
            Date birthd=birth.parse(request.getParameter("birth_date"));
            users.setBirth_date(birthd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String redirect="/register?passworderror";
        if(password.equals(re_password)){
            redirect="/register?emailerror";
            Users users1= DBManager.getUser(email);
            if(users1==null){

                users.setEmail(email);
                users.setFull_name(full_name);
                users.setPassword(password);
                users.setPicture_url("https://img2.freepng.ru/20180614/vzk/kisspng-computer-icons-anonymous-anonymity-5b2333ee0ec363.3736667315290337100605.jpg");
                if(DBManager.addUsers(users))
                redirect="/register?success";
            }

        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
