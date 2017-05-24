package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Job;
import model.Locale;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-12T23:00:14")
@StaticMetamodel(Job_Localized.class)
public class Job_Localized_ { 

    public static volatile SingularAttribute<Job_Localized, String> jobName;
    public static volatile SingularAttribute<Job_Localized, Integer> id;
    public static volatile SingularAttribute<Job_Localized, Locale> locale;
    public static volatile SingularAttribute<Job_Localized, Job> job;

}