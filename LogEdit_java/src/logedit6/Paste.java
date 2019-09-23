/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Lotus
 */
public class Paste   {
    
    public Paste()
    {
        
    }
    public void paste( MouseEvent t ) throws InstantiationException, IllegalAccessException
    {
        for(Node node :FullApp.gatesg.getChildren())
        {
            
            Gate g = (Gate)node;
            Class nc;
            Gate g1;
            nc= node.getClass();
            g1 =(Gate)nc.newInstance();
             g1.x=g.x;
                         g1.y=g.y;
                         g1.svggate.setFill(new Color(1,1,1,1));
                          
                          AnchorPane.setLeftAnchor(g1,g1.x.doubleValue()-FullApp.gatesg.fX+t.getX()+FullApp.gatesg.getLayoutX());
                          AnchorPane.setTopAnchor(g1,g1.y.doubleValue()-FullApp.gatesg.fY+t.getY()+FullApp.gatesg.getLayoutY());
                          GateDeck.gd.getChildren().add(g1);
            
        }
        
     
    }
    
   
}
