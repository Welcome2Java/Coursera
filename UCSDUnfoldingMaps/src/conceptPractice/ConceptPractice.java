package conceptPractice;

import processing.core.*;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;    


public class ConceptPractice extends PApplet {
	private String url = "https://static.tripzilla.com/thumb/9/a/18074_620x.jpg";
	private PImage backgroundImg;
	
	public void setup() {
		size(600, 600, OPENGL);
		
		//loading the image. Not uploading it.
		backgroundImg = loadImage(url, "jpg");
	
	}
	
	public void draw() {
		backgroundImg.resize(0, height);
		image(backgroundImg, 0, 0);
		//fills the color of the shape
		settingColorByTheTimeOfTheDay();

		//sets the ellipse on the image. 
		ellipse(width/4, height/5, width/5, height/5);

	}
	
	//This method set the color of the ellipse based on the time of the day.
	public void settingColorByTheTimeOfTheDay() {
		LocalTime now = LocalTime.now();
		boolean koreaTimeZone = false;
		if(koreaTimeZone) {
			colorOfTheSunInKorea(koreaTimeZone);
		}
		else {
			if(now.getHour()>6 && now.getHour()<12) {
				fill(255, 204, 51);
			} else if(now.getHour()>=12 && now.getHour()<17) {
				fill(255, 209, 0);
			} else if(now.getHour()>=17 && now.getHour()<19) {
				fill(255, 102, 0);
			} else {
				fill(51, 51, 51);
			}
		}
	}

	//This method set the color of the ellipse based on the time of the day in Korea.
	public void colorOfTheSunInKorea(boolean korea) {
		if (korea) {
			TimeZone timeZoneKorea = TimeZone.getTimeZone("Asia/Seoul");
			Calendar calendar = new GregorianCalendar();
			calendar.setTimeZone(timeZoneKorea);
			if(calendar.get(Calendar.HOUR_OF_DAY)>6 && calendar.get(Calendar.HOUR_OF_DAY)<12) {
				fill(255, 204, 51);
			} else if(calendar.get(Calendar.HOUR_OF_DAY)>=12 && calendar.get(Calendar.HOUR_OF_DAY)<17) {
				fill(255, 209, 0);
			} else if(calendar.get(Calendar.HOUR_OF_DAY)>=17 && calendar.get(Calendar.HOUR_OF_DAY)<19) {
				fill(255, 102, 0);
			} else {
				fill(51, 51, 51);
			}
		}
	}
}
