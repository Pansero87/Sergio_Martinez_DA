package Model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Users", catalog = "Library", uniqueConstraints = @UniqueConstraint(columnNames = "Email"))
public class Users implements java.io.Serializable {

	private Integer idUser;
	private String name;
	private String email;
	private Set<Loans> loanses = new HashSet<Loans>(0);

	public Users() {
	}

	public Users(String name, String email, Set<Loans> loanses) {
		this.name = name;
		this.email = email;
		this.loanses = loanses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_User", unique = true, nullable = false)
	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	@Column(name = "Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Email", unique = true)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Loans> getLoanses() {
		return this.loanses;
	}

	public void setLoanses(Set<Loans> loanses) {
		this.loanses = loanses;
	}

}
