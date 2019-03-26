package webservices;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.core.Application;

/**
 * Unit test for the Category REST Services using Jersey Testing Framework
 */
public class CategoryResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(CategoryResource.class);
    }


}
