package com.Controller;

import java.sql.*;

import com.Model.Research;

public class ResearchController {
	
	//newly added.
	private Research research;


	private Connection connect() {
		
		Connection con = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver"); 
			//Provide the correct details: DBServer/DBName, user name, password 
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gbresearchdb", "root", ""); 
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;	
	}
	
	//CREATING A CONNECTION FOR READING DATA FROM RESEARCHER TABLE
	
	public String readResearch() {
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if(con == null) {
				return "error while connecting to the database for reading data";
			}
			
			//HTML TABLE TO DISPLAY
			output = "<table border='1'><tr><th>Researcher ID</th><th>User ID</th><th>Researcher Name</th><th>Description</th><th>Status ID</th><th>Proposed Budget</th></tr>";
			
			String query = "SELECT * FROM research";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
		//ITERATE THROUGH ROWS IN THE RESULT SET
			while(rs.next()) {
				
				int rid = rs.getInt("rid");
				int userid =rs.getInt("userid");
				String name = rs.getString("name");
				String description = rs.getString("description");
				String status = rs.getString("status");
				Double budget= rs.getDouble("budget");
				
				//ADD INTO HTML TABLE
				
				output += "<tr><td>" + rid +"</td>";
				output += "<td>" + userid +"</td>";
				output += "<td>" + name +"</td>";
				output += "<td>" + description +"</td>";
				output += "<td>" + status +"</td>";
				output += "<td>" + budget +"</td>";
				
				
				//HTML FOR BUTTONS
				
				
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary data-rid'" + rid + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger'"
						+ "data-rId='" + rid + "'>" + "</td></tr>";
						
							
			}
			con.close();
		
			output +="</table>";
		
		}
		catch(Exception e) {
			output = "error while connecting to the database for reading data";
			System.err.print(e.getMessage());
		}
		return output;
	}
	
	//CREAING A METHOD TO INSERT RESEARCH TABLE DATA
	
		public String insertResearch(String userid, String name, String description, String status, String budget) {
			
			String output = "";
			
			try {
				Connection con = connect();
				if(con == null) {
					return "error while connecting to the researcher database when inserting data";	
				}
				
				//CREATE A PREPARED STATEMENT
				String query =" insert into gbresearchdb.research (`rid`,`userid`,`name`,`description`,`status`,`budget`)"
						+ " values (?,?,?,?,?,?)"; 
				 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				//BINDING VALUES
				
				preparedStmt.setInt(1, 0);
			
				preparedStmt.setInt(2, research.getUserid());
				preparedStmt.setString(3, research.getName());
				preparedStmt.setString(4, research.getDescription());
				preparedStmt.setString(5, research.getStatus());
				preparedStmt.setDouble(6, research.getBudget());
				
				//EXECUTE THE STATEMENTS
				
				preparedStmt.execute();
				con.close();
				
				String newResearch = readResearch();
				output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}";
				
				
				
			}
			catch(Exception e) {
				
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the information.\"}"; 
				
				System.err.println(e.getMessage());
			}
			return output;	
		}
		
		//CREATING A METHOD TO UPDATE RESEARCHER TABLE DATA
		
		public String updateResearch(String rid,String userid, String name, String description, String status, String budget) {
			String output = "";
			try {
				Connection con = connect();
				
				if(con == null) {
					return "error while connecting to the database when updating research information";
				}
				
				//CREATE A PREPARED STATEMENT
				String query = "UPDATE research SET userid=?,name=?,description=?,status=?,budget=? WHERE rid=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				//BINDING VALUES
				
				preparedStmt.setInt(1,Integer.parseInt(userid));
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, description);
				preparedStmt.setString(4, status);
				preparedStmt.setDouble(5,Double.parseDouble(budget));
				preparedStmt.setInt(6, Integer.parseInt(rid));
				
				//EXECUTE THE STATEMENT
				
				preparedStmt.execute();
				con.close();
				
				String newResearch = readResearch();
				
				output = "{\"status\":\"success\", \"data\": \"" + newResearch + "\"}";
			}
			catch(Exception e) {
				
				output = "{\"status\":\"error\",\"data\": \"Error while updating the information.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		//CREATING A METHOD TO DELETE RESEARCHER TABLE DATA
		
		public String deleteResearch(String rid) {
			
			String output="";
			
			try {
				Connection con = connect();
				if(con == null) {
					return "error while deleting research data";
				}
				
				//create a prepared statement
				String query = "DELETE FROM research WHERE rid=?";
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				//BINDING VALUES
				preparedStmt.setInt(1, Integer.parseInt(rid));
				
				//EXECUTE THE STATEMENT
				preparedStmt.execute();
				con.close();
						
				String newResearch = readResearch();
				output = "{\"status\":\"status\", \"data\": \"" + newResearch + "\"}";
			}
			catch(Exception e) {
							
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the information\"}";
				
				System.err.println(e.getMessage());
			}
			return output;
		}
}
