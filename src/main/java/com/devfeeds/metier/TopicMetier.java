package com.devfeeds.metier;


import org.springframework.data.domain.Page;
import com.devfeeds.entities.Comment;
import com.devfeeds.entities.Topic;

public interface TopicMetier {
	
	public Page<Topic> listTopics(int page, int size);
	public Topic saveTopic(Topic topic);
	public void deleteTopic(Long id);
	public Topic findTopic(Long id);
	public Topic upVote (long topicId);
	public Topic unVote (long topicId);
	public Comment comment(long topicId, Comment comment);
	public Page<Comment> getCommentTopic (long id,int page, int size);
	public Page<Topic> getPopularTopics(int page, int size);
	public Page<Topic> getNewestTopics(int page, int size);
}
