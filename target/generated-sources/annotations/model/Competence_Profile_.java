package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Application;
import model.Competence;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-12T23:00:14")
@StaticMetamodel(Competence_Profile.class)
public class Competence_Profile_ { 

    public static volatile SingularAttribute<Competence_Profile, Competence> competence;
    public static volatile SingularAttribute<Competence_Profile, Application> application;
    public static volatile SingularAttribute<Competence_Profile, Double> years_of_experience;
    public static volatile SingularAttribute<Competence_Profile, Integer> id;

}