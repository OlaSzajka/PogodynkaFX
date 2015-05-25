package pl.szajka;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WuHistory extends WuResponse {
	public WuHistory(String jsonDocument) throws ParseException, WuException {
		System.out.println(jsonDocument);
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(jsonDocument);
		JSONObject response = (JSONObject) obj.get("response");
		this.version = (String) response.get("version");
		this.termsofService = (String) response.get("termsofService");
	}

}
