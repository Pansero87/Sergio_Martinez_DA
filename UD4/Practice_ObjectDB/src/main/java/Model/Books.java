package Model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Books", catalog = "Library")
public class Books implements java.io.Serializable {

	private Integer idBook;
	private String title;
	private String author;
	private Integer publicationYear;
	private Set<Categories> categorieses = new HashSet<Categories>(0);
	private Set<Loans> loanses = new HashSet<Loans>(0);

	public Books() {
	}

	public Books(String title, String author, Integer publicationYear, Set<Categories> categorieses,
			Set<Loans> loanses) {
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.categorieses = categorieses;
		this.loanses = loanses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_Book", unique = true, nullable = false)
	public Integer getIdBook() {
		return this.idBook;
	}

	public void setIdBook(Integer idBook) {
		this.idBook = idBook;
	}

	@Column(name = "Title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "Author")
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "Publication_Year")
	public Integer getPublicationYear() {
		return this.publicationYear;
	}

	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Books_Categories", catalog = "Library", joinColumns = {
			@JoinColumn(name = "ID_Book", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "ID_Category", nullable = false, updatable = false) })
	public Set<Categories> getCategorieses() {
		return this.categorieses;
	}

	public void setCategorieses(Set<Categories> categorieses) {
		this.categorieses = categorieses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "books")
	public Set<Loans> getLoanses() {
		return this.loanses;
	}

	public void setLoanses(Set<Loans> loanses) {
		this.loanses = loanses;
	}

}
