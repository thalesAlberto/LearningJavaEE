package org.casadocodigo.loja.infra;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

public class MessageHelper {

	@Inject
	private FacesContext facesContext;

	public void sendMessage(String message) {
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		facesContext.addMessage(null, new FacesMessage(message));
	}

}
