package com.dicipulus.app.model;

import java.util.ArrayList;
import java.util.List;

public class Applications {
	private List<Application> applicationList;

	public Applications() {
		this.applicationList = new ArrayList<Application>();
	}

	public List<Application> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(List<Application> applicationList) {
		this.applicationList = applicationList;
	}
	
	
	
}
