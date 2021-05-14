package com.Service;

import com.Model.Research;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.Controller.*;


import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;

@Path("/researchServices")

//TO READ DATA FROM researcher TABLE
public class ResearchServices {

ResearchController newResearch = new ResearchController();
	
	@GET
	@Path("/readResearch")
	@Produces(MediaType.TEXT_HTML)
	public String readResearch() {
		
		return newResearch.readResearch();
	}
	
	//TO INSERT DATA FOR researcher TABLE
	
		@POST
		@Path("/insertResearch")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		
		
		public String insertResearch(String researchData)
		{
		
			//convert the input string to a JSON object
			JsonObject researchObject = new JsonParser().parse(researchData).getAsJsonObject();
		
			//READ THE VALUES FROM HE JSON OBJECT
			
			int userid = researchObject.get("userid").getAsInt();
			String name = researchObject.get("name").getAsString();
			String description = researchObject.get("description").getAsString();
			String status = researchObject.get("status").getAsString();
			double budget = researchObject.get("budget").getAsDouble();
			
			Research research = new Research(userid,name,description,status,budget);
			
			String output = newResearch.insertResearch(research);
			return output;	
		}
		
		//TO UPDATE researcher TABLE
		
		@PUT
		@Path("/updateResearch")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String updateResearch(String researcherData) {
			
			JsonObject researchObject = new JsonParser().parse(researcherData).getAsJsonObject();
			
			//READ VALUES FROM THE JSON OBJECT
			String rid = researchObject.get("rid").getAsString();
			String userid = researchObject.get("userid").getAsString();
			String name = researchObject.get("name").getAsString();
			String description = researchObject.get("description").getAsString();
			String status = researchObject.get("status").getAsString();
			String budget = researchObject.get("budget").getAsString();
			
			String output = newResearch.updateResearch(rid, userid, name, description, status, budget);
			return output;
		}
		
		//TO DELETE VALUES FROM RESEARCHER TABLE
		
		@DELETE
		@Path("/deleteResearch")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String deleteResearcher(String researchData) {
		
			JsonObject researchObject = new JsonParser().parse(researchData).getAsJsonObject();
			
			String researchID = researchObject.get("rid").getAsString();
			
			String output = newResearch.deleteResearch(researchID);
			return output;
			
			
		}
}
