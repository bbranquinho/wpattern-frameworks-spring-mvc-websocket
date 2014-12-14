package org.wpattern.frameworks.spring.mvc.model;

import org.wpattern.frameworks.spring.mvc.utils.BaseBean;

public class ContactBean extends BaseBean {

	private static final long serialVersionUID = 5585493974059809141L;

	private Integer id;

	private String firstname;

	private String lastname;

	private String email;

	private String phone;

	public ContactBean() {
	}

	public ContactBean(Integer id, String firstname, String lastname, String email, String phone) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


}
