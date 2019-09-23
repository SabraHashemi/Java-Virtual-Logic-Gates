/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;


import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import javax.xml.bind.JAXBContext;

/**
 *
 * @author admin
 */
public class FullApp extends Application {
//public JAXBContext jaxbCtx;
   public   static  GatesGroup gatesg ;

   
 public   static HashMap<RepositoryItem, Object> repository;
public    static ObservableList<Gate> sgates;
    
 public   static ObservableList<Gate> gates;
 public   static ObservableList<Connector> connectors;
 public   static Parent root;
public   static int i = 0;
    Close c11;

    public static HashMap<RepositoryItem, Object> getRepository() {
        return repository;
    }
   

    static {
        repository = new HashMap<>();
         connectors= FXCollections.observableArrayList();
       sgates =FXCollections.observableArrayList();
        
       gates = FXCollections.observableArrayList();
    gatesg = new GatesGroup();
      
    }

    @Override
    public void start(final Stage stage) throws Exception {

        root = FXMLLoader.load(getClass().getResource("fullapp.fxml"));
    
     

       


        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        /////////////////////////////////////////////////////////////////////////////////////////////////
       Close close = new Close();
       repository.put(RepositoryItem.close, close);
         try {
                   
                    Class c= (FullApp.repository.get(RepositoryItem.close)).getClass();
                   c11= (Close) c.newInstance();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
       
       LogicAppController.close.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                c11.close(stage);
              
            }
        });
       
          LogicAppController.close1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                c11.close(stage);
              
            }
        });
             LogicAppController.close2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                c11.close(stage);
              
            }
        });
       
        
    }
    
   

    public static void main(String[] args) {
        launch(args);
    }
}
