package dominio;

import java.time.LocalDate;
import java.util.Date;

public class PessoaFisica extends Pessoa {
	protected String cpf;
	protected Genero genero;
	protected LocalDate dataNascimento ;
	protected String sobrenome;
	
  public String getCpf() {
    return cpf;
  }
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
  public Genero getGenero() {
    return genero;
  }
  public void setGenero(Genero genero) {
    this.genero = genero;
  }
  public LocalDate getDataNascimento() {
    return dataNascimento;
  }
  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
  }
  public String getSobrenome() {
    return sobrenome;
  }
  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

}
