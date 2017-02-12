package encaixe.online.codigo.temporario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("encaixeonlinePU");

	public static EntityManager createEntityManager() {
		return emf.createEntityManager();
	}
}
