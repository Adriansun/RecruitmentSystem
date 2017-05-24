package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Competence_Localized;
import model.Competence_Profile;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-12T23:00:14")
@StaticMetamodel(Competence.class)
public class Competence_ { 

    public static volatile CollectionAttribute<Competence, Competence_Localized> competenceLocalized;
    public static volatile CollectionAttribute<Competence, Competence_Profile> competenceProfile;
    public static volatile SingularAttribute<Competence, Integer> id;

}