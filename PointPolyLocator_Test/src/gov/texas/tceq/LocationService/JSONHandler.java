package gov.texas.tceq.LocationService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
@ClassPreamble()

public class JSONHandler {

	/**
	 * Processes the JSON response from the geoprocessing service.
	 */
	
	// State
	private String filePath;
	private String errorMessage;
	private String paramNameKeyString = "paramName";
	private String lepcKey = "lepc";
	private String countyKey = "county";
	private String zipKey = "zip";
	private String errorMessageKey = "message";
	
	// Constructor
	public JSONHandler() {
		setErrorMessage("");
		setFilePath("");
	}

	// Behavior
	/**
	 * 
	 * @param inJSON Response from geoprocessing service; it is json format
	 */
	void parseJSON(String inJSON) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject_1 = null;
		JSONObject myJSONFileObject = null;
		JSONObject myErrorMessageObject = null;
		try {
			jsonObject_1 = (JSONObject) parser.parse(inJSON);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONArray jsonArray = (JSONArray) jsonObject_1.get("results");

		for(int i = 0; i < jsonArray.size(); i++){
			JSONObject jsonObject_2 = (JSONObject)jsonArray.get(i);
			if(jsonObject_2.get(getParamNameKeyString()).equals("file")){
				myJSONFileObject = jsonObject_2;
			}
			else if(jsonObject_2.get(getParamNameKeyString()).equals("Error_Message")){
				myErrorMessageObject = jsonObject_2;
			}
			else{}
		}
		parseFileUrlValue(myJSONFileObject);
		parseErrorMessage(myErrorMessageObject);
	}
	
	/**
	 * 
	 * @param fileURLObject URL of output file, containing the zip code and LEPC name results from the geoprocessing service.
	 */
	void parseFileUrlValue(JSONObject fileURLObject){
		JSONObject fileObject = (JSONObject)fileURLObject.get("value");
		String urlString = (String) fileObject.get("url");
		urlString = urlString.replace("\\", "");
		this.setFilePath(urlString);
	}
	
	/**
	 * 
	 * @param errorMessageObject If the geoprocessing service encounters issues and generates an error message, 
	 * it will be stored in this object taken from the JSON response
	 */
	void parseErrorMessage(JSONObject errorMessageObject){
		this.setErrorMessage((String) errorMessageObject.get("Error_Message"));
	}
	
	/**
	 * 
	 * @param zipCodeName Name of the Zip Code where the point is located
	 * @param lepcName Name of the LEPC where the point is located
	 * @param countyName Name of the TX county where the point is located
	 * @param errorMessage Any error messages passed from the gp service or the java
	 * @return JSON Object containing the three input parameters.
	 */
	@SuppressWarnings("unchecked")
	public JSONObject buildJSONObject(String zipCode, String lepcName, String countyName, String errorMessage){
		JSONObject obj = new JSONObject();
		obj.put(getLepcKey(), lepcName);
		obj.put(getCountyKey(), countyName);
		obj.put(getZipKey(), zipCode);
        obj.put(getErrorMessageKey(), errorMessage);
        return obj;
	}

	// Getters and Setters
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getParamNameKeyString() {
		return paramNameKeyString;
	}

	public void setParamNameKeyString(String paramNameKeyString) {
		this.paramNameKeyString = paramNameKeyString;
	}

	public String getLepcKey() {
		return lepcKey;
	}

	public void setLepcKey(String lepcKey) {
		this.lepcKey = lepcKey;
	}

	public String getCountyKey() {
		return countyKey;
	}

	public void setCountyKey(String countyKey) {
		this.countyKey = countyKey;
	}

	public String getZipKey() {
		return zipKey;
	}

	public void setZipKey(String zipKey) {
		this.zipKey = zipKey;
	}

	public String getErrorMessageKey() {
		return errorMessageKey;
	}

	public void setErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
	}

	
	//ToString
	@Override
	public String toString() {
		return "JSONHandler [filePath=" + filePath + ", errorMessage=" + errorMessage + ", paramNameKeyString="
				+ paramNameKeyString + ", lepcKey=" + lepcKey + ", countyKey=" + countyKey + ", zipKey=" + zipKey
				+ ", errorMessageKey=" + errorMessageKey + "]";
	}
	
	//Hashcode and Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countyKey == null) ? 0 : countyKey.hashCode());
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result + ((errorMessageKey == null) ? 0 : errorMessageKey.hashCode());
		result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
		result = prime * result + ((lepcKey == null) ? 0 : lepcKey.hashCode());
		result = prime * result + ((paramNameKeyString == null) ? 0 : paramNameKeyString.hashCode());
		result = prime * result + ((zipKey == null) ? 0 : zipKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JSONHandler other = (JSONHandler) obj;
		if (countyKey == null) {
			if (other.countyKey != null)
				return false;
		} else if (!countyKey.equals(other.countyKey))
			return false;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		if (errorMessageKey == null) {
			if (other.errorMessageKey != null)
				return false;
		} else if (!errorMessageKey.equals(other.errorMessageKey))
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (lepcKey == null) {
			if (other.lepcKey != null)
				return false;
		} else if (!lepcKey.equals(other.lepcKey))
			return false;
		if (paramNameKeyString == null) {
			if (other.paramNameKeyString != null)
				return false;
		} else if (!paramNameKeyString.equals(other.paramNameKeyString))
			return false;
		if (zipKey == null) {
			if (other.zipKey != null)
				return false;
		} else if (!zipKey.equals(other.zipKey))
			return false;
		return true;
	}
	
}
