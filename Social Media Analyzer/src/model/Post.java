package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {

    private int id;
    private String content;
    private String author;
    private int likes;
    private int shares;
    private LocalDateTime dateTime;

    public Post(int id, String content, String author, int likes, int shares, String dateTimeStr) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.likes = likes;
        this.shares = shares;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
        this.dateTime = LocalDateTime.parse(dateTimeStr, formatter);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("%d | %s | %d likes | %d shares | %s", 
                id, content, likes, shares, dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }
}
