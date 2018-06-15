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
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.*;
public class Controller{
	
	private Boolean draggedEvent,draggedLine,mode,b1Exists,b2Exists;
	public static blackCircle b1;
	public static blackCircle b2;
	private static int c=0;
	
	public Controller() {
		draggedEvent = new Boolean(false);
		draggedLine = new Boolean(false);
		mode = new Boolean(false);
		b1Exists = new Boolean(false);
		b2Exists = new Boolean(false);
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
			blackCircle blackcircle = new blackCircle(pane);
			boundsController(blackcircle,pane);
    	});
	}
	
	public void linkButtonController(Button button, VisualGraph<String> visualGraph) {
		button.setOnMouseClicked(event -> { //FUORI DA QUI, MODE NON VIENE MODIFICATO
			if (!mode) {
				button.setText("Normal Mode");
				mode = true;
				System.out.println(mode);
			}
			else {
				button.setText("Link Mode");
				mode = false;
				System.out.println(mode);
			}
		});
		/*button.setOnMouseClicked(event ->{
			Stage stage = new Stage();
			HBox hbox1 = new HBox();
			HBox hbox2 = new HBox();
			VBox vbox = new VBox();
			Text text1 = new Text("Starting node ");
			Text text2 = new Text("Target node ");
			TextField textField1 = new TextField();
			TextField textField2 = new TextField();
			Button button2 = new Button("Apply");
			hbox1.getChildren().addAll(text1,textField1);
			hbox2.getChildren().addAll(text2,textField2);
			vbox.getChildren().addAll(hbox1,hbox2,button2);
			button2.setOnMouseClicked(e -> {
				//function that gets the blackcircle with that label
				/*Arrow arrow = new Arrow(blackcircle1, blackcircle2, string);
				pane.getChildren().addAll(arrow.getLine1(), arrow.getLine2, arrow.getLine3());
				stage.close();
				
			});
			Scene scene = new Scene(vbox);
			stage.setScene(scene);
			stage.show();
		});*/
		
	}

	public void setOnArrowTextClickedController(Arrow arrow) {
		arrow.getText().setOnMouseClicked(event -> {
			Stage stage = new Stage();
			HBox hbox = new HBox();
			VBox vbox = new VBox();
			Text text = new Text("New name");
			TextField textField = new TextField();
			Button button = new Button("Apply");
			hbox.getChildren().addAll(text,textField);
			vbox.getChildren().addAll(hbox,button);
			button.setOnMouseClicked(e -> {
				arrow.setText(textField.getText());
				stage.close();
			});
			Scene scene = new Scene(vbox);
			stage.setScene(scene);
			stage.show();
		});
	}
	
	
	public void setOnCircleTextClickedController(blackCircle blackcircle) {
		blackcircle.getText().setOnMouseClicked(event -> {
			Stage stage = new Stage();
			HBox hbox = new HBox();
			VBox vbox = new VBox();
			Text text = new Text("New name");
			TextField textField = new TextField();
			Button button = new Button("Apply");
			hbox.getChildren().addAll(text,textField);
			vbox.getChildren().addAll(hbox,button);
			button.setOnMouseClicked(e -> {
				blackcircle.setText(textField.getText());
				stage.close();
			});
			Scene scene = new Scene(vbox);
			stage.setScene(scene);
			stage.show();
		});
	}
	
	
	
	
	public void circleOnMouseDraggedController(blackCircle blackcircle, Pane pane) {
		
		blackcircle.getCircle().setOnMouseDragged(event -> {
			System.out.println(mode);
			if (event.isPrimaryButtonDown()) {
				
					blackcircle.getCircle().setCenterX(event.getX());
					blackcircle.getCircle().setCenterY(event.getY());
					for (Arrow o : blackcircle.getOutList()) {
						o.getLine1().setStartX(event.getX());
						o.getLine1().setStartY(event.getY());
						o.managePointer();
					}
					for (Arrow o : blackcircle.getInList()) {
						o.getLine1().setEndX(event.getX());
						o.getLine1().setEndY(event.getY());
						o.managePointer();
					}
					boundsController(blackcircle, pane);
            
					draggedEvent = true;
				
				/*else {
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

				}*/
			}
    });
	}
	
	public void circleOnMouseClickedController(blackCircle blackcircle,Pane pane) {
		
		blackcircle.getCircle().setOnMouseClicked(event -> {
			if (!draggedEvent) {
				if (mode) {

				if (!blackcircle.getChosen()) {
					if (c==0) {
						b1 = new blackCircle();
						blackcircle.getCircle().setFill(Color.GREY);
						b1 = blackcircle;
						
					}
					if (c==1) {
						b2 = new blackCircle();
						b2 = blackcircle;
						b1.getOutList().add(new Arrow(b1,b2,"lol"));
						b1.incrementMaxList();
						pane.getChildren().add(b1.getOutList().get(b1.getMaxList()-1).getLine1());
						pane.getChildren().add(b1.getOutList().get(b1.getMaxList()-1).getLine2());
						pane.getChildren().add(b1.getOutList().get(b1.getMaxList()-1).getLine3());
						pane.getChildren().add(b1.getOutList().get(b1.getMaxList()-1).getText());
						
						b2.getInList().add(b1.getOutList().get(b1.getMaxList()-1));
						b1.getCircle().setFill(Color.BLACK);
					}
					c = c+1;
					if (c == 2) c=0;
				}
				}
				else {
					c = 0;
					if (b1Exists) b1.getCircle().setFill(Color.BLACK);
					if(!blackcircle.getChosen()) {
						blackcircle.getCircle().setFill(Color.GREY);
						blackcircle.changeChosen();
					}
					else {
						blackcircle.getCircle().setFill(Color.BLACK);
						blackcircle.changeChosen();
					}
				}
			}
			else draggedEvent = false;
			
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
		Text text = blackcircle.getText();
		
		if (circle.getCenterX() > (bounds.getMaxX()-circle.getRadius())) {
			circle.setCenterX(bounds.getMaxX()-circle.getRadius());
			text.setX(circle.getCenterX()-text.getLayoutBounds().getWidth()/2);
		}
		if (circle.getCenterX() < (bounds.getMinX()+circle.getRadius())) {
			circle.setCenterX(bounds.getMinX()+circle.getRadius());
			text.setX(circle.getCenterX()-text.getLayoutBounds().getWidth()/2);
		}
		if (circle.getCenterY() > (bounds.getMaxY()-circle.getRadius())) {
			circle.setCenterY(bounds.getMaxY()-circle.getRadius());
			text.setY(circle.getCenterY()-2*circle.getRadius());
		}
		if (circle.getCenterY() < (bounds.getMinY()+circle.getRadius())) {
			circle.setCenterY(bounds.getMinY()+circle.getRadius());
			text.setY(circle.getCenterY()-2*circle.getRadius());
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