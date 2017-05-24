package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Competence;
import model.Locale;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-12T23:00:13")
@StaticMetamodel(Competence_Localized.class)
public class Competence_Localized_ { 

    public static volatile SingularAttribute<Competence_Localized, Competence> competence;
    public static volatile SingularAttribute<Competence_Localized, Integer> id;
    public static volatile SingularAttribute<Competence_Localized, Locale> locale;
    public static volatile SingularAttribute<Competence_Localized, String> competenceName;

}