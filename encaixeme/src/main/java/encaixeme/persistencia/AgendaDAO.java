package encaixeme.persistencia;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import encaixeme.managedbeans.filtro.FiltroDeOfertas;
import encaixeme.modelo.Agenda;
import encaixeme.modelo.Dia;
import encaixeme.modelo.Semana;
import encaixeme.modelo.Status;
import encaixeme.persistencia.arquitetura.HibernateDAO;
import encaixeme.persistencia.dto.AgendaDTO;
import encaixeme.persistencia.dto.AtividadeDTO;
import encaixeme.persistencia.dto.QuantidadeDTO;

@SuppressWarnings("unchecked") 
public class AgendaDAO extends HibernateDAO<Agenda> {

	private static final long serialVersionUID = 1L;

	@Inject	private EntityManager entityManager;

	public void salvar(Agenda agenda) {

		this.entityManager.persist(agenda);
	}

	public List<AgendaDTO> ofertasDesejadas(FiltroDeOfertas filtro) {

		Criteria criteria = getCriteria()
								.createAlias("atividades", "atividades")
								.createAlias("especialidade", "especialidade")
								.createAlias("empresa", "empresa")
								.add(Restrictions.eq("status", Status.ATIVO))
								.add(Restrictions.eq("especialidade.id", filtro.getEspecialidade().getId()))
								.add(Restrictions.ge("atividades.comeca", new Date()));

				if ( filtro.getPlanoSaude() != null ) {

					criteria
						.createAlias("empresa.convenios", "convenios")
						.createAlias("convenios.planoSaude", "planoSaude")
						.add(Restrictions.eq("planoSaude.id", filtro.getPlanoSaude().getId()));
				}

				if ( filtro.getSemana() != null ) {

					criteria
						.add(Restrictions.eq("atividades.semana", filtro.getSemana()));
				} else {

					criteria
						.add(Restrictions.in("atividades.semana", Arrays.asList(Semana.values())));
				}

				if ( filtro.getPeriodo() != null ) {

					criteria
						.add(Restrictions.eq("atividades.periodo", filtro.getPeriodo()));
				} else {

					criteria
						.add(Restrictions.in("atividades.periodo", Arrays.asList(Dia.values())));
				}

		return criteria
					.setProjection(Projections.projectionList()
													.add(Projections.property("id"), "id")
													.add(Projections.property("atividades.semana"), "semana")
													.add(Projections.property("atividades.periodo"), "periodo")
													.add(Projections.property("atividades.comeca"), "horario")
													.add(Projections.property("atividades.resultado"), "resultado")
													.add(Projections.property("valor"), "valor")
													.add(Projections.property("titulo"), "titulo")
													.add(Projections.property("empresa.cnpj"), "cnpj")
													.add(Projections.property("empresa.id"), "idEmpresa")
													.add(Projections.property("empresa.razaoSocial"), "razaoSocial"))
					.setResultTransformer(Transformers.aliasToBean(AgendaDTO.class))
					.addOrder(Order.asc("atividades.comeca"))
					.addOrder(Order.asc("criado"))
					.list();
	}

	public List<AtividadeDTO> agendasDaSecretaria(Long idUsuario) {

		return getCriteria()
					.createAlias("especialidade", "especialidade")
					.createAlias("especialidade.categoria", "categoria")
					.createAlias("empresa", "empresa")
					.createAlias("agenda.permissoes", "permissoes")
					.createAlias("permissoes.secretaria", "secretaria")
					.createAlias("secretaria.usuario", "usuario")
					.add(Restrictions.eq("usuario.id", idUsuario))
					.add(Restrictions.eq("status", Status.ATIVO))
					.setProjection(Projections.projectionList()
													.add(Projections.property("id"), "id")
													.add(Projections.property("agenda.id"), "idAgenda")
													.add(Projections.property("agenda.titulo"), "titulo")
													.add(Projections.property("categoria.nome"), "categoria")
													.add(Projections.property("especialidade.nome"), "especialidade")
													.add(Projections.property("empresa.razaoSocial"), "razaoSocial")
													.add(Projections.rowCount(), "quantidadeConcluida")
													.add(Projections.groupProperty("especialidade.nome")))
					.setResultTransformer(Transformers.aliasToBean(AtividadeDTO.class))
					.list();
	}

	public List<AtividadeDTO> agendasDaEmpresa(Long idUsuario) {

		return getCriteria()
					.createAlias("especialidade", "especialidade")
					.createAlias("especialidade.categoria", "categoria")
					.createAlias("empresa", "empresa")
					.add(Restrictions.eq("status", Status.ATIVO))
					.setProjection(Projections.projectionList()
													.add(Projections.property("id"), "id")
													.add(Projections.property("titulo"), "titulo")
													.add(Projections.property("categoria.nome"), "categoria")
													.add(Projections.property("especialidade.nome"), "especialidade")
													.add(Projections.property("empresa.id"), "idEmpresa")
													.add(Projections.groupProperty("especialidade.nome")))
					.setResultTransformer(Transformers.aliasToBean(AtividadeDTO.class))
					.list();
	}

	public QuantidadeDTO contadorAtivas(Long idAgenda) {

		return (QuantidadeDTO) getCriteria()
									.createAlias("atividades", "atividades")
									.createAlias("especialidade", "especialidade")
									.add(Restrictions.eq("id", idAgenda))
									.add(Restrictions.eq("status", Status.ATIVO))
									.add(Restrictions.eq("atividades.status", Status.ATIVO))
									.setProjection(Projections.projectionList()
																.add(Projections.rowCount(), "disponivel")
																.add(Projections.groupProperty("especialidade.nome")))
									.setResultTransformer(Transformers.aliasToBean(QuantidadeDTO.class))
									.uniqueResult();
	}

	public QuantidadeDTO contadorConcluidas(Long idAgenda) {

		return (QuantidadeDTO) getCriteria()
									.createAlias("atividades", "atividades")
									.createAlias("especialidade", "especialidade")
									.add(Restrictions.eq("id", idAgenda))
									.add(Restrictions.eq("status", Status.ATIVO))
									.add(Restrictions.eq("atividades.status", Status.INATIVO))
									.setProjection(Projections.projectionList()
																.add(Projections.rowCount(), "concluida")
																.add(Projections.groupProperty("especialidade.nome")))
									.setResultTransformer(Transformers.aliasToBean(QuantidadeDTO.class))
									.uniqueResult();
	}

	public AgendaDTO porEspecialidade(Long idEspecialidade) {

		return (AgendaDTO) getCriteria()
								.createAlias("especialidade", "especialidade")
								.add(Restrictions.eq("status", Status.ATIVO))
								.add(Restrictions.eq("especialidade.id", idEspecialidade))
								.setProjection(Projections.projectionList()
																	.add(Projections.property("id"), "id")
																	.add(Projections.property("especialidade.nome"), "especialidade"))
								.setResultTransformer(Transformers.aliasToBean(AgendaDTO.class))
								.uniqueResult();
	}

	public List<AgendaDTO> porTitulo(Long idUsuario, String titulo) {

		return getCriteria()
					.createAlias("empresa", "empresa")
					.createAlias("empresa.usuario", "usuario")
					.add(Restrictions.eq("usuario.id", idUsuario))
					.add(Restrictions.ilike("titulo", titulo, MatchMode.ANYWHERE))
					.setProjection(Projections.projectionList()
							.add(Projections.property("id"), "id")
							.add(Projections.property("titulo"), "titulo"))
					.setResultTransformer(Transformers.aliasToBean(AgendaDTO.class))
					.setCacheable(true)
					.addOrder(Order.asc("titulo"))
					.list();
	}

	@Override 
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}