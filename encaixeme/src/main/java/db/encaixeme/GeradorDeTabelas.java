package db.encaixeme;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeradorDeTabelas {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("encaixemePU");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.close();
	}
}