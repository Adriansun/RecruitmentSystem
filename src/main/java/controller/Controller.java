package controller;

import DTO.ApplicationDTO;
import DTO.AvailabilityDTO;
import DTO.CompetenceDTO;
import DTO.CompetenceProfileDTO;
import DTO.JobDTO;
import integration.ApplicationDAO;
import integration.JobDAO;
import integration.RegisterDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Controller takes calls from the view and return them to the integration 
 * layer and back.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class Controller {
    @EJB
    private RegisterDAO registerDAO;
    @EJB
    private JobDAO jobDAO;
    @EJB
    private ApplicationDAO applicationDAO;

    /**
     * Calls loginAndRegisterDAO to register new applicant.
     * @param name first name
     * @param surname surname
     * @param ssn social security number
     * @param email e-mail
     * @param username username
     * @param password password
     * @return Boolean about it it went well or not
     */
    public Boolean register(String name, String surname, String ssn, String email, String username, String password)
    {
        return registerDAO.register(name, surname, ssn, email, username, password);
    }
    
    /**
     * Get list of available jobs.
     * 
     * @param lang language code
     * @return list of all jobs
     */
    public List<JobDTO> getJobs(String lang)
    {
        return jobDAO.getJobs(lang);
    }
    
    /**
     * Get job with a certain id.
     * 
     * @param id of job
     * @param lang of job
     * @return name required of job
     */
    public String getJobNameById(Integer id, String lang)
    {
        return jobDAO.getJobNameById(id, lang);
    }
    
    /**
     * Get status of a name with a certain id.
     * 
     * @param id status
     * @param lang code
     * @return name of status
     */
    public String getStatusNameById(Integer id, String lang)
    {
        return applicationDAO.getStatusNameById(id, lang);
    }
    
    /**
     * Get list of all competences.
     * 
     * @param lang code
     * @return list of competences
     */
    public List<CompetenceDTO> getAllCompetences(String lang)
    {
        return applicationDAO.getAllCompetences(lang);
    }
    
    /**
     * Calls DAO application to make a new application.
     * 
     * @param competenceList list of applicants competence
     * @param yearsList list of years of competence
     * @param fromDateList list of start dates
     * @param toDateList list of end dates
     * @param username of person
     * @param jobId id of the specific job
     * @return Boolean if it was any issues or not
     */
    public Boolean createApplication(ArrayList<String> competenceList, 
                                     ArrayList<String> yearsList,
                                     ArrayList<String> fromDateList,
                                     ArrayList<String> toDateList,
                                     String username, Integer jobId)
    {
        return applicationDAO.createApplication(competenceList, yearsList, 
                                   fromDateList, toDateList, username, jobId);
    }
    
    /**
     * Get done applications of an applicant.
     * 
     * @param username of person
     * @return applicantJobList applicants list of applied jobs
     */
    public List<ApplicationDTO> getApplicationsByUsername(String username)
    {
        return applicationDAO.getApplicationsByUsername(username);
    }
    
    /**
     * Get list of competence profiles of an applicant.
     *
     * @param id of the applicant
     * @return competenceList list of competence profiles
     */
    public List<CompetenceProfileDTO> getCompetenceProfileByApplicationId(Integer id)
    {
        return applicationDAO.getCompetenceProfileByApplicationId(id);
    }
    
    /**
     * Get the name of a specific competence through id.
     * 
     * @param id of a competence
     * @param lang code
     * 
     * @return name of a competence
     */
    public String getCompetenceNameById(Integer id, String lang)
    {
        return applicationDAO.getCompetenceNameById(id, lang);
    }
    
    /**
     * Get list of available periods for a specific application.
     * 
     * @param id of the specific application
     * @return listOfPeriods list of periods
     */
    public List<AvailabilityDTO> getAvailabilityByApplicationId(Integer id)
    {
        return applicationDAO.getAvailabilityByApplicationId(id);
    }
}
