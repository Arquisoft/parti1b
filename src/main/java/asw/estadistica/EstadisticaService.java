package asw.estadistica;

import java.util.List;

import asw.dto.model.Comment;
import asw.dto.model.Estadistica;
import asw.dto.model.Suggestion;





public interface EstadisticaService {

	public Estadistica nuevaSugerencia(Suggestion sugerencia);
	
	public List<Estadistica> listaPopularidadSugerencia(List<Suggestion> sugerencia);
	
	public Estadistica nuevoComentario(Comment comentario, Estadistica estsdistica);

}
