package encaixeme.persistencia;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import encaixeme.managedbeans.filtro.FiltroDeAgendamentos;
import encaixeme.modelo.Agendamento;
import encaixeme.persistencia.arquitetura.HibernateDAO;
import encaixeme.persistencia.dto.AgendamentoDTO;

@SuppressWarnings("unchecked") 
public class AgendamentoDAO extends HibernateDAO<Agendamento> {
	private static final long serialVersionUID = 1L;
	@Inject private EntityManager entityManager;

	public List<AgendamentoDTO> todosAgendamentos(FiltroDeAgendamentos filtro) {
		Criteria criteria = getCriteria();
		addRestricoes(filtro, criteria);
		return criteria
					.setProjection(Projections.projectionList()
									.add(Projections.property("id"), "id")
									.add(Projections.property("categoria.nome"), "categoria")
									.add(Projections.property("especialidade.nome"), "especialidade")
									.add(Projections.property("agenda.valor"), "valor")
									.add(Projections.property("agenda.titulo"), "titulo")
									.add(Projections.property("agenda.subTitulo"), "subTitulo")
									.add(Projections.property("agenda.resultado"), "resultado")
									.add(Projections.property("periodo.nome"), "periodo")
									.add(Projections.property("semana.nome"), "semana")
									.add(Projections.property("cidade.nome"), "cidade")
									.add(Projections.property("endereco.cep"), "cep")
									.add(Projections.property("endereco.numero"), "numero")
									.add(Projections.property("endereco.bairro"), "bairro")
									.add(Projections.property("endereco.logradouro"), "logradouro")
									.add(Projections.property("endereco.complemento"), "complemento")
									.add(Projections.property("usuario.nome"), "nomeFantasia")
									.add(Projections.property("usuario.login"), "cnpj")
									.add(Projections.property("empresa.descricao"), "descricao")
									.add(Projections.property("empresa.razaoSocial"), "razaoSocial")
									.add(Projections.property("empresa.telefonePrincipal"), "telefoneSecundario")
									.add(Projections.property("empresa.telefoneSecundario"), "telefonePrincipal"))
					.setResultTransformer(Transformers.aliasToBean(AgendamentoDTO.class))
					.list();
	}

	private void addRestricoes(FiltroDeAgendamentos filtro, Criteria criteria) {
		criteria
			.createAlias("assistido", "assistido")
			.createAlias("assistido.usuario", "usuario")
			.createAlias("atividade", "atividade")
			.createAlias("atividade.agenda", "agenda")
			.createAlias("atividade.vacancia", "vacancia")
			.createAlias("vacancia.periodo", "periodo")
			.createAlias("vacancia.semana", "semana")
			.createAlias("agenda.especialidade", "especialidade")
			.createAlias("especialidade.categoria", "categoria")
			.createAlias("agenda.empresa", "empresa")
			.createAlias("empresa.endereco", "endereco")
			.createAlias("endereco.cidade", "cidade")
			.add(Restrictions.eq("usuario.login", filtro.getLogin()));

		Disjunction or = Restrictions.disjunction();

		if ( filtro.getSemana() != null ) {
			or.add(Restrictions.eq("semana.nome", filtro.getSemana()));
		}

		if ( filtro.getPeriodo() != null ) {
			or.add(Restrictions.eq("periodo.nome", filtro.getPeriodo()));
		}

		criteria.add(or);
	}

	@Override protected EntityManager getEntityManager() {
		return entityManager;
	}
}