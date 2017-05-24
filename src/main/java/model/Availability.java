package model;

import DTO.AvailabilityDTO;
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
 * Availability for an applicant of jobs during certain periods.
 */
@Entity
public class Availability implements Serializable, AvailabilityDTO 
{
    @Id
    @SequenceGenerator(name = "availabilityIdSeq", sequenceName = "AVAILABILITY_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "availabilityIdSeq")
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "from_date", length = 10, nullable = false)
    private String from_date;

    @Basic(optional = false)
    @Column(name = "to_date", length = 10, nullable = false)
    private String to_date;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "application", referencedColumnName = "id", nullable = false)
    private Application application;
    
    /**
     * Method takes in data when a new job is made.
     *
     * @param from_date available from this date
     * @param to_date not available from this date
     * @param application the application
     */
    public Availability(String from_date, String to_date, Application application)
    {
        this.from_date = from_date;
        this.to_date = to_date;
        this.application = application;
    }

    /**
     * Contructor.
     */
    public Availability() {}
    
    /**
     * Get id from column ID of a specific availability for an applicant.
     *
     * @return id of job
     */
    @Override
    public Integer getId() {
        return id;
    }
    
    /**
     * Get start date for a job.
     *
     * @return startDate start date
     */
    @Override
    public String getFromDate(){
        return this.from_date;
    }
    
    /**
     * Sets the date when an applicant can start to work.
     *
     * @param from_date from date
     */
    public void setFromDate(String from_date){
        this.from_date = from_date;
    }
    
    /**
     * Get date when a job cannot be applied to.
     *
     * @return endDate end date
     */
    @Override
    public String getToDate(){
        return this.to_date;
    }
    
    /**
     * Set to database the date when a worker cannot work anymore.
     *
     * @param to_date to date
     */
    public void setToDate(String to_date){
        this.to_date = to_date;
    }
    
    /**
     * Get id of a certain application.
     *
     * @return id of application
     */
    @Override
    public Integer getApplication(){
        return application.getId();
    }
    
    /**
     * Set application to database.
     *
     * @param application application
     */
    public void setApplication(Application application){
        this.application = application;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Availability)) {
            return false;
        }
        Availability other = (Availability) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "model.Availability[ id=" + id + " ]";
    }
}
