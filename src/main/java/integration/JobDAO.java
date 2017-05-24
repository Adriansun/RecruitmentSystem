package integration;

import DTO.JobDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Class process queries to the database about jobs.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class JobDAO {
    @PersistenceContext(unitName = "Projektgrupp9PU")
    private EntityManager em;
    
    /**
     * List of all types of jobs.
     *
     * @param lang code
     * @return jobList list of available jobs
     */
    public List<JobDTO> getJobs(String lang)
    {
        Query query = em.createQuery("SELECT jl FROM Job_Localized AS jl "
                + "WHERE jl.locale = (SELECT l FROM Locale AS l WHERE l.lang_code = ?1)");
        query.setParameter(1, lang);
        return query.getResultList();
    }
    
    /**
     * Search for a job based on id.
     *
     * @param id of job
     * @param lang code
     * @return jobId id of a job
     */
    public String getJobNameById(Integer id, String lang)
    {
        Query query = em.createQuery("SELECT jl.jobName FROM Job_Localized AS jl "
                + "WHERE jl.locale = (SELECT l FROM Locale AS l WHERE l.lang_code = ?1) "
                + "AND jl.job = (SELECT j FROM Job AS j WHERE j.id = ?2)");
        query.setParameter(1, lang);
        query.setParameter(2, id);
        return (String)query.getSingleResult();
    }
}        