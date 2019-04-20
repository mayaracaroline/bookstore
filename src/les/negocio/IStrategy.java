package les.negocio;

import javax.servlet.http.HttpServletRequest;

import dominio.EntidadeDominio;

public interface IStrategy {

	String processar(EntidadeDominio entidade);
	
	
	
}
