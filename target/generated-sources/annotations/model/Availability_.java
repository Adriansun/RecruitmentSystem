package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Application;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-12T23:00:14")
@StaticMetamodel(Availability.class)
public class Availability_ { 

    public static volatile SingularAttribute<Availability, String> from_date;
    public static volatile SingularAttribute<Availability, String> to_date;
    public static volatile SingularAttribute<Availability, Application> application;
    public static volatile SingularAttribute<Availability, Integer> id;

}