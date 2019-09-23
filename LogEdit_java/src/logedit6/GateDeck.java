/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public  class GateDeck extends AnchorPane {

    Gate g;
  static GateDeck gd;

 
    public GateDeck() {
       gd= this;
        setPrefSize(700, 700);
     


      
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent t) {
                  
           
                      
                
                
                     LogicAppController.paste.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t1) {
//                        t.equals(t1);
                        
                        System.out.println("paste");
                       Paste paste = new Paste();
                        try {                       
                            paste.paste(t);
                        } catch (InstantiationException ex) {
                            Logger.getLogger(GateDeck.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalAccessException ex) {
                            Logger.getLogger(GateDeck.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            t1.consume();
                            t.consume();
                       
                    }

           
                   
                }); 
                     if (t.getSource() != GateDeck.this) {
                    t.consume();
                    return;
                }
                     else{
                     addChild(t.getX(), t.getY());
                    t.consume();
                     }
             
            }
        });
       

            
          
            
     
                 
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                t.consume();

            }
        });
    }
    
    public void addChild(double x , double y)
    {
       
                Gate selGate = (Gate) (FullApp.getRepository().get(RepositoryItem.SelectedGate));
                
                if( selGate==null)return ;
               
                try {
                    
                    Class cg = (selGate.getClass());
                    g = (Gate) cg.newInstance();
                    g.enabled=true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (g != null) {
                    g.x.set(x);
                    g.y.set(y);
                    AnchorPane.setLeftAnchor(g, x);
                    AnchorPane.setTopAnchor(g, y);
                      g.textName= g.toString();
                        g.setName();
                 
                    getChildren().add(g);
                    if(g.toString().equals("Timer Gate"))
                    {
                        
                       Thread t1= new Thread((TimerGate)g);
                       g.enabled=true;
                       t1.start();
                    }
                    
                    Gate g1 = null ;
                try {
                    
                    Class cg1 = (g.getClass());
                    g1 = (Gate) cg1.newInstance();
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                    FullApp.gates.add(g1);
                    
                    
                    
                    
                    ///////////////////////tree view
                    
                    LogicAppController.treev.setShowRoot(true);
                      
                    FullApp.gates.addListener(new ListChangeListener<Gate>() {

                        @Override
                        public void onChanged(Change<? extends Gate> change) {
                            LogicAppController.treev.setRoot(PartsItems2.getParts2());
                        }
                    });
                    LogicAppController.treev.setRoot(PartsItems2.getParts2());
               LogicAppController. treev.getRoot().setExpanded(true);
                LogicAppController.treev.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                LogicAppController.treev.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Gate>>() {

                        @Override
                        public void changed(ObservableValue<? extends TreeItem<Gate>> ov, TreeItem<Gate> t, TreeItem<Gate> t1) {
                            System.out.println("selected");
                            t1.getValue().enabled=false;
                             LogicAppController.name.setText(t1.getValue().toString());
                             LogicAppController.dragX.setText(t1.getValue().dragX+ "");
                             LogicAppController.dragY.setText(t1.getValue().dragY+ "");
                        }
                    });
                 
                   
                    
                }


    }
}
