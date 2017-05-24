package DTO;

/**
 * DTO interface for the competence class.
 */
public interface CompetenceDTO 
{
    /**
     * Get language of that the job requires.
     * 
     * @return localCode language code
     */
    public String getLocale();
    
    /**
     * Get id for the competence.
     * 
     * @return id of competence
     */
    public Integer getCompetence();
    
    /**
     * Get name of competence.
     * 
     * @return name of competence
     */
    public String getCompetenceName();
}
