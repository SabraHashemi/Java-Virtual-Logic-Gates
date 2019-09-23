/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;

import java.util.HashMap;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class LogEdit6 extends Application {

   HashMap<RepositoryItem, Object> repository;

    TreeView initPartsTree() {
      TreeView tv = new TreeView(PartsItems.getParts());
        tv.setPrefWidth(400);
        tv.setPrefHeight(700);
        tv.setShowRoot(false);
        tv.getRoot().setExpanded(true);


        tv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Gate>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Gate>> ov, TreeItem<Gate> oldItem, TreeItem<Gate> newItem) {
                repository.put(RepositoryItem.SelectedGate, newItem.getValue());
                Gate selGate = (Gate) (repository.get(RepositoryItem.SelectedGate));
             }
        });
        for (int i = 0; i < tv.getRoot().getChildren().size(); i++) {
            ((TreeItem) (tv.getRoot().getChildren().get(i))).setExpanded(true);
        }
        tv.setStyle("-fx-font-size:40");
        tv.getTransforms().add(new Scale(0.4, 0.4));
        return tv;
    }

    @Override
    public void start(Stage primaryStage) {


        repository = new HashMap<>();


        final AnchorPane gateDock = new AnchorPane();
        gateDock.setPrefSize(300, 300);

        HBox bb = new HBox();
        AnchorPane doc1 = new AnchorPane();
        doc1.setPrefSize(150, 800);
        doc1.getChildren().add(RectangleBuilder.create().fill(new Color(0.6, 0.5, 0.4, 0.9)).width(150).height(800).build());
        doc1.getChildren().add(initPartsTree());

        AnchorPane doc2 = new AnchorPane();
        doc2.setPrefSize(150, 800);
        doc2.getChildren().add(RectangleBuilder.create().fill(new Color(0.6, 0.5, 0.4, 0.9)).width(150).height(800).build());

        ScrollPane sp = new ScrollPane();
        gateDock.setPrefSize(900, 800);
        sp.setPrefSize(900, 800);
        sp.setContent(gateDock);
        bb.getChildren().addAll(doc1, sp, doc2);
        bb.setFillHeight(true);
        
        Scene scene = new Scene(bb, 1200, 800);


        //        gateDock.getTransforms().add(new Scale(0.5,0.5));


        gateDock.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if (t.getSource() != gateDock) {
                    t.consume();
                    return;
                }
                Gate selGate = (Gate) (repository.get(RepositoryItem.SelectedGate));
                Gate g=null;
                try {
                    Class cg= (selGate.getClass());
                    System.out.println(g=(Gate)cg.newInstance( ));
                    
                } catch (Exception ex) {
                   ex.printStackTrace(); 
                }
                if(g!=null){
                AnchorPane.setLeftAnchor(g, t.getX());
                AnchorPane.setTopAnchor(g, t.getY());
                gateDock.getChildren().add(g);
                }




                t.consume();

            }
        });

        // gateDock.setEffect(new DropShadow(1.0, Color.AQUA)) ;
        // gateDock.getTransforms().add(new Scale(0.2,0.2));
        gateDock.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                t.consume();

            }
        });



        primaryStage.setTitle("Gate Dock");
        primaryStage.setScene(scene);
        primaryStage.setHeight(600);
        primaryStage.setWidth(900);

        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
