package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Livro extends Produto implements Serializable {
	
	/**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String autor;
	private int ano;
	private String titulo;
	private String edicao;
	private String editora;
	private String isbn;
	private String sinopse;
	private ArrayList<GeneroLiterario> categorias = new ArrayList<>();
	private int quantidadePaginas;
	private double altura;
	private double largura;
	private double peso;
	private double profundidade;
	
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public int getQuantidadePaginas() {
		return quantidadePaginas;
	}
	public void setQuantidadePaginas(int quantidadePaginas) {
		this.quantidadePaginas = quantidadePaginas;
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
	public String getEdicao() {
		return edicao;
	}
	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public List<GeneroLiterario> getCategorias() {
		return categorias;
	}
	public void setCategorias(ArrayList<GeneroLiterario> categorias) {
		this.categorias = categorias;
	}


}
