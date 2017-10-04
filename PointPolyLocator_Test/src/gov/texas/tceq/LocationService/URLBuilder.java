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
		String devURLPartI = "http://tceq-sap-mwgd01.tceq.texas.gov/arcgis/rest/services/Tier_II/TierIIPointPolyLocatorZipLepcCounty/GPServer/Tier%20II%20Point%20Poly%20Locator%20zip%20lepc/execute?lat=";
		String testURLPartI = "http://tceq4spmagst1.tceq.texas.gov:6080/arcgis/rest/services/Tier_II/TierIIPointPolyLocatorZipLepcCounty/GPServer/Tier%20II%20Point%20Poly%20Locator%20zip%20lepc/execute?lat=";

		// TODO: Switch the test url for the prod url, once published.
		String prodURLPartI = "http://tceq4spmagst1.tceq.texas.gov:6080/arcgis/rest/services/Tier_II/TierIIPointPolyLocatorZipLepcCounty/GPServer/Tier%20II%20Point%20Poly%20Locator%20zip%20lepc/execute?lat=";

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

			// Problem Fix Attempt 1: Can't use the secure version of our
			// service. Don't remember the exact reason why. Tony K. explained.
			// String[] geoprocessingServiceURLParts = {
			// "https://tceq4spmagst1:6443/arcgis/rest/services/Tier_II/TierIIPointPolyLocator/GPServer/Tier%20II%20Point%20Poly%20Locator/execute?lat=",
			// latitude, "&lon=", longitude,
			// "&env%3AoutSR=&env%3AprocessSR=&returnZ=false&returnM=false&returnTrueCurves=false&f=pjson"
			// };

			// Initial Problem: From the web you access the services using
			// giswebtest but the servers in web need the server name to get to
			// the arcserver
			// services. Otherwise you are telling the web servers to go back
			// through the load balancer to get to arcserver and this isn't
			// possible.
			// String[] geoprocessingServiceURLParts = {
			// "https://giswebtest.tceq.texas.gov/arcgis/rest/services/Tier_II/TierIIPointPolyLocator/GPServer/Tier%20II%20Point%20Poly%20Locator/execute?lat=",
			// latitude, "&lon=", longitude,
			// "&env%3AoutSR=&env%3AprocessSR=&returnZ=false&returnM=false&returnTrueCurves=false&f=pjson"
			// };

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

		// "MANUAL" WORKAROUND WHEN ENVIRONMENT DETECTION NOT WANTED

		/*
		 * DEV Environment and ALL OTHER ENVIRONMENTS UNTIL ISSUE IS FIXED
		 */
		// String[] geoprocessingServiceURLParts = {devURLPartI, latitude,
		// longitudeQueryStringParameter, longitude, urlEndingString};
		// StringBuilder stringBuilder = new StringBuilder();
		// for (int i = 0; i < geoprocessingServiceURLParts.length; i++) {
		// stringBuilder.append(geoprocessingServiceURLParts[i]);
		// }
		// String completeURL = stringBuilder.toString();
		// return completeURL;

		/*
		 * TEST Environment
		 */
		// String[] geoprocessingServiceURLParts = {testURLPartI, latitude,
		// longitudeQueryStringParameter, longitude,
		// urlEndingString };
		// StringBuilder stringBuilder = new StringBuilder();
		// for (int i = 0; i < geoprocessingServiceURLParts.length; i++) {
		// stringBuilder.append(geoprocessingServiceURLParts[i]);
		// }
		// String completeURL = stringBuilder.toString();
		// return completeURL;

		/*
		 * PROD Environment
		 */
		// String[] geoprocessingServiceURLParts = {prodURLPartI, latitude,
		// longitudeQueryStringParameter, longitude, urlEndingString };
		// StringBuilder stringBuilder = new StringBuilder();
		// for (int i = 0; i < geoprocessingServiceURLParts.length; i++) {
		// stringBuilder.append(geoprocessingServiceURLParts[i]);
		// }
		// String completeURL = stringBuilder.toString();
		// return completeURL;
	}

}
