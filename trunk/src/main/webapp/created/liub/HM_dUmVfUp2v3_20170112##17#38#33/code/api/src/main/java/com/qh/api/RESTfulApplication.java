package com.qh.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/drugService/1.0.0")
public class RESTfulApplication extends Application {
	 
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<Class<?>>();
		try {
				resources.add(Class.forName("com.qh.api.drug.service.DrugBasicServiceImpl"));
				resources.add(Class.forName("com.qh.api.drug.service.DrugDestroyServiceImpl"));
				resources.add(Class.forName("com.qh.api.drug.service.DrugInStorageServiceImpl"));
				resources.add(Class.forName("com.qh.api.drug.service.DrugManagerServiceImpl"));
				resources.add(Class.forName("com.qh.api.drug.service.DrugOrderServiceImpl"));
				resources.add(Class.forName("com.qh.api.drug.service.DrugReceptionServiceImpl"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resources;
	}
	
}
