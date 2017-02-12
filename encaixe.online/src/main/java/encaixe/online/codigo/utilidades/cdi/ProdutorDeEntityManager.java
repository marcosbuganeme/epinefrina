package encaixe.online.codigo.utilidades.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class ProdutorDeEntityManager {

	private EntityManagerFactory factoryEntityManager = null;

	public ProdutorDeEntityManager() {
		factoryEntityManager = Persistence.createEntityManagerFactory("encaixeonlinePU");
	}

	@Produces
	@RequestScoped
	public EntityManager criaConexao() {
		System.out.println("EntityManager criado");
		return factoryEntityManager.createEntityManager();
	}

	public void fechaConexao(@Disposes final EntityManager entityManager) {
		entityManager.close();
		System.out.println("EntityManager fechado");
	}
}