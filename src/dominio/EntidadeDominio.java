package dominio;

import java.math.BigInteger;
import java.time.LocalDate;

public class EntidadeDominio {
	
	protected BigInteger id;
	protected LocalDate dataOcorrencia;
	public BigInteger getId() {
		return id;
	}

	public void setId(int id) {
		this.id = new BigInteger(String.valueOf(id));
	}

  public LocalDate getDataOcorrencia() {
    return dataOcorrencia;
  }

  public void setDataOcorrencia(LocalDate dataOcorrencia) {
    this.dataOcorrencia = dataOcorrencia;
  }
	
}
