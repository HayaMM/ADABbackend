package com.ga.adab.model;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="Quote")
public class Quote {
@Id
@GeneratedValue
private int id;

private String qtitle;
private String qbody;
private String qwriter;
private String qfrom;
private int qreivew;



////Relationship many quotes belongs to one user:
@ManyToOne
@JoinColumn(name="FK_UserId")
private User user;

////Relationship one quote has many likes:

@OneToMany(mappedBy="quote")
private Set<Liked> Likeds;

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

public String getQtitle() {
	return qtitle;
}

public void setQtitle(String qtitle) {
	this.qtitle = qtitle;
}

public String getQbody() {
	return qbody;
}

public void setQbody(String qbody) {
	this.qbody = qbody;
}

public String getQwriter() {
	return qwriter;
}

public void setQwriter(String qwriter) {
	this.qwriter = qwriter;
}

public String getQfrom() {
	return qfrom;
}

public void setQfrom(String qfrom) {
	this.qfrom = qfrom;
}

public int getQreivew() {
	return qreivew;
}

public void setQreivew(int qreivew) {
	this.qreivew = qreivew;
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

public Set<Liked> getLikeds() {
	return Likeds;
}

public void setLikeds(Set<Liked> likeds) {
	Likeds = likeds;
}


public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}



}
