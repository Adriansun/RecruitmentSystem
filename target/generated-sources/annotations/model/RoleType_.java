package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.UserRole;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-12T23:00:13")
@StaticMetamodel(RoleType.class)
public class RoleType_ { 

    public static volatile CollectionAttribute<RoleType, UserRole> userRoles;
    public static volatile SingularAttribute<RoleType, String> name;

}