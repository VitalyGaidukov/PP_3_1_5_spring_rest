package PP_3_1_5_spring_rest_test.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, message = "Не меньше 2 знаков")
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, message = "Не меньше 2 знаков")
    private String surname;

    @Column(name = "age")
    private String age;

    @Column(name = "email")
    @Size(min=2, message = "Не меньше 2 знаков")
    private String email;

    @Size(min=2, message = "Не меньше 5 знаков")
    @Column(name = "password")
    private String password;



    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name="user_role",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    @Fetch(FetchMode.JOIN)
    private Set<Role> roles;

    public User() {
    }

    public User(String name, String surname, String age, String email) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
    }

    public User(int id, String name, String surname, String age, String email, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User {" + "\n"+
                " id = " + id + "\n"+
                ", name = '" + name + '\'' + "\n"+
                ", surname = '" + surname + '\'' + "\n"+
                ", age = '" + age + '\'' + "\n"+
                ", email = '" + email + '\'' + "\n"+
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

