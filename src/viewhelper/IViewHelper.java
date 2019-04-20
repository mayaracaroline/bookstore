package viewhelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.EntidadeDominio;
import util.Resultado;

public interface IViewHelper {
	
	public EntidadeDominio getEntidade(HttpServletRequest request);
	
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response);

}
