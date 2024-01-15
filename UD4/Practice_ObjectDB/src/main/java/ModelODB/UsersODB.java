package ModelODB;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class UsersODB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_User")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email", unique = true)
    private String email;

    // Constructor vacío
    public UsersODB() {
    }

    // Constructor con campos
    public UsersODB(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
