package model;

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
 * Class creates competence profiles.
 */
@Entity
public class Competence_Profile implements Serializable {
    @Id
    @SequenceGenerator(name = "competenceProfileIdSeq", 
            sequenceName = "COMPETENCEPROFILE_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "competenceProfileIdSeq")
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "years_of_experience" ,nullable = false, columnDefinition="Decimal(4,2)")
    private Double years_of_experience;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "competence", referencedColumnName = "id", nullable = false)
    private Competence competence;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "application", referencedColumnName = "id", nullable = false)
    private Application application;

    /**
     * Constructor.
     */
    public Competence_Profile() {}

    /**
     * Methods parameters contains years of experience, name of job and 
     * application. One new profile for each user.
     *
     * @param years_of_experience year of experience
     * @param competence of a certain job
     * @param application application
     */
    public Competence_Profile(Double years_of_experience, Competence competence,
                              Application application)
    {
        this.years_of_experience = years_of_experience;
        this.competence = competence;
        this.application = application;
    }
    
    /**
     * Get id number of profile.
     *
     * @return ID nummer of profile
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Get id of a specific competence.
     *
     * @return ID of competence
     */
    public Integer getCompetence(){
        return competence.getId();
    }
    
    /**
     * Set a certain competence.
     *
     * @param competence competence
     */
    public void setCompetence(Competence competence){
        this.competence = competence;
    }
    
    /**
     * Get number of years a person worked on a specific job.
     *
     * @return numberOfYears in a job
     */
    public Double getYearsOfExperience(){
        return years_of_experience;
    }
    
    /**
     * Set years of experience with in a certain job.
     *
     * @param years_of_experience number of experience
     */
    public void setYearsOfExperience(Double years_of_experience){
        this.years_of_experience = years_of_experience;
    }
    
    /**
     * Get id of an application.
     *
     * @return ID of an application
     */
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence_Profile)) {
            return false;
        }
        Competence_Profile other = (Competence_Profile) object;
        return this.id.equals(other.id);
    }
    
    @Override
    public String toString() {
        return "model.Competence_profile[ id=" + id + " ]";
    }
}
