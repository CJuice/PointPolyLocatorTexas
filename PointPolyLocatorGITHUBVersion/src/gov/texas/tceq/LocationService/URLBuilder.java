package gov.texas.tceq.LocationService;

@ClassPreamble()
/**
 * 
 * Builds the URL for the geoprocessing service
 */
public class URLBuilder {
	/**
	 * 
	 * @param environmentCommonName
	 *            Common name for the environment where the java war file is
	 *            deployed. [Development, Test, Production]
	 * @param latitude
	 *            The y geographic coordinate value, in decimal degrees. Assumed
	 *            to be NAD83 by gp service code.
	 * @param longitude
	 *            The x geographic coordinate value, in decimal degrees. Assumed
	 *            to be NAD83 by gp service code.
	 * @return URL for the appropriate geoprocessing service on arcserver
	 */
	// Build the url to hit the geoprocessing service
	static String buildGeoprocessingServiceURL(String environmentCommonName, String latitude, String longitude) {
		String longitudeQueryStringParameter = "&lon=";
		String urlEndingString = "&env%3AoutSR=&env%3AprocessSR=&returnZ=false&returnM=false&returnTrueCurves=false&f=pjson";
		String devURLPartI = "INSERT YOUR GEOPROCESSING SERVICE URL HERE /execute?lat=";
		String testURLPartI = "INSERT YOUR GEOPROCESSING SERVICE URL HERE /execute?lat=";

		String prodURLPartI = "INSERT YOUR GEOPROCESSING SERVICE URL HERE /execute?lat=";

		/*
		 * Server Environment Detection
		 */
		if (environmentCommonName.equals("Development")) {
			// dev or local
			String[] geoprocessingServiceURLParts = { devURLPartI, latitude, longitudeQueryStringParameter, longitude,
					urlEndingString };
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < geoprocessingServiceURLParts.length; i++) {
				stringBuilder.append(geoprocessingServiceURLParts[i]);
			}
			String completeURL = stringBuilder.toString();
			return completeURL;
		} else if (environmentCommonName.equals("Test")) {

			// test
			// Problem Solution: using the fully qualified open path to the
			// magst1 machine, the web servers (either t1 or t2) can access the
			// service on arcserver.
			String[] geoprocessingServiceURLParts = { testURLPartI, latitude, longitudeQueryStringParameter, longitude,
					urlEndingString };
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < geoprocessingServiceURLParts.length; i++) {
				stringBuilder.append(geoprocessingServiceURLParts[i]);
			}
			String completeURL = stringBuilder.toString();
			return completeURL;
		} else {

			// prod
			String[] geoprocessingServiceURLParts = { prodURLPartI, latitude, longitudeQueryStringParameter, longitude,
					urlEndingString };
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < geoprocessingServiceURLParts.length; i++) {
				stringBuilder.append(geoprocessingServiceURLParts[i]);
			}
			String completeURL = stringBuilder.toString();
			return completeURL;
		}
	}

}
