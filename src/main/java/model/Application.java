package model;

import DTO.ApplicationDTO;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * Application creation class.
 */
@Entity
public class Application implements Serializable, ApplicationDTO
{
    @Id
    @SequenceGenerator(name = "applicationIdSeq", sequenceName = "APPLICATION_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "applicationIdSeq")
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "date_made", length = 16, nullable = false)
    private String date_made;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application")
    private Collection<Competence_Profile> competenceProfile;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "application")
    private Collection<Availability> availability;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "person", referencedColumnName = "username", nullable = false)
    private Person person;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "status", referencedColumnName = "id", nullable = false)
    private Status status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "job", referencedColumnName = "id", nullable = false)
    private Job job;
    
    /**
     * Constructor.
     */
    public Application(){
    }
    
    /**
     * Constructor.
     *
     * @param date_made date of when application was made
     * @param person applicant
     * @param status of the application
     * @param job the job
     */
    public Application(String date_made, Person person, Status status, Job job)
    {
        this.date_made = date_made;
        this.person = person;
        this.status = status;
        this.job = job;
    }
    
    /**
     * Get id of application.
     * 
     * @return id of application
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Get date of when the application was made.
     *
     * @return creation date
     */
    @Override
    public String getDateMade(){
        return date_made;
    }
    
    /**
     * Set date in database when the application was made.
     *
     * @param date_made of application
     */
    public void setDateMade(String date_made){
        this.date_made = date_made;
    }
    
    /**
     * Get name of user.
     *
     * @return username of user
     */
    @Override
    public String getPerson(){
        return person.getUsername();
    }
    
    /**
     * Set applicants in to database when application was made.
     *
     * @param person the user
     */
    public void setPerson(Person person){
        this.person = person;
    }
    
    /**
     * Get application status.
     *
     * @return id status of id
     */
    @Override
    public Integer getStatus(){
        return status.getId();
    }
    
    /**
     * Set status to database of application.
     *
     * @param status id status of application
     */
    public void setStatus(Status status){
        this.status = status;
    }
    
    /**
     * Get id of job from database.
     *
     * @return id of a job
     */
    @Override
    public Integer getJob(){
        return job.getId();
    }
    
    /**
     * Set a specific job in to database.
     *
     * @param job the job
     */
    public void setJob(Job job){
        this.job = job;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) 
    {
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "model.Application[ id=" + id + " ]";
    }
}
