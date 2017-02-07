package encaixeme.persistencia;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import encaixeme.modelo.Assistido;
import encaixeme.modelo.Empresa;
import encaixeme.modelo.Secretaria;
import encaixeme.modelo.Usuario;
import encaixeme.persistencia.arquitetura.HibernateDAO;
import encaixeme.persistencia.dto.UsuarioDTO;

@SuppressWarnings("unchecked")
public class UsuarioDAO extends HibernateDAO<Usuario> {

	private static final long serialVersionUID = 1L;

	@Inject private EntityManager entityManager;

	public Empresa empresaPorUsuario(Long idUsuario) {
		return (Empresa) getSession()
							.createCriteria(Empresa.class)
							.createAlias("usuario", "usuario")
							.add(Restrictions.eq("usuario.id", idUsuario))
							.uniqueResult();
	}

	public void salvar(Empresa empresa) {
		entityManager.persist(empresa);
	}

	public void salvar(Secretaria secretaria) {
		entityManager.persist(secretaria);
	}

	public void salvar(Assistido assistido) {
		this.entityManager.persist(assistido);
	}

	public Empresa alterar(Empresa empresa) {
		return this.entityManager.merge(empresa);
	}
	
	public List<Secretaria> todasSecretariasAtivas() {
		return getSession()
					.createCriteria(Secretaria.class)
					.createAlias("usuario", "usuario")
					.setCacheable(true)
					.list();
	}

	public boolean isEmailExiste(String email) {
		return (UsuarioDTO) getCriteria()
								.add(Restrictions.eq("email", email))
								.setProjection(Projections.projectionList()
										.add(Projections.property("id"), "id")
										.add(Projections.property("email"), "email"))
								.setResultTransformer(Transformers.aliasToBean(UsuarioDTO.class))
								.uniqueResult() != null;
	}

	public boolean isLoginExiste(String login) {
		return (UsuarioDTO) getCriteria()
								.add(Restrictions.eq("login", login))
								.setProjection(Projections.projectionList()
										.add(Projections.property("id"), "id")
										.add(Projections.property("login"), "login"))
								.setResultTransformer(Transformers.aliasToBean(UsuarioDTO.class))
								.uniqueResult() != null;
	}

	public UsuarioDTO porLogin(String login) {
		return (UsuarioDTO) getCriteria()
								.add(Restrictions.eq("login", login))
								.setProjection(Projections.projectionList()
										.add(Projections.property("id"), "id")
										.add(Projections.property("email"), "email")
										.add(Projections.property("login"), "login")
										.add(Projections.property("senha"), "senha")
										.add(Projections.property("permissao"), "permissao"))
								.setResultTransformer(Transformers.aliasToBean(UsuarioDTO.class))
								.uniqueResult();
	}

	public Usuario porEmailELogin(String email, String login) {
		return (Usuario) getCriteria()
							.add(Restrictions.eq("email", email))
							.add(Restrictions.eq("login", login))
							.uniqueResult();
	}

	public Empresa empresaPorEmailELogin(String email, String login) {
		return (Empresa) getSession()
							.createCriteria(Empresa.class)
							.createAlias("usuario", "usuario")
							.add(Restrictions.eq("usuario.email", email))
							.add(Restrictions.eq("usuario.login", login))
							.uniqueResult();
	}
	
	@Override protected EntityManager getEntityManager() {
		return entityManager;
	}
}