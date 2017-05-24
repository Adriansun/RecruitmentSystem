package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Application;
import model.Job_Localized;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-12T23:00:14")
@StaticMetamodel(Job.class)
public class Job_ { 

    public static volatile CollectionAttribute<Job, Job_Localized> jobLocalized;
    public static volatile SingularAttribute<Job, Integer> id;
    public static volatile CollectionAttribute<Job, Application> applications;

}