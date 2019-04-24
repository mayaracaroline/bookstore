package dominio;

public class Produto extends EntidadeDominio {
	protected String codigoBarras;
	protected int categoriaAtivacao;
	protected int categoriaInativacao;
	protected String justificativaAtivacao;
	protected String justificativaInativacao;
	protected Double preco;
	protected boolean ativo;
	protected String imagePath;
	private double altura;
  private double largura;
  private double peso;
  private double profundidade;
	
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public int getCategoriaAtivacao() {
		return categoriaAtivacao;
	}
	public void setCategoriaAtivacao(int categoriaAtivacao) {
		this.categoriaAtivacao = categoriaAtivacao;
	}
	public int getCategoriaInativacao() {
		return categoriaInativacao;
	}
	public void setCategoriaInativacao(int categoriaInativacao) {
		this.categoriaInativacao = categoriaInativacao;
	}
	public String getJustificativaAtivacao() {
		return justificativaAtivacao;
	}
	public void setJustificativaAtivacao(String justificativaAtivacao) {
		this.justificativaAtivacao = justificativaAtivacao;
	}
	public String getJustificativaInativacao() {
		return justificativaInativacao;
	}
	public void setJustificativaInativacao(String justificativaInativacao) {
		this.justificativaInativacao = justificativaInativacao;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
  public Double getPreco() {
    return preco;
  }
  public void setPreco(Double preco) {
    this.preco = preco;
  }
  public String getImagePath() {
    return imagePath;
  }
  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }	
  
  public double getAltura() {
    return altura;
  }
  public void setAltura(double altura) {
    this.altura = altura;
  }
  public double getLargura() {
    return largura;
  }
  public void setLargura(double largura) {
    this.largura = largura;
  }
  public double getPeso() {
    return peso;
  }
  public void setPeso(double peso) {
    this.peso = peso;
  }
  public double getProfundidade() {
    return profundidade;
  }
  public void setProfundidade(double profundidade) {
    this.profundidade = profundidade;
  }
		
}
