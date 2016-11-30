package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import pojo.MyPojo;
import pojo.User;

public class RestUtil {
	HttpClient client;
	HttpUriRequest request;
	HttpResponse response;

	String URI = Configuration.URI;
	String ConsumerKey=Configuration.CONSUMER_KEY;
	String ConsumerSecret=Configuration.CONSUMER_SECRET;
	String Token=Configuration.TOKEN;
	String TokenSecret=Configuration.TOKEN_SECRET;
	

	private MyPojo twitterUser;
	
	public MyPojo getTwitterUser() {
		return twitterUser;
	}

	public void setTwitterUser(MyPojo twitterUser) {
		this.twitterUser = twitterUser;
	}

	public void addAuthentication() throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException{
		OAuthConsumer consumer=new CommonsHttpOAuthConsumer(ConsumerKey, ConsumerSecret);
		consumer.setTokenWithSecret(Token, TokenSecret);
		consumer.sign(request);
	}

	public void getRequest(String resourceUrn,String screenName,String count) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException{
		client = HttpClientBuilder.create().build();
		request=new HttpGet(URI+resourceUrn+"screen_name=" +screenName +"&count=" +count);
		request.addHeader(HttpHeaders.CONTENT_TYPE,"application/json");
		addAuthentication();
		try {
			response=client.execute(request);
			if(response!=null){
				setTwitterUser(ResourceUtil.retrieveResource(response, MyPojo.class));
				
			}
			else{
				System.out.println("Error" +response);
			}
		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	

	public String id(){
		return twitterUser.getId();
	}

	public int statusCode(){
		return response.getStatusLine().getStatusCode();
	}
	
	public String text(){
		return twitterUser.getText();
	}
}
