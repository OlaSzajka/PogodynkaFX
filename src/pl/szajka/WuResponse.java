package pl.szajka;

import java.util.Date;
import java.util.Map;

import org.json.simple.JSONObject;

public class WuResponse {
	public String version;
	public String termsofService;
	public Map<String, Integer> features;
	
	Date parseDate(JSONObject obj) {
		Date date = new Date();
		date.setYear(Integer.parseInt((String)obj.get("year")));
		date.setMonth(Integer.parseInt((String)obj.get("mon")));
		date.setDate(Integer.parseInt((String)obj.get("mday")));
		date.setHours(Integer.parseInt((String)obj.get("hour")));
		date.setMinutes(Integer.parseInt((String)obj.get("min")));
		return date;
	}
}


