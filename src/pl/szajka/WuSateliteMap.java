package pl.szajka;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WuSateliteMap extends WuResponse {
	public String image_url;
	public String image_url_ir4;
	public String image_url_vis;

	public WuSateliteMap(String jsonDocument) throws ParseException,WuException {
		System.out.println(jsonDocument);

		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(jsonDocument);
		JSONObject response = (JSONObject) obj.get("response");
		this.version = (String) response.get("version");
		this.termsofService = (String) response.get("termsofService");

		JSONObject satellite = (JSONObject) obj.get("satellite");
		if (satellite != null) {
			this.image_url = (String) satellite.get("image_url");
			this.image_url_ir4 = (String) satellite.get("image_url_ir4");
			this.image_url_vis = (String) satellite.get("image_url_vis");
			
			
		} else {
			JSONObject error = (JSONObject) response.get("error");
			if (error != null) {
				throw new WuException((String) error.get("description"));
			}
			throw new WuException("Coœ posz³o Ÿle");
		}
	}
	
	public String getImage(int width, int height) {
		String url = this.image_url;
		url = url.replace("width=300", "width="+Integer.toString(width));
		url = url.replace("height=300", "height="+Integer.toString(height));
		return url;
	}

}
