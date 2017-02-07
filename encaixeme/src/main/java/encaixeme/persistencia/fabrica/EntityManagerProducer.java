package encaixeme.persistencia.fabrica;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe responsável por criar instâncias de <code>EntityManager</code>
 * 
 * @author Marcos Olavo S. Buganeme
 */

@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory;

	public EntityManagerProducer() {
		factory = Persistence.createEntityManagerFactory("encaixePU");
	}

	@Produces
	@RequestScoped
	public EntityManager cria() {
		return factory.createEntityManager();
	}

	public void fecha(@Disposes final EntityManager manager) {
		manager.close();
	}
}
