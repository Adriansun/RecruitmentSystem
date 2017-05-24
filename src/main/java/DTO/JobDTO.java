package DTO;

/**
 * DTO interface for the job class.
 */
public interface JobDTO 
{
    /**
     * Get language for the specific job.
     * 
     * @return languageCode language code
     */
    public String getLocale();
    
    /**
     * Get id of the job.
     * 
     * @return id of the job
     */
    public Integer getJob();
    
    /**
     * Get name of the job.
     * 
     * @return name of the job
     */
    public String getJobTypeName();
}
