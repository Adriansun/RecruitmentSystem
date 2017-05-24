package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Competence_Localized;
import model.Job_Localized;
import model.Status_Localized;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-12T23:00:14")
@StaticMetamodel(Locale.class)
public class Locale_ { 

    public static volatile CollectionAttribute<Locale, Competence_Localized> competenceLocalized;
    public static volatile SingularAttribute<Locale, String> lang_code;
    public static volatile CollectionAttribute<Locale, Status_Localized> statusLocalized;
    public static volatile CollectionAttribute<Locale, Job_Localized> jobLocalized;
    public static volatile SingularAttribute<Locale, Integer> id;

}