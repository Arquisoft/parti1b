package asw.DBManagement.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.model.Comment;
import asw.DBManagement.model.VoteComment;
import asw.DBManagement.repository.VoteCommentRepository;
import asw.DBManagement.services.VoteCommentService;

public class VoteCommentServiceImpl implements VoteCommentService {

	@Autowired
	private VoteCommentRepository voteCommentRepository;

	@Override
	public List<VoteComment> findByComment(Comment comment) {
		return voteCommentRepository.findByCommentEquals(comment);
	}

	@Override
	public List<VoteComment> findByCitizenDB(CitizenDB citizen) {
		return voteCommentRepository.findByCitizenDB(citizen);
	}

	//@Override
	//public VoteComment findByVoteCommentKey(VoteCommentKey voteCommentKey) {
	//	return voteCommentRepository.findByVoteCommentKey(voteCommentKey);
	//}

	@Override
	public VoteComment createVoteComment(VoteComment voteComment) {
		return voteCommentRepository.save(voteComment);
	}

	@Override
	public void deleteVoteComment(VoteComment voteComment) {
		voteCommentRepository.delete(voteComment);
		
	}

}
