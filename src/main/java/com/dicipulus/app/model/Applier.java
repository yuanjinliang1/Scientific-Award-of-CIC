package com.dicipulus.app.model;

public class Applier extends Person {
	
	String role="applier";
	
	Referee owner;
	
	String group;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Referee getOwner() {
		return owner;
	}

	public void setOwner(Referee owner) {
		this.owner = owner;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
}