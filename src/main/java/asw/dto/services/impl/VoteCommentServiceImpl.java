package asw.dto.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import asw.dto.model.CitizenDB;
import asw.dto.model.Comment;
import asw.dto.model.VoteComment;
import asw.dto.model.key.VoteCommentKey;
import asw.dto.repository.VoteCommentRepository;
import asw.dto.services.VoteCommentService;

public class VoteCommentServiceImpl implements VoteCommentService {

	@Autowired
	private VoteCommentRepository voteCommentRepository;

	@Override
	public List<VoteComment> findByComment(Comment comment) {
		return voteCommentRepository.findByComment(comment);
	}

	@Override
	public List<VoteComment> findByCitizenDB(CitizenDB citizen) {
		return voteCommentRepository.findByCitizenDB(citizen);
	}

	@Override
	public VoteComment findByVoteCommentKey(VoteCommentKey voteCommentKey) {
		return voteCommentRepository.findByVoteCommentKey(voteCommentKey);
	}

	@Override
	public VoteComment createVoteComment(VoteComment voteComment) {
		return voteCommentRepository.save(voteComment);
	}

	@Override
	public void deleteVoteComment(VoteComment voteComment) {
		voteCommentRepository.delete(voteComment);
		
	}

}
