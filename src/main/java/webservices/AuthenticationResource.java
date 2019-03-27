package webservices;


import java.security.Key;
import java.util.AbstractMap.SimpleEntry;
import java.util.Calendar;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import model.Account;
import model.Product;
import persistence.AccountDAO;
import persistence.AccountPostgreSQLDAOImpl;

@Path("/authentication")
public class AuthenticationResource {
	final static public Key key = MacProvider.generateKey();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("email") String email, @FormParam("password") String password) {
		try {
			AccountDAO dao = new AccountPostgreSQLDAOImpl();
			if (dao.validateLogin(email, password) == true) {
				String token = createToken(email);
				SimpleEntry<String, String> JWT = new SimpleEntry<String, String>("JWT", token);
				return Response.ok(JWT).build();
			}
		} catch (JwtException | IllegalArgumentException e) {
			System.out.println(e);
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}

	public String createToken(String email) throws JwtException {
		{
			// Key key = MacProvider.generateKey();
			Calendar expiration = Calendar.getInstance();
			expiration.add(Calendar.MINUTE, 30);
			Date exp = expiration.getTime();
			String JWT = Jwts.builder().setIssuer("192.168.0.10").setSubject(email).setExpiration(exp)
					.signWith(SignatureAlgorithm.HS512, "brocoli").compact();
			return JWT;
		}
	}
	
	public String openToken(String jwt) {
	    Claims claims = Jwts.parser()         
	       .setSigningKey(DatatypeConverter.parseBase64Binary("brocoli"))
	       .parseClaimsJws(jwt).getBody();
	    return (claims.getSubject());
	}
	
    @POST
	@Path("/details")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public JsonObject getAccountDetails(@FormParam("jwt") String jwt) {
    	AuthenticationService as = new AuthenticationService();
    	if (openToken(jwt) != null) {
    		String email = openToken(jwt);
    		Account acc = as.getAccount(email);
			JsonObjectBuilder con = Json.createObjectBuilder();
			con.add("id", acc.getId());
			con.add("adress", acc.getAdress());
			con.add("email", acc.getEmail());
    		JsonObject array = con.build();
    		return array;
    	}
    		return null;
    	}

}


