package DTO;

/**
 * DTO interface for a person.
 */
public interface PersonDTO 
{    
    /**
     * Get person name.
     * 
     * @return name of person
     */
    public String getName();
    
    /**
     * Get person last name.
     * 
     * @return surname of person
     */
    public String getSurname();
    
    /**
     * Get person social security number.
     * 
     * @return SSN of person
     */
    public String getSSN();
    
    /**
     * Get e-mail of person.
     * 
     * @return email of person
     */
    public String getEmail();
    
    /**
     * Get username of person.
     * 
     * @return username of person
     */
    public String getUsername();
    
    /**
     * Get password of person.
     * 
     * @return password of person
     */
    public String getPassword();

    /**
     * Get userprofile of person.
     * 
     * @return role of person
     */
    public Integer getUserRole();
}
