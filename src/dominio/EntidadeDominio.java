package dominio;

import java.math.BigInteger;

public class EntidadeDominio {
	
	protected BigInteger id;

	public BigInteger getId() {
		return id;
	}

	public void setId(int id) {
		this.id = new BigInteger(String.valueOf(id));
	}
	
}
