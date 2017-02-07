package encaixeme.utilitarios.cdi;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@SuppressWarnings("unchecked")
public class CDIServiceLocator {

	private static BeanManager getBeanManager() {
		try {
			return (BeanManager) new InitialContext().lookup("java:comp/env/BeanManager");
		} catch (NamingException exception) {
			throw new RuntimeException("Não pôde encontrar BeanManager na árvore JNDI");
		}
	}

	/**
	 * Método responsável por obter uma referência do contexto de um determinado
	 * bean<br>
	 * e o bean di tipo do bean através de uma classe parametrizada
	 * 
	 * @param classe
	 * @return <i>objeto</i>
	 */
	public static <T> T getBean(Class<T> classe) {
		BeanManager manager = getBeanManager();
		Set<Bean<?>> beans = (Set<Bean<?>>) manager.getBeans(classe);
		if (beans == null || beans.isEmpty()) {
			return null;
		}
		Bean<T> bean = (Bean<T>) beans.iterator().next();
		CreationalContext<T> contexto = manager.createCreationalContext(bean);
		T referencia = (T) manager.getReference(bean, classe, contexto);
		return referencia;
	}
}
