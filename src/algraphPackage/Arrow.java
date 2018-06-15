package algraphPackage;

import javafx.geometry.VPos;
import javafx.scene.shape.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

import java.lang.Math;
import java.time.format.TextStyle;

public class Arrow {
	private Line line1, line2, line3;
	private Boolean manageArcTan2, manageArcTan3;
	private Controller controller;
	public Text label;
	
	/*public Arrow(double centerX, double centerY) {
		line1 = new Line();
		line2 = new Line();
		line3 = new Line();
		manageArcTan2 = new Boolean(false);
		manageArcTan3 = new Boolean(false);
		line1.setStartX(centerX);
		line1.setStartY(centerY);
		line1.setEndX(centerX+1);
		line1.setEndY(centerY);
		label=new Text();
		label.setY(centerY);
		label.setText("lollopollo");

		label.setX(centerX-label.getLayoutBounds().getWidth()/2);
	}*/
	
	
	public void setStartX(double x) {
		line1.setStartX(x);
		managePointer();
	}
	public void setEndX(double x) {
		line1.setEndX(x);
		managePointer();

	}
	public void setStartY(double y) {
		line1.setStartY(y);
		managePointer();

	}
	public void setEndY(double y) {
		line1.setEndY(y);
		managePointer();

	}
	
	public double getStartX() {
		return line1.getStartX();
	}
	public double getStartY() {
		return line1.getStartY();
	}
	public double getEndX() {
		return line1.getEndX();
	}
	public double getEndY() {
		return line1.getEndY();
	}
	
	public Arrow(Circle parent, Circle target, String l) {
		line1 = new Line();
		line2 = new Line();
		line3 = new Line();
		manageArcTan2 = new Boolean(false);
		manageArcTan3 = new Boolean(false);
		line1.setStartX(parent.getCenterX());
		line1.setStartY(parent.getCenterY());
		line1.setEndX(target.getCenterX());
		line1.setEndY(target.getCenterY());
		label=new Text();
		label.setX((line1.getEndX()-line1.getEndX())/2);
		label.setY((line1.getEndY()-line1.getEndY())/2);
		label.setText(l);
		controller.setOnArrowTextClickedController(this);
		managePointer();
	}
	
	public Arrow(blackCircle parent, blackCircle target, String l) {
		line1 = new Line();
		line2 = new Line();
		line3 = new Line();
		controller = new Controller();
		manageArcTan2 = new Boolean(false);
		manageArcTan3 = new Boolean(false);
		line1.setStartX(parent.getCircle().getCenterX());
		line1.setStartY(parent.getCircle().getCenterY());
		line1.setEndX(target.getCircle().getCenterX());
		line1.setEndY(target.getCircle().getCenterY());
		label=new Text();
		label.setX((line1.getEndX()-line1.getEndX())/2);
		label.setY((line1.getEndY()-line1.getEndY())/2);
		label.setText(l);
		controller.setOnArrowTextClickedController(this);
		managePointer();
	}
	
	public Line getLine1() {
		return line1;
	}
	
	public Text getText() {
		return label;
	}
	
	public Line getLine2() {
		return line2;
	}
	
	public Line getLine3() {
		return line3;
	}
	
	public void setText(String s) {
		label.setText(s);
	}
	
	public void setLines(blackCircle parent, blackCircle target) {
		line1 = new Line();
		line1.setStartX(parent.getCircle().getCenterX());
		line1.setStartY(parent.getCircle().getCenterY());
		line1.setEndX(target.getCircle().getCenterX());
		line1.setEndY(target.getCircle().getCenterY());
		this.managePointer();
		//missing arrow
		
	}
	
	public void managePointer() {
		boolean segno2;
		boolean segno3;
		line2.setEndX(line1.getEndX());
		line2.setEndY(line1.getEndY());
		line3.setEndX(line1.getEndX());
		line3.setEndY(line1.getEndY());
		
		double sinTeta= (double)(line1.getEndY()-line1.getStartY())/Math.sqrt(Math.pow((line1.getEndY()-line1.getStartY()),2)+ Math.pow((line1.getStartX()-line1.getEndX()), 2));
		double cosTeta= (double)(line1.getEndX()-line1.getStartX())/Math.sqrt(Math.pow((line1.getEndY()-line1.getStartY()),2)+ Math.pow((line1.getStartX()-line1.getEndX()), 2));
		double tanTeta = (double)(line1.getStartY()-line1.getEndY())/(line1.getStartX()-line1.getEndX());
		
		line2.setEndX(line1.getEndX()-10*cosTeta);
		line2.setEndY(line1.getEndY()-10*sinTeta);
		line3.setEndX(line1.getEndX()-10*cosTeta);
		line3.setEndY(line1.getEndY()-10*sinTeta);

		label.setX(line1.getStartX()+(line1.getEndX()-line1.getStartX())/2-label.getLayoutBounds().getWidth()/2);
		label.setY(line1.getStartY()+(line1.getEndY()-line1.getStartY())/2);
		
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
	public void pushInPane (Pane pane) {
		pane.getChildren().add(getLine1());
		pane.getChildren().add(getLine2());
		pane.getChildren().add(getLine3());
		pane.getChildren().add(getText());
	}
}