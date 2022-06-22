package kz.csse.db;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

public class Post {
    private Long id;
    private String title;
    private String short_content;
    private String content;
    private Date post_date;
    private Users users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShort_content() {
        return short_content;
    }

    public void setShort_content(String short_content) {
        this.short_content = short_content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Post() {
    }

    public Post(Long id, String title, String short_content, String content, Date post_date, Users users) {
        this.id = id;
        this.title = title;
        this.short_content = short_content;
        this.content = content;
        this.post_date = post_date;
        this.users = users;
    }
    public String getMonth(){
        java.util.Date utilDate = new java.util.Date(post_date.getTime());
        LocalDate birth_date = utilDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // Get month from date
        Month month = birth_date.getMonth();


        return ""+month;
    }
    public String getYear(){
        java.util.Date utilDate = new java.util.Date(post_date.getTime());
        LocalDate birth_date = utilDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();


        // Get year from date
        int year = birth_date.getYear();
        return ""+year;
    }
    public String getDay(){
        java.util.Date utilDate = new java.util.Date(post_date.getTime());
        LocalDate birth_date = utilDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        int day = birth_date.getDayOfMonth();
        return ""+day;
    }
}
