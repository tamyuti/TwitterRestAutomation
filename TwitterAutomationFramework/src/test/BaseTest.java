package test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import util.Configuration;

public abstract class BaseTest {
Configuration _config;
	
	@BeforeSuite
	public void beforeSuite(){
		_config=new Configuration();
	}
	
	
	@AfterSuite
	public void afterSuite(){
		_config=null;
	}

}
