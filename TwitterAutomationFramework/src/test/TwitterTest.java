package test;


import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import util.RestUtil;

public class TwitterTest extends BaseTest {
RestUtil util;
	
	@BeforeClass
	public void beforeClass() throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ClientProtocolException, IOException{
		util=new RestUtil();
		util.getRequest("/statuses/user_timeline.json?","yutstam","1");
	}
	
	@Test
	public void statusCodeTest(){
		System.out.println("Status code:"+util.statusCode());
		Assert.assertEquals(200, util.statusCode());
	}
	
	@Test
	public void textTest(){
		System.out.println("Tweet:"+util.text());
		Assert.assertEquals("Rest Automation HttpClient", util.text());
	}
	
	@Test
	public void idTest(){
		System.out.println("ID:" +util.id());
		Assert.assertEquals("780975571505262592", util.id());
	}
	

}
