package gritLogin;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SubClass
{

StackPane root = new StackPane();
Stage stage;

public SubClass(Stage stage)
{	
	Text text = new Text("Yeah, I'm a programmer bitch!");
	
	//setting the position and style of the text  
	text.setStyle("-fx-font-size: 40;");
    text.setX(50); 
    text.setY(130);     
    text.setFill(Color.BROWN);   
    text.setStrokeWidth(2); 
    text.setStroke(Color.GOLD);
    
    //create new scene
    Scene scene=new Scene(root,600,600);
    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    
    //add elements
    root.getChildren().add(text);
    root.setAlignment(Pos.TOP_CENTER);
    
    //fades text
    FadeTransition fade = new FadeTransition(); 
	fade.setDuration(Duration.millis(3000));
	fade.setFromValue(10);  
    fade.setToValue(0); 
    fade.setNode(text);
    fade.play();  
    
    //show new scene
    stage.setScene(scene);
    stage.show();
}
}