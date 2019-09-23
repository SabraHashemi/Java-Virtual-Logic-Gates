/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;

import javafx.scene.control.TreeItem;
import javafx.scene.transform.Scale;

/**
 *
 * @author admin
 */
public class PartsItems2 {
    public static TreeItem<Object> getParts2(){
        
       
       TreeItem<Object> section2 = new TreeItem<>();
       for(Gate gate : FullApp.gates)
       {
      
        
     
       // orGate.getTransforms().add(new Scale(0.3, 0.3));
        section2.getChildren().add( new TreeItem<>((Object)gate));
      
       }
       
        return section2;
        
    }
            
}
