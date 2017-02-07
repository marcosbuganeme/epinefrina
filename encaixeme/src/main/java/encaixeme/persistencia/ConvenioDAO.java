package encaixeme.persistencia;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import encaixeme.modelo.ConvenioEmpresa;
import encaixeme.persistencia.arquitetura.HibernateDAO;
import encaixeme.persistencia.dto.ConvenioDTO;

@SuppressWarnings("unchecked")
public class ConvenioDAO extends HibernateDAO<ConvenioEmpresa> {
	private static final long serialVersionUID = 1L;
	@Inject	private EntityManager entityManager;

	public List<ConvenioDTO> porAgenda(Long idEmpresa) {
		return getCriteria()
					.createAlias("empresa", "empresa")
					.createAlias("planoSaude", "planoSaude")
					.add(Restrictions.eq("empresa.id", idEmpresa))
					.setProjection(Projections.projectionList()
													.add(Projections.property("planoSaude.id"), "id")
													.add(Projections.property("planoSaude.nome"), "nome"))
					.setResultTransformer(Transformers.aliasToBean(ConvenioDTO.class))
					.list();
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}