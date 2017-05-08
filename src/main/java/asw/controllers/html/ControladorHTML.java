package asw.controllers.html;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.model.Suggestion;
import asw.DBManagement.services.CitizenDBService;
import asw.DBManagement.services.SuggestionService;
import asw.controllers.util.estadistica.Estadistica;
import asw.controllers.util.estadistica.EstadisticaService;

@Scope("session")
@Controller
public class ControladorHTML {



	@Autowired
	private CitizenDBService citizenDBService;

	@Autowired
	private SuggestionService suggestionService;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHTML(Model modelo){
		return "login";
	}


	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String postHTML(HttpSession session,@RequestBody String parametros, Model modelo){
		//parametros = email=nombre&password=contraseña
		String[] p = parametros.split("&");

		//Usuario en blanco
		if(p[0].length() <= 8){
			modelo.addAttribute("error", "Usuario en blanco.");
			return "error";
		}

		//Contraseña en blanco
		if(p[1].length() <= 9){
			modelo.addAttribute("error", "Contraseña en blanco.");
			return "error";
		}

		String email = p[0].split("=")[1];
		email = email.replace("%40", "@");
		String password = p[1].split("=")[1];

		//Comprobar los datos

		try{
			CitizenDB ciudadano = citizenDBService.findByMail(email);
			if (ciudadano!= null)
			{
				if(!ciudadano.getMail().equals(email))
				{
					modelo.addAttribute("error", "Email no coincide.");
					return "error";
				}

				if(!ciudadano.getPassword().equals(password))
				{
					modelo.addAttribute("error", "La contraseña no coincide con la del usuario.");
					return "error";
				}

				if(ciudadano != null){
					session.setAttribute("usuario", ciudadano);
					session.setAttribute("sugerencias",suggestionService.findAll());

					if(ciudadano.getType().equals("POLITICO"))
						return this.popularidadSugerencia(parametros, modelo);

					if(ciudadano.getType().equals("ADMIN"))
						return "Admin/home";
					else
						return "User/homeUsuario";
				}
			}

			modelo.addAttribute("error", "Usuario no registrado.");
			return "error";

		}catch(Exception e){
			modelo.addAttribute("error", "Ha ocurrido en error al conseguir los datos del usuario.");
			return "error";
		}
	}

	@Autowired
	private EstadisticaService estatService;



	public List<Estadistica> popularidadSugerencia(List<Suggestion> sugerencia) {
		return estatService.listaPopularidadSugerencia(sugerencia);
	}


	@RequestMapping(path="/userPriv", method=RequestMethod.GET)
	public String popularidadSugerencia(@RequestBody String parametros, Model modelo) {
		List<Suggestion> sugerencias = suggestionService.findAll();
		List<Estadistica> estadisticas = estatService.listaPopularidadSugerencia(sugerencias);
		modelo.addAttribute("estadisticas",estadisticas);
		return "userPriv";
	}

	@RequestMapping(value="/cerrarSesion")
    public String logOut( HttpSession session){
     
		session.setAttribute("usuario", null);  		
		return "/login";
	}

}
