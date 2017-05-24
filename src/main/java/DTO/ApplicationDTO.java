package DTO;

/**
 * DTO interface for applications.
 */
public interface ApplicationDTO 
{
    /**
     * Get id of a applicant.
     * 
     * @return id from applicant 
     */
    public Integer getId();
    
    /**
     * Get date when the application was made.
     * 
     * @return date of application
     */
    public String getDateMade();
    
    /**
     * Get username
     * 
     * @return username of applicant
     */
    public String getPerson();
    
    /**
     * Get status of id of the applicant.
     * 
     * @return Id:t för statusen på ansökan
     */
    public Integer getStatus();
    
    /**
     * Get id of the job the applicant applying for.
     * 
     * @return id of the job
     */
    public Integer getJob();
}

