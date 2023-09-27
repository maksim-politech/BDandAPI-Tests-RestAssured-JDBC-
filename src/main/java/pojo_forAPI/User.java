package pojo_forAPI;

import java.util.Objects;

public class User {
    private int userId;
    private int id;
    private String title;
    private String body;

    public User(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {   ///метод вызывается внутри assertEquals
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && id == user.id && Objects.equals(title, user.title) && Objects.equals(body, user.body);
    }

    @Override
    public int hashCode() {
       return Objects.hash(userId, id, title, body);
    }

    //Если мы переопределяем хешкод, то и equals надо, и наоборот, такая практика
    //Хешкод помогает работать в коллекциях (для hashMAP(рус.хеш таблица) нужно значение хеша), говорить, что не углублялся

    @Override
    public String toString() {  ///до переопределения просто выводила хеш
        return "User{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
