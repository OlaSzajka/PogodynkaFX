package pl.szajka;

import java.util.Date;

import org.json.simple.JSONObject;

public class WuObservation extends WuResponse{
	public Date date;
	public float tempm; //":"17.0", 
	public float dewptm;//":"15.0", 
	public int hum; //":"88",
	public float wspdm; //":"3.7", 
	public String wgustm; //":"-9999.0", 
	public String wdird; //":"0",
	public String wdire; //":"zmienny",
	public float vism; //":"-9999.0", 
	public int pressurem; //":"1010", 
	public float windchillm; //":"-999", 
	public float precipm; //":"-9999.00", 
	public String conds;//":"pogodnie",
	public String icon; //":"clear",
	public Boolean fog; //":"0",
	public Boolean rain; //":"0",
	public Boolean snow; //":"0",
	public Boolean hail; //":"0",
	public Boolean thunder; //":"0",
	public Boolean tornado; //":"0",

	public WuObservation(JSONObject obj) {
		
		this.date = this.parseDate((JSONObject) obj.get("date"));
		this.tempm = Float.parseFloat((String)obj.get("tempm"));
		this.dewptm = Float.parseFloat((String)obj.get("dewptm"));
		this.hum = Integer.parseInt((String)obj.get("hum"));
		this.wspdm = Float.parseFloat((String)obj.get("wspdm"));
		this.wgustm = (String)obj.get("wgustm");
		this.wdird = (String)obj.get("wdird");
		this.wdire = (String)obj.get("wdire");
		this.vism = Float.parseFloat((String)obj.get("vism"));
		this.pressurem = Integer.parseInt((String)obj.get("pressurem"));
		this.windchillm = Float.parseFloat((String)obj.get("windchillm"));
		if(((String)obj.get("precipm")).equals("") || ((String)obj.get("precipm")).equals("-9999.00"))
			this.precipm = 0.0f;
		else
		this.precipm = Float.parseFloat((String)obj.get("precipm"));
		this.conds = (String)obj.get("conds");
		this.icon = (String)obj.get("icon");
		this.fog = (Integer.parseInt((String)obj.get("fog")) != 0 );
		this.rain = (Integer.parseInt((String)obj.get("rain")) != 0 );
		this.snow = (Integer.parseInt((String)obj.get("snow")) != 0 );
		this.hail = (Integer.parseInt((String)obj.get("hail")) != 0 );
		this.thunder = (Integer.parseInt((String)obj.get("thunder")) !=0 );
		this.tornado = (Integer.parseInt((String)obj.get("tornado")) !=0 );
	}
}
