package Manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

import Weather.Currently;
import Weather.Daily;
import Weather.Data;
import Weather.Weather;
/**
 * Wetterdaten aus Darksky.net
 * @author Markus
 *
 */
public class WeatherManager {
	
	
	
	
	
	public static void main(String args[]) {
	
		
		
		String urlCurr = "https://api.darksky.net/forecast/cf8d1946864754a620fc958577a6bcdf/48.2412872,16.474788?units=si&lang=de&exclude=minutely,hourly,alerts,flags";
		Weather weather = getWeatherData(urlCurr);
		Currently currently = weather.getCurrently();
		Daily daily = weather.getDaily();
		System.out.println(weather.getTimezone());
		Double currFeelTemp = Double.parseDouble(currently.getApparentTemperature());
		System.out.println(currently.getSummary());
		System.out.println("Jetzige Temperatur: " + currently.getTemperature() + "°" + ". Gefühlte Temperatur: " + currFeelTemp + "°");
		
		for(Data d: daily.getData()) {
			
			Long yourSeconds =  Long.parseLong(d.getTime(), 10) ;
			Date date = new Date(yourSeconds * 1000);
			DateFormat df = new SimpleDateFormat("dd MMM yyyy");
			
			Double tempHigh = Double.parseDouble(d.getTemperatureHigh());
			Double tempLow = Double.parseDouble(d.getTemperatureLow());
			
			System.out.println("Wetterprognose für " + df.format(date) + " " + d.getSummary() + " Höchsttemperatur: " + Math.round(tempHigh) + "° Tiefsttemperatur: " + Math.round(tempLow) + "°");
		}
		
		
	}
	
	
	public static Currently getCurrently() {
	    System.out.println("Get Currenlty");
		String urlCurr = "https://api.darksky.net/forecast/cf8d1946864754a620fc958577a6bcdf/48.2412872,16.474788?units=si&lang=en&exclude=minutely,hourly,alerts,flags";
		Weather weather = getWeatherData(urlCurr);
		Currently currently = weather.getCurrently();
		
		
		return currently;
	}
	
	public static Daily getForecast() {
		System.out.println("Get Forecast");
		String urlCurr = "https://api.darksky.net/forecast/cf8d1946864754a620fc958577a6bcdf/48.2412872,16.474788?units=si&lang=de&exclude=minutely,hourly,alerts,flags";
		Weather weather = getWeatherData(urlCurr);
		Daily daily = weather.getDaily();
		for(Data d: daily.getData()) {
			
			Long yourSeconds =  Long.parseLong(d.getTime(), 10) ;
			Date date = new Date(yourSeconds * 1000);
			DateFormat df = new SimpleDateFormat("dd MMM yyyy");
			
			Double tempHigh = Double.parseDouble(d.getTemperatureHigh());
			Double tempLow = Double.parseDouble(d.getTemperatureLow());
			
			System.out.println("Wetterprognose für " + df.format(date) + " " + d.getSummary() + " Höchsttemperatur: " + Math.round(tempHigh) + "° Tiefsttemperatur: " + Math.round(tempLow) + "°" + d.getIcon());
		}
		return daily;
		
		
	}
	
	public static String getJSON(String urlC) throws IOException{
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlC);
		
		URLConnection conn = url.openConnection();
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		
		while((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		
		return result.toString();
		
	}
	
	
	public static Weather getWeatherData(String url) {
		String json = null;
		
		try {
			json = getJSON(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Gson gson = new Gson();
		return gson.fromJson(json, Weather.class);
		
				
	}
	
}
