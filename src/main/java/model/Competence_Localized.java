package model;

import DTO.CompetenceDTO;
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
 * Class of translations of names.
 */
@Entity
public class Competence_Localized implements Serializable, CompetenceDTO {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "competenceLocalizedIdSeq", 
            sequenceName = "COMPETENCELOZALIZED_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "competenceLocalizedIdSeq")
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "competenceName", nullable = false, unique = true)
    private String competenceName;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "locale", referencedColumnName = "id", nullable = false)
    private Locale locale;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "competence", referencedColumnName = "id", nullable = false)
    private Competence competence;
    
    /**
     * Sets the parameters of which language is to be used for the return 
     * methods.
     *
     * @param competenceName competence name
     * @param locale code
     * @param competence number of years for a certain job
     */
    public Competence_Localized(String competenceName, Locale locale,
                                Competence competence)
    {
        this.competenceName = competenceName;
        this.locale = locale;
        this.competence = competence;
    }

    /**
     * Constructor.
     */
    public Competence_Localized() {}
    
    /**
     * Get id of which row for the language.
     *
     * @return ID row number
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get the specific number in the localize column of which language to use.
     *
     * @return locale code
     */
    @Override
    public String getLocale(){
        return locale.getLangCode();
    }
    
    /**
     * Set a certain number which represents which language that was requested
     * to be used.
     * 
     * @param locale code
     */
    public void setLocale(Locale locale){
        this.locale = locale;
    }
    
    /**
     * Get id of a specific competence.
     *
     * @return ID number of competence
     */
    @Override
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
     * Get competence name.
     *
     * @return name of competence
     */
    @Override
    public String getCompetenceName(){
        return competenceName;
    }
    
    /**
     * Set a specific competence name.
     *
     * @param competenceName competence name
     */
    public void setCompetenceName(String competenceName){
        this.competenceName = competenceName;
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
        if (!(object instanceof Competence_Localized)) {
            return false;
        }
        Competence_Localized other = (Competence_Localized) object;
        return this.id.equals(other.id);    
    }

    @Override
    public String toString() {
        return "model.Competence_Localized[ id=" + id + " ]";
    }    
}
