package com.uhcl.parade.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ParadeUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userid;
	
	private String fname;

	private String lname;

	@Column(length = 20)
	private String phone;

	private String studentId;

	@Column(length = 100, unique = true)
	private String email;

	@Column(length = 60)
	private String password;

	@Column(name = "token", length = 32)
	private String token;

	@Column(name = "tokenGeneratedAt", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tokenGeneratedAt = null;

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenGeneratedAt() {
		return tokenGeneratedAt;
	}

	public void setTokenGeneratedAt() {
		this.tokenGeneratedAt = new Date();
	}

	public ParadeUser(long userid, String fname, String lname, String phone, String studentId, String email,
			String password, String token, Date tokenGeneratedAt) {
		super();
		this.userid = userid;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.studentId = studentId;
		this.email = email;
		this.password = password;
		this.token = token;
		this.tokenGeneratedAt = tokenGeneratedAt;
	}

	public ParadeUser() {
		super();
	}

	@Override
	public String toString() {
		return "ParadeUser [userid=" + userid + ", fname=" + fname + ", lname=" + lname + ", phone=" + phone
				+ ", studentId=" + studentId + ", email=" + email + ", password=" + password + ", token=" + token
				+ ", tokenGeneratedAt=" + tokenGeneratedAt + "]";
	}
	
	

}
