package org.wpattern.frameworks.websocket.beans;

import org.wpattern.frameworks.spring.mvc.model.ContactBean;
import org.wpattern.frameworks.spring.mvc.utils.BaseBean;

public class ContactWSBean extends BaseBean {

	private static final long serialVersionUID = 201410272153L;

	private final ContactBean contact;

	private final String action;

	public ContactWSBean(ContactBean contact, String action) {
		this.contact = contact;
		this.action = action;
	}

	public ContactBean getContact() {
		return this.contact;
	}

	public String getAction() {
		return this.action;
	}

}
