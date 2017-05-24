package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * Class handles the period of how long an applicant has worked on a specific 
 * job.
 */
@Entity
public class Competence implements Serializable {
    @Id
    @SequenceGenerator(name = "competenceIdSeq", sequenceName = "COMPETENCE_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "competenceIdSeq")
    @Column(name = "id")
    private Integer id;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competence")
    private Collection<Competence_Localized> competenceLocalized;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competence")
    private Collection<Competence_Profile> competenceProfile;

    /**
     * Constructor.
     */
    public Competence() {}
    
    /**
     * Get id of which row a certain competence is at.
     * 
     * @return ID row of competence
     */
    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "model.Competence[ id=" + id + " ]";
    }
}
