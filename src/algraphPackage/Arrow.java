package algraphPackage;

import javafx.event.EventHandler;
import javafx.scene.shape.Line;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class Arrow {
	private Line line1, line2, line3;
	
	public Arrow() {
		line1 = new Line();
		line2 = new Line();
		line3 = new Line();
	}
	
	public Line getLine1() {
		return line1;
	}
	
	public Line getLine2() {
		return line2;
	}
	
	public Line getLine3() {
		return line3;
	}
	
	public void managePointer() {
		line2.setEndX(line1.getEndX());
		line2.setEndY(line1.getEndY());
		line3.setEndX(line1.getEndX());
		line3.setEndY(line1.getEndY());
		
		double tanTeta = (double)(line1.getStartY()-line1.getEndY())/(line1.getStartX()-line1.getEndX());
		double angle = Math.atan(tanTeta);
		double angle2 = angle + Math.PI/4;
		double angle3 = angle - Math.PI/4;
		
		System.out.println(Math.atan(tanTeta));
		
		double tanTeta2 = Math.tan(angle2);
		line2.setStartX(line2.getEndX()-(10/Math.sqrt(Math.pow(tanTeta2, 2)+1)));
		line2.setStartY(line2.getEndY()-(10/Math.sqrt(Math.pow(tanTeta2, 2)+1))*tanTeta2);
		
		double tanTeta3 = Math.tan(angle3);
		line3.setStartX(line3.getEndX()-(10/Math.sqrt(Math.pow(tanTeta3, 2)+1)));
		line3.setStartY(line3.getEndY()-(10/Math.sqrt(Math.pow(tanTeta3, 2)+1))*tanTeta3);
	}
	
}