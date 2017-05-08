package asw.controllers.util.estadistica.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import asw.DBManagement.model.Comment;
import asw.DBManagement.model.Suggestion;
import asw.controllers.util.estadistica.Estadistica;
import asw.controllers.util.estadistica.EstadisticaService;

@Service("estadisticaService")
public class EstadisticaServiceImpl implements EstadisticaService {

	@Override
	public Estadistica nuevaSugerencia(Suggestion sugerencia) {
		Map<String, Integer> campos = new HashMap<>();
		
		campos.put("Comentarios",sugerencia.getComments().size());
		campos.put("Apoyos",sugerencia.getNum_votes()*1);
		
		return new Estadistica(sugerencia.getTitle(),campos);
	}
	
	
	

	@Override
	public List<Estadistica> listaPopularidadSugerencia(List<Suggestion> sugerencia) {
		
		List<Estadistica> devuelto = new ArrayList<Estadistica>();
		
		for(Suggestion su : sugerencia)
		{
			devuelto.add(this.nuevaSugerencia(su));
		}
		
		return devuelto;
	}




	@Override
	public Estadistica nuevoComentario(Comment comentario, Estadistica estadistica) {
		
		estadistica.getCampos().put("Comentarios", estadistica.getCampos().get("Comentarios")+1);
		
		return estadistica;
	}

}
