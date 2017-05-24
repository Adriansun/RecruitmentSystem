package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Person;
import model.RoleType;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-12T23:00:13")
@StaticMetamodel(UserRole.class)
public class UserRole_ { 

    public static volatile SingularAttribute<UserRole, Person> person;
    public static volatile SingularAttribute<UserRole, RoleType> roletype;
    public static volatile SingularAttribute<UserRole, Integer> id;

}