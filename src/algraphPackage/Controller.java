package algraphPackage;

import javafx.application.Application;
import graphPackage.*;
import graphPackage.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.*;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Circle;
import javafx.scene.input.ClipboardContent;
import javafx.scene.Cursor;
import javafx.animation.*;
import javafx.scene.input.Dragboard;
import javafx.scene.shape.Line;
import javafx.scene.effect.*;

import java.io.File;
import java.lang.Boolean;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.*;
public class Controller <T extends Comparable<T>>{
	
	private Boolean draggedEvent;
	private static Boolean mode;
	public static blackCircle b1;
	public static Boolean b1Exists;
	public static blackCircle b2;
	public static ObservableList<String> items;
	public static ObservableList<String> queue;
	public static ObservableList<String> dist;
	private static int c=0;
	private static Boolean applyMode;
	private static Boolean end;
	private static Boolean next;
	private static VisualGraph<String> visualGraph;
	private static GraphVisit<String> graphVisit;
	
	public Controller() {
		draggedEvent = new Boolean(false);
	}
	
	public Controller(ObservableList<String> item, ObservableList<String> qu, ObservableList<String> ledist,  VisualGraph visualGraph) {

		items = item;
		queue = qu;
		dist= ledist;
		draggedEvent = new Boolean(false);
		b1Exists = new Boolean(false);
		mode = new Boolean(true);
		next=new Boolean(false);
		end=new Boolean (false);
		applyMode = new Boolean(false);
		this.visualGraph = visualGraph;
	}
	 
	public void setOnMouseTextController(Text text, Integer i, applicationRunning a) {
			
	    	text.setOnMouseClicked(event ->{
	        	a.setStart(i);
	        	a.getMenuStage().close();
	        });
	        text.setOnMouseEntered(event ->{ text.setUnderline(true);});
	        text.setOnMouseExited(event ->{ text.setUnderline(false);});
	    }
	
	public void addButtonController(Button button, Pane pane, VisualGraph<String> visualGraph) {
		button.setOnMouseClicked(event ->{
			visualGraph.insertNode();
			applyMode = false;
			items.add(0, "Node added");
    	});
	}
	
	public void nextButtonController(Button button, BellmanFordMoore<T> bellmanFord) {
		button.setOnMouseClicked(event ->{
			next=true;
			bellmanFord.programCounter();
			GraphVisualize(bellmanFord.getVisualGraph(), bellmanFord.getRadice(), bellmanFord.getQ(), bellmanFord.getN(), bellmanFord.getM(), bellmanFord.getDistances(), bellmanFord.getParents(), bellmanFord.getIndex());
    	});
	}
	
	public void endButtonController(Button button,Button button2,Button button3,Button button4,Button button5,Button button6,Button button7,BellmanFordMoore<T> bellmanFord) {
		button5.setOnMouseClicked(event ->{
			while (end==false) {
				bellmanFord.programCounter();
			}
			GraphVisualize(bellmanFord.getVisualGraph(), bellmanFord.getRadice(), bellmanFord.getQ(), bellmanFord.getN(), bellmanFord.getM(), bellmanFord.getDistances(), bellmanFord.getParents(), bellmanFord.getIndex());
        	Controller.setEnd(false);
			button.setDisable(false);
			button2.setDisable(false);
			button3.setDisable(false);
			button4.setDisable(false);
			button5.setDisable(true);
			button6.setDisable(true);
			button7.setDisable(false);
		});
	}
	
	public static Boolean getEnd() {
		return end;
	}
	
	public static void setEnd(boolean b) {
		end=b;
	}
	
	public Boolean getNext() {
		return next;
	}
	
	public void linkButtonController(Button button, VisualGraph<String> visualGraph) {
		button.setOnMouseClicked(event -> { //FUORI DA QUI, MODE NON VIENE MODIFICATO
			if (!mode) {
				button.setText("Go to Selection mode");
				mode = true;
				if (c == 1) {
					b1.circleExpand(1);
					b1.changeHovered();
				}
				items.add(0, "Switched to Link mode");
			}
			else {
				button.setText("Go to Link mode");
				mode = false;
				if (c == 1) {
					b1.circleExpand(1);
					b1.changeHovered();
				}
				items.add(0, "Switched to Selection mode");
			}
			applyMode = false;
		});
		
	}

	public void setOnSave(MenuItem save, Graph<String> G) {
		save.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent event) {
 				 FileChooser fileChooser = new FileChooser();
 				 fileChooser.setInitialDirectory(new File("."));
 		         fileChooser.setTitle("Save graph...");
 		         fileChooser.getExtensionFilters().addAll(
 		                 new FileChooser.ExtensionFilter("All Files", "*.*"),
 		                 new FileChooser.ExtensionFilter("gph", "*.gph"),
 		                 new FileChooser.ExtensionFilter("txt", "*.txt")
 		             );
 		           File file = fileChooser.showSaveDialog(new Stage());
 		           if (file != null) {
 		                	G.outGraph(file.getPath().toString());
 		                items.add(0, "Graph saved succesfully to file");
 		                }
 			}
 		});
	}
	
	public boolean setOnOpen(MenuItem menuItem, Graph<String> G, Pane pane) {
		
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent event) {
 				applicationRunning.graphFromFile(G);
 				pane.getChildren().remove(0, pane.getChildren().size());
 				items.add(0, "Graph opened succesfully");
 		                }
 		});
		return true;
	}
	

	public void setOnVettDis (MenuItem vettdis, GraphVisit graphvisit) {
		
		String css = this.getClass().getResource("../style.css").toExternalForm();
		Stage stage = new Stage();
		stage.setTitle("Vettore delle Distanze");
		VBox panel = new VBox();
		Scene scene = new Scene(panel);
		String vettore = null;
		for (int i = 0; i < graphvisit.getLength(); i++)
		{
			vettore = vettore + graphvisit.distances[i] + " ";
			
		}
		
		Text text = new Text(vettore);
		Text titolino = new Text("Questo � il vettore delle distanze");

		panel.getChildren().addAll(titolino,text);
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	public void applyButtonController(VisualGraph<String> visualGraph, Button button1, Button button2, Button button3, Button button4, Button button5, Button button6,Button button7, ObservableList<String> qu,BellmanFordMoore<String> bellmanFord) {

		button3.setOnMouseClicked(event ->{

		if (visualGraph.countSelected()==0) items.add(0, "Error: can't apply because there are no nodes selected");
		else if (visualGraph.countSelected()>1) items.add(0, "Error: can't apply because there are more than one nodes selected");
		else {
			graphVisit=new GraphVisit<String>();
			List<Node<String>> list = new LinkedList<Node<String>>();
			list = graphVisit.detectNegativeCycles(visualGraph.getGraph(), visualGraph.getSelectedNode());
			
			if (!list.isEmpty()) {   //Colora i nodi selezionati e anche le frecce tra di loro
				items.add(0, "Error: negative cycle");
				for (Node<String> node : list) {
					visualGraph.getBlackCircle(node).getCircle().setFill(Color.GOLD);
					}
			}
			else {
				applyMode = true;
				button1.setDisable(true);
				button2.setDisable(true);
				button3.setDisable(true);
				button4.setDisable(true);
				button5.setDisable(false);
				button6.setDisable(false);
				button7.setDisable(true);
				bellmanFord.setRadice(visualGraph.getSelectedNode());
				//graphVisit.BellmanFord(visualGraph, visualGraph.getSelectedNode(),button5,button6,button1,button2,button3,button4,qu);
			}
		}
		});
	}
	
	
	public void setOnArrowTextClickedController(Arrow arrow) {
		arrow.getText().setOnMouseClicked(event -> {
			Stage stage = new Stage();
			HBox hbox = new HBox();
			HBox hbox2 = new HBox();
			VBox vbox = new VBox();
			Text text = new Text("New name");
			TextField textField = new TextField();
			Button button = new Button("Apply");
			hbox.getChildren().addAll(text,textField);
			hbox2.getChildren().add(button);
			hbox.setAlignment(Pos.CENTER);
			hbox2.setAlignment(Pos.CENTER);
			hbox.setMinWidth(300);
			hbox.setMinHeight(80);
			hbox.setSpacing(20);
			hbox2.setMinWidth(300);
			hbox2.setMinHeight(80);
			vbox.getChildren().addAll(hbox,hbox2);
			button.setOnMouseClicked(e -> {
			try {
					String tmp =  arrow.getText().getText();
					applicationRunning.getVisualGraph().renameEdge(arrow.getParent(), arrow.getTarget(), Integer.parseInt(textField.getText()));
					arrow.setText(textField.getText());
					items.add(0, "Arrow value changed succesfully from "+tmp+" to "+arrow.getText().getText());
					stage.close();
				} catch (NumberFormatException except){
					Alert alert=new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Invalid value.");
					items.add(0, "Invalid value");
					alert.showAndWait();
				}
			applyMode = false;
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
			HBox hbox2 = new HBox();
			VBox vbox = new VBox();
			Text text = new Text("New name");
			TextField textField = new TextField();
			Button button = new Button("Apply");
			hbox.getChildren().addAll(text,textField);
			hbox2.getChildren().add(button);
			hbox.setAlignment(Pos.CENTER);
			hbox2.setAlignment(Pos.CENTER);
			hbox.setMinWidth(300);
			hbox.setMinHeight(80);
			hbox.setSpacing(20);
			hbox2.setMinWidth(300);
			hbox2.setMinHeight(80);
			vbox.getChildren().addAll(hbox,hbox2);
			button.setOnMouseClicked(e -> {
				String tmp = blackcircle.getText().getText();
				if (!textField.getText().contains(":")) {
				applicationRunning.getVisualGraph().renameNode(blackcircle, textField.getText());
				items.add(0, "Node name changed succesfully from "+tmp+" to "+blackcircle.getText().getText());
				}
				else
					items.add(0, "Name must not contain ':'");
				stage.close();
			});
			Scene scene = new Scene(vbox);
			stage.setScene(scene);
			stage.show();
		});
	}
	
	
	
	
	public void circleOnMouseDraggedController(blackCircle blackcircle, Pane pane) {
		
		blackcircle.getCircle().setOnMouseDragged(event -> {
			if (event.isPrimaryButtonDown()) {
				if (event.isDragDetect()) items.add(0, "Dragging "+blackcircle.getText().getText()+"...");
					blackcircle.getCircle().setCenterX(event.getX());
					blackcircle.getCircle().setCenterY(event.getY());
					for (Arrow o : blackcircle.getOutList()) {
						o.setLines(o.getParent(), o.getTarget());
					}
					for (Arrow o : blackcircle.getInList()) {
						o.setLines(o.getParent(), o.getTarget());
					}
					boundsController(blackcircle, pane);
            
					draggedEvent = true;
			}
    });
	}
	
	
	public void setOnCircleHovered(blackCircle b) {
		b.getCircle().setOnMouseEntered(event -> {
			b.circleExpand(1.5);
		});
		b.getCircle().setOnMouseExited(event -> {
			if (!b.getHovered()) {
				b.circleExpand(1);
			}
		});
	}
	
	
	public void arrowOnMouseClickedController(Arrow arrow) {
		arrow.getLine1().setOnMouseClicked(event -> {
		if (!mode) {
			if (!arrow.getChosen()) {
				arrow.getLine1().setStroke(Color.GREY);
				arrow.getLine2().setStroke(Color.GREY);
				arrow.getLine3().setStroke(Color.GREY);
				items.add(0, "Arrow selected");
			}
			else {
				arrow.getLine1().setStroke(Color.BLACK);
				arrow.getLine2().setStroke(Color.BLACK);
				arrow.getLine3().setStroke(Color.BLACK);
				items.add(0, "Arrow deselected");
			}
			arrow.changeChosen();
		}
		});
	}
	
	public void circleOnMouseClickedController(blackCircle blackcircle,Pane pane) {
		
		
		
		blackcircle.getCircle().setOnMouseClicked(event -> {
			if (!draggedEvent) {
				if (applyMode) {}
				else if (mode) {
					if (c==0) {
						b1Exists = true;
						b1 = new blackCircle();
						b1 = blackcircle;
						b1.changeHovered();
						
					}
					if (c==1) {
						b2 = new blackCircle();
						b2 = blackcircle;
						b1.incrementMaxList();
						
						if (!b2.equals(b1)) {
						applicationRunning.getVisualGraph().insertEdge(applicationRunning.getVisualGraph().getNode(b1), applicationRunning.getVisualGraph().getNode(b2),"1");
						b1.changeHovered();
						b1.circleExpand(1);
						b2.getInList().add(b1.getOutList().get(b1.getMaxList()-1));
						items.add(0, b1.getText().getText()+" -> "+b2.getText().getText()+" succesfully linked");
						}
					}
					c = c+1;
					if (c == 2) c=0;
					
				}
				else {
					c = 0;
					if(!blackcircle.getChosen()) {
						blackcircle.getCircle().setFill(Color.GREY);
						blackcircle.changeChosen();
						items.add(0, blackcircle.getText().getText()+" selected");
					}
					else {
						blackcircle.getCircle().setFill(Color.BLACK);
						blackcircle.changeChosen();
						items.add(0, blackcircle.getText().getText()+" deselected");
					}
				}
			}
			else draggedEvent = false;
		});
	}
	
	public void removeButtonController(Button button, VisualGraph<String> visualGraph, Pane pane) {
		button.setOnMouseClicked(event -> {
			applyMode = false;
			visualGraph.deleteChosenArrows();
			visualGraph.deleteChosenNode();
			pane.getChildren().remove(0, pane.getChildren().size());
			visualGraph.visualize();
		});
		
	}
	
	public static void boundsController(blackCircle blackcircle, Pane pane) {
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
		
		for(Arrow line: blackcircle.getInList())
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
	
	public void clearColorButtonController(Button b, VisualGraph<T> visualGraph) {
		b.setOnMouseClicked(event -> {
			applyMode = false;
			HashSet<blackCircle> set=new HashSet<blackCircle>();
			set = visualGraph.selectedCircles();
			for (blackCircle o : set) {
				o.changeChosen();
			}
			visualGraph.allBlack();
		});
	}
	
	public void GraphVisualize(VisualGraph<T> visualGraph, Node<T> radice, LinkedList<Node<T>> q, Node<T> poppedNode, Node<T> adjNode,Integer[] distances,Node<T>[] parents, TreeMap<Node<T>, Integer> index) {

		visualGraph.setColor(Color.BLACK);
		visualGraph.getBlackCircle(radice).getCircle().setFill(Color.RED);
		visualGraph.getBlackCircle(poppedNode).getCircle().setFill(Color.BLUE);
		if (adjNode!=null)
			visualGraph.getBlackCircle(adjNode).getCircle().setFill(Color.GREEN);
		for (Node<T> n: visualGraph.getGraph().V()) {
			for (Arrow arrow: visualGraph.getArrows()) {
				if (parents[index.get(n)]!=null && arrow.getTarget()==visualGraph.getBlackCircle(n) && arrow.getParent()==visualGraph.getBlackCircle(parents[index.get(n)])) {//dovrebbe colorare le frecce del vettore dei padri
					arrow.setColor(Color.RED);
				}
			}
		
		}
		
		Controller.queue.remove(0, queue.size());
		queue.add("Coda:");
		for (Node<T> a: q)
		queue.add(a.toString());
		
		Controller.dist.remove(0, dist.size());
		for (Node<T> n: visualGraph.getGraph().V()) {
			dist.add( n.getElement()+": "+distances[index.get(n)]);
			
		}
		//Bisogna trovare un modo di vedere il vettore delle distanze
			
		//POI ABBIAMO FINITO
	}
	
}