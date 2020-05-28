package org.casadocodigo.loja.managedbeans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.casadocodigo.loja.daos.BookDAO;
import org.casadocodigo.loja.models.Book;

@Model
public class AdminBooksBean {

	@Inject
	private BookDAO bookDAO;
	
	private Book product = new Book();

	@Transactional
	public void save() {
		bookDAO.save(product);
	}

	public Book getProduct() {
		return product;
	}
}
