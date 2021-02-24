package com.ga.adab.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue
	private int id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private Date dateofBirth;
	private String password;
	private String userRole;
	private String resetPasswordToken;
	
//Relationship one user has many quote:
	@OneToMany(mappedBy="user")
	private Set<Quote> quotes;
	
	 @Lob
	    @Column(name = "Image")
	    private byte[] image;

	    public User() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	    public User(int id, byte[] image) {
	        super();
	        this.id = id;
	        this.image = image;
	    }


	public Set<Quote> getQuotes() {
			return quotes;
		}

		public void setQuotes(Set<Quote> quotes) {
			this.quotes = quotes;
		}

		public Set<Liked> getLikes() {
			return likes;
		}

		public void setLikes(Set<Liked> likes) {
			this.likes = likes;
		}
//Relationship one user has many like:
		@OneToMany(mappedBy="user")
		private Set<Liked> likes;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Date getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(Date dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
}
