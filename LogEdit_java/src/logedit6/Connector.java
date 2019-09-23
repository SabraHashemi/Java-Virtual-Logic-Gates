/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;


import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;

/**
 * Skin
 *
 * @author admin
 */
class Connector extends Group implements InvalidationListener {
    
    Pin source;
    Pin dist;
    // Polyline wire;
    Shape wire;
      ConnectorType ct1;
    
    boolean added;
    
    public Connector()
    {
      
               
    }
    
    @Override
    public void invalidated(Observable o) {
        
      
        getChildren().remove(wire);
      
        if (ct1 == ConnectorType.Obliq) {
           
            wire = new Polyline();
            
            ((Polyline) wire).getPoints().addAll(source.getCenterX() + AnchorPane.getLeftAnchor(source.getParent()), source.getCenterY() + AnchorPane.getTopAnchor(source.getParent()), dist.getCenterX() + AnchorPane.getLeftAnchor(dist.getParent()), dist.getCenterY() + AnchorPane.getTopAnchor(dist.getParent()));
        }
        if (ct1 == ConnectorType.Orthogonal) {
         
            wire = new Polyline();
            ((Polyline) wire).getPoints().addAll(source.getCenterX() + AnchorPane.getLeftAnchor(source.getParent()), source.getCenterY() + AnchorPane.getTopAnchor(source.getParent()), source.getCenterX() + AnchorPane.getLeftAnchor(source.getParent()), dist.getCenterY() + AnchorPane.getTopAnchor(dist.getParent()), dist.getCenterX() + AnchorPane.getLeftAnchor(dist.getParent()), dist.getCenterY() + AnchorPane.getTopAnchor(dist.getParent()));
        }
        if (ct1 == ConnectorType.Curve) {
            wire = new CubicCurve();
            
           
            
            ((CubicCurve) wire).setStartX(source.getCenterX() + AnchorPane.getLeftAnchor(source.getParent()));
            ((CubicCurve) wire).setStartY(source.getCenterY() + AnchorPane.getTopAnchor(source.getParent()));
            ((CubicCurve) wire).setEndX(dist.getCenterX() + AnchorPane.getLeftAnchor(dist.getParent()));
            ((CubicCurve) wire).setEndY(dist.getCenterY() + AnchorPane.getTopAnchor(dist.getParent()));
            
            ((CubicCurve) wire).setControlX1(source.getCenterX() + AnchorPane.getLeftAnchor(source.getParent()) + (source.getCenterX()-dist.getCenterX()) );
            ((CubicCurve) wire).setControlY1(source.getCenterY() + AnchorPane.getTopAnchor(source.getParent()));
            ((CubicCurve) wire).setControlX2(dist.getCenterX() + AnchorPane.getLeftAnchor(dist.getParent()) + (dist.getCenterX()-source.getCenterX()) );
            ((CubicCurve) wire).setControlY2(dist.getCenterY() + AnchorPane.getTopAnchor(dist.getParent()));
            
            wire.setFill(null);
            
        }
        wire.setStroke(new Color(0.0, 0, 0, 1.0));
        wire.setStrokeWidth(4.0);
        getChildren().add(wire);
        toBack();
        toBack();
        toBack();
        source.toFront();
        dist.toFront();
       
       
        dist.state.bindBidirectional(source.state);
         LogicAppController.name.setText(ct1.name());
        
    }
    
    
    public enum ConnectorType {
        
        Obliq, Orthogonal, Curve
    }
    
    public Connector(Pin source, Pin dist,  ConnectorType ct) {
        this.source = source;
        this.dist = dist;
        this.ct1=ct;
         this.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
               System.out.println("OnMouseClick"+t.getX());
               LogicAppController.cb3.setDisable(false);
             
              
            }
        });
          LogicAppController.cb3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                
                 LogicAppController.cb2.setSelected(false);
                 LogicAppController.cb1.setSelected(false);
              ct1=ConnectorType.Obliq;
               invalidated(null);
            }
        });
         
           LogicAppController.cb2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                
                 LogicAppController.cb3.setSelected(false);
                 LogicAppController.cb1.setSelected(false);
              ct1=ConnectorType.Orthogonal;
               invalidated(null);
            }
        });
          LogicAppController.cb1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                
                 LogicAppController.cb3.setSelected(false);
                 LogicAppController.cb2.setSelected(false);
              ct1=ConnectorType.Curve;
               invalidated(null);
                t.consume();
            }
        });
         
       invalidated(null);
     
        ((Group) (source.getParent())).getProperties().addListener(this);
        ((Group) (dist.getParent())).getProperties().addListener(this);
        
        
    }
    
    
}
