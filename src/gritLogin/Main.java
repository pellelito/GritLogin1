package gritLogin;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		TilePane root = new TilePane();
		Scene scene = new Scene(root, 400, 820);
		Button login = new Button("Login");
		Button cancel = new Button("Cancel");
		Button register = new Button("Register");
		TextField user = new TextField("username");
		PasswordField passw = new PasswordField();
		Label label = new Label("Login");
		
		try {
			//create form___________________________________________
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			HBox hbox = new HBox(10); 
			VBox vbox = new VBox(5);
			vbox.getChildren().addAll(user,passw);
			hbox.getChildren().addAll(register, login, cancel);
			
			label.setAlignment(Pos.BOTTOM_LEFT);
			vbox.setAlignment(Pos.BOTTOM_RIGHT);
			hbox.setAlignment(Pos.BOTTOM_CENTER);
			root.getChildren().add(label);
			root.getChildren().addAll(vbox,hbox);
			
			root.setAlignment(Pos.BOTTOM_CENTER);
			primaryStage.setScene(scene);
			primaryStage.show();
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		//end creation of form_________________________________________________
		
		//check if mail addresses is a valid one
		user.focusedProperty().addListener((ov, oldV, newV) -> {
		      if (!newV) { // focus lost
		              
		              if(!Validate.valEmail(user.getText())) label.setText("Not a valid email adress");
		           }
		        });
		
		//check if password is a valid one
		passw.focusedProperty().addListener((ov, oldV, newV) -> {
		      if (!newV) { // focus lost
		          
		    	  if(!Validate.valPassw(passw.getText())) label.setText("Not a valid password, please \nuse least 2 upper case, 2 lower \n2case,  numbers, 1 special \nchar and NO spaces");
		           }
		        });
		//press register button
		register.setOnAction(new EventHandler<ActionEvent>()
	    {
	        public void handle(ActionEvent e)
	        {
				try {
					//checks input
					if(Validate.valEmail(user.getText()) && Validate.valPassw(passw.getText())) {
						
						//adds new user
						Validate.addUser(user.getText(), passw.getText());
						label.setText("User created, please login.");			
					}else {
						//What if something goes wrong
						label.setText("Something went wrong \nplease try again");
					}
				} catch (IOException e1) {
					
					e1.printStackTrace();
				};
				//let user fill out credentials again, haha
				user.setText("");
				passw.setText("");
	        }
	    });     
		//press login button
		login.setOnAction(new EventHandler<ActionEvent>()
	    {
	        public void handle(ActionEvent e)
	        {
				try {
					if(Validate.checkLogin(user.getText(), passw.getText())) {
			
						new SubClass(primaryStage);
						
					}else{
						//Yeah I know, but what if something goes wrong
						label.setText("Something went wrong, \nyou may try again");
					}
				} catch (IOException e1) {
				
					e1.printStackTrace();
				};
				user.setText("");
				passw.setText("");
	        }
	    });     
		
		//press cancel button
		cancel.setOnAction(e -> {
	
			user.setText("");
			passw.setText("");
			label.setText("Login");
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}

