/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import logedit6.Connector.ConnectorType;


/**
 * FXML Controller class
 *
 * @author admin
 */
public class LogicAppController implements Initializable {
 
    @FXML
   static ScrollPane partsHost;
    @FXML
    Slider zoomSldr;
    @FXML
    ScrollPane dockHost;
    
    @FXML
    static CheckBox cb1;
    
    @FXML
     static CheckBox cb2;
    
    @FXML 
        static CheckBox cb3;
    
     TreeView tv ;
    @FXML 
 static   Button close;
    
    @FXML
 static   MenuItem close2;
    
    @FXML
static   MenuItem del1;
    
    @FXML
static   MenuItem delete;
    
    @FXML
 static   MenuItem paste;
    
    @FXML
  static  TreeView treev;
    @FXML
static    Button del;
    
    @FXML
 static   Label name;
    
     @FXML
  static  Label dragX;
     
      @FXML
  static  Label dragY;
    
    @FXML 
    static TitledPane x1;
    
    @FXML
    Button ungroup;
   
    @FXML
    AnchorPane Content;
    
     Delete d1 ;
     Delete d11;
    
     @FXML
     Button add;
   static  int in;
   static  int out;
   static   String name2;
  
   @FXML 
  static  MenuItem close1;
   
    GateDeck gd;
    Transform lastTransform;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      cb1.disableProperty().bind(cb2.disableProperty());
      cb2.disableProperty().bind(cb3.disableProperty());
      ungroup.pressedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
               del1.setDisable(true);
               del.setDisable(true);
               delete.setDisable(true);
                      
            }
        });
        ungroup.setOnAction(new EventHandler<ActionEvent>() {

       
           
            @Override
            public void handle(ActionEvent t) {
                
                
                
      
            System.out.println("ungroup");
                 GatesGroup gg =  FullApp.gatesg;
              
       
          
                
      for(Node reg: gg.getChildren())
     {
        
        gg.getChildren().get(gg.getChildren().indexOf(reg)).setVisible(false);
        
     }
     
 
     
      
     for(int i= gg.getChildren().size()-1; i>=0;i--)
      {
         if(!(gg.getChildren().get(i) instanceof Connector))
         {
              Gate g = (Gate)gg.getChildren().get(i);
               AnchorPane.setLeftAnchor(g,gg.getLayoutX()+g.x.doubleValue());
               AnchorPane.setTopAnchor(g,gg.getLayoutY()+g.y.doubleValue());
               g.setVisible(true);
               g.selected=false;
               if(g instanceof ComplexGate)
               {
                   ComplexGate cg = (ComplexGate)g;
                   cg.rect . setFill(Color.PINK);
               }
               g.svggate.setFill(Color.WHITE);
               
         
             GateDeck. gd.getChildren().add(g);
          
         }
         else
         {
             Connector ct = (Connector)gg.getChildren().get(i);
             ct.setVisible(true);
             GateDeck.gd.getChildren().add(ct);
         }
         
      }
  
    
                     

      FullApp.sgates  = FXCollections.observableArrayList();
      GatesGroup gatesg1 = new GatesGroup();
      FullApp.gatesg=gatesg1;
      FullApp.i=0;
            }
        });
       
       
        d1 = new Delete();
        FullApp.repository.put(RepositoryItem.todel,d1 );
        try {
                    
                    Class d = (FullApp.repository.get(RepositoryItem.todel)).getClass();
                   d11= (Delete) d.newInstance();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
    
        
        del1.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent t) {
              d11.delete();
              t.consume();
            }
        });
  
     del.setOnMouseClicked(new EventHandler<MouseEvent>()
       {

            @Override
            public void handle(MouseEvent t) {
                
               
                d11.delete();
                t.consume();
            
                 }
           
       });
     
     delete.setOnAction(new EventHandler<ActionEvent>()
     {

            @Override
            public void handle(ActionEvent t) {
              
                  
                d11.delete();
                
              t.consume();
                
            }
         
     });
    
    
      
     add.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
            
                 FullApp.getRepository().put(RepositoryItem.gatesGroup,FullApp.gatesg);
                 String inPin= JOptionPane.showInputDialog(" How Many inPins does your ComplexGate have ? ");
                 in = Integer.parseInt(inPin);
                 String outPin = JOptionPane.showInputDialog(" How Many outPins does your ComplexGate have ? ");
                 out = Integer.parseInt(outPin);
       
                name2 = JOptionPane.showInputDialog(" What is the ComplexGate name? ");
                     
                      ComplexGate cg = new ComplexGate(false);
                      PartsItems.allParts.getChildren().add( new TreeItem<>((Object)cg));
                      tv.setRoot(PartsItems.allParts);
                      FullApp.getRepository().put(RepositoryItem.SelectedGate,cg);
        
              /*     JAXBContext jaxbCtx = JAXBContext.newInstance("newpackage");
                      
                        try {
                 Marshaller marshaller = jaxbCtx.createMarshaller();
               
                 marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
                 marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
               //  marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION,"shapes.xsd");
                 
                  marshaller.marshal(cg, new FileOutputStream("../modified.xml"));
                 
             } catch (FileNotFoundException ex) {
                 ex.printStackTrace();
             } catch (javax.xml.bind.JAXBException ex) {
                 ex.printStackTrace();
             }
       */
                    
                       gd.addChild(cg.gg.fX,cg.gg.fY);
                       d11.delete();
                       gd.getChildren().remove(cg.gg);
                       FullApp.sgates  = FXCollections.observableArrayList();
                       GatesGroup gatesg1 = new GatesGroup();
                       FullApp.gatesg=gatesg1;
                       FullApp.i=0;
           
            }
        });
          
           

           
       
       
        partsHost.setContent(getPartsTree());
        
    
        gd=new GateDeck();
        dockHost.setContent(gd);
       
        gd.getTransforms().add(lastTransform=new Scale(0.5, 0.5));
        
        zoomSldr.valueProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldV, Number newV) {
                         gd.getTransforms().remove( lastTransform);
                         System.out.println("t->"+oldV.doubleValue());
                         System.out.println("t1->"+newV.doubleValue());
                        gd.getTransforms().add(lastTransform=new Scale(newV.doubleValue()/100, newV.doubleValue()/100));
                      
                       
                       
             }
        });
       
          }

    
    
    
    
    TreeView getPartsTree() {
        tv = new TreeView(PartsItems.getParts());
        tv.setPrefWidth(600);
        tv.setPrefHeight(1800);
        tv.setShowRoot(false);
        tv.getRoot().setExpanded(true);
    

        tv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Gate>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<Gate>> ov, TreeItem<Gate> oldItem, TreeItem<Gate> newItem) {
                
                if(newItem!=null&&newItem.getValue() instanceof Gate)
                {
                    
                    FullApp.getRepository().put(RepositoryItem.SelectedGate, newItem.getValue());
                  
        
                }
               
              }
        });
        for (int i = 0; i < tv.getRoot().getChildren().size(); i++) {
            ((TreeItem) (tv.getRoot().getChildren().get(i))).setExpanded(true);
        }
        tv.setStyle("-fx-font-size:40");
        tv.getTransforms().add(new Scale(0.4, 0.4));
        treev.setStyle("-fx-font-size:40");
        treev.getTransforms().add(new Scale(0.6, 0.6));
        treev.setPrefSize(400, 800);
        treev.prefWidthProperty().bindBidirectional(dockHost.prefWidthProperty());
        tv.prefWidthProperty().bindBidirectional(dockHost.prefWidthProperty());
        return tv;
    }
    
    public void createGate()
    {
        Pin  sou = new Pin(14.2, 21.5, 12.0, new Color(1.0, 0.0, 1.0, 1.0), "inPin1");
        AndGate gate = new AndGate(false);
        
           AnchorPane.setLeftAnchor(gate,50.0);
           AnchorPane.setTopAnchor(gate,100.0);
           GateDeck.gd.getChildren().add(gate);
           GateDeck.gd.getChildren().add(sou);
           
            Shape wire  = new Polyline();
            
            ((Polyline) wire).getPoints().addAll(14.2 +14.2,21.5 +21.5, gate.inPins.get(0).getCenterX() + AnchorPane.getLeftAnchor(gate.inPins.get(0).getParent()),gate.inPins.get(0).getCenterY() + AnchorPane.getTopAnchor(gate.inPins.get(0).getParent()));
         wire.setStroke(new Color(0.0, 0, 0, 1.0));
        wire.setStrokeWidth(4.0);
       GateDeck.gd. getChildren().add(wire);
           
        
    }
    
   
}
