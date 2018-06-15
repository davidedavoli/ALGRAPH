package algraphPackage;

import javafx.event.EventHandler;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import java.util.concurrent.ThreadLocalRandom;

import algraphPackage.Node;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class blackCircle{
	private static int c=0;
	private Circle circle;
	private Controller controller;
	private Boolean chosen;
	private List<Arrow> outList;
	private List<Arrow> inList;
	private Integer maxList;
	private Text text;
	private Pane pane;
	public blackCircle() {}
	/*public blackCircle(Pane pane) {
		c=c+1;
		this.pane=pane;
		text=new Text();
		setText(name);
		controller = new Controller();
		outList = new ArrayList<Arrow>();
		inList = new ArrayList<Arrow>();
		chosen = false;
		maxList = new Integer(0);
		circle = new Circle(10);
		circle.setFill(Color.BLACK);
    	circle.setCursor(Cursor.MOVE);
        circle.setCenterX(ThreadLocalRandom.current().nextInt(0,1700));
        circle.setCenterY(ThreadLocalRandom.current().nextInt(0,1000));
        text.setText(String.valueOf(c));
        text.setX(circle.getCenterX()-text.getLayoutBounds().getWidth()/2);
        text.setY(circle.getCenterY()-2*circle.getRadius());
        controller.circleOnMouseClickedController(this,pane);
        controller.circleOnMouseDraggedController(this, pane);
        controller.setOnCircleTextClickedController(this);
		pane.getChildren().add(this.getCircle());
		pane.getChildren().add(this.getText());
	}
	*/
	public blackCircle(Pane pane) {
		c=c+1;
		this.pane=pane;
		text=new Text();
		controller = new Controller();
		outList = new ArrayList<Arrow>();
		inList = new ArrayList<Arrow>();
		chosen = false;
		maxList = new Integer(0);
		circle = new Circle(10);
		circle.setFill(Color.BLACK);
    	circle.setCursor(Cursor.MOVE);
        circle.setCenterX(ThreadLocalRandom.current().nextInt(0,1700));
        circle.setCenterY(ThreadLocalRandom.current().nextInt(0,1000));
        text.setText(String.valueOf(c));
        text.setX(circle.getCenterX()-text.getLayoutBounds().getWidth()/2);
        text.setY(circle.getCenterY()-2*circle.getRadius());
        controller.circleOnMouseClickedController(this,pane);
        controller.circleOnMouseDraggedController(this, pane);
        controller.setOnCircleTextClickedController(this);
		pane.getChildren().add(this.getCircle());
		pane.getChildren().add(this.getText());
	}
	
	public void pushInPane() {
		pane.getChildren().add(this.getCircle());
		pane.getChildren().add(this.getText());
	}
	
	public Pane getPane() {
		return pane;
	}
	public List<Arrow> getOutList() {
		return outList;
	}
	
	public void setText(String t) {
		text.setText(t);
		text.setX(circle.getCenterX()-text.getLayoutBounds().getWidth()/2);
	}
	
	public Text getText() {
		return text;
	}
	
	public Boolean getChosen() {
		return chosen;
	}
	
	public void changeChosen() {
		chosen=!chosen;
	}
	
	public Circle getCircle() {
		return circle;
	}
	
	public void incrementMaxList() {
		maxList = maxList+1;
	}
	
	public Integer getMaxList() {
		return maxList;
	}
	
	public void bind(blackCircle target, String label) {
		this.getOutList().add(new Arrow(this, target, label));
		this.incrementMaxList();
		this.outList.get(this.getMaxList()-1).pushInPane(pane);
		target.getInList().add(this.getOutList().get(this.getOutList().size()-1));
	}
	
	public List<Arrow> getInList() {
		return inList;
	}

	public void setInList(List<Arrow> inList) {
		this.inList = inList;
	}

	public void setOutList(List<Arrow> outList) {
		this.outList = outList;
	}
	public void moveText() {
		 text.setX(circle.getCenterX()-text.getLayoutBounds().getWidth()/2);
	        text.setY(circle.getCenterY()-2*circle.getRadius());
	}
	public void deleteLink (blackCircle target) {
		Iterator<Arrow> a = this.outList.iterator();
		List<Arrow> toRemove=new ArrayList<Arrow>();
		while (a.hasNext()) {
			Arrow p=a.next();
			if (p.getTarget()==target)
			{
				toRemove.add(p);
			}
		}
		outList.removeAll(toRemove);
	}
}