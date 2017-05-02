package asw.estadistica.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import asw.dto.model.Comment;
import asw.dto.model.Estadistica;
import asw.dto.model.Suggestion;
import asw.estadistica.EstadisticaService;

@Service("estadisticaService")
public class EstadisticaServiceImpl implements EstadisticaService {

	@Override
	public Estadistica nuevaSugerencia(Suggestion sugerencia) {
		Map<String, Integer> campos = new HashMap<>();
		
		campos.put("Comentarios",0);
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
