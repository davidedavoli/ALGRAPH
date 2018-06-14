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


public class blackCircle{
	private Circle circle;
	private Controller controller;
	private Boolean chosen;
	private List<Arrow> list;
	private Integer maxList;
	public blackCircle(Pane pane) {
		controller = new Controller();
		list = new ArrayList<Arrow>();
		chosen = false;
		maxList = 0;
		circle = new Circle(10);
		circle.setFill(Color.BLACK);
    	circle.setCursor(Cursor.MOVE);
        circle.setCenterX(ThreadLocalRandom.current().nextInt(0,1700));
        circle.setCenterY(ThreadLocalRandom.current().nextInt(0,1000));
        controller.circleOnMouseClickedController(this);
        controller.circleOnMouseDraggedController(this, pane);
	}
	
	public List<Arrow> getList() {
		return list;
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
		this.getList().add(new Arrow(this, target, label));
		this.incrementMaxList();
	}
	
	public void deleteLink (blackCircle target) {
		for (Arrow a : list) {
			if (a.getLine1().getEndX() == target.getCircle().getCenterX() && a.getLine1().getEndY() == target.getCircle().getCenterY())
			{
				list.remove(a);
			}
		}
	}
}