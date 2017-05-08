package asw.controllers.util.estadistica;

import java.util.List;

import asw.DBManagement.model.Comment;
import asw.DBManagement.model.Suggestion;

public interface EstadisticaService {

	public Estadistica nuevaSugerencia(Suggestion sugerencia);
	
	public List<Estadistica> listaPopularidadSugerencia(List<Suggestion> sugerencia);
	
	public Estadistica nuevoComentario(Comment comentario, Estadistica estsdistica);

}
