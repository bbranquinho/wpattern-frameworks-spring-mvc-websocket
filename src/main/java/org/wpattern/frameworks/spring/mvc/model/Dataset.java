package org.wpattern.frameworks.spring.mvc.model;

import java.util.ArrayList;
import java.util.List;

public class Dataset {

	private static List<ContactBean> contacts = new ArrayList<ContactBean>();

	private static int id = 0;

	private Dataset() {
	}

	static {
		contacts.add(new ContactBean(id++, "Augusto", "Branquinho", "augusto@gmail.com", "34-9999-1111"));
		contacts.add(new ContactBean(id++, "Faculdade", "Politecnica", "faculdade@fpu.com", "34-9999-2222"));
		contacts.add(new ContactBean(id++, "Jean", "Carlo", "jean@gmail.com", "34-8899-1199"));
		contacts.add(new ContactBean(id++, "Reginaldo", "Mendes", "reginaldo@yahoo.com", "34-9988-3344"));
	}

	public static List<ContactBean> getContacts() {
		return contacts;
	}

	public static void setContacts(List<ContactBean> contacts) {
		Dataset.contacts = contacts;
	}

	public static void addContact(ContactBean contact) {
		contact.setId(id++);
		contacts.add(contact);
	}

	public static void updateContact(ContactBean contact) {
		for (int i = 0; i < contacts.size(); i++) {
			if (contacts.get(i).getId() == contact.getId()) {
				contacts.set(i, contact);
			}
		}
	}

}
