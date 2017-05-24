package DTO;

/**
 * DTO interface of available periods of applying for specific jobs.
 */
public interface AvailabilityDTO 
{
    /**
     * Get id of a available period.
     * 
     * @return id of available period
     */
    public Integer getId();
    
    /**
     * Get start date of job.
     * 
     * @return startDate of job
     */
    public String getFromDate();
    
    /**
     * Get end date of job.
     * 
     * @return endDate of job
     */
    public String getToDate();
    
    /**
     * Get id of appliacation.
     * 
     * @return id of application
     */
    public Integer getApplication();
}
