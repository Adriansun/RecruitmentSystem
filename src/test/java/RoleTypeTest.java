import model.RoleType;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;

/**
 * Dummy for unit testing of role types with Arquillian.
 */
@RunWith(Arquillian.class)
public class RoleTypeTest {

    RoleType instance;

    /**
     * Constructor of creation of unit test of role types (RoleType).
     *
     * @return JavaArchive java archive
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(RoleType.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    /**
     * Test method for adding and removing a role type.
     */
    @org.junit.Test
    public void testSetAndGetName() {
        instance = new RoleType();
        String name = "admin";
        String expResult = "admin";
        instance.setName(name);
        String result = instance.getName();
        assertEquals(expResult, result);
    }
}
