package webservices;


import java.security.Key;
import java.util.AbstractMap.SimpleEntry;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
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
}

