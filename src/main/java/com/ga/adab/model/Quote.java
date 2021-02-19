package com.ga.adab.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="Quote")
public class Quote {
@Id
@GeneratedValue
private int id;
private String title;
private String body;
private String writer;
private String from;
private int reivew;

////Relationship many quotes belongs to one user:
//@ManyToOne
//@JoinColumn(name="FK_UserId")
//private User user;

////Relationship one quote has many likes:
//@OneToMany(mappedBy="quote")
//private Set<Like> likes;

@Column(name="createdAt", nullable = false, updatable = false)
@CreationTimestamp
private LocalDateTime createAt;

@Column(name="updatedat", nullable = false, updatable = true)
@UpdateTimestamp
private LocalDateTime updateAt;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getBody() {
	return body;
}

public void setBody(String body) {
	this.body = body;
}

public String getWriter() {
	return writer;
}

public void setWriter(String writer) {
	this.writer = writer;
}

public String getFrom() {
	return from;
}

public void setFrom(String from) {
	this.from = from;
}

public int getReivew() {
	return reivew;
}

public void setReivew(int reivew) {
	this.reivew = reivew;
}

public LocalDateTime getCreateAt() {
	return createAt;
}

public void setCreateAt(LocalDateTime createAt) {
	this.createAt = createAt;
}

public LocalDateTime getUpdateAt() {
	return updateAt;
}

public void setUpdateAt(LocalDateTime updateAt) {
	this.updateAt = updateAt;
}

}
