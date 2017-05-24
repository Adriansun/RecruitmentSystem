package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Locale;
import model.Status;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-12T23:00:13")
@StaticMetamodel(Status_Localized.class)
public class Status_Localized_ { 

    public static volatile SingularAttribute<Status_Localized, String> statusName;
    public static volatile SingularAttribute<Status_Localized, Integer> id;
    public static volatile SingularAttribute<Status_Localized, Locale> locale;
    public static volatile SingularAttribute<Status_Localized, Status> status;

}