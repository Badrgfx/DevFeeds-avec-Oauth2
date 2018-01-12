package com.devfeeds.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;

@Entity
public class User implements Serializable,UserDetails{
	@Id @GeneratedValue
  private long id;
  private String username;
  private String password;
  private String name;
  private String email;

@Column(name = "enabled", nullable = false)
  private boolean enabled;
  @OneToMany(mappedBy="user",fetch=FetchType.LAZY)
  private Collection<Topic> topics;
  @OneToMany(mappedBy="user",fetch=FetchType.LAZY)
  private Collection<Comment> comments;
  @ManyToMany(fetch = FetchType.LAZY,
          cascade = {
                  CascadeType.PERSIST,
                  CascadeType.MERGE
              })
  @JoinTable(
      name = "User_TopicsVoted", 
      joinColumns = { @JoinColumn (name = "userId") }, 
      inverseJoinColumns = { @JoinColumn(name = "topicId") }
  )
  private Collection<Topic> topicsVoted;
  
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
@JsonIgnore
public Collection<Topic> getTopics() {
	return topics;
}
public void setTopics(Collection<Topic> topics) {
	this.topics = topics;
}
@JsonIgnore
public Collection<Topic> getTopicsVoted() {
	return topicsVoted;
}
public void setTopicsVoted(Collection<Topic> topicsVoted) {
	this.topicsVoted = topicsVoted;
}
@JsonIgnore
public Collection<Comment> getComments() {
	return comments;
}
public void setComments(Collection<Comment> comments) {
	this.comments = comments;
}
public User() {
	super();
	// TODO Auto-generated constructor stub
}
public User(String username, String password,String name, String email, boolean enabled) {
	super();
	this.username = username;
	this.password = password;
	this.name = name;
	this.email = email;
	this.enabled=enabled;
}
@Override
@JsonIgnore
public Collection<? extends GrantedAuthority> getAuthorities() {
	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	return authorities;
}
@Override
public boolean isAccountNonExpired() {
	return true;
}
@Override
public boolean isAccountNonLocked() {
	return true;
}
@Override
public boolean isCredentialsNonExpired() {
	return true;
}
@Override
public boolean isEnabled() {
	return enabled;
}
  

}
