package encaixeme.managedbeans.formulario.arquitetura;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import encaixeme.modelo.arquitetura.Entidade;

/**
 * Classe abstrata pai de todas as classes de formulários e responsável por abstrair os campos dos formulários
 * 
 * @author Marcos Olavo S. Buganeme
 * @param <E>
 *            - Entidade concreta
 */

@SuppressWarnings("unchecked")
public abstract class Formulario<E extends Entidade> implements Serializable {

	private static final long serialVersionUID = 1L;

	private E entidade;
	private List<E> entidades;

	public <F> Formulario() {
		try {
			this.entidade = getTipoEntidade().newInstance();
		} catch ( InstantiationException | IllegalAccessException exception ) {
			System.out.println("Deu erro mermão !! Construtor [1]");
		}
	}

	public Formulario( Class<E> classe ) {
		try {
			this.entidade = classe.newInstance();
		} catch ( InstantiationException | IllegalAccessException exception ) {
			System.out.println("Deu erro mermão !! Construtor [2]");
		}
	}

	/**
	 * @return <i>O tipo da classe genérica declara no escopo deste bean de formulário<br>
	 * 			Este método é executado em tempo de execução</i>
	 */
	private Class<E> getTipoEntidade() {
		final Type[] tipoEntidade = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments();
		return (Class<E>) tipoEntidade[0];
	}

	public E getEntidade() {
		return entidade;
	}

	public void setEntidade(E entidade) {
		this.entidade = entidade;
	}

	public Collection<E> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<E> entidades) {
		this.entidades.clear();
		this.entidades = entidades;
	}
}