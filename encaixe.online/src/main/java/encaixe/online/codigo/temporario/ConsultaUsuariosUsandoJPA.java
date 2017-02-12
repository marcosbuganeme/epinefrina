package encaixe.online.codigo.temporario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class ConsultaUsuariosUsandoJPA {

	public static void main(String[] args) {
		EntityManagerFactory emf = JPAUtil.createEntityManager().getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		String documento = "03287476106";

		String jpql = "select e.cnpj from Estabelecimento e where e.cnpj = '" + documento + "'";

		TypedQuery<String> query = em.createQuery(jpql, String.class);
		List<String> cnpjs = query.getResultList();

		for (String cnpj : cnpjs) {
			System.out.println("Número de cnpj: " + cnpj);
		}

		em.close();
	}
}