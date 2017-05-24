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
 * Entity "Status_Localized" stores information about if an applicant was
 * hired or dismissed in different languages. It is connected to "Status" and
 * "Locale" entities id columns and uses these id's foreign keys to identidy
 * the language and status.
 */
@Entity
public class Status_Localized implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "statusLocalizedIdSeq", 
            sequenceName = "STATUSLOCALIZED_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statusLocalizedIdSeq")
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "statusName", nullable = false, unique = true)
    private String statusName;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "locale", referencedColumnName = "id", nullable = false)
    private Locale locale;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "status", referencedColumnName = "id", nullable = false)
    private Status status;
    
    /**
     * Constructor when something new happens with the language or status.
     *
     * @param statusName name of status
     * @param locale code
     * @param status status of language
     */
    public Status_Localized(String statusName, Locale locale, Status status){
        this.statusName = statusName;
        this.locale = locale;
        this.status = status;
    }

    /**
     * Constructor.
     */
    public Status_Localized() {}
        
    /**
     * Get id of a row.
     *
     * @return ID row number
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get language code of which language is being used.
     *
     * @return getLandCode language code
     */
    public String getLocale(){
        return locale.getLangCode();
    }
    
    /**
     * Set language code.
     *
     * @param locale code
     */
    public void setLocale(Locale locale){
        this.locale = locale;
    }
    
    /**
     * Get status of the foreign key of the language.
     *
     * @return id code
     */
    public Integer getStatus(){
        return status.getId();
    }
    
    /**
     * Set status of which language.
     *
     * @param status code
     */
    public void setStatus(Status status){
        this.status = status;
    }

    /**
     * Get name of the status.
     *
     * @return status name of the status
     */
    public String getStatusName(){
        return statusName;
    }

    /**
     * Set the name of status.
     *
     * @param statusName name of status
     */
    public void setStatusName(String statusName){
        this.statusName = statusName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Status_Localized)) {
            return false;
        }
        Status_Localized other = (Status_Localized) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "model.Status_Localized[ id=" + id + " ]";
    }
}
