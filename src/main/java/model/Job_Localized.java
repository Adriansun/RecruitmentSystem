package model;

import DTO.JobDTO;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Class creates job names and language the user selects.
 */
@Entity
public class Job_Localized implements Serializable, JobDTO 
{
    @Id
    @SequenceGenerator(name = "jobLocalizedIdSeq", 
            sequenceName = "JOBLOCALIZED_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobLocalizedIdSeq")
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "jobName", nullable = false, unique = true)
    private String jobName;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "locale", referencedColumnName = "id", nullable = false)
    private Locale locale;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "job", referencedColumnName = "id", nullable = false)
    private Job job;

    /**
     * Called when a job is set.
     *
     * @param jobName job name
     * @param locale code
     * @param job job
     */
    public Job_Localized(String jobName, Locale locale,
                          Job job)
    {
        this.jobName = jobName;
        this.locale = locale;
        this.job = job;
    }

    /**
     * Constructor.
     */
    public Job_Localized() {}
    
    /**
     * Get id of job.
     *
     * @return id of job
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Get language code of which language that was selected.
     *
     * @return id of language
     */
    @Override
    public String getLocale(){
        return locale.getLangCode();
    }
    
    /**
     * Pick language by user.
     *
     * @param locale code
     */
    public void setLocale(Locale locale){
        this.locale = locale;
    }
    
    /**
     * Get job id.
     *
     * @return id of job
     */
    @Override
    public Integer getJob(){
        return job.getId();
    }
    
    /**
     * Set job.
     *
     * @param job the job
     */
    public void setJob(Job job){
        this.job = job;
    }
    
    /**
     * Get name of a type of job.
     *
     * @return jobType name of job type
     */
    @Override
    public String getJobTypeName(){
        return jobName;
    }
    
    /**
     * Set job name.
     *
     * @param jobName job name
     */
    public void setJobName(String jobName){
        this.jobName = jobName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Job_Localized)) {
            return false;
        }
        Job_Localized other = (Job_Localized) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "model.Job_Localized[ id=" + id + " ]";
    }
}
