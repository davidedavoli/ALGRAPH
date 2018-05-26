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
import javafx.scene.text.Font;
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


public class Algraph extends Application {
    
	public static void main(String[] args) {
        launch(args);
    }
    
	
    @Override
    public void start(Stage primaryStage) {
    	applicationRunning a = new applicationRunning();
    }
}