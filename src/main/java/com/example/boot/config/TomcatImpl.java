package com.example.boot.config;

import org.apache.catalina.Engine;
import org.apache.catalina.Realm;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.startup.Tomcat;

public class TomcatImpl extends Tomcat {

	public void setServer(Server server) {
		this.server = server;
	}
	
	public void setService(Service service) {
		this.service = service;
	}
	
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	public Realm createDefaultRealm() {
		return super.createDefaultRealm();
	}
}
