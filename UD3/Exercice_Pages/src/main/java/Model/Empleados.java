package Model;

// default package
// Generated 24 nov 2023 12:16:53 by Hibernate Tools 5.2.13.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Empleados generated by hbm2java
 */
@Entity
@Table(name = "empleados", catalog = "employees")
public class Empleados implements java.io.Serializable {

	private Integer idEmpleado;
	private String nombre;
	private Integer edad;
	private String telefono;
	private String dni;
	private String email;

	public Empleados() {
	}

	public Empleados(String nombre) {
		this.nombre = nombre;
	}

	public Empleados(String nombre, Integer edad, String telefono, String dni, String email) {
		this.nombre = nombre;
		this.edad = edad;
		this.telefono = telefono;
		this.dni = dni;
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id_empleado", unique = true, nullable = false)
	public Integer getIdEmpleado() {
		return this.idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "edad")
	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	@Column(name = "telefono", length = 15)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "dni", length = 20)
	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
