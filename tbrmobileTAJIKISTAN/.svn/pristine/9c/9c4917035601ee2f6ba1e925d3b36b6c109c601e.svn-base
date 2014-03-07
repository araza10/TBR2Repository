package org.irdresearch.tbreach.mobile.util;

import javax.microedition.location.Coordinates;
import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationProvider;

public class GPSUtil {
	
	private Double lat;
	private Double lng;
	
	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	public GPSUtil() {
		lat = new Double(-1);
		lng = new Double(-1);
	}

	public void getLocation() throws LocationException, InterruptedException {
		// Set criteria for selecting a location provider:
		// accurate to 500 meters horizontally
		/*if(!GPSUtil.isGPSAvailable())
			return;*/
		
		Criteria cr= new Criteria();
		cr.setHorizontalAccuracy(500);

		// Get an instance of the provider
		LocationProvider lp= LocationProvider.getInstance(cr);

		// Request the location, setting a one-minute timeout
		Location l = lp.getLocation(60);
		Coordinates c = l.getQualifiedCoordinates();

		if(c != null ) {
		  // Use coordinate information
		   lat = new Double(c.getLatitude());
		   lng = new Double(c.getLongitude());
		}
	
	}
	
	

}
