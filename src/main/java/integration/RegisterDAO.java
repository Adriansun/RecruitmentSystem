package integration;

import DTO.PersonDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Person;
import model.RoleType;
import model.UserRole;

/**
 * The class takes care of log in and registration of users towards the
 * database.
 */
@Stateless
public class RegisterDAO {
    @PersistenceContext(unitName = "Projektgrupp9PU")
    private EntityManager em;

    /**
     * Register user to database.
     *
     * @param name first name
     * @param surname surname
     * @param ssn social security number
     * @param email e-mail
     * @param username username
     * @param password password
     * @return success if it went well or not
     */
    public Boolean register(String name, String surname, String ssn, 
                            String email, String username, String password) {
        Query query = em.createQuery("SELECT p FROM Person AS p WHERE p.username = ?1");
        query.setParameter(1, username);
        if(query.getResultList().size() > 0)
            return false;
        
        query = em.createQuery("SELECT rt FROM RoleType AS rt WHERE rt.name = 'applicant'");
        RoleType roletype = (RoleType)query.getSingleResult();
        
        Person person = new Person(name, surname, ssn, email, username, encrypt(password));
        UserRole userRole = new UserRole(person, roletype);
        em.persist(userRole);
        
        person.setUserRole(em.find(UserRole.class, userRole.getId()));
        em.persist(person);
        
        return true;
    }

    private String encrypt(String password) {
        String encrypted="";
        try {
            java.security.MessageDigest digestion = java.security.MessageDigest.getInstance("SHA-256");
            digestion.update(password.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = digestion.digest();
            java.math.BigInteger bigInt = new java.math.BigInteger(1, digest);
            encrypted = bigInt.toString(16);
System.out.println("c: "+encrypted);
        } catch (java.security.NoSuchAlgorithmException | java.io.UnsupportedEncodingException ex) {
            System.out.println("e: "+ex.getMessage());
        }
        return encrypted;
    }
}        
