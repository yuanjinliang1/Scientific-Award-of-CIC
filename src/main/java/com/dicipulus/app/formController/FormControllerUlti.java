package com.dicipulus.app.formController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dicipulus.app.model.Applier;
import com.dicipulus.app.model.Person;

public final class FormControllerUlti {
	public static Person getPersonInRequest(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute("person");
		return person;
	}
}
