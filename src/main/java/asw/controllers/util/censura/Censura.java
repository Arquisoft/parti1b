package asw.controllers.util.censura;

import java.util.ArrayList;

public class Censura {

	private ArrayList<String> palabrasCensuradas = new ArrayList<String>();

	public Censura() {
		palabrasCensuradas = new ArrayList<String>();
		palabrasCensuradas.add("cabron");
		palabrasCensuradas.add("mierda");
		palabrasCensuradas.add("imbecil");
	}

	/*
	 * Metodo para censurar palabras no permitidas
	 */
	public String censurar(String texto) {
		for (int i = 0; i < palabrasCensuradas.size(); i++) {
			if (texto.contains(palabrasCensuradas.get(i))) {
				texto = texto.replace(palabrasCensuradas.get(i), "****");
				
			}
		}
		return texto;
	}

}
