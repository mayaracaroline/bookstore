package dominio;

public abstract class Pessoa extends EntidadeDominio {
	 
	protected String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
