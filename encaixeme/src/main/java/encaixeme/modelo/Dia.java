package encaixeme.modelo;

public enum Dia {

	MATUTINO("Matutino - 07:00 ás 11:30"),

	ALMOCO("Almoço - 12:00 ás 13:30"),

	VESPERTINO("Vespertino - 14:00 ás 17:30"),

	NOTURNO("Noturno - 18:00 ás 22:00");

	private String descricao;

	private Dia(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}