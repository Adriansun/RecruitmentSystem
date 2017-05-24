package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 * Class to create and store user roles.
 */
@Entity
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "userRoleIdSeq", sequenceName = "USERROLE_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userRoleIdSeq")
    @Column(name = "id")
    private Integer id;
    
    @OneToOne(optional = false)
    @JoinColumn(name = "person", referencedColumnName = "username", nullable = false)   
    private Person person;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "roletype", referencedColumnName = "name", nullable = false)
    private RoleType roletype;  
    
    /**
     * Constructor.
     */
    public UserRole(){
    }
    
    /**
     * Constructor for user roles.
     *
     * @param person person this user role matters to
     * @param roletype role type the person shall have
     */
    public UserRole(Person person, RoleType roletype){
        this.person = person;
        this.roletype = roletype;
    }
    
    /**
     * Get id of user role.
     *
     * @return id of user role
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Get persons username
     *
     * @return username of person
     */
    public String getPerson(){
        return person.getUsername();
    }
    
    /**
     * Set person to a specific user role.
     *
     * @param person person
     */
    public void setPerson(Person person){
        this.person = person;
    }
    
    /**
     * Get the role type for this user role.
     *
     * @return getName of user role
     */
    public String getRoleType(){
        return roletype.getName();
    }
    
    /**
     * Set role type for a user role.
     *
     * @param roletype for a user role
     */
    public void setRoleType(RoleType roletype){
        this.roletype = roletype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserRole)) {
            return false;
        }
        UserRole other = (UserRole) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "model.UserRole[ id=" + id + " ]";
    }   
}
