package kz.csse.db;

import java.sql.Timestamp;
import java.util.Date;

public class Chat {
    private Long id;
    private Users users;
    private Users opponent_user;
    private Date created_date;
    private String latest_message;
    private Timestamp latest_message_time;
    private boolean is_read=false;

    public Chat(Long id, Users users, Users opponent_user, Date created_date, String latest_message, Timestamp latest_message_time) {
        this.id = id;
        this.users = users;
        this.opponent_user = opponent_user;
        this.created_date = created_date;
        this.latest_message = latest_message;
        this.latest_message_time = latest_message_time;
    }

    public Chat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Users getOpponent_user() {
        return opponent_user;
    }

    public void setOpponent_user(Users opponent_user) {
        this.opponent_user = opponent_user;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getLatest_message() {
        return latest_message;
    }

    public void setLatest_message(String latest_message) {
        this.latest_message = latest_message;
    }

    public Date getLatest_message_time() {
        return latest_message_time;
    }

    public void setLatest_message_time(Timestamp latest_message_time) {
        this.latest_message_time = latest_message_time;
    }

    public boolean isIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }
}
