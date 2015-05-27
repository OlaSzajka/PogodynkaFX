package pl.szajka;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Date;

import javax.ws.rs.core.UriBuilder;

import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class WeatherClient {
	private static String key =  "b866716f90f0a90e";
	private static String lang = "lang:PL";
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://api.wunderground.com/api/"+key+"/").build();	
	}
	
	private Client client;
	
	WeatherClient() {
		client = Client.create();
	}
	
	public String getDocument(String relativeUrl) {
		WebResource webResource = client.resource(getBaseURI());
		ClientResponse response = webResource.path(relativeUrl).accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Niepowodzenie : HTTP error code : " + response.getStatus());
		}
		return response.getEntity(String.class);
	}

	public WuConditions getCurrentCondition(String country, String city) throws ParseException, WuException {
		//conditions/q/Poland/Krakow.json
		String relativeUrl = "conditions/"+ lang +"/q/" + country + "/" + city + ".json";
		WuConditions currentConfidions = new WuConditions(this.getDocument(relativeUrl));		
		return currentConfidions;
	}
	
	public WuAstronomy getAstronomy(String country, String city) throws ParseException, WuException {
		String relativeUrl = "astronomy/"+ lang +"/q/" + country + "/" + city + ".json";
		WuAstronomy astronomy = new WuAstronomy(this.getDocument(relativeUrl));
		return astronomy;
	}
	
	public WuSateliteMap getSatelliteMap(String country, String city) throws ParseException, WuException {
		String relativeUrl = "satellite/"+ lang +"/q/" + country + "/" + city + ".json";
		WuSateliteMap satelliteMap = new WuSateliteMap(this.getDocument(relativeUrl));
		return satelliteMap;
	}
	
	public WuHistory getHistoryData(String country, String city, Date date) throws ParseException, WuException {
		String relativeUrl = "history_20150505/"+ lang +"/q/" + country + "/" + city + ".json";
		WuHistory history = new WuHistory(this.getDocument(relativeUrl));
		return history;
	}
	
	public WuHistory getHistoryData(String country, String city) throws ParseException, WuException {
		String relativeUrl = "history_20150505/"+ lang +"/q/" + country + "/" + city + ".json";
		WuHistory history = new WuHistory(this.getDocument(relativeUrl));
		return history;
	}
}

