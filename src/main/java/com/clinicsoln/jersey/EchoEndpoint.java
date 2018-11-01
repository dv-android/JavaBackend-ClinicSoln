
package com.clinicsoln.jersey;



//import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;

//import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.StreamingOutput;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import java.util.logging.Logger;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
*/
@Path("/echo")
@Produces(TEXT_PLAIN)
public class EchoEndpoint {
	
	private static final String filePath = "C:\\Users\\DEVANG\\eclipse-workspace\\ClinicSoln\\src\\main\\java\\com\\clinicsoln\\jersey\\dump.json";

    // ======================================
    // =          Injection Points          =
    // ======================================

    //@Inject
    //private Logger logger;

    // ======================================
    // =          Business methods          =
    // ======================================

    @GET
    @JWTTokenNeeded
    public Response echo(@QueryParam("message") String message) {
    	
    	 final Map<Integer, String> people  = new HashMap<>();
         people.put(1, "Michael");
         people.put(2, "Mark");
         
         StreamingOutput stream = new StreamingOutput() {
             @Override
             public void write(OutputStream os) throws IOException, WebApplicationException
             {
            	 JsonFactory jsonfactory = new JsonFactory(); 
            	// File jsonDoc = new File(path);
            	// JsonGenerator generator = jsonfactory.createJsonGenerator(jsonDoc, JsonEncoding.UTF8);

            	// Read more: https://javarevisited.blogspot.com/2015/03/parsing-large-json-files-using-jackson.html#ixzz5VciDvoEr
            	 
            	 
                 JsonGenerator jg = jsonfactory.createJsonGenerator( os, JsonEncoding.UTF8 );
                 jg.writeStartArray();

                 for ( Map.Entry<Integer, String> person : people.entrySet()  )
                 {
                     jg.writeStartObject();
                     jg.writeFieldName( "id" );
                     jg.writeString( person.getKey().toString() );
                     jg.writeFieldName( "name" );
                     jg.writeString( person.getValue() );
                     jg.writeEndObject();
                 }
                 jg.writeEndArray();

                 jg.flush();
                 jg.close();
             }
         };


         return Response.ok().entity( stream ).type( MediaType.APPLICATION_JSON ).build();
    }

    @GET
    @Path("jwt")
    @JWTTokenNeeded
    public Response echoWithJWTToken(@QueryParam("message") String message) throws FileNotFoundException,IOException,ParseException,NullPointerException {
    	
    	
    				FileReader reader = new FileReader(filePath);

    				JSONParser jsonParser = new JSONParser();
    				JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

    				// get a String from the JSON object
    				String firstName = (String) jsonObject.get("firstname");
    				System.out.println("The first name is: " + firstName);

    				// get a number from the JSON object
    				long id =  (long) jsonObject.get("id");
    				System.out.println("The id is: " + id);

    				// get an array from the JSON object
    				JSONArray lang= (JSONArray) jsonObject.get("languages");
    				
    				// take the elements of the json array
    				for(int i=0; i<lang.size(); i++){
    					System.out.println("The " + i + " element of the array: "+lang.get(i));
    				}
    				Iterator i = lang.iterator();

    				// take each value from the json array separately
    				while (i.hasNext()) {
    					JSONObject innerObj = (JSONObject) i.next(); 
    					System.out.println("language "+ innerObj.get("lang") + 
    							" with level " + innerObj.get("knowledge"));
    				}
    				// handle a structure into the json object
    				JSONObject structure = (JSONObject) jsonObject.get("job");
    				System.out.println("Into job structure, name: " + structure.get("name"));
    				

    	return Response.status(Status.OK).entity(jsonObject.toString()).build();        
    }
}
