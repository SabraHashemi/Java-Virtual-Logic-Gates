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
public class PartsItems {
    
  static  TreeItem<Object> allParts;
    public static TreeItem<Object> getParts(){
        
        allParts = new TreeItem<>((Object)"All Parts");
        TreeItem<Object> section = new TreeItem<>((Object)"LogicGates");
        allParts.getChildren().add(section); 
        Gate andGate = new AndGate(false);
        //andGate.getTransforms().add(new Scale(0.3, 0.3));
        section.getChildren().add( new TreeItem<>((Object)andGate));
        
        Gate bufferGate = new BufferGate(false);
        section.getChildren().add(new TreeItem<>((Object)bufferGate));
        Gate orGate = new OrGate(false);
       // orGate.getTransforms().add(new Scale(0.3, 0.3));
        section.getChildren().add( new TreeItem<>((Object)orGate));

        Gate norGate = new NorGate(false);
       // norGate.getTransforms().add(new Scale(0.3, 0.3));
        section.getChildren().add( new TreeItem<>((Object)norGate));
        
        Gate notGate = new NotGate(false);
         section.getChildren().add( new TreeItem<>((Object)notGate));
        Gate xnorGate = new XNorGate(false);
       // xnorGate.getTransforms().add(new Scale(0.3, 0.3));
        // TreeItem t;
       Gate timerGate = new TimerGate(false);
       section.getChildren().add( new TreeItem<>((Object)timerGate));
         
        section.getChildren().add( new TreeItem<>((Object)xnorGate));
         Gate nandGate = new NandGate(false);
         section.getChildren().add( new TreeItem<>((Object)nandGate));
         
           Gate xorGate = new XorGate(false);
        //xorGate.getTransforms().add(new Scale(0.3, 0.3));
        section.getChildren().add( new TreeItem<>((Object)xorGate));
        
        section = new TreeItem<>((Object)"Outputs");
        allParts.getChildren().add(section); 
        Gate sevenSegment = new SevenSegment(false);
        section.getChildren().add( new TreeItem<>((Object)sevenSegment));        
        Gate led = new LedDiod(false);
        section.getChildren().add( new TreeItem<>((Object)led));
        
        section = new TreeItem<>((Object)"Inputs");
        allParts.getChildren().add(section); 
        Gate switchPart = new Switch(false);
        section.getChildren().add( new TreeItem<>((Object)switchPart));        
 
        return allParts;
        
    }
            
}
