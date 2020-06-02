package org.casadocodigo.loja.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.casadocodigo.loja.daos.AuthorDAO;
import org.casadocodigo.loja.daos.BookDAO;
import org.casadocodigo.loja.infra.MessageHelper;
import org.casadocodigo.loja.models.Author;
import org.casadocodigo.loja.models.Book;

@Model
public class AdminBooksBean {

	@Inject
	private BookDAO bookDAO;

	@Inject
	private AuthorDAO authorDAO;

	@Inject
	private MessageHelper messageHelper;

	private Book product = new Book();

	private List<Integer> selectedAuthorsIds = new ArrayList<>();

	private List<Author> authors = new ArrayList<Author>();

	@Transactional
	public String save() {

		populateBookAuthor();
		bookDAO.save(product);
		clearObjects();

		messageHelper.sendMessage("Livro gravado com sucesso");

		return "/livros/lista?faces-redirect=true";
	}

	private void populateBookAuthor() {
		selectedAuthorsIds.stream().map((id) -> {
			return new Author(id);
		}).forEach(product::add);
	}

	private void clearObjects() {
		this.product = new Book();
		this.selectedAuthorsIds.clear();
	}

	public void add(Author author) {
		authors.add(author);
	}

	public Book getProduct() {
		return product;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Integer> getSelectedAuthorsIds() {
		return selectedAuthorsIds;
	}

	public void setSelectedAuthorsIds(List<Integer> selectedAuthorsIds) {
		this.selectedAuthorsIds = selectedAuthorsIds;
	}

	@PostConstruct
	private void loadObjects() {
		this.authors = authorDAO.list();
	}
}
