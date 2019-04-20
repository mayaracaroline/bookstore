package les.command;

import fachada.Fachada;
import fachada.IFachada;

public abstract class AbstractCommand  implements ICommand{
	
	protected IFachada fachada = new Fachada();

}
