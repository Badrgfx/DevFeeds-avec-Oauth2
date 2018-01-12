package com.devfeeds.metier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.devfeeds.dao.CommentRepository;
import com.devfeeds.entities.Comment;
import com.devfeeds.entities.User;

@Service
public class CommentMetierImpl implements CommentMetier{

	@Autowired
	private CommentRepository commentRepository;
	
	

	@Override
	public Comment saveComment(Comment comment) {
		comment.setUser(currentlogged());
	    comment.setDatePub(new Date());
		return commentRepository.save(comment);
	}
	
	 public User currentlogged() {
		   User u= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		   return u;
	   }

}
