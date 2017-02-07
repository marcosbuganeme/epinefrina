package encaixeme.modelo;

public enum Semana {

	SEGUNDA("Segunda-feira"),

	TERCA("Terça-feira"),

	QUARTA("Quarta-feira"),

	QUINTA("Quinta-feira"),

	SEXTA("Sexta-feira"),

	SABADO("Sábado"),

	DOMINGO("Domingo");

	private String descricao;

	private Semana(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}