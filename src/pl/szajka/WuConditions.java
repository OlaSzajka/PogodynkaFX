package pl.szajka;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WuConditions extends WuResponse {
	public String station_id;
	public String observation_time;
	public String observation_time_rfc822;
	// "observation_epoch":"1427313600",
	// "local_time_rfc822":"Wed, 25 Mar 2015 21:25:45 +0100",
	// "local_epoch":"1427315145",
	// "local_tz_short":"CET",
	// "local_tz_long":"Europe/Warsaw",
	// "local_tz_offset":"+0100",
	public String weather;
	public String temperature_string;
	public int temp_f;
	public int temp_c;
	public String relative_humidity;
	public String wind_string;
	public String wind_dir;
	public int wind_degrees;
	public int wind_mph;
	// public int wind_gust_mph;
	public int wind_kph;
	public int wind_gust_kph;
	public String pressure_mb;
	public String pressure_in;
	public String pressure_trend;
	// "dewpoint_string":"41 F (5 C)",
	// "dewpoint_f":41,
	public int dewpoint_c;
	// "heat_index_string":"NA",
	// "heat_index_f":"NA",
	// "heat_index_c":"NA",
	// "windchill_string":"NA",
	// "windchill_f":"NA",
	// "windchill_c":"NA",
	// "feelslike_string":"52 F (11 C)",
	public String feelslike_f;
	public String feelslike_c;
	public String visibility_mi;
	public String visibility_km;
	// "solarradiation":"--",
	// "UV":"0","precip_1hr_string":"-9999.00 in (-9999.00 mm)",
	// "precip_1hr_in":"-9999.00",
	// "precip_1hr_metric":"--",
	// "precip_today_string":"0.00 in (0.0 mm)",
	// "precip_today_in":"0.00",
	public String precip_today_metric;
	public String icon_url;

	public WuObservationLocation location;

	public WuConditions(String jsonDocument) throws ParseException, WuException {
		//System.out.println(jsonDocument);
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(jsonDocument);
		JSONObject response = (JSONObject) obj.get("response");
		this.version = (String) response.get("version");
		this.termsofService = (String) response.get("termsofService");

		JSONObject current_observation = (JSONObject) obj
				.get("current_observation");
		if (current_observation != null) {
			this.station_id = (String) current_observation.get("station_id");
			this.observation_time = (String) current_observation
					.get("observation_time");
			this.observation_time_rfc822 = (String) current_observation
					.get("observation_time_rfc822");
			this.weather = (String) current_observation.get("weather");
			this.temperature_string = (String) current_observation.get("temperature_string");
			this.temp_f = ((Number) current_observation.get("temp_f"))
					.intValue();
			this.temp_c = ((Number) current_observation.get("temp_c"))
					.intValue();
			this.relative_humidity = (String) current_observation
					.get("relative_humidity");
			this.wind_string = (String) current_observation.get("wind_string");
			this.wind_dir = (String) current_observation.get("wind_dir");
			this.wind_degrees = ((Number) current_observation
					.get("wind_degrees")).intValue();
			this.temp_c = ((Number) current_observation.get("temp_c"))
					.intValue();
			this.wind_mph = ((Number) current_observation.get("wind_mph"))
					.intValue();
			// this.wind_gust_mph = ((Number)
			// current_observation.get("wind_gust_mph")).intValue();
			this.wind_kph = ((Number) current_observation.get("wind_kph"))
					.intValue();
			this.pressure_mb = (String) current_observation.get("pressure_mb");
			this.pressure_in = (String) current_observation.get("pressure_in");
			this.pressure_trend = (String) current_observation.get("pressure_trend");
			this.feelslike_f = (String) current_observation.get("feelslike_f");
			this.feelslike_c = (String) current_observation.get("feelslike_c");
			this.visibility_mi = (String) current_observation
					.get("visibility_mi");
			this.visibility_km = (String) current_observation
					.get("visibility_km");
			this.icon_url = (String) current_observation.get("icon_url");
			this.precip_today_metric = (String) current_observation
					.get("precip_today_metric");
			
			JSONObject obsLocation = (JSONObject) current_observation.get("observation_location");
			this.location = new WuObservationLocation((String) obsLocation.get("country"),
					(String) obsLocation.get("city"),
					(String) obsLocation.get("latitude"),
					(String) obsLocation.get("longitude"),
					(String) obsLocation.get("elevation"));
			
		} else {
			JSONObject error = (JSONObject) response.get("error");
			if (error != null) {
				throw new WuException((String) error.get("description"));
			}
			throw new WuException("Coœ posz³o Ÿle");
		}
	}
}
