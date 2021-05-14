package com.Model;

public class Research {
	
	//research id,user id, name, description, status and the estimated price of the project 
		//by the researcher are the attributes of the researcher
	
	private int rid;
	private int userid;
	private String name;
	private String description;
	private String status;
	private double budget;
	
	public Research() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Research(int rid, int userid, String name, String description, String status, double budget) {
		super();
		this.rid = rid;
		this.userid = userid;
		this.name = name;
		this.description = description;
		this.status = status;
		this.budget = budget;
	}
	
	public Research(int userid, String name, String description, String status, double budget) {
		super();
		this.userid = userid;
		this.name = name;
		this.description = description;
		this.status = status;
		this.budget = budget;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

}
