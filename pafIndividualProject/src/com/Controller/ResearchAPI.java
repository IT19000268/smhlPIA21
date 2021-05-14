package com.Controller;


import com.Controller.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


/**
 * Servlet implementation class ResearchAPI
 */
@WebServlet("/ResearchAPI")
public class ResearchAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResearchAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		
		//FIND THE ERROR
		String output = newResearch.insertResearch(request.getParameter("userId"), 
				request.getParameter("rName"), 
				request.getParameter("rDesc"), 
				request.getParameter("rStatus"),
				request.getParameter("rBudget"));
		
		response.getWriter().write(output);
	}

	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
		 String[] p = param.split("=");
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
		}

	 
		 
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Map paras = getParasMap(request); 
		
		
		//FIND THE ERROR
		String output = newResearch.updateResearch(paras.get("hidItemIDSave").toString(), 
				 paras.get("userId").toString(), 
				 paras.get("rName").toString(), 
				 paras.get("rDesc").toString(),
				paras.get("rStatus").toString(), 
				paras.get("rBudget").toString()
				); 
				response.getWriter().write(output);
		
		
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		
		//FIND THE ERROR
		 String output = newResearch.deleteResearch(paras.get("rId").toString()); 
		response.getWriter().write(output); 
		
	}

}
