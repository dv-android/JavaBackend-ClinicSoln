package com.clinicsoln.jersey; 


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;



@Path("cliniclogin")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ClinicLogin {
	
		@Context
	    private UriInfo uriInfo;
	
	    //@Inject
        private KeyGenerator keyGenerator;
	
	    private String token;
	    @Path("/dologin")
    	@POST
    	@Consumes(APPLICATION_FORM_URLENCODED) 
		
		
		public Response doLogin(@FormParam("username") String uname, @FormParam("password") String pwd){
		
	    	try {
			
					System.out.println("Endpoint cliniclogin/dologin hit"+uname + pwd);
					authenticate(uname,pwd);
					return Response.ok().header(AUTHORIZATION,"Bearer " + token).build();
	    	} catch(Exception e ) {
	    		    return Response.status(UNAUTHORIZED).build();
	    	}       
		}
    	
    	private void authenticate(String uname,String pwd) throws Exception{
    		
    		if(uname.equals("devang") && pwd.equals("devang")) {
    		   	 token = issueToken(uname);
    		} else {
    			;
    			throw new SecurityException("Invalid username/password");
    		}
    	}

    	   	
    	private String issueToken(String uname) {
    		
    		keyGenerator = new SimpleKeyGenerator();
    		Key key = keyGenerator.generateKey();
            String jwtToken = Jwts.builder()
                     .setSubject(uname)
                    .setIssuer(uriInfo.getAbsolutePath().toString())
                    .setIssuedAt(new Date())
                    .setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
                    .signWith(SignatureAlgorithm.HS512, key)
                    .compact();
  //          System.out.println("#### generating token for a key : " + jwtToken + " - " + key);
            return jwtToken; 
    	}  
    	
    	private Date toDate(LocalDateTime localDateTime) {
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }

}
