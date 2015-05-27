package pl.szajka;

import org.json.simple.JSONObject;;

public class WuDailySummary {
	public Boolean fog; //":"1",
	public Boolean rain; //":"0",
	public Boolean snow; //":"0",
	public Boolean hail; //":"0",
	public Boolean thunder; //":"0",
	public Boolean tornado; //":"0",
	
	public float precipm; //":"0.0"," 
	public String snowfallm; //":"",
	public String snowdepthm; //":"",
	
	public int meantempm; //":"16", 
	public int maxtempm; //":"25", 
	public int mintempm; //":"8", 
	
	public int meandewptm; //":"13", 
	public int maxdewptm; //":"16", 
	public int mindewptm; //":"8", 
	
	public float meanpressurem; //":"1011.32",
	public int maxpressurem; //":"1014", 
	public int minpressurem;// ":"1008", 
	
	public int meanwindspdm; //":"6",
	public int maxwspdm; //":"18", 
	public int minwspdm; //":"0", 
	
	public String meanwdire; //":"WsPnWs",
	public int meanwdird; //":"66",
	
	public float meanvism; // ":"10.3",
	public float maxvism; //":"30.0", 
	public float minvism; //":"5.0", 
	
	public int humidity;//":"75",
	public int maxhumidity; //":"100",
	public int minhumidity; //":"39",


	public WuDailySummary(JSONObject obj) {

		this.fog = (Integer.parseInt((String)obj.get("fog")) != 0 );
		this.rain = (Integer.parseInt((String)obj.get("rain")) != 0 );
		this.snow = (Integer.parseInt((String)obj.get("snow")) != 0 );
		this.hail = (Integer.parseInt((String)obj.get("hail")) != 0 );
		this.thunder = (Integer.parseInt((String)obj.get("thunder")) !=0 );
		this.tornado = (Integer.parseInt((String)obj.get("tornado")) !=0 );
		this.precipm = Float.parseFloat((String)obj.get("precipm"));
		this.snowfallm = (String)obj.get("snowfallm");
		this.snowdepthm = (String)obj.get("snowdepthm");
		this.meantempm = Integer.parseInt((String)obj.get("meantempm"));
		this.mintempm = Integer.parseInt((String)obj.get("mintempm"));
		this.maxtempm = Integer.parseInt((String)obj.get("maxtempm"));
		this.meandewptm = Integer.parseInt((String)obj.get("meandewptm"));
		this.mindewptm = Integer.parseInt((String)obj.get("mindewptm"));
		this.maxdewptm = Integer.parseInt((String)obj.get("maxdewptm"));
		this.meanpressurem = Float.parseFloat((String)obj.get("meanpressurem"));
		this.maxpressurem = Integer.parseInt((String)obj.get("maxpressurem"));
		this.minpressurem = Integer.parseInt((String)obj.get("minpressurem"));
		this.meanwindspdm = Integer.parseInt((String)obj.get("meanwindspdm"));
		this.minwspdm = Integer.parseInt((String)obj.get("minwspdm"));
		this.maxwspdm = Integer.parseInt((String)obj.get("maxwspdm"));
		this.meanwdire	= (String)obj.get("meanwdire");
		this.meanwdird = Integer.parseInt((String)obj.get("meanwdird"));
		this.meanvism = Float.parseFloat((String)obj.get("meanvism"));
		this.minvism = Float.parseFloat((String)obj.get("minvism"));
		this.maxvism = Float.parseFloat((String)obj.get("maxvism"));
		this.humidity = Integer.parseInt((String)obj.get("humidity"));
		this.minhumidity = Integer.parseInt((String)obj.get("minhumidity"));
		this.maxhumidity = Integer.parseInt((String)obj.get("maxhumidity"));
	}

}
