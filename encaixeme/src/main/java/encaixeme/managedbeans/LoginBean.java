package encaixeme.managedbeans;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import encaixeme.utilitarios.jsf.MessageHelper;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;

	@Inject	private MessageHelper helper;
	@Inject	private FacesContext context;
	@Inject	private HttpServletRequest request;
	@Inject	private HttpServletResponse response;

	public void preRender() {

		if ( "true".equals(request.getParameter("invalid")) ) {

			helper.error("Usuário e/ou senha inválidos!", null);
		}
	}

	public void autenticar() throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);
		context.responseComplete();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}