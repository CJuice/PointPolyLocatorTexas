package gov.texas.tceq.LocationService;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
@ClassPreamble() 
/**
 * Creates a spatial data object containing all spatial related information. Also includes information from the 
 * geoprocessing service, such as zip code, lepc name, and output file location.
 */
@ManagedBean(name="sdo")
@RequestScoped
public class SpatialDataObject implements Serializable {
	
	//State
	private static final long serialVersionUID = 1L;
	
	//For NAD83 TXDOT Counties layer these are the bounding box decimal degree coordinates
	private static float[] texasLatitudeBounds = {25.837220f, 36.500380f};
	private static float[] texasLongitudeBounds = {-106.646220f, -93.507800f};
	private String latitude;
	private String longitude;
	private String errorMessage;
	private String nameLEPC;
	private String nameCounty;
	private String codeZCTA;
	private String filePath;
	private String serverInfo; //For detecting environment when deployed.
	
	//Constructor
	public SpatialDataObject(){
		setLatitude("Null");
		setLongitude("Null");
		setErrorMessage("Null");
		setNameLEPC("Null");
		setNameCounty("Null");
		setZipZCTA("Null");
		setServerInfo("Null");
	}
	
	//Behavior
	/**
	 * @param latitude The y geographic coordinate value, in decimal degrees. Assumed to be NAD83 by gp service code.
	 * @param longitude The x geographic coordinate value, in decimal degrees. Assumed to be NAD83 by gp service code.
	 * @return If coordinates are within the spatial bounding box for Texas then true, if not then false.
	 */
	boolean checkForValidSpatialCoordinates(String latitude, String longitude){
		float floatLatitude;
		float floatLongitude;
		if(latitude == null || latitude.isEmpty() || longitude == null || longitude.isEmpty()){
			return false;
		}
		try{
			floatLatitude = Float.parseFloat(latitude);
			floatLongitude = Float.parseFloat(longitude);
		}catch(NumberFormatException nfe){
			return false;
		}		
		if (floatLatitude <= texasLatitudeBounds[0] || floatLatitude >= texasLatitudeBounds[1] || floatLongitude <= texasLongitudeBounds[0] || floatLongitude >= texasLongitudeBounds[1]){
			return false;
		}
		else{
			return true;
		}
	}
	
	//Getters and Setters
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getNameLEPC() {
		return nameLEPC;
	}

	public void setNameLEPC(String nameLEPC) {
		this.nameLEPC = nameLEPC;
	}

	public String getNameCounty() {
		return nameCounty;
	}

	public void setNameCounty(String nameCounty) {
		this.nameCounty = nameCounty;
	}

	public String getZipZCTA() {
		return codeZCTA;
	}

	public void setZipZCTA(String zipZCTA) {
		this.codeZCTA = zipZCTA;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getServerInfo() {
		return serverInfo;
	}

	public void setServerInfo(String serverInfo) {
		this.serverInfo = serverInfo;
	}

	//ToString
	@Override
	public String toString() {
		return "SpatialDataObject [latitude=" + latitude + ", longitude=" + longitude + ", errorMessage=" + errorMessage
				+ ", nameLEPC=" + nameLEPC + ", nameCounty=" + nameCounty + ", codeZCTA=" + codeZCTA + ", filePath="
				+ filePath + ", serverInfo=" + serverInfo + "]";
	}
	
	//Hashcode & Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeZCTA == null) ? 0 : codeZCTA.hashCode());
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((nameCounty == null) ? 0 : nameCounty.hashCode());
		result = prime * result + ((nameLEPC == null) ? 0 : nameLEPC.hashCode());
		result = prime * result + ((serverInfo == null) ? 0 : serverInfo.hashCode());
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
		SpatialDataObject other = (SpatialDataObject) obj;
		if (codeZCTA == null) {
			if (other.codeZCTA != null)
				return false;
		} else if (!codeZCTA.equals(other.codeZCTA))
			return false;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (nameCounty == null) {
			if (other.nameCounty != null)
				return false;
		} else if (!nameCounty.equals(other.nameCounty))
			return false;
		if (nameLEPC == null) {
			if (other.nameLEPC != null)
				return false;
		} else if (!nameLEPC.equals(other.nameLEPC))
			return false;
		if (serverInfo == null) {
			if (other.serverInfo != null)
				return false;
		} else if (!serverInfo.equals(other.serverInfo))
			return false;
		return true;
	}
}
