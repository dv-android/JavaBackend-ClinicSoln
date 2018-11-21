
package com.clinicsoln.jersey;



//import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
*/
@Path("/echo")
@Produces(TEXT_PLAIN)
public class EchoEndpoint {
	
	private static final String filePath = "C:\\Users\\DEVANG\\eclipse-workspace\\ClinicSoln\\src\\main\\java\\com\\clinicsoln\\jersey\\dump.json";
	
	private ResultSet rs;

	
	
	@GET
	@Path("getPatientDetail")
	
	public Response getPatientDetail(@QueryParam("patient_id") String patient_id) throws IOException,SQLException,Exception{
	 
	 rs = DBConnection.getPatient("Ravi");
	 while (rs.next()) {
			System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
			
		}
		
	 return Response.ok().entity("gfdgfdg").type(TEXT_PLAIN).build();

	
	} 

    @GET
    @JWTTokenNeeded
    public Response echo(@QueryParam("message") String message)  {
    	        
         StreamingOutput stream = new StreamingOutput() {
             @Override
             public void write(OutputStream os) throws IOException, WebApplicationException 
             {
            	 JsonFactory jsonfactory = new JsonFactory();             	 
            	 try {
            		 
		            		 JsonGenerator jg = jsonfactory.createJsonGenerator( os, JsonEncoding.UTF8 );
		                     jg.writeStartArray();
			            	 rs = DBConnection.getPatient("Ravi");
			            	 			            	 
			            	 
			            	 while (rs.next()) {	            			
			            			jg.writeStartObject();
			                        jg.writeFieldName( "patient_id" );
			                        jg.writeString(rs.getString(1));
			                        jg.writeFieldName( "name" );
			                        jg.writeString(rs.getString(2));
			                        jg.writeFieldName( "mobile" );
			                        jg.writeString(rs.getString(3));
			                        jg.writeFieldName( "email" );
			                        jg.writeString(rs.getString(4));
			                        jg.writeFieldName( "aadhar_no" );
			                        jg.writeString(rs.getString(5));
			                        jg.writeFieldName( "address" );
			                        jg.writeString(rs.getString(6));
			                        jg.writeFieldName( "pincode" );
			                        jg.writeString(rs.getString(7));
			                        jg.writeFieldName( "weight" );
			                        jg.writeString(rs.getString(8));
			                        jg.writeFieldName( "age" );
			                        jg.writeString(rs.getString(9));
			                        jg.writeFieldName( "gender" );
			                        jg.writeString(rs.getString(10));
			                        jg.writeFieldName( "temprature" );
			                        jg.writeString(rs.getString(11));
			                        jg.writeFieldName( "low_bp" );
			                        jg.writeString(rs.getString(12));
			                        jg.writeFieldName( "high_bp" );
			                        jg.writeString(rs.getString(13));
			                        jg.writeFieldName( "symptoms" );
			                        jg.writeString(rs.getString(14));
			                        jg.writeFieldName( "consultant" );
			                        jg.writeString(rs.getString(15));
			                        jg.writeFieldName( "appointment_time" );
			                        jg.writeString(rs.getString(16));
			                        jg.writeFieldName( "visit_status" );
			                        jg.writeString(rs.getString(17));
			                        jg.writeFieldName( "doc_id" );
			                        jg.writeString(rs.getString(18));
			                        jg.writeEndObject();
			            			
			            		}
			            	 
			            	 jg.writeEndArray();

			                 jg.flush();
			                 jg.close();
			                 
			                 DBConnection.closeAllConnection();			                 
			                 
            	 }
            	 catch(SQLException e) {
            		 
            	 }
            	 catch(IOException e) {
            		 
            	 }
            	 catch(Exception e) {
            		 
            	 }
                 

             }
         };


         return Response.ok().entity( stream ).type( MediaType.APPLICATION_JSON ).build();
    }
    
    @GET
    @Path("pushNotification")
    public Response pushNotifications() throws IOException {
    	
    	Result result = null;
    	
    	Sender sender = new Sender("AIzaSyAlSLKGD8qNX3eznOsifEz1ijZ_jTwzOSo");
    	Message message = new Message.Builder().timeToLive(30)
				.delayWhileIdle(true).addData("Message", "Jay Swami Narayan").build();
    	
    	result = sender.send(message, "fdsfdfsdfdsf", 1);
    	
    	
    	return Response.ok().entity("gfdgfdg").type(TEXT_PLAIN).build();
    	
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
