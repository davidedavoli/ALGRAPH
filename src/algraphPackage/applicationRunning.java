package algraphPackage;

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
import javafx.collections.*;

public class applicationRunning{
	
	private Stage stage,stage2;
	private Integer start;
	private Controller controller;
	//variable for the graph
	
	public applicationRunning() {
		controller=new Controller();
		menu();
		if (start==1) {} //random graph
		else if (start==2) {} //graph from file
		mainPage();
	}
	
	public void setStart(Integer a) {
		start=a;
	}
	
	public Stage getMenuStage() {
		return stage;
	}
	
	public void mainPage()
    {
		String css = this.getClass().getResource("../style.css").toExternalForm();
		stage2 = new Stage();
    	stage2.setMaximized(true);
    	stage2.setTitle("Algraph");
    	final Menu menu1 = new Menu("File");
    	final Menu menu2 = new Menu("Options");
    	final Menu menu3 = new Menu("Help");
    	final MenuItem menuItem = new MenuItem("Open");
    	
    	menuItem.getStyleClass().add(css);
    	
    	menu1.getItems().add(menuItem);
    	MenuBar menuBar = new MenuBar();
    	menuBar.getMenus().addAll(menu1, menu2, menu3);
    	Button button=new Button("Add node");
    	Button button2=new Button("Remove node");
    	Button button3=new Button("Apply algorithm");
    	Button button4=new Button("Move arrows");
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
    	
        controller.addButtonController(button,pane);
        controller.manageButtonController(button4);
        controller.removeButtonController(button2);
    	
        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (
            "Single", "Double", "Suite", "Family App","culo","tette","altroculo");
        list.setItems(items);
        list.getStylesheets().add(css);
        items.add("ilmiopene");
    	
    	vbox.getChildren().addAll(menuBar,hbox,pane,list);
    	
    	Scene scene=new Scene(vbox);
    	scene.getStylesheets().add(css);
    	stage2.setScene(scene);
    	stage2.show();
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