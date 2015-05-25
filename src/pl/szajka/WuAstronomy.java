package pl.szajka;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WuAstronomy extends WuResponse {

	public String percentIlluminated;
	public String ageOfMoon;
	public String phaseofMoon;
	public Date sunrise;
	public Date sunset;
	public String sunrise_str;
	public String sunset_str;
	public String day_duration;

	@SuppressWarnings("deprecation")
	public WuAstronomy(String jsonDocument) throws ParseException, WuException {
		//System.out.println(jsonDocument);

		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(jsonDocument);
		JSONObject response = (JSONObject) obj.get("response");
		this.version = (String) response.get("version");
		this.termsofService = (String) response.get("termsofService");

		JSONObject moon_phase = (JSONObject) obj.get("moon_phase");
		if (moon_phase != null) {
			this.percentIlluminated = (String) moon_phase.get("percentIlluminated");
			this.ageOfMoon = (String) moon_phase.get("ageOfMoon");
			this.phaseofMoon = (String) moon_phase.get("phaseofMoon");

			JSONObject sunriseObj = (JSONObject) moon_phase.get("sunrise"); 
			this.sunrise = new Date();
			this.sunrise.setHours(Integer.parseInt((String) sunriseObj.get("hour")));
			this.sunrise.setMinutes(Integer.parseInt((String) sunriseObj.get("minute")));
			
			JSONObject sunsetObj = (JSONObject) moon_phase.get("sunset"); 
			this.sunset = new Date();
			this.sunset.setHours(Integer.parseInt((String) sunsetObj.get("hour")));
			this.sunset.setMinutes(Integer.parseInt((String) sunsetObj.get("minute")));
			
			
			
			final DateFormat df = new SimpleDateFormat("HH:mm");
			this.sunrise_str = df.format(sunrise);
			this.sunset_str = df.format(sunset);
			
			long duration = sunset.getTime() - sunrise.getTime();
			this.day_duration = Long.toString(TimeUnit.MILLISECONDS.toHours(duration)) + "h " + 
								Long.toString(TimeUnit.MICROSECONDS.toMinutes(duration)) + "m";
					
		} else {
			JSONObject error = (JSONObject) response.get("error");
			if (error != null) {
				throw new WuException((String) error.get("description"));
			}
			throw new WuException("Coœ posz³o Ÿle");
		}

	}

}
