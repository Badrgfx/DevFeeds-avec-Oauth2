package com.devfeeds.metier;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.devfeeds.dao.CommentRepository;
import com.devfeeds.dao.TopicRepository;
import com.devfeeds.dao.UserRepository;
import com.devfeeds.entities.Comment;
import com.devfeeds.entities.Topic;
import com.devfeeds.entities.User;

@Service
public class TopicMetierImpl implements TopicMetier {

	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentRepository commentRepository;
	
   

	public Page<Topic> listTopics(int page, int size) {
		Page<Topic> listTopicWithSize = topicRepository.findAll(new PageRequest(page, size));
		for (Topic topic : listTopicWithSize) {
			topic.setSizeComments(topic.getComments().size());
			topic.setSizeVotes(topic.getUservoted().size());
			for(User user : topic.getUservoted())
			if(user.getId()==currentlogged().getId()) {
				topic.setVotedByConnectuser(true);
			}
		}
		return listTopicWithSize;
	}

	public Topic saveTopic(Topic topic) {
		topic.setUser(currentlogged());
		topic.setDatePublication(new Date());
		return topicRepository.save(topic);
	}

	public void deleteTopic(Long id) {
		topicRepository.delete(id);	
	}

	public Topic findTopic(Long id) {
		Topic t = topicRepository.findOne(id);
		t.setSizeComments(t.getComments().size());
		t.setSizeVotes(t.getUservoted().size());
		for(User user : t.getUservoted()) {
		if(user.getId()==currentlogged().getId()) {
			t.setVotedByConnectuser(true);
		  }
	    }
		return t;
	}

	
	public Topic upVote(long topicId) {
		Topic topic = topicRepository.findOne(topicId);
		topic.getUservoted().add(currentlogged());
		currentlogged().getTopicsVoted().add(topic);
		return topicRepository.save(topic);
	}

	public Topic unVote(long topicId) {
		Topic topic = topicRepository.findOne(topicId);
		topic.getUservoted().remove(currentlogged());
		currentlogged().getTopicsVoted().remove(topic);
		return topicRepository.save(topic);
	}
	
	public Comment comment(long topicId,Comment comment) {
		Topic topic = topicRepository.findOne(topicId);
		comment.setUser(currentlogged());
		comment.setTopic(topic);
		commentRepository.save(comment);
		return comment;
	}
	
	public Page<Comment> getCommentTopic (long id, int page, int size) {
		return commentRepository.getCommentTopic(id, new PageRequest(page, size));
	}

	
	public Page<Topic> getPopularTopics(int page, int size) {
		Page<Topic> listTopicWithSize = topicRepository.findAllTopicOrderByVote(new PageRequest(page, size));
		for (Topic topic : listTopicWithSize) {
			topic.setSizeComments(topic.getComments().size());
			topic.setSizeVotes(topic.getUservoted().size());
			for(User user : topic.getUservoted())
			if(user.getId()==currentlogged().getId()) {
				topic.setVotedByConnectuser(true);
			}
		}
		return listTopicWithSize;
	}
	
	public Page<Topic> getNewestTopics(int page, int size) {
		Page<Topic> listTopicWithSize = topicRepository.findAllByOrderByDatePublicationDesc(new PageRequest(page, size));
		for (Topic topic : listTopicWithSize) {
			topic.setSizeComments(topic.getComments().size());
			topic.setSizeVotes(topic.getUservoted().size());
			for(User user : topic.getUservoted())
			if(user.getId()==currentlogged().getId()) {
				topic.setVotedByConnectuser(true);
			}
		}
		return listTopicWithSize;
	}
	
   public User currentlogged() {
	   User u= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   return u;
   }

}
