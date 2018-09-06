package algraphPackage;

import javafx.event.ActionEvent;
import java.io.File;
import java.util.List;

import javafx.application.Application;
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
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.text.*;
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
import javafx.scene.shape.Line;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.css.Styleable;
import javafx.collections.*;
import graphPackage.*;
import javafx.scene.control.*;;

public class applicationRunning{
	
	private Stage stage,stage2;
	private Integer start;
	private Controller<String> controller;
	private static VisualGraph<String> visualGraph;
	private ListView<String> list;
	private ListView<String> lacoda;
	private ListView<String> ledist;
	private ObservableList<String> items;
	private ObservableList<String> dist;
	private ObservableList<String> qu;
	//variable for the graph
	
	public applicationRunning() {
		String css = this.getClass().getResource("../style.css").toExternalForm();
		Graph<String> G=new Graph<String>();
		FileChooser fileChooser = new FileChooser();
		controller=new Controller<String>();
		fileChooser.setInitialDirectory(new java.io.File("."));
		list = new ListView<String>();
		items = FXCollections.observableArrayList ();
		list.setItems(items);
		lacoda = new ListView<String>();
		qu = FXCollections.observableArrayList();
		lacoda.setItems(qu);
		ledist = new ListView<String>();
		dist = FXCollections.observableArrayList ();
		ledist.setItems(dist);
		
		//controller=new Controller<String>(items);
		menu();
		items.add("Welcome to Algraph");
		qu.add("Coda dell' algoritmo");
		dist.add("Distanze dei nodi");
		if (start==1) {
			randomWindow(G, css);
		} //random graph
		else if (start==2) {
			items.add(graphFromFile(G));
		} //graph from file
		mainPage(G);
	}
	
	public static String graphFromFile(Graph<String> G) {
 		FileChooser fileChooser = new FileChooser();
 		fileChooser.setInitialDirectory(new java.io.File("."));
 		fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("gph", "*.gph"),
                new FileChooser.ExtensionFilter("txt", "*.txt")
            );
 		Stage fc=new Stage();
 		fc.setTitle("Input graph...");
 		File file = fileChooser.showOpenDialog(fc);
         if (file != null) {
             return G.inGraph(file.getAbsolutePath());
         }
         else return "Unable to read the file";
 	}

	private void randomWindow(Graph<String> G, String css) {
		Stage stage = new Stage();
		Text text1 = new Text("Insert max nodes");
		Text text2 = new Text("Insert min nodes");
		Text text3 = new Text("Insert Mw");
		Text text4 = new Text("Insert mw");
		Text text5 = new Text("Density");
		TextField textField1 = new TextField();
		TextField textField2 = new TextField();
		TextField textField3 = new TextField();
		TextField textField4 = new TextField();
		CheckBox checkBox = new CheckBox();
		Button button = new Button("Continue");
		HBox hbox1 = new HBox();
		HBox hbox2 = new HBox();
		HBox hbox3 = new HBox();
		HBox hbox4 = new HBox();
		HBox hbox5 = new HBox();
		HBox hbox6 = new HBox();
		VBox vbox = new VBox();
		HBox box1 = new HBox();
		HBox box2 = new HBox();
		HBox box3 = new HBox();
		HBox box4 = new HBox();
		HBox box5 = new HBox();
		HBox box6 = new HBox();
		HBox box7 = new HBox();
		HBox box8 = new HBox();
		HBox box9 = new HBox();
		HBox box10 = new HBox();
		box1.getChildren().add(text1);
		box1.setMinHeight(80);
		box1.setMinWidth(300);
		box1.setAlignment(Pos.CENTER);
		box2.getChildren().add(textField1);
		box2.setMinHeight(80);
		box2.setMinWidth(300);
		box2.setAlignment(Pos.CENTER);
		box3.getChildren().add(text2);
		box3.setMinHeight(80);
		box3.setMinWidth(300);
		box3.setAlignment(Pos.CENTER);
		box4.getChildren().add(textField2);
		box4.setMinHeight(80);
		box4.setMinWidth(300);
		box4.setAlignment(Pos.CENTER);
		box5.getChildren().add(text3);
		box5.setMinHeight(80);
		box5.setMinWidth(300);
		box5.setAlignment(Pos.CENTER);
		box6.getChildren().add(textField3);
		box6.setMinHeight(80);
		box6.setMinWidth(300);
		box6.setAlignment(Pos.CENTER);
		box7.getChildren().add(text4);
		box7.setMinHeight(80);
		box7.setMinWidth(300);
		box7.setAlignment(Pos.CENTER);
		box8.getChildren().add(textField4);
		box8.setMinHeight(80);
		box8.setMinWidth(300);
		box8.setAlignment(Pos.CENTER);
		box9.getChildren().add(text5);
		box9.setMinHeight(80);
		box9.setMinWidth(300);
		box9.setAlignment(Pos.CENTER);
		box10.getChildren().add(checkBox);
		box10.setMinHeight(80);
		box10.setMinWidth(300);
		box10.setAlignment(Pos.CENTER);
		hbox1.getChildren().addAll(box1,box2);
		hbox2.getChildren().addAll(box3,box4);
		hbox3.getChildren().addAll(box5,box6);
		hbox4.getChildren().addAll(box7,box8);
		hbox5.getChildren().addAll(box9,box10);
		hbox6.getChildren().add(button);
		hbox6.setAlignment(Pos.CENTER);
		hbox6.setMinHeight(80);
		vbox.getChildren().addAll(hbox1,hbox2,hbox3,hbox4,hbox5,hbox6);
		button.setOnMouseClicked(event -> {
			Boolean b = new Boolean(false);
			try {
				if(Integer.valueOf(textField1.getText())<Integer.valueOf(textField2.getText()) || Integer.valueOf(textField1.getText())<Integer.valueOf(textField2.getText()) )
					{b = true; throw new RuntimeException("Max must be greater than min");}
			G.randomGraph(Integer.valueOf(textField1.getText()), Integer.valueOf(textField2.getText()), Integer.valueOf(textField3.getText()), Integer.valueOf(textField4.getText()), checkBox.isSelected());
			}catch(NumberFormatException e) {
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Invalid values.");
				alert.showAndWait();
			}catch(RuntimeException e) {
				Alert alert=new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Invalid values."+e.getMessage());
				alert.showAndWait();
			}
			if (b == false) stage.close();
		});
		Scene scene = new Scene(vbox);
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.showAndWait();
	}
	
	public void setStart(Integer a) {
		start=a;
	}
	
	public Stage getMenuStage() {
		return stage;
	}
	
	public void mainPage(Graph<String> G)
    {
		String css = this.getClass().getResource("../style.css").toExternalForm();
		stage2 = new Stage();
    	stage2.setMaximized(true);
    	stage2.setTitle("Algraph");
    	final Menu menu1 = new Menu("File");
    	final MenuItem menuItem = new MenuItem("Open");
    	final MenuItem otherMenuItem = new MenuItem("Save");
    	
    	((Styleable)menuItem).getStyleClass().add(css);
    	
    	menu1.getItems().add(menuItem);
    	menu1.getItems().add(otherMenuItem);
    	MenuBar menuBar = new MenuBar();
    	menuBar.getMenus().addAll(menu1); 
    	Button button=new Button("Add node");
    	Button button2=new Button("Remove");
    	Button button3=new Button("Apply algorithm");
    	Button button4=new Button("Go to Selection mode");
    	Button button5=new Button("End of computation");
    	Button button6=new Button("Next step");
    	Button button7=new Button("Clear color");
    	button.getStylesheets().add(css);
    	button2.getStylesheets().add(css);
    	button3.getStylesheets().add(css);
    	button4.getStylesheets().add(css);
    	button5.getStylesheets().add(css);
    	button6.getStylesheets().add(css);
    	button5.setDisable(true);
    	button6.setDisable(true);
    	VBox vbox=new VBox();
    	HBox contliste = new HBox();
    	contliste.getChildren().addAll(list,lacoda, ledist);
    	
    	HBox hbox=new HBox();
    	hbox.getChildren().addAll(button,button2,button3,button4,button7,button5,button6);
    	hbox.setMinHeight(100);
    	hbox.setAlignment(Pos.CENTER);
    	hbox.setSpacing(10);

    	Pane pane=new Pane();
    	vbox.getChildren().addAll(menuBar,hbox,pane,contliste);
                 
        
 		menuItem.setOnAction(new EventHandler<ActionEvent>() {	
 			@Override
 			public void handle(ActionEvent event) {
 				G.removeAll();
 				//System.out.println(G.V().size());
 				applicationRunning.graphFromFile(G);
 				pane.getChildren().remove(0, pane.getChildren().size());
 		        visualGraph.removeAll();
 		        visualGraph.readGraph(G, pane);
 		        for (blackCircle b: visualGraph.circles()) {
 			    	controller.boundsController(b, pane);
 			    	}
 				//System.out.println(G.V().size());
 		                }
 		});
   


		
		Scene scene = new Scene(vbox);
		scene.getStylesheets().add(css);

		
    	stage2.setScene(scene);
    	
    	stage2.setX(Screen.getPrimary().getVisualBounds().getMinX());
    	stage2.setY(Screen.getPrimary().getVisualBounds().getMinY());
    	stage2.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
    	stage2.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
    	
    	vbox.prefWidthProperty().bind(stage2.widthProperty());
    	vbox.prefHeightProperty().bind(stage2.heightProperty());
    	
    	
    	System.out.println(stage2.getHeight());
    	
    	contliste.setMinHeight(50);
    	
    	list.prefWidthProperty().bind(vbox.widthProperty());
    	lacoda.prefWidthProperty().bind(vbox.widthProperty());
    	ledist.prefWidthProperty().bind(vbox.widthProperty());

    	
    	pane.prefWidthProperty().bind(vbox.widthProperty());
    	pane.prefHeightProperty().bind(vbox.heightProperty());
    	
    	stage2.show();
    	
    	//pane.setMinHeight(stage2.getHeight()-contliste.getHeight()-hbox.getHeight()-menuBar.getHeight());
    	
    	visualGraph = new VisualGraph<String>(G, pane);
		controller = new Controller<String>(items, qu, dist, visualGraph);
		BellmanFordMoore<String> bellmanFord=new BellmanFordMoore<>(visualGraph, null);
    	
        controller.addButtonController(button,pane, visualGraph);
        controller.linkButtonController(button4, visualGraph);
        controller.removeButtonController(button2, visualGraph, pane);
        controller.setOnSave(otherMenuItem, G);
        controller.endButtonController(button, button2, button3, button4, button5, button6,button7, bellmanFord);
        controller.clearColorButtonController(button7,visualGraph);
		controller.applyButtonController(visualGraph, button, button2, button3, button4, button5, button6,button7,qu, bellmanFord);
        controller.nextButtonController(button6, bellmanFord);
    	
    	for (blackCircle b: visualGraph.circles()) {
    	b.setPositions();
    	for (Arrow o : b.getOutList()) {
			o.setLines(o.getParent(), o.getTarget());
		}
		for (Arrow o : b.getInList()) {
			o.setLines(o.getParent(), o.getTarget());
		}
    	controller.boundsController(b, pane);
    	}
    }
	
	public static VisualGraph<String> getVisualGraph() {
		return visualGraph;
	}
	
    public void menu() {
    	String css = this.getClass().getResource("../style.css").toExternalForm();
    	stage= new Stage();
    	stage.setTitle("Algraph");
    	GridPane grid=new GridPane();
    	Text text=new Text("Generate graph manually");
    	Text text2=new Text("Generate random graph");
    	Text text3=new Text("Import graph from file");
    	grid.setMinSize(400, 400); 
    	grid.setPadding(new Insets(10, 10, 10, 10)); 
    	grid.setVgap(100); 
        grid.setHgap(0);  
        grid.setAlignment(Pos.CENTER);
        grid.add(text, 1, 0);
        grid.add(text2, 1, 1);
        grid.add(text3, 1, 2);
        
        controller.setOnMouseTextController(text,0,this);
        controller.setOnMouseTextController(text2,1,this);
        controller.setOnMouseTextController(text3,2,this);
        
        Scene scene=new Scene(grid);
        scene.getStylesheets().add(css);
    	stage.setScene(scene);
    	stage.showAndWait();
    }
    
}