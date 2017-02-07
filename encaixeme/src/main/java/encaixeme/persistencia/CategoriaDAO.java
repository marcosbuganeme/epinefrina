package encaixeme.persistencia;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import encaixeme.modelo.Categoria;
import encaixeme.modelo.CorpoHumano;
import encaixeme.modelo.Especialidade;
import encaixeme.persistencia.arquitetura.HibernateDAO;
import encaixeme.persistencia.dto.EspecialidadeDTO;

@SuppressWarnings("unchecked") 
public class CategoriaDAO extends HibernateDAO<Categoria> {

	private static final long serialVersionUID = 1L;

	@Inject private EntityManager entityManager;

	public List<Categoria> todos() {

		return getCriteria()
					.setCacheable(true)
					.list(); 
	}

	public List<EspecialidadeDTO> especialidadesRelacionadas(Long idCategoria) {

		return getSession()
					.createCriteria(Especialidade.class)
					.createAlias("categoria", "categoria")
					.add(Restrictions.eq("categoria.id", idCategoria))
					.setProjection(Projections.projectionList()
													.add(Projections.property("id"), "id")
													.add(Projections.property("nome"), "nome"))
					.setResultTransformer(Transformers.aliasToBean(EspecialidadeDTO.class))
					.setCacheable(true)
					.list();
	}

	public List<Especialidade> especialidadesPorCategoria(Long idCategoria) {

		return getSession()
					.createCriteria(Especialidade.class)
					.createAlias("categoria", "categoria")
					.add(Restrictions.eq("categoria.id", idCategoria))
					.setCacheable(true)
					.list();
	}

	public List<CorpoHumano> corposRelacionadosAoExame(Long idEspecialidade) {

		return getSession()
					.createCriteria(CorpoHumano.class)
					.createAlias("requisitos", "requisitos")
					.createAlias("requisitos.especialidade", "especialidade")
					.add(Restrictions.eq("especialidade.id", idEspecialidade))
					.setCacheable(true)
					.list();
	}

	@Override 
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}