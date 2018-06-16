package algraphPackage;

import javafx.event.ActionEvent;
import java.io.File;

import graphPackage.Graph;
import graphPackage.VisualGraph;
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

public class applicationRunning{
	
	private Stage stage,stage2;
	private Integer start;
	private Controller controller;
	private VisualGraph<String> visualGraph;
	private ListView<String> list;
	private ObservableList<String> items;
	//variable for the graph
	
	public applicationRunning() {
		Graph<String> G=new Graph<String>();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new java.io.File("."));
		list = new ListView<String>();
		items = FXCollections.observableArrayList ();
		list.setItems(items);
		controller=new Controller(items);
		menu();
		if (start==1) {
			G.randomGraph(10, 5, 20, 10, false);
			G.print();
		} //random graph
		else if (start==2) {
			graphFromFile(G);
		} //graph from file
		mainPage(G);
	}
	
	public static void graphFromFile(Graph<String> G) {
 		FileChooser fileChooser = new FileChooser();
 		fileChooser.setInitialDirectory(new java.io.File("."));
 		Stage fc=new Stage();
 		fc.setTitle("Input graph...");
 		File file = fileChooser.showOpenDialog(fc);
         if (file != null) {
             G.inGraph(file.getAbsolutePath());
         }
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
    	final Menu menu2 = new Menu("Options");
    	final Menu menu3 = new Menu("Help");
    	final MenuItem menuItem = new MenuItem("Open");
    	final MenuItem otherMenuItem = new MenuItem("Save");
    	
    	((Styleable)menuItem).getStyleClass().add(css);
    	
    	menu1.getItems().add(menuItem);
    	menu1.getItems().add(otherMenuItem);
    	MenuBar menuBar = new MenuBar();
    	menuBar.getMenus().addAll(menu1, menu2, menu3);
    	Button button=new Button("Add node");
    	Button button2=new Button("Remove node");
    	Button button3=new Button("Apply algorithm");
    	Button button4=new Button("Go to Selection mode");
    	button.getStylesheets().add(css);
    	button2.getStylesheets().add(css);
    	button3.getStylesheets().add(css);
    	button4.getStylesheets().add(css);
    	/*button.setPadding(new Insets(10,10,10,10));
    	button2.setPadding(new Insets(10,10,10,10));
    	button3.setPadding(new Insets(10,10,10,10));
    	button4.setPadding(new Insets(10,10,10,10));
    	button.setMinWidth(130);
    	button2.setMinWidth(130);
    	button3.setMinWidth(130);
    	button4.setMinWidth(130);*/
    	VBox vbox=new VBox();
    	
    	HBox hbox=new HBox();
    	hbox.getChildren().addAll(button,button2,button3,button4);
    	hbox.setMinHeight(100);
    	//hbox.setMinWidth(100);
    	hbox.setAlignment(Pos.CENTER);
    	hbox.setSpacing(10);

    	Pane pane=new Pane();
    	pane.setMinHeight(100);
    	pane.setMinWidth(100);
    	pane.prefWidthProperty().bind(vbox.widthProperty());
    	pane.prefHeightProperty().bind(vbox.heightProperty());
    

		visualGraph = new VisualGraph<String>(G, pane);
    	
        controller.addButtonController(button,pane, visualGraph);
        controller.linkButtonController(button4, visualGraph);
        controller.removeButtonController(button2, visualGraph, pane);
        
        controller.setOnSave(otherMenuItem, G);
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
 			
 			@Override
 			public void handle(ActionEvent event) {
 				G.removeAll();
 				System.out.println(G.V().size());
 				applicationRunning.graphFromFile(G);
 				pane.getChildren().remove(0, pane.getChildren().size());
 		        visualGraph.removeAll();
 		        visualGraph.readGraph(G, pane);
 		        for (blackCircle b: visualGraph.circles()) {
 			    	controller.boundsController(b, pane);
 			    	}
 				System.out.println(G.V().size());
 		                }
 		});
    	
        /*ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList ();
        list.getStylesheets().add(css);
        list.setItems(items);
        items.add("ilmiopene");*/
    	
    	vbox.getChildren().addAll(menuBar,hbox,pane,list);
    	

		
		Scene scene = new Scene(vbox);
		scene.getStylesheets().add(css);

    	stage2.setScene(scene);
    	stage2.show();
    	for (blackCircle b: visualGraph.circles()) {
    	controller.boundsController(b, pane);
    	}
    }
	
    public void menu() {
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
    	stage.setScene(scene);
    	stage.showAndWait();
    }
    
}