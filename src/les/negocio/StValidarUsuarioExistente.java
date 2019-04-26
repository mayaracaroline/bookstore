package les.negocio;

import dominio.EntidadeDominio;
import dominio.Usuario;
import les.dao.DAOUsuario;

public class StValidarUsuarioExistente implements IStrategy {

  @Override
  public String processar(EntidadeDominio entidade) {
    
    Usuario usuario = (Usuario) entidade;
    
    DAOUsuario dao = new DAOUsuario();
    boolean usuarioExistente = dao.buscarUsuario(usuario);
    
    if (!usuarioExistente) {
      return "Usu�rio ou senha inv�lidos";
    } 
    
    Usuario usuarioEncontrado = dao.buscarUsuarioEsenha(usuario);
    
    if (!usuarioEncontrado.getPassword().equals(usuario.getPassword())) {
      return "Usu�rio ou senha inv�lidos";
    }
    
    return "";
  }

}
