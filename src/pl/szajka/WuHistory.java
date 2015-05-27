package pl.szajka;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WuHistory extends WuResponse {
	public WuDailySummary dailySummary;
	public List<WuObservation> observationList;
	
	public WuHistory(String jsonDocument) throws ParseException, WuException {
		System.out.println(jsonDocument);

		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(jsonDocument);
		JSONObject response = (JSONObject) obj.get("response");
		this.version = (String) response.get("version");
		this.termsofService = (String) response.get("termsofService");

		JSONObject obj_history = (JSONObject) obj.get("history");
		if (obj_history != null) {
			JSONArray arr_summ = (JSONArray) obj_history.get("dailysummary");
			JSONObject obj_summ = (JSONObject) arr_summ.get(0);
			this.dailySummary = new WuDailySummary(obj_summ);
			JSONArray arr_obs = (JSONArray) obj_history.get("observations");
			this.observationList = new ArrayList<WuObservation>();
			Iterator<JSONObject> itr = arr_obs.iterator();
			while(itr.hasNext()) {
				this.observationList.add(new WuObservation(itr.next()));
			}

		} else {
			JSONObject error = (JSONObject) response.get("error");
			if (error != null) {
				throw new WuException((String) error.get("description"));
			}
			throw new WuException("Coœ posz³o Ÿle");
		}
	}

}
