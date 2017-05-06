package asw.dto.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="sugerencia")
public class Suggestion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@OneToMany(mappedBy = "suggestion",fetch = FetchType.EAGER)
	private List<Comment> comments  = new ArrayList<Comment>();
	private int num_votes; // quizás estaría bien que la consulta cargara su número de votos de la BD en primera instancia
						   // y de cara al rendimiento los mantuviera en esta variable (hablarlo)
	@Temporal(TemporalType.TIMESTAMP)
	private Date suggestion_date;
	
	//private boolean aprobacion; ??
	@ManyToOne
	private CitizenDB citizenDB;
	
	private String content;  //contenido de la sugerencia (el cuerpo de la misma...)
	
	@OneToMany(mappedBy = "suggestion",fetch = FetchType.EAGER)
	private List<VoteSuggestion> voteSuggestions  = new ArrayList<VoteSuggestion>();
	
	public Suggestion() {
	}
	
	public Suggestion(Long id,String title ,CitizenDB citizenDB){
		Association.Sugerir.link(citizenDB, this);
		this.title = title;
		this.id = id;
		this.num_votes = 0;
		this.suggestion_date = Calendar.getInstance().getTime();
	}

	public CitizenDB getCitizenDB() {
		return citizenDB;
	}

	public void setCitizenDB(CitizenDB citizenDB) {
		this.citizenDB = citizenDB;
	}
	
    void _setCitizenDB(CitizenDB citizenDB) {
		this.citizenDB = citizenDB;
	}

	public List<VoteSuggestion> getVoteSuggestions() {
		return new ArrayList<>(voteSuggestions);
	}
	
	List<VoteSuggestion> _getVoteSuggestions() {
		return voteSuggestions;
	}


	public void setVoteSuggestions(List<VoteSuggestion> voteSuggestions) {
		this.voteSuggestions = voteSuggestions;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Comment> getComments() {
		return new ArrayList<Comment>(comments);
	}
	
	List<Comment> _getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public int getNum_votes() {
		return num_votes;
	}

	public void setNum_votes(int num_votes) {
		this.num_votes = num_votes;
	}

	public Date getSuggestion_date() {
		return suggestion_date;
	}

	public void setSuggestion_date(Date suggestion_date) {
		this.suggestion_date = suggestion_date;
	}
	
	public Long getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Suggestion other = (Suggestion) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	//public void saveSuggestion(){
		//bd.insert_suggestion()...
	//}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
