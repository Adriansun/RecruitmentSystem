package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Availability;
import model.Competence_Profile;
import model.Job;
import model.Person;
import model.Status;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-12T23:00:14")
@StaticMetamodel(Application.class)
public class Application_ { 

    public static volatile SingularAttribute<Application, String> date_made;
    public static volatile CollectionAttribute<Application, Competence_Profile> competenceProfile;
    public static volatile SingularAttribute<Application, Person> person;
    public static volatile SingularAttribute<Application, Integer> id;
    public static volatile CollectionAttribute<Application, Availability> availability;
    public static volatile SingularAttribute<Application, Job> job;
    public static volatile SingularAttribute<Application, Status> status;

}