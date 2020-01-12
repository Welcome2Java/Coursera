package guiModule;

import processing.core.PApplet;
import processing.core.PImage;

public class MyDisplay extends PApplet {
	String url = "https://i1.wp.com/metro.co.uk/wp-content/uploads/2019/01/GettyImages-1051894352.jpg?quality=90&strip=all&zoom=1&resize=644%2C416&ssl=1";
	private PImage background;
	public void setup() {

		size(1500, 1500);
		//loading the image. Not uploading it.
		background = loadImage(url, "jpg");
	}

	public void draw() {
		background.resize(0, height);
		image(background, 0, 0);
		
		
		fill(255, 255, 255);
		rect(525, 115, 255, 150, 7);
		// drawing a happy face
		fill(255, 255, 0);
		ellipse(300, 300, 390, 390);

		fill(0, 0, 0);
		ellipse(225, 200, 50, 70);
		fill(0, 0, 0);
		ellipse(375, 200, 50, 70);

		arc(300, 350, 150, 150, 0, PI);

		fill(0, 102, 153);
		textSize(32);
		text("He has low IQ", 550, 200); 
	}
}
