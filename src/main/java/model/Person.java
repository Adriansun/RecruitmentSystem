package model;

import DTO.PersonDTO;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Class handles a persons personal attributes.
 */
@Entity
public class Person implements PersonDTO, Serializable 
{
    @Basic(optional = false)
    @Column(name = "fname", nullable = false)
    private String fname;
    @Basic(optional = false)
    @Column(name = "surname", nullable = false)
    private String surname;
    @Basic(optional = false)
    @Column(name = "ssn", length = 13, nullable = false)
    private String ssn;
    @Basic(optional = false)
    @Column(name = "email", nullable = false)
    private String email;
    
    @Id
    @Basic(optional = false)
    @Column(name="username", nullable = false)
    private String username;
    
    @Basic(optional = false)
    @Column(name="password", nullable = false)
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Collection<Application> applications;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private UserRole userRole;
    
    /**
     * Constructor.
     */
    public Person(){
    }
    
    /**
     * Constructor of a person.
     * 
     * @param fname first name
     * @param surname surname
     * @param ssn social security number
     * @param email e-mail
     * @param username username
     * @param password password
     */
    public Person(String fname, String surname, 
            String ssn, String email, String username, String password)
    {
        this.fname = fname;
        this.surname = surname;
        this.ssn = ssn;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    
    /**
     * Get name of a person.
     *
     * @return fname first name
     */
    @Override
    public String getName(){
        return fname;
    }
    
    /**
     * Set first name of a person.
     * 
     * @param name first name
     */
    public void setName(String name){
        this.fname = name;
    }
    
     /**
      * Get surname of a person.
     *
     * @return surname surname
     */
    @Override
    public String getSurname(){
        return surname;
    }
    
    /**
     * Set surname of a person.
     *
     * @param surname surname
     */
    public void setSurname(String surname){
        this.surname = surname; 
    }
    
    /**
     * Get ssn of a person.
     *
     * @return ssn ssn
     */
    @Override
    public String getSSN(){
        return ssn;
    }
    
    /**
     * Set ssn of a person.
     * 
     * @param ssn ssn
     */
    public void setSSN(String ssn){
        this.ssn = ssn;
    }
    
    /**
     * Get a persons e-mail.
     *
     * @return email e-mail
     */
    @Override
    public String getEmail(){
        return email;
    }
    
    /**
     * Set e-mail of a person.
     *
     * @param email e-mail
     */
    public void setEmail(String email){
        this.email = email;
    }
    
    /**
     * Get username of a person.
     *
     * @return username username
     */
    @Override
    public String getUsername(){
        return username;
    }
    
    /**
     * Set username of a person.
     *
     * @param username username
     */
    public void setUsername(String username){
        this.username = username;
    }
    
    /**
     * Get password of a person.
     *
     * @return password password
     */
    @Override
    public String getPassword(){
        return password;
    }
    
    /**
     * Set password of a person.
     *
     * @param password password
     */
    public void setPassword(String password){
        this.password = password;
    }
    
    /**
     * Get user role - recruit or applicant.
     *
     * @return id is either recruit or applicant
     */
    @Override
    public Integer getUserRole(){
        return userRole.getId();
    }
    
     /**
      * Set user role - recruit or applicant.
     *
     * @param userRole user role
     */
    public void setUserRole(UserRole userRole){
        this.userRole = userRole;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        return this.username.equals(other.username);
    }

    @Override
    public String toString() {
        return "model.Person[ username=" + username + " ]";
    }
}
