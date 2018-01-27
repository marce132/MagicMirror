package UI;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.LineChartConfig;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.options.InteractionMode;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.CategoryScale;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.byteowls.vaadin.chartjs.utils.ColorUtils;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Image;

import Manager.TempAndHumdityManager;
import Manager.WeatherManager;
import RSSFeed.Feed;
import RSSFeed.FeedMessage;
import RSSFeed.RSSFeedParser;
import SensorData.TempAndHumidity;
import Weather.Currently;
import Weather.Daily;
import Weather.Data;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	@Override
    protected void init(VaadinRequest vaadinRequest) {
      	AbsoluteLayout layout = new AbsoluteLayout();
      	
      	//Path for Weather Images
      	String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();

      	
    //	TempAndHumdityManager tahManager = new TempAndHumdityManager();
    //	tahManager.setup();
	//	tah.createNewEntry();
    //	TempAndHumidity tAh = tahManager.getCurrTempAndHum();
    //	tahManager.exit();
    	
    	Currently currently = WeatherManager.getCurrently();
    	Daily forecast = WeatherManager.getForecast();
    	
    	
    	Label city = new Label("Wien");
    	layout.addComponent(city,  "left: 10px; top: 10px;");
    	
    	if(currently.getIcon().contains("partly-cloudy")) {
    		FileResource resource = new FileResource(new File(basepath +
                    "/WEB-INF/images/wolkig_sonne.png"));
    		Image image = new Image("", resource);
    		layout.addComponent(image,  "left: 10px; top: 40px;");
    	}else if(currently.getIcon().contains("clear")) {
    		FileResource resource = new FileResource(new File(basepath +
                    "/WEB-INF/images/sonnig.png"));
    		Image image = new Image("", resource);
    		layout.addComponent(image,  "left: 10px; top: 40px;");
    	}else if(currently.getIcon().contains("rain")) {
    		FileResource resource = new FileResource(new File(basepath +
                    "/WEB-INF/images/regen.png"));
    		Image image = new Image("", resource);
    		layout.addComponent(image,  "left: 10px; top: 40px;");
    	}else if(currently.getIcon().contains("snow")) {
    		FileResource resource = new FileResource(new File(basepath +
                    "/WEB-INF/images/schnee.png"));
    		Image image = new Image("", resource);
    		layout.addComponent(image,  "left: 10px; top: 40px;");
    	}
    	else if(currently.getIcon().contains("fog")) {
    		FileResource resource = new FileResource(new File(basepath +
                    "/WEB-INF/images/nebel.png"));
    		Image image = new Image("", resource);
    		layout.addComponent(image,  "left: 10px; top: 40px;");
    	}else if(currently.getIcon().contains("wind")) {
    		FileResource resource = new FileResource(new File(basepath +
                    "/WEB-INF/images/wind.png"));
    		Image image = new Image("", resource);
    		layout.addComponent(image,  "left: 10px; top: 40px;");
    	}else if(currently.getIcon().contains("cloudy")) {
    		FileResource resource = new FileResource(new File(basepath +
                    "/WEB-INF/images/wolkig.png"));
    		Image image = new Image("", resource);
    		layout.addComponent(image,  "left: 10px; top: 40px;");
    	}
    	
    	
    	
    	
    	
    	//Label lTemp = new Label(Double.toString(Math.round(tAh.getTemperature()))+ " °");
    	//Label lTemp = new Label(Double.toString(tAh.getTemperature())+ "°");
    	Label lTemp = new Label(currently.getTemperature()+ "°");
    	layout.addComponent(lTemp,  "left: 200px; top: 82px;");
    	
    	
    	//Label slHum = new Label("Current Humdity");
    	//Label lHum = new Label(Integer.toString(tAh.getHumidity()) + "%");
    	Label lHum = new Label(currently.getHumidity() + "%");
    	layout.addComponent(lHum,  "left: 200px; top: 100px;");
    	
    	
    	
    	int bottom = 450;
    	LocalDate today = LocalDate.now();
    	int counter = 0;
		for(Data d: forecast.getData()) {
			Label abstand = new Label("----------------------------------------------");
    		layout.addComponent(abstand, "left: 10px; bottom: "+(bottom +10) +";");
			LocalDate day = today.plus(counter, ChronoUnit.DAYS);
		    DayOfWeek dayofWeek = day.getDayOfWeek(); 
		    bottom-=7;
			Double tempHigh = Double.parseDouble(d.getTemperatureHigh());
			Double tempLow = Double.parseDouble(d.getTemperatureLow());
			String output;
		    if(counter == 0) {
		    	output = "Today" + ", " + day.getDayOfMonth() + "." + day.getMonthValue();
		    }else{
		    	output = dayofWeek.toString().substring(0, 3) + ", " + day.getDayOfMonth() + "." + day.getMonthValue();
		    }
			Label dateShow = new Label (output);
			layout.addComponent(dateShow, "left: 10px; bottom: "+bottom +";");
			Label tHigh = new Label(Long.toString(Math.round(tempHigh)) + "°");
			layout.addComponent(tHigh, "left: 200px; bottom: "+bottom +";");
			
			Label sum = new Label(d.getIcon());
			if(d.getIcon().contains("partly-cloudy")) {
			  sum = new Label(d.getIcon().substring(0, 13));	
			}
			layout.addComponent(sum, "left: 10px; bottom: "+(bottom - 20) +";");
			Label tlow = new Label(Long.toString(Math.round(tempLow)) + "°");
			layout.addComponent(tlow, "left: 200px; bottom: "+(bottom - 20) +";");
			System.out.println("scheiß icon." + d.getIcon()+".");
			if(d.getIcon().contains("partly-cloudy")) {
	    		FileResource resource = new FileResource(new File(basepath +
	                    "/WEB-INF/images/wolkig_sonne_small.png"));
	    		Image image = new Image("", resource);
	    		image.setWidth("25px");
	    		image.setHeight("30px");
	    		layout.addComponent(image,  "left: 155px; bottom: "+(bottom - 10) + ";");
	    	}else if(d.getIcon().contains("clear")) {
	    		FileResource resource = new FileResource(new File(basepath +
	                    "/WEB-INF/images/sonnig_small.png"));
	    		Image image = new Image("", resource);
	    		image.setWidth("25px");
	    		image.setHeight("30px");
	    		layout.addComponent(image,  "left: 150px; bottom: " + (bottom - 20) + ";");
	    	}else if(d.getIcon().contains("rain")) {
	    		FileResource resource = new FileResource(new File(basepath +
	                    "/WEB-INF/images/regen_small.png"));
	    		Image image = new Image("", resource);
	    		image.setWidth("25px");
	    		image.setHeight("30px");
	    		layout.addComponent(image,  "left: 150px; bottom: "+ (bottom - 20) + ";");
	    	}else if(d.getIcon().contains("snow")) {
	    		FileResource resource = new FileResource(new File(basepath +
	                    "/WEB-INF/images/schnee_small.png"));
	    		Image image = new Image("", resource);
	    		image.setWidth("25px");
	    		image.setHeight("30px");
	    		layout.addComponent(image,  "left: 150px; bottom: " + (bottom - 20) + ";");
	    	}else if(d.getIcon().contains("fog")) {
	    		System.out.println("drin");
	    		FileResource resource = new FileResource(new File(basepath +
	                    "/WEB-INF/images/nebel_small.png"));
	    		Image image = new Image("", resource);
	    		image.setWidth("25px");
	    		image.setHeight("30px");
	    		layout.addComponent(image,  "left: 155px; bottom: " + (bottom - 13) + ";");
	    	}else if(d.getIcon().contains("wind")) {
	    		FileResource resource = new FileResource(new File(basepath +
	                    "/WEB-INF/images/wind_small.png"));
	    		Image image = new Image("", resource);
	    		image.setWidth("25px");
	    		image.setHeight("30px");
	    		layout.addComponent(image,  "left: 155px; bottom: " + (bottom - 15) + ";");
	    	}else if(d.getIcon().contains("cloudy")) {
	    		FileResource resource = new FileResource(new File(basepath +
	                    "/WEB-INF/images/wolkig_small.png"));
	    		Image image = new Image("", resource);
	    		image.setWidth("25px");
	    		image.setHeight("30px");
	    		layout.addComponent(image,  "left: 155px; bottom: " + (bottom - 15) + ";");
	    	}else {
	    		System.out.println("nothing");
	    	}
			
			bottom -= 50;
			//System.out.println("Wetterprognose für " + df.format(date) + " " + d.getSummary() + " Höchsttemperatur: " + Math.round(tempHigh) + "° Tiefsttemperatur: " + Math.round(tempLow) + "°" + d.getIcon());
		    counter++;
		     
		}
		Label abstand = new Label("----------------------------------------------");
    	layout.addComponent(abstand, "left: 10px; bottom: " +(bottom +10) + ";");
		
    	RSSFeedParser parserORF = new RSSFeedParser("https://rss.orf.at/news.xml");
        Feed feedOrf = parserORF.readFeed();
        bottom = 400;
        for (int i=0; i<= 5;i++) {
           	FeedMessage message = feedOrf.getMessages().get(i);
           	Label orf = new Label(message.getTitle());
           	layout.addComponent(orf, "right: 10px; bottom: "+ bottom +";");
           	bottom -= 20;
        }
        RSSFeedParser parserStand = new RSSFeedParser("http://derStandard.at/?page=rss&ressort=Seite1");
      	Feed feedStand = parserStand.readFeed();
      	bottom -= 25;
        for (int i=1; i<= 6;i++) {
        	FeedMessage message = feedStand.getMessages().get(i);
         	Label stand = new Label(message.getTitle());
         	layout.addComponent(stand, "right: 10px; bottom: "+ bottom +";");
         	bottom -= 20;
        }
        RSSFeedParser parserGol = new RSSFeedParser("https://rss.golem.de/rss.php?feed=RSS2.0");
      	Feed feedGol = parserGol.readFeed();
      	bottom -= 25;
        for (int i=1; i<= 6;i++) {
        	FeedMessage message = feedGol.getMessages().get(i);
        	Label gol = new Label(message.getTitle());
        	layout.addComponent(gol, "right: 10px; bottom: "+ bottom +";");
        	bottom -= 20;
        }
                
  
   // 	ChartJs chart = chart();
   // 	layout.addComponents(slTemp, lTemp, slHum, lHum);
    	String clock = "<iframe src= "+ "\"http://free.timeanddate.com/clock/i621zho7/n259/tlat3/fs18/fcaaa/tc000/pc000/tt0/tw1/tm2/td2/th1/ts1/tb4\"" + " frameborder=" + "\"0\""+" width="+ "\"128\"" +" height=" +"\"44\""+ "></iframe>";
    	Label htmlLabel = new Label(clock, ContentMode.HTML);
    	layout.addComponent(htmlLabel,"right: 10px; bottom: 425px");
    	setContent(layout);
    }
    
    

    public ChartJs chart() {
    	LineChartConfig lineConfig = new LineChartConfig();
    	lineConfig.data()
    		.labels("Jannuar", "Februar", "March", "April", "Mai", "Juni", "July")
    		.addDataset(new LineDataset().label("My first Dataset").fill(true))
    		.and()
    		.options()
    		  .responsive(true)
    		  .title()
    		  .display(true)
    		  .text("First Charts")
    		.and()
    		.tooltips()
    		 .mode(InteractionMode.INDEX)
    		 .intersect(false)
    		 .and()
    		.hover()
    		 .mode(InteractionMode.NEAREST)
    		 .intersect(true)
    		.and()
    		.scales()
    		.add(Axis.X, new CategoryScale()
    				.display(true)
    				
    				
    		/*		.scaleLabel()
    					.display(true)
    					.labelString("Month")
    					.and()*/
    				.position(Position.BOTTOM))
    		.add(Axis.Y, new LinearScale()
    				.display(true)
    				.scaleLabel()
    					.display(true)
    					.labelString("value")
    					.and()
    				.ticks()
    					.beginAtZero(true)
    					.and()
    				.position(Position.LEFT))
    		.and()
    		.done()	;
    	
    	List<String> labels = lineConfig.data().getLabels();
    	for(Dataset<?, ?> ds: lineConfig.data().getDatasets() ) {
    		LineDataset lds = (LineDataset) ds;
    		List<Double> data = new ArrayList<>();
    		for(int i = 0; i < labels.size();i++) {
    			data.add((double) Math.round(Math.random() * 30));
    		}
    		lds.dataAsList(data);
    		lds.borderColor(ColorUtils.randomColor(0.3));
    		lds.backgroundColor(ColorUtils.randomColor(0.5));
    	}
    	ChartJs chart = new ChartJs(lineConfig);
    	chart.setHeight("400");
    	chart.setWidth("400");
    	chart.setJsLoggingEnabled(true);
    	
    	return chart;
    }
    
    
    

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
    }
}
