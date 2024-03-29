package integration;

import DTO.ApplicationDTO;
import DTO.AvailabilityDTO;
import DTO.CompetenceDTO;
import DTO.CompetenceProfileDTO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Application;
import model.Availability;
import model.Competence;
import model.Competence_Profile;
import model.Job;
import model.Person;
import model.Status;

/**
 * Application class process and sends queries to the database about 
 * applications.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ApplicationDAO {
    @PersistenceContext(unitName = "Projektgrupp9PU")
    private EntityManager em;
    
    /**
     * List of all competences.
     *
     * @param lang code
     * @return competenceList list of competences
     */
    public List<CompetenceDTO> getAllCompetences(String lang)
    {
        Query query = em.createQuery("SELECT cl FROM Competence_Localized AS cl "
            + "WHERE cl.locale = (SELECT l FROM Locale AS l WHERE l.lang_code = ?1)");
        query.setParameter(1, lang);
        return query.getResultList();
    }
    
    /**
     * Send all data to the database of an application.
     *
     * @param competenceList list of competences
     * @param yearsList list of years of competence
     * @param fromDateList list of start dates
     * @param toDateList lkist of end dates
     * @param username of person
     * @param jobId id of job
     * @return boolean true if application was success
     */
    public Boolean createApplication(ArrayList<String> competenceList, 
                                     ArrayList<String> yearsList,
                                     ArrayList<String> fromDateList,
                                     ArrayList<String> toDateList,
                                     String username, Integer jobId)
    {
        Query query = em.createQuery("SELECT p FROM Person AS p WHERE p.username = ?1", Person.class);
        query.setParameter(1, username);
        Person person = (Person) query.getSingleResult();
        
        query = em.createQuery("SELECT s FROM Status AS s WHERE s.id = "
                + "(SELECT sl.status FROM Status_Localized AS sl WHERE sl.statusName = ?1)", Status.class);
        query.setParameter(1, "Not reviewed");
        Status status = (Status) query.getSingleResult();
        
        query = em.createQuery("SELECT j FROM Job AS j WHERE j.id = ?1", Job.class);
        query.setParameter(1, jobId);
        Job job = (Job) query.getSingleResult();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date date = new java.util.Date();
        String datetime = dateFormat.format(date);
        
        Application application = new Application(datetime, person, status, job); 
        em.persist(application);
        
        Availability availability;
        for(int i = 0; i < fromDateList.size(); i++)
        {
            availability = new Availability
                           (fromDateList.get(i), toDateList.get(i), application);
            em.persist(availability);
        }
        
        Competence_Profile competenceProfile;
        Competence competence;
        for(int i = 0; i < competenceList.size(); i++)
        {
            query = em.createQuery("SELECT c FROM Competence AS c WHERE c.id = ?1", Competence.class);
            query.setParameter(1, Integer.parseInt(competenceList.get(i)));
            competence = (Competence) query.getSingleResult();
            competenceProfile = new Competence_Profile
                           (Double.parseDouble(yearsList.get(i)), competence, application);
            em.persist(competenceProfile);
        }        
        return true;
    }
    
    /**
     * Get completed applications of a specific applicant.
     *
     * @param username of the applicant
     * @return listOfApplication list of applications
     */
    public List<ApplicationDTO> getApplicationsByUsername(String username)
    {
        Query query = em.createQuery("SELECT a FROM Application AS a WHERE "
                + "a.person = (SELECT p FROM Person AS p WHERE p.username = ?1)", ApplicationDTO.class);
        query.setParameter(1, username);
        return query.getResultList();
    }
    
    /**
     * Get name of a status by id.
     *
     * @param id status of id number
     * @param lang code
     * @return name of status
     */
    public String getStatusNameById(Integer id, String lang)
    {
        Query query = em.createQuery("SELECT sl.statusName FROM Status_Localized AS sl "
                + "WHERE sl.locale = (SELECT l FROM Locale AS l WHERE l.lang_code = ?1) "
                + "AND sl.status = (SELECT s FROM Status AS s WHERE s.id = ?2)");
        query.setParameter(1, lang);
        query.setParameter(2, id);
        return (String)query.getSingleResult();
    }
    
    /**
     * Get a list of competence profiles of an applicant.
     *
     * @param id of the specific application
     * @return listOfCompetenceProfiles list of competence profiles for the
     * specific job
     */
    public List<CompetenceProfileDTO> getCompetenceProfileByApplicationId(Integer id){
        Query query = em.createQuery("SELECT cp FROM Competence_Profile AS cp "
                + "WHERE cp.application = (SELECT a FROM Application AS a WHERE a.id = ?1)", 
                        CompetenceProfileDTO.class);
        query.setParameter(1, id);
        return query.getResultList();
    }
    
    /**
     * Get list of available periods of a application.
     *
     * @param id of the specific application
     * @return listOfAvailablePeriodsOfTheSpecificApplication list of 
     * available periods of the specific application
     */
    public List<AvailabilityDTO> getAvailabilityByApplicationId(Integer id){
        Query query = em.createQuery("SELECT av FROM Availability AS av "
                + "WHERE av.application = (SELECT a FROM Application AS a WHERE a.id = ?1)", 
                        AvailabilityDTO.class);
        query.setParameter(1, id);
        return query.getResultList();
    }
    
    /**
     * Get the name for a specific competence.
     *
     * @param id of a specific competence
     * @param lang code
     * @return name of competence
     */
    public String getCompetenceNameById(Integer id, String lang){
        Query query = em.createQuery("SELECT cl.competenceName FROM Competence_Localized AS cl "
                + "WHERE cl.competence = (SELECT c FROM Competence AS c WHERE c.id = ?1)"
                + " AND cl.locale = (SELECT l FROM Locale AS l WHERE l.lang_code = ?2)");
        query.setParameter(1, id);
        query.setParameter(2, lang);
        return (String)query.getSingleResult();
    }
}        
