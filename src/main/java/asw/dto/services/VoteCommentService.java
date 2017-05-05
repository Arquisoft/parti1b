package asw.dto.services;

import java.util.List;

import asw.dto.model.CitizenDB;
import asw.dto.model.Comment;
import asw.dto.model.VoteComment;
import asw.dto.model.key.VoteCommentKey;


public interface VoteCommentService {

	List<VoteComment> findByComment (Comment comment);
	List<VoteComment> findByCitizenDB(CitizenDB citizen);
	//VoteComment findByVoteCommentKey(VoteCommentKey voteCommentKey);
	
	VoteComment createVoteComment (VoteComment voteComment);
	void deleteVoteComment (VoteComment voteComment);
}
