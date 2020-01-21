package module6;

import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimpleLinesMarker;
import processing.core.PConstants;
import processing.core.PGraphics;

/** 
 * A class to represent AirportMarkers on a world map.
 *   
 * @author Adam Setters and the UC San Diego Intermediate Software Development
 * MOOC team
 *
 */
public class AirportMarker extends CommonMarker {
	public static List<SimpleLinesMarker> routes;
	
	public AirportMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
	
	}
	
	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		pg.fill(11);
		pg.ellipse(x, y, 7, 7);	
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		/*
		 * To show the code and name of the airport.
		 */
		
		String code = getCode();
		String name = getName();
		pg.pushStyle();
		
		pg.rectMode(PConstants.CORNER);
		
		pg.stroke(110);
		pg.fill(255,255,255);
		pg.rect(x, y-5-39, Math.max(pg.textWidth(name), pg.textWidth(code)) + 6, 39);
		
		
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		
		pg.fill(0, 0, 0);
		pg.text(code, x+3, y-5-33);
		pg.text(name, x+3, y-5-18);
		// show rectangle with title
		pg.popStyle();
		// show routes

	}
	public String getCode() {
		return (String) getProperty("code");		
	}
	
	public String getName() {
		return getProperty("name").toString();
	}
}
