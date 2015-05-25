package pl.szajka;

public class WuObservationLocation {
	public String latitude;
	public String longitude;
	public float lat;
	public float lon;
	public float elevation_m;
	public float elevation_ft;
	public String country;
	public String city;
	
	public WuObservationLocation(String country, String city, String latitude, String longitude, String elevation) {
		this.country = country;
		this.city = city;
		this.lat = Float.parseFloat(latitude);
		this.lon = Float.parseFloat(longitude);
		String[] partStr = elevation.split(" ");
		this.elevation_ft = Float.parseFloat(partStr[0]);
		
		if(lat>=0.0) 
			this.latitude = Float.toString(lat) + "⁰ N";
		else
			this.latitude = Float.toString(lat*(-1.0f)) + "⁰ S";
		if(lon>=0.0)
			this.longitude = Float.toString(lon) + "⁰ E";
		else
			this.longitude = Float.toString(lon*(-1.0f)) + "⁰ W";
		
		this.elevation_m = 0.3048f * elevation_ft;
	}
}
