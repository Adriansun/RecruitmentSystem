package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Application;
import model.Status_Localized;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-12T23:00:14")
@StaticMetamodel(Status.class)
public class Status_ { 

    public static volatile CollectionAttribute<Status, Status_Localized> statusLocalized;
    public static volatile SingularAttribute<Status, Integer> id;
    public static volatile CollectionAttribute<Status, Application> applications;

}