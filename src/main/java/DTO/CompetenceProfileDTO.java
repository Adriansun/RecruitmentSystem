package DTO;

/**
 * DTO interface for competence profiles.
 */
public interface CompetenceProfileDTO 
{
    /**
     * Get id of competence profile.
     * 
     * @return id of competence profile
     */
    public Integer getId();
    
    /**
     * Get id for the specific competence profile.
     *
     * @return id of the competence
     */
    public Integer getCompetence();
    
    /**
     * Get number of years required of competence of the job.
     * 
     * @return numberOfYears of competence
     */
    public Double getYearsOfExperience();
    
    /**
     * Get id for the specific application.
     * 
     * @return id of the specific application
     */
    public Integer getApplication();
}
