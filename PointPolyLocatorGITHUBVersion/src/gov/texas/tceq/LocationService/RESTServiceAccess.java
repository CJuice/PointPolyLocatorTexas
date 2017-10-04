package gov.texas.tceq.LocationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
@ClassPreamble()

public class RESTServiceAccess {
	
	/**
	 * Access a REST service
	 */
	// State
	private String connectionRequestMethodString = "GET";
	private String beginningErrorStatement = "Error establishing ";
	
	// Constructor
	public RESTServiceAccess() {
	}
	
	/**
	 * 
	 * @param restURL URL for REST service
	 * @return String representation of response from rest service
	 */
	// Behavior
	public String sendRequest(String restURL){
		String urlString = restURL;
		URL urlObj = null;
		try {
			urlObj = new URL(urlString);
		} catch (MalformedURLException e) {
			System.out.println(getBeginningErrorStatement() + "URL object.");
			e.printStackTrace();
		}
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) urlObj.openConnection();
		} catch (IOException e) {
			System.out.println(getBeginningErrorStatement() + "HttpURLConnection object.");
			e.printStackTrace();
		}
		try {
			con.setRequestMethod(getConnectionRequestMethodString());
		} catch (ProtocolException e) {
			System.out.println(getBeginningErrorStatement() + "GET protocol.");
			e.printStackTrace();
		}
		int responseCode = 0;
		try {
			responseCode = con.getResponseCode();
		} catch (IOException e) {
			System.out.println("Error Http response code.");
			e.printStackTrace();
		}
		
		//TESTING
//		System.out.println("\nSending GET request to URL : " + urlString);
//		System.out.println("Response Code : " + responseCode);

		if(responseCode != 200){
			throw new RuntimeException("HttpResponseCode: " + responseCode);
		}
		else {		
			StringBuffer response = new StringBuffer();
			try(BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())))
			{
				String inputLine;		
				while((inputLine = in.readLine()) != null){
					response.append(inputLine);
				}
			} catch(IOException ioE) {
				System.out.println("IO Exception with REST interaction.");
				ioE.printStackTrace();
			}
			return response.toString();
		}
		
	}

	public String getConnectionRequestMethodString() {
		return connectionRequestMethodString;
	}

	public void setConnectionRequestMethodString(String connectionRequestMethodString) {
		this.connectionRequestMethodString = connectionRequestMethodString;
	}

	public String getBeginningErrorStatement() {
		return beginningErrorStatement;
	}

	public void setBeginningErrorStatement(String beginningErrorStatement) {
		this.beginningErrorStatement = beginningErrorStatement;
	}

	//ToString
	@Override
	public String toString() {
		return "RESTServiceAccess [connectionRequestMethodString=" + connectionRequestMethodString
				+ ", beginningErrorStatement=" + beginningErrorStatement + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beginningErrorStatement == null) ? 0 : beginningErrorStatement.hashCode());
		result = prime * result
				+ ((connectionRequestMethodString == null) ? 0 : connectionRequestMethodString.hashCode());
		return result;
	}
	
	//Hashcode and Equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RESTServiceAccess other = (RESTServiceAccess) obj;
		if (beginningErrorStatement == null) {
			if (other.beginningErrorStatement != null)
				return false;
		} else if (!beginningErrorStatement.equals(other.beginningErrorStatement))
			return false;
		if (connectionRequestMethodString == null) {
			if (other.connectionRequestMethodString != null)
				return false;
		} else if (!connectionRequestMethodString.equals(other.connectionRequestMethodString))
			return false;
		return true;
	}
	
	
	
}