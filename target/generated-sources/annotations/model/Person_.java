package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Application;
import model.UserRole;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-12T23:00:14")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, String> fname;
    public static volatile SingularAttribute<Person, String> password;
    public static volatile SingularAttribute<Person, String> surname;
    public static volatile SingularAttribute<Person, UserRole> userRole;
    public static volatile SingularAttribute<Person, String> email;
    public static volatile SingularAttribute<Person, String> ssn;
    public static volatile SingularAttribute<Person, String> username;
    public static volatile CollectionAttribute<Person, Application> applications;

}