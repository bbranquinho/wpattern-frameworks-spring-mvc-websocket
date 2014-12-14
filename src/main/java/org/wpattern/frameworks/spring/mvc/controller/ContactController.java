package org.wpattern.frameworks.spring.mvc.controller;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.wpattern.frameworks.spring.mvc.model.ContactBean;
import org.wpattern.frameworks.spring.mvc.model.ContactFormBean;
import org.wpattern.frameworks.spring.mvc.model.Dataset;
import org.wpattern.frameworks.websocket.ContactWS;

@Controller
@RequestMapping(value = { "/contact" })
public class ContactController {

	private static final Logger LOGGER = Logger.getLogger(ContactController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("List all contacts.");
		}

		ContactFormBean contactForm = new ContactFormBean();

		contactForm.setContacts(Dataset.getContacts());

		return new ModelAndView("/contact/list", "contactForm", contactForm);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Creating a new contact.");
		}

		return "/contact/addEdit";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") int id) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(String.format("Delete the contact with id [%s].", id));
		}

		Iterator<ContactBean> iterator = Dataset.getContacts().iterator();

		while (iterator.hasNext()) {
			ContactBean contact = iterator.next();

			if (contact.getId() == id) {
				iterator.remove();
				ContactWS.sendContact(contact, "DELETE");
			}
		}

		return this.list();
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id") int id) {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(String.format("Edit the contact with id [%s].", id));
		}

		Iterator<ContactBean> iterator = Dataset.getContacts().iterator();

		while (iterator.hasNext()) {
			ContactBean contact = iterator.next();

			if (contact.getId() == id) {
				return new ModelAndView("/contact/addEdit", "contact", contact);
			}
		}

		return this.list();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("contact") ContactBean contact, @RequestParam("action") String action) {
		if ((action != null) && (action.trim().toLowerCase().equals("cancel"))) {
			return this.list();
		}

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(String.format("Saving the contact [%s].", contact));
		}

		if (contact == null) {
			LOGGER.error("Contact not received.");

			return this.list();
		} else if (contact.getId() == null) {
			Dataset.addContact(contact);
			ContactWS.sendContact(contact, "ADD");
		} else {
			Dataset.updateContact(contact);
			ContactWS.sendContact(contact, "EDIT");
		}

		return this.list();
	}

	@RequestMapping(value = "/listEdit", method = RequestMethod.GET)
	public ModelAndView listEdit() {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("List all contacts.");
		}

		ContactFormBean contactForm = new ContactFormBean();

		contactForm.setContacts(Dataset.getContacts());

		return new ModelAndView("/contact/listEdit", "contactForm", contactForm);
	}

	@RequestMapping(value = "/saveList", method = RequestMethod.POST)
	public ModelAndView saveList(@ModelAttribute("contactForm") ContactFormBean contactForm,
			@RequestParam("action") String action) {
		if ((action != null) && (action.trim().toLowerCase().equals("cancel"))) {
			return this.list();
		}

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Saving all contacts.");
		}

		for (ContactBean contact : Dataset.getContacts()) {
			ContactWS.sendContact(contact, "DELETE");
		}

		Dataset.setContacts(contactForm.getContacts());

		for (ContactBean contact : Dataset.getContacts()) {
			ContactWS.sendContact(contact, "ADD");
		}

		return this.list();
	}

	@RequestMapping(value = "/listWS", method = RequestMethod.GET)
	public ModelAndView listWS() {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("List contacts in real time.");
		}

		ContactFormBean contactForm = new ContactFormBean();

		contactForm.setContacts(Dataset.getContacts());

		return new ModelAndView("/contact/listWS", "contactForm", contactForm);
	}

}
