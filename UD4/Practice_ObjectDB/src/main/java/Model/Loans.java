package Model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Loans", catalog = "Library")
public class Loans implements java.io.Serializable {

	private Integer idLoan;
	private Books books;
	private Users users;
	private Date loanDate;
	private Date returnDate;

	public Loans() {
	}

	public Loans(Books books, Users users, Date loanDate, Date returnDate) {
		this.books = books;
		this.users = users;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_Loan", unique = true, nullable = false)
	public Integer getIdLoan() {
		return this.idLoan;
	}

	public void setIdLoan(Integer idLoan) {
		this.idLoan = idLoan;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_Book")
	public Books getBooks() {
		return this.books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_User")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Loan_Date", length = 10)
	public Date getLoanDate() {
		return this.loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Return_Date", length = 10)
	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}
