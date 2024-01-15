// default package
// Generated 22 nov 2023 13:01:39 by Hibernate Tools 5.2.13.Final

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
import javax.persistence.UniqueConstraint;

/**
 * Orchestra generated by hbm2java
 */
@Entity
@Table(name = "Orchestra", catalog = "OrchestraDB", uniqueConstraints = @UniqueConstraint(columnNames = "OrchestraName"))
public class Orchestra implements java.io.Serializable {

	private Integer orchestraId;
	private String orchestraName;
	private Set<Musician> musicians = new HashSet<Musician>(0);
	private Set<Musician> musicians_1 = new HashSet<Musician>(0);



	public Orchestra(String orchestraName, Set<Musician> musicians, Set<Musician> musicians_1) {
		this.orchestraName = orchestraName;
		this.musicians = musicians;
		this.musicians_1 = musicians_1;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "OrchestraID", unique = true, nullable = false)
	public Integer getOrchestraId() {
		return this.orchestraId;
	}

	public void setOrchestraId(Integer orchestraId) {
		this.orchestraId = orchestraId;
	}

	@Column(name = "OrchestraName", unique = true)
	public String getOrchestraName() {
		return this.orchestraName;
	}

	public void setOrchestraName(String orchestraName) {
		this.orchestraName = orchestraName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orchestra")
	public Set<Musician> getMusicians() {
		return this.musicians;
	}

	public void setMusicians(Set<Musician> musicians) {
		this.musicians = musicians;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "OrchestraMusician", catalog = "OrchestraDB", joinColumns = {
			@JoinColumn(name = "OrchestraID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "MusicianID", nullable = false, updatable = false) })
	public Set<Musician> getMusicians_1() {
		return this.musicians_1;
	}

	public void setMusicians_1(Set<Musician> musicians_1) {
		this.musicians_1 = musicians_1;
	}

}