package webservices;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for the Product REST Services using Jersey Testing Framework
 */
public class ProductResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(ProductResource.class);
    }

    @Test
    @DisplayName("Restservice Test for getting all products")
    void testGetAllProducts() {
        Response response = target("/products").request()
                .get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(), "HTTP Response should be 200");
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE)
                , "HTTP Content-Type should be JSON");
    }

}
