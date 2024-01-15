package Model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Categories", catalog = "Library", uniqueConstraints = @UniqueConstraint(columnNames = "Category_Name"))
public class Categories implements java.io.Serializable {

	private Integer idCategory;
	private String categoryName;
	private Set<Books> bookses = new HashSet<Books>(0);

	public Categories() {
	}

	public Categories(String categoryName, Set<Books> bookses) {
		this.categoryName = categoryName;
		this.bookses = bookses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "ID_Category", unique = true, nullable = false)
	public Integer getIdCategory() {
		return this.idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}

	@Column(name = "Category_Name", unique = true)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categorieses")
	public Set<Books> getBookses() {
		return this.bookses;
	}

	public void setBookses(Set<Books> bookses) {
		this.bookses = bookses;
	}

}
