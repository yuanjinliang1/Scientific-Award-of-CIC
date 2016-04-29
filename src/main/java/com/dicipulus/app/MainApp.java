package com.dicipulus.app;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main (String[] fvk){
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		DicipulusJDBC studentJDBC =(DicipulusJDBC)context.getBean("studentJDBC");
		
		System.out.println("----Record Creation----");
		studentJDBC.create("Polen", 1878);
		studentJDBC.create("deutsch", 1945);
		
		System.out.println("-----Listing ---");
		List<Dicipulus> students =studentJDBC.listDicipulus();
		for(Dicipulus record: students){
			System.out.println("ID: "+record.getId());
			System.out.println("Name: "+record.getName());
			System.out.println("Phone: "+record.getPhone());
		}
	}
}