package algraphPackage;

import javafx.application.Application;
import java.util.List;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.Group;
import javafx.scene.layout.GridPane; 
import javafx.scene.control.Button;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Circle;
import javafx.scene.input.ClipboardContent;
import javafx.scene.Cursor;
import javafx.scene.input.Dragboard;
import javafx.scene.shape.Line;
import java.lang.Boolean;
import java.util.concurrent.atomic.AtomicBoolean;
public class Controller{
	
	private Boolean draggedEvent,draggedLine;
	private Boolean mode;
	
	public Controller() {
		draggedEvent = new Boolean(false);
		draggedLine = new Boolean(false);
		mode = new Boolean(true);
	}
	 
	public void setOnMouseTextController(Text text, Integer i, applicationRunning a) {
			
	    	text.setOnMouseClicked(event ->{
	        	a.setStart(i);
	        	a.getMenuStage().close();
	        });
	        text.setOnMouseEntered(event ->{ text.setUnderline(true);});
	        text.setOnMouseExited(event ->{ text.setUnderline(false);});
	    }
	
	public void addButtonController(Button button, Pane pane) {
		button.setOnMouseClicked(event ->{
			blackCircle blackcircle = new blackCircle(pane, "lollo");
			boundsController(blackcircle,pane);
    	});
	}
	
	public void manageButtonController(Button button) {
		button.setOnMouseClicked(event ->{
			if (!mode) {
				button.setText("Move nodes");
				mode = true;
			}
			else {
				button.setText("Move arrows");
				mode = false;
			}
		});
		
	}

	
	public void circleOnMouseDraggedController(blackCircle blackcircle, Pane pane) {
		
		blackcircle.getCircle().setOnMouseDragged(event -> {
			if (event.isPrimaryButtonDown()) {
				
				if (!mode) {
					blackcircle.getCircle().setCenterX(event.getX());
					blackcircle.getCircle().setCenterY(event.getY());
					for (Arrow o : blackcircle.getOutList()) {
						o.getLine1().setStartX(event.getX());
						o.getLine1().setStartY(event.getY());
					}
            
					boundsController(blackcircle, pane);
            
					draggedEvent = true;
				}
				else {
					if (!draggedLine) {
						blackcircle.getOutList().add(new Arrow(blackcircle.getCircle().getCenterX(), blackcircle.getCircle().getCenterY()));
						blackcircle.incrementMaxList();
						blackcircle.getOutList().get(blackcircle.getMaxList()-1).pushInPane(pane);
						draggedLine = true;
					}
					pane.setOnMouseClicked(e -> {
						
						// if the event is dropped inside a blackcircle, 
						
						draggedLine = false;
					});
					Line line = blackcircle.getOutList().get(blackcircle.getMaxList()-1).getLine1();
					line.setStartX(blackcircle.getCircle().getCenterX());
					line.setStartY(blackcircle.getCircle().getCenterY());
					
					line.setEndX(event.getX());
					line.setEndY(event.getY());
					
					blackcircle.getOutList().get(blackcircle.getMaxList()-1).managePointer();
            	
					boundsController(blackcircle, pane);
            	
					draggedEvent = true;

				}
			}
    });
	}
	
	public void circleOnMouseClickedController(blackCircle blackcircle) {
		
		blackcircle.getCircle().setOnMouseClicked(event -> {
			
			if (!draggedEvent) {
				if (!blackcircle.getChosen()) blackcircle.getCircle().setFill(Color.GREY);
				else blackcircle.getCircle().setFill(Color.BLACK);
			}
			else draggedEvent = false;
			
			blackcircle.changeChosen();
			
		});
	}
	
	public void removeButtonController(Button button) {
		button.setOnMouseClicked(event -> {
			//function that looks for selected circles needed
		});
		
	}
	
	public void boundsController(blackCircle blackcircle, Pane pane) {
		Bounds bounds = pane.getLayoutBounds();
		Circle circle = blackcircle.getCircle();
		
		if (circle.getCenterX() > (bounds.getMaxX()-circle.getRadius())) {
			circle.setCenterX(bounds.getMaxX()-circle.getRadius());
		}
		if (circle.getCenterX() < (bounds.getMinX()+circle.getRadius())) {
			circle.setCenterX(bounds.getMinX()+circle.getRadius());
		}
		if (circle.getCenterY() > (bounds.getMaxY()-circle.getRadius())) {
			circle.setCenterY(bounds.getMaxY()-circle.getRadius());
		}
		if (circle.getCenterY() < (bounds.getMinY()+circle.getRadius())) {
			circle.setCenterY(bounds.getMinY()+circle.getRadius());
		}
		
		for(Arrow line: blackcircle.getOutList())
		{
		if (line.getEndX() > (bounds.getMaxX()-circle.getRadius())) {
			line.setEndX(bounds.getMaxX()-circle.getRadius());
		}
		if (line.getEndX() < (bounds.getMinX()+circle.getRadius())) {
			line.setEndX(bounds.getMinX()+circle.getRadius());
		}
		if (line.getEndY() > (bounds.getMaxY()-circle.getRadius())) {
			line.setEndY(bounds.getMaxY()-circle.getRadius());
		}
		if (line.getEndY() < (bounds.getMinY()+circle.getRadius())) {
			line.setEndY(bounds.getMinY()+circle.getRadius());
		}
		}
	
	for(Arrow line: blackcircle.getOutList()) {
		if (line.getStartX() > (bounds.getMaxX()-circle.getRadius())) {
			line.setStartX(bounds.getMaxX()-circle.getRadius());
		}
		if (line.getStartX() < (bounds.getMinX()+circle.getRadius())) {
			line.setStartX(bounds.getMinX()+circle.getRadius());
		}
		if (line.getStartY() > (bounds.getMaxY()-circle.getRadius())) {
			line.setStartY(bounds.getMaxY()-circle.getRadius());
		}
		if (line.getStartY() < (bounds.getMinY()+circle.getRadius())) {
			line.setStartY(bounds.getMinY()+circle.getRadius());
		}
		
		}
	blackcircle.moveText();
	}
	
	/////////////////////////////////////////////////////////////////////////////////
	
}