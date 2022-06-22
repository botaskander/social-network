package kz.csse.db;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;
    static {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/project5?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC","root","");

        }
        catch (Exception e ){
            e.printStackTrace();

        }
    }
    public static boolean addUsers(Users users){
        int rows=0;
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "INSERT INTO users(id ,email  , password_user  , full_name , birth_date ,picture_url ) "+
                    "VALUES (NULL ,?,?,?,?,?)"+ "");
            statement.setString(1,users.getEmail());
            statement.setString(2,users.getPassword());
            statement.setString(3,users.getFull_name());
            statement.setDate(4, Date.valueOf(users.getBirth_date().toInstant().atZone(ZoneId.of("UTC+6")).toLocalDate()));
            statement.setString(5,users.getPicture_url());
           
            rows=statement.executeUpdate();
            statement.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }
    public static ArrayList<Users> getAllUsers(){
        ArrayList<Users> users=new ArrayList<>();
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT id, email , password_user , full_name , birth_date , picture_url  "+
                    "FROM users "+"");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                users.add(new Users(resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password_user"),
                        resultSet.getString("full_name"),
                        resultSet.getDate("birth_date"),
                        resultSet.getString("picture_url")));

            }
            statement.close();
        }

        catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
    public static Users  getUser(Long id){
        Users user=null;
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT id, email, password_user, full_name , birth_date , picture_url " +
                    "FROM users  where id = ? "+"");
            statement.setLong(1,id);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                user=new Users(resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password_user"),
                        resultSet.getString("full_name"),
                        resultSet.getDate("birth_date"),
                        resultSet.getString("picture_url"));

            }
            statement.close();
        }

        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    public static boolean updateMainInformUsers(Users users){
        int affectedRows = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users   "+
                    "SET full_name= ?, "+
                    "birth_date = ?  "+
                    "WHERE id = ?");



            statement.setString(1, users.getFull_name());
            statement.setDate(2, Date.valueOf(users.getBirth_date().toInstant().atZone(ZoneId.of("UTC+6")).toLocalDate()));
            statement.setLong(3,users.getId());

            affectedRows = statement.executeUpdate();

            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("ID: " + users.getId());
        System.out.println("UPDATED ROWS :" +affectedRows);

        return affectedRows>0;
    }
    public static boolean editPictureUsers(Users users){
        int affectedRows = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users   "+
                    "SET picture_url = ? "+
                    "WHERE id = ? ");



            statement.setString(1,users.getPicture_url());
            statement.setLong(2, users.getId());


            affectedRows = statement.executeUpdate();

            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("ID: " + users.getId());
        System.out.println("UPDATED ROWS :" +affectedRows);

        return affectedRows>0;
    }
    public static boolean updatePasswordUser(Users users){
        int affectedRows = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users   "+
                    "SET password_user = ? "+
                    "WHERE id = ? ");



            statement.setString(1,users.getPassword());
            statement.setLong(2, users.getId());


            affectedRows = statement.executeUpdate();

            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("ID: " + users.getId());
        System.out.println("UPDATED ROWS :" +affectedRows);

        return affectedRows>0;
    }
    public static boolean addPost(Post post){
        int rows=0;
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "INSERT INTO posts(id,title,short_content,content ,post_date,author_id) "+
                    "VALUES (NULL ,?,?,?,DEFAULT,?)"+ "");

            statement.setString(1,post.getTitle());
            statement.setString(2,post.getShort_content());
            statement.setString(3,post.getContent());
            statement.setLong(4,post.getUsers().getId());
            rows=statement.executeUpdate();
            statement.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }
    public static boolean savePost(Post post){
        int affectedRows = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE posts "+
                    "SET title= ?, "+
                    "short_content= ?, " +
                    "content= ? " +
                    " where id = ?");

            statement.setString(1,post.getTitle());
            statement.setString(2, post.getShort_content());
            statement.setString(3,post.getContent());
            statement.setLong(4,post.getId());


            affectedRows = statement.executeUpdate();

            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return affectedRows>0;
    }
    public static ArrayList<Post> getAllPosts(){
        ArrayList<Post> posts = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                   "SELECT p.id, p.title, p.short_content, p.content, p.post_date, p.author_id,u.email ,u.password_user, u.full_name,u.birth_date,u.picture_url " +
                    "                    FROM posts p " +
                    "                    INNER JOIN users u ON u.id= p.author_id " +
                    "                    order by post_date desc ");



            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                posts.add(new Post(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date"),
                        new Users(
                                resultSet.getLong("author_id"),
                                resultSet.getString("email"),
                                resultSet.getString("password_user"),
                                resultSet.getString("full_name"),
                                resultSet.getDate("birth_date"),
                                resultSet.getString("picture_url")
                        )


                ));

            }
            statement.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return posts;

    }
    public static void deletePost(Long id) {
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "DELETE FROM posts WHERE id = ? "+"");
            statement.setLong(1,id);
            statement.executeUpdate();

            statement.close();
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Post getPost(Long id ){
        Post post = null;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "Select * " +
                    "FROM posts " +
                    "where id = ? ");


            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                post = new Post(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date"),
                        getUser(resultSet.getLong("user_id"))
                );
                statement.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return post;
    }
    public  static ArrayList<Post> getPostbyUser(Long id){
        String query = "Select * FROM posts where author_id = ?  order by post_date desc ";
        ArrayList<Post> posts = new ArrayList<>();
        try {
            PreparedStatement prt = connection.prepareStatement(query);

            prt.setLong(1, id);
            ResultSet resultSet = prt.executeQuery();

            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("post_date"),
                        getUser(resultSet.getLong("author_id"))
                ));
            }
            prt.close();
        }
        catch (Exception e){
                e.printStackTrace();
        }
        return posts;
    }
    public static Users getUser(String email){
        Users user=null;
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT * " +
                    "FROM users  where email = ? "+"");
            statement.setString(1,email);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                user=new Users(resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password_user"),
                        resultSet.getString("full_name"),
                        resultSet.getDate("birth_date"),
                        resultSet.getString("picture_url"));

            }
            statement.close();
        }

        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    public static ArrayList<Users> getAllFriendsbyUser(Long id){
        ArrayList<Users> users = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "Select * FROM friends where user_id = ? ");


            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                users.add(getUser(resultSet.getLong("friend_id")));

            }
            statement.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return users;

    }
    public static ArrayList<Users> getAllRequestFriend(Long id ){
        ArrayList<Users> users = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "Select * FROM friends_requests where user_id = ? ");


            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                users.add(getUser(resultSet.getLong("request_sender_id")));

            }
            statement.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
    public static ArrayList<Users> getUserbyName(String name,Long id){
        ArrayList<Users> users=new ArrayList<>();
        name="%"+name+"%";
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT id, email , password_user , full_name , birth_date , picture_url  "+
                    "FROM users "+
                    "where full_name like ? and id != ? ");
            statement.setString(1, name);
            statement.setLong(2,id);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                users.add(new Users(resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password_user"),
                        resultSet.getString("full_name"),
                        resultSet.getDate("birth_date"),
                        resultSet.getString("picture_url")));

            }
            statement.close();
        }

        catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
    public static void deleteFriend(Long user_id, Long friend_id) {
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "DELETE FROM friends WHERE user_id= ? and friend_id = ?  "+"");
            statement.setLong(1,user_id);
            statement.setLong(2,friend_id);
            statement.executeUpdate();

            statement.close();
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }
    public  static boolean sendRequest_Friend(Long user_id,Long request_id){
        int rows=0;
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "INSERT INTO friends_requests(id ,user_id  , request_sender_id  , sent_time ) "+
                    "VALUES (NULL ,?,?,DEFAULT )"+ "");
            statement.setLong(1,user_id);
            statement.setLong(2,request_id);


            rows=statement.executeUpdate();
            statement.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }
    public  static  void addFriends(Long user_id,Long friend_id){
        addFriend(user_id,friend_id);
        addFriend(friend_id,user_id);
    }
    public static boolean addFriend(Long user_id,Long friend_id){
        int rows=0;
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "INSERT INTO friends(id, friend_id ,user_id,added_time) "+
                    "VALUES (NULL ,?,?,DEFAULT)"+ "");

            statement.setLong(1,friend_id);
            statement.setLong(2,user_id);

            rows=statement.executeUpdate();
            statement.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }
    public static void deleteFriendS(Long user_id, Long friend_id){
        deleteFriend(user_id,friend_id);
        deleteFriend(friend_id,user_id);
    }
    public static void deleteRequest(Long user_id,Long friend_id){
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "DELETE FROM friends_requests WHERE user_id= ? and request_sender_id = ?  "+"");
            statement.setLong(1,user_id);
            statement.setLong(2,friend_id);
            statement.executeUpdate();

            statement.close();
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }
    public  static ArrayList<Users> getRequest_User(Long id){
        ArrayList<Users> users = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "Select * FROM friends_requests where user_id = ? ");


            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                users.add(getUser(resultSet.getLong("request_sender_id")));

            }
            statement.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return users;

    }
    public static ArrayList<Users> getPodpisi_User(Long id){
        ArrayList<Users> users = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "Select * FROM friends_requests where request_sender_id = ? ");


            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                users.add(getUser(resultSet.getLong("user_id")));

            }
            statement.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return users;

    }
    public static Users  getRequestSender(Long id,Long user_id){
        Users user=null;
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT * " +
                    "FROM friends_requests  where  request_sender_id = ? and user_id =? "+"");
            statement.setLong(1,id);
            statement.setLong(2,user_id);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                user=getUser(resultSet.getLong("request_sender_id"));

            }
            statement.close();
        }

        catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    public static Users  getFriendbyFriendId(Long id,Long user_id){
        Users user=null;
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT * " +
                    "FROM friends  where  friend_id = ? and user_id = ?  "+"");
            statement.setLong(1,id);
            statement.setLong(2,user_id);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                user=getUser(resultSet.getLong("friend_id"));

            }
            statement.close();
        }

        catch (Exception e){
            e.printStackTrace();
        }
        return user;

    }
    public static void bbbb(){
        Users users=getFriendbyFriendId(1L,2L);
        System.out.println(users+"bbb");
    }
    public static boolean isFriend(Long user_id , Long friend_id ){
        boolean isFriend = false;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    " Select exists( " +
                    "SELECT * " +
                    "FROM friends f " +
                    "INNER JOIN users u on f.user_id = u.id " +
                    "where f.user_id = ? and f.friend_id = ? ) as isFriend "
            );

            statement.setLong(1, user_id);
            statement.setLong(2, friend_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isFriend = resultSet.getBoolean("isFriend");
            }
            System.out.println(isFriend);
            statement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return isFriend;

    }
    public static boolean isRequested(Long user_id , Long request_sender_id ){
        boolean isRequested = false;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    " Select exists( " +
                    "SELECT * " +
                    "FROM friends_requests f " +
                    "INNER JOIN users u on f.user_id = u.id " +
                    "where f.user_id = ? and f.request_sender_id = ? ) as isRequested "
            );

            statement.setLong(1, user_id);
            statement.setLong(2, request_sender_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isRequested = resultSet.getBoolean("isRequested");
            }
            System.out.println(isRequested);
            statement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return isRequested;

    }
    public static  boolean createChat(Chat chat){
        int rows=0;
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "INSERT INTO chats(id,user_id,opponent_user_id,created_date,latest_message_text,latest_message_time) "+
                    "VALUES (NULL ,?,?,DEFAULT ,?,DEFAULT)"+ "");

            statement.setLong(1,chat.getUsers().getId());
            statement.setLong(2,chat.getOpponent_user().getId());
            statement.setString(3,chat.getLatest_message());

            rows=statement.executeUpdate();
            statement.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;

    }
    public static boolean updateChat(Chat chat){
        int affectedRows = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE chats " +
                    "SET latest_message_text= ? ," +
                    "    latest_message_time=default " +
                    "where id = ? ");

            statement.setString(1,chat.getLatest_message());
            statement.setLong(2,chat.getId());

            affectedRows = statement.executeUpdate();

            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return affectedRows>0;
    }
    public static boolean addMessage(Message message){
        int rows=0;
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "INSERT INTO messages(id,chat_id,user_id,sender_id,message_text,read_by_receiver,sent_date) "+
                    "VALUES (NULL ,?,?,?,?,?,DEFAULT )"+ "");

            statement.setLong(1,message.getChat().getId());
            statement.setLong(2,message.getUser().getId());
            statement.setLong(3,message.getSender().getId());
            statement.setString(4,message.getMessage_text());
            statement.setBoolean(5,message.isRead_by_receiver());


            rows=statement.executeUpdate();
            statement.close();


        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;

    }
    public static ArrayList<Chat> getAllChat_check(Long id){
        ArrayList<Chat> chats=new ArrayList<>();
        ArrayList<Chat> chats1=getAllChats(id);
        ArrayList<Chat> chats2=getAllChatsbyUser(id);
        chats.addAll(chats1);
        chats.addAll(chats2);
        return chats;
    }

    public static ArrayList<Chat> getAllChats(Long id){
        ArrayList<Chat> chats = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id as id , c.user_id as user_id , c.opponent_user_id as opponent_user_id, c.created_date as created_date, c.latest_message_text as latest_message_text , c.latest_message_time as latest_message_time   " +
                    "FROM chats c " +
                    "INNER JOIN users u on user_id = u.id " +
                    "where user_id = ? " +
                    "ORDER BY latest_message_time DESC");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                chats.add(new Chat(
                        resultSet.getLong("id"),
                        DBManager.getUser(resultSet.getLong("user_id")),
                        DBManager.getUser(resultSet.getLong("opponent_user_id")),
                        resultSet.getTimestamp("created_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getTimestamp("latest_message_time")

                ));

            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return chats;

    }
    public static ArrayList<Message> getAllMessages(Long id){
        ArrayList<Message> messages = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT m.id as id , m.chat_id as chat_id ,  m.user_id as user_id , m.sender_id as sender_id,  m.message_text as message_text , m.read_by_receiver as read_by_receiver , m.sent_date as sent_date   " +
                    "FROM messages m " +
                    "INNER JOIN chats c on chat_id = c.id " +
                    "where chat_id = ? " +
                    "ORDER BY sent_date asc ");
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                messages.add(new Message(
                        resultSet.getLong("id"),
                        DBManager.getChat(resultSet.getLong("chat_id")),
                        DBManager.getUser(resultSet.getLong("user_id")),
                        DBManager.getUser(resultSet.getLong("sender_id")),
                        resultSet.getString("message_text"),
                        resultSet.getBoolean("read_by_receiver"),
                        resultSet.getTimestamp("sent_date")

                ));

            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return messages;

    }
    public static Chat getCheck_Chat(Long user_id,Long opponent_id){
        if(getChatbyId(user_id,opponent_id)!=null){
            return  getChatbyId(user_id, opponent_id);
        }
        else  if(getChatbyId(opponent_id,user_id)!=null){
            return  getChatbyId(opponent_id,user_id);
        }
        return null;

    }
    public static Chat getChatbyId(Long user_id,Long opponent_id){
        Chat chat = null;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "Select * " +
                    "FROM chats " +
                    "where user_id = ? and opponent_user_id = ? ");


            statement.setLong(1, user_id);
            statement.setLong(2,opponent_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                chat = new Chat(
                        resultSet.getLong("id"),
                        DBManager.getUser(resultSet.getLong("user_id")),
                        DBManager.getUser(resultSet.getLong("opponent_user_id")),
                        resultSet.getTimestamp("created_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getTimestamp("latest_message_time")
                );
                statement.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return chat;
    }
    public static Chat getChat(Long id ){
        Chat chat = null;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "Select * " +
                    "FROM chats " +
                    "where id= ? ");


            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                chat = new Chat(
                        resultSet.getLong("id"),
                        DBManager.getUser(resultSet.getLong("user_id")),
                        DBManager.getUser(resultSet.getLong("opponent_user_id")),
                        resultSet.getTimestamp("created_date"),
                        resultSet.getString("latest_message_text"),
                        resultSet.getTimestamp("latest_message_time")
                );
                statement.close();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return chat;
    }
    public static boolean updateMessage(Message message){
        int affectedRows = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE messages " +
                    "SET  read_by_receiver = ? "+
                    "where id = ? ");

            statement.setBoolean(1,true);
            statement.setLong(2,message.getId());


            affectedRows = statement.executeUpdate();

            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return affectedRows>0;
    }
    public static ArrayList<Chat> getAllChatsbyUser(Long id){
        ArrayList<Chat> chats = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT DISTINCT * FROM chats c INNER JOIN chats c1 on c.opponent_user_id = c1.opponent_user_id " +
                    "WHERE c.user_id  = ? or c1.opponent_user_id = ? order by c.latest_message_time desc ");
            statement.setLong(1,id);
            statement.setLong(2,id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                chats.add(new Chat(
                        resultSet.getLong("c.id"),
                        DBManager.getUser(resultSet.getLong("c.user_id")),
                        DBManager.getUser(resultSet.getLong("c.opponent_user_id")),
                        resultSet.getTimestamp("c.created_date"),
                        resultSet.getString("c.latest_message_text"),
                        resultSet.getTimestamp("c.latest_message_time")

                ));

            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return chats;

    }

    public static ArrayList<Message> getAllMessageswthoutId(){
        ArrayList<Message> messages = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT m.id as id , m.chat_id as chat_id ,  m.user_id as user_id , m.sender_id as sender_id,  m.message_text as message_text , m.read_by_receiver as read_by_receiver , m.sent_date as sent_date   " +
                    "FROM messages m " +
                    "INNER JOIN chats c on chat_id = c.id " +
                    "ORDER BY sent_date asc ");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                messages.add(new Message(
                        resultSet.getLong("id"),
                        DBManager.getChat(resultSet.getLong("chat_id")),
                        DBManager.getUser(resultSet.getLong("user_id")),
                        DBManager.getUser(resultSet.getLong("sender_id")),
                        resultSet.getString("message_text"),
                        resultSet.getBoolean("read_by_receiver"),
                        resultSet.getTimestamp("sent_date")

                ));

            }
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return messages;

    }
    public static boolean upDateMessage(Long id){
        int affectedRows = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE messages " +
                    "SET read_by_receiver= ? " +
                    "where chat_id = ? ");

            statement.setBoolean(1,true);
            statement.setLong(2,id);

            affectedRows = statement.executeUpdate();

            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return affectedRows>0;
    }
}
