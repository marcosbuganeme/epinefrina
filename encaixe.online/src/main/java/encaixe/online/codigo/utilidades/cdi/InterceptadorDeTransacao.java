package encaixe.online.codigo.utilidades.cdi;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transacional
@SuppressWarnings("serial")
@Priority(Interceptor.Priority.APPLICATION + 1)
public class InterceptadorDeTransacao implements Serializable {

	@Inject
	private EntityManager entityManager;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {

		EntityTransaction transacao = entityManager.getTransaction();
		
		try {

			if ( !transacao.isActive() ) {
				transacao.begin();
			}

			return context.proceed();
			
		} catch(Exception excecao) {

			if ( transacao != null ) {
				transacao.rollback();
			}

			throw excecao;

		} finally {

			if ( transacao != null && transacao.isActive() ) {
				transacao.commit();
			}
		}
	}
}
