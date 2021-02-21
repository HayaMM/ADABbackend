package com.ga.adab.model;

import java.time.LocalDateTime;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="Liked")
public class Liked {
	@Id
	@GeneratedValue
	private int id;
	
	private boolean qliked;
	
	//// Relationship many likes belong to one user:
	@ManyToOne
	@JoinColumn(name="FK_UserId")
	private User user;
	
	//// Relationship many likes for one quote:
	@ManyToOne
	@JoinColumn(name="FK_QuoteId")
	private Quote quote;
	
	@Column(name="createdAt", nullable=false, updatable=false)
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@Column(name="updatedat", nullable=false,updatable=true)
	@UpdateTimestamp
	private LocalDateTime updateAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public Quote getQuote() {
		return quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}


	public boolean getQliked() {
		return qliked;
	}

	public void setQliked(boolean qliked) {
		this.qliked = qliked;
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
