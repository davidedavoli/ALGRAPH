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
		boolean segno2;
		boolean segno3;
		line2.setEndX(line1.getEndX());
		line2.setEndY(line1.getEndY());
		line3.setEndX(line1.getEndX());
		line3.setEndY(line1.getEndY());
		
		double tanTeta = (double)(line1.getStartY()-line1.getEndY())/(line1.getStartX()-line1.getEndX());
		double angle = Math.atan(tanTeta);
		double angle2 = angle + Math.PI/4;
		double angle3 = angle - Math.PI/4;
		
		double tanTeta2 = Math.tan(angle2);
		double tanTeta3 = Math.tan(angle3);
		
		if (tanTeta<1 && tanTeta>-1) {
			if (line1.getStartX()<line1.getEndX()) {
				
				segno2=false;
				segno3=false;
			}
			else {
				segno2=true;
				segno3=true;
			}
		}
		else {
				if (line1.getStartY()>line1.getEndY()) {
					segno2=false;
					segno3=true;
				}
				
				else {
					segno2=true;
					segno3=false;
				}
		}
		if (!segno2) {
		line2.setStartX(line2.getEndX()-(10/Math.sqrt(Math.pow(tanTeta2, 2)+1)));
		line2.setStartY(line2.getEndY()-(10/Math.sqrt(Math.pow(tanTeta2, 2)+1))*tanTeta2);
		}
		if(segno2) {
			line2.setStartX(line2.getEndX()+(10/Math.sqrt(Math.pow(tanTeta2, 2)+1)));
			line2.setStartY(line2.getEndY()+(10/Math.sqrt(Math.pow(tanTeta2, 2)+1))*tanTeta2);
		}
		if(!segno3) {
			line3.setStartX(line3.getEndX()-(10/Math.sqrt(Math.pow(tanTeta3, 2)+1)));
			line3.setStartY(line3.getEndY()-(10/Math.sqrt(Math.pow(tanTeta3, 2)+1))*tanTeta3);
	}
		if (segno3) {
			line3.setStartX(line3.getEndX()+(10/Math.sqrt(Math.pow(tanTeta3, 2)+1)));
			line3.setStartY(line3.getEndY()+(10/Math.sqrt(Math.pow(tanTeta3, 2)+1))*tanTeta3);
		}
	}
	
	
	
}