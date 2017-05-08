package asw.DBManagement.services;

import java.util.List;

import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.model.Comment;
import asw.DBManagement.model.VoteComment;


public interface VoteCommentService {

	List<VoteComment> findByComment (Comment comment);
	List<VoteComment> findByCitizenDB(CitizenDB citizen);
	
	VoteComment createVoteComment (VoteComment voteComment);
	void deleteVoteComment (VoteComment voteComment);
	VoteComment findByCitizenDBAndComment(CitizenDB citizen, Comment comment);

}
