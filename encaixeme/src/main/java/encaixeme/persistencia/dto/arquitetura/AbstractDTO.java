package encaixeme.persistencia.dto.arquitetura;

public class AbstractDTO implements DTO<Long> {
	private static final long serialVersionUID = 1L;
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}