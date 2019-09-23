/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;



import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javax.jws.WebMethod;
import javax.jws.WebParam;
//import javax.jws.WebService;

/**
 *
 * @author admin
 */
//@WebService(serviceName = "AndGate")
class AndGate extends Gate {

  
     AndGate thisg = this;
   
  
    
    
    AndGate() {
        
    }

    AndGate(boolean enabled) {
        super(enabled);
    }
    
// @WebMethod(operationName = "makeSkin")
    @Override
    void makeSkin() {
      
       svggate = new SVGPath();
        iconName = "and.gif";
        
        svggate.setContent("m 74.9089,2.05279 0,81.75 43.1562,0 c 27.2808,0 37.966,-23.1852 39.2813,-38.65625 C 158.6858,29.39049 142.0974,1.7963 118.0651,2.05279 z m -60.1563,5.625 c -7.4603,0 -13.5,6.03967 -13.5,13.5 0,7.46033 6.0397,13.53125 13.5,13.53125 7.4603,0 13.5,-6.07092 13.5,-13.53125 0,-7.46033 -6.0397,-13.5 -13.5,-13.5 z m 14.0334,12.96875 45.5795,0 z m 189.9979,8.59375 c -7.4604,0 -13.5,6.07092 -13.5,13.53125 0,7.46033 6.0396,13.5 13.5,13.5 7.4603,0 13.5,-6.03967 13.5,-13.5 0,-7.46033 -6.0397,-13.53125 -13.5,-13.53125 z m -60.6832,13.0625 46.2705,0 z m -142.8168,9.8125 c -7.4604,0 -13.5,6.03967 -13.5,13.5 0,7.46033 6.0396,13.53125 13.5,13.53125 7.4603,0 13.5,-6.07092 13.5,-13.53125 0,-7.46033 -6.0397,-13.5 -13.5,-13.5 z m 14,13.42934 44.9584,0 z");
        svggate.setFill(new Color(1.0, 1.0, 1.0, 1.0));

       svggate.setStrokeWidth(4);
        svggate.setStroke(new Color(0.0, 0, 0, 1.0));

       
       Pin  inPin1 = new Pin(14.2, 21.5, 12.0, new Color(1.0, 0.0, 1.0, 1.0), "inPin1");


       Pin  inPin2 = new Pin(14.2, 66, 12, new Color(1.0, 0.0, 1.0, 1.0),"inPin2" );

       Pin  outPin = new Pin(219, 42, 12, new Color(1.0, 1.0, 0.0, 1.0),"outPin");
         
        getChildren().add(svggate);
        getChildren().add(inPin1);
        getChildren().add(inPin2);
        getChildren().add(outPin);
        inPins.add(inPin1);
        inPins.add(inPin2);
        outPins.add(outPin);
        
       

    }

//  @WebMethod(operationName = "addBehavior")
    @Override
    void addBehavior(){
    
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
          
         
                dragX=t.getX() ;
                dragY=t.getY();
               
                
                t.consume();
                

            }
        });
        
        
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
          public void handle(MouseEvent t) {
                if(selected==true)
                {
                     
               dragAll(t);
                }
           else
                {
                    drag(t, thisg);
                }
               
        }
        });

           
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
               if( t.getClickCount()==2)
               {
                   if(selected)
                   {
                     
                       svggate. setFill(new Color(1, 1, 1, 1.0));
                       selected=false;
                       removeSelection(thisg); 
                       
                   }
                 else  if(!selected)
                   {
                
//                name.setStyle(" -fx-effect: dropshadow( three-pass-box , rgba(0.5,0.5,0.6,0.6) , 10, 0.0 , 0 , 1 );");
                svggate. setFill(new Color(0.5, 0.4, 0.6, 1.0));
                selected=true;
                LogicAppController.name.setText(thisg.toString());
                 LogicAppController.dragX.setText(thisg.x.doubleValue()+ "");
                  LogicAppController.dragY.setText(thisg.y.doubleValue()+ "");
                 
                FullApp.repository.put(RepositoryItem.sGate, thisg);
                selection(thisg);
                
                   }
                 
               
                  
               
               }
                t.consume();
                
                

            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                t.consume();

            }
        });
        
        
        

    }
  

// @WebMethod(operationName = "setStyle")
    @Override
    void setStyle() {
    }
// @WebMethod(operationName = "logic")
    @Override
    void logic() {
    
        outPins.get(0).state.set((inPins.get(0).state.getValue() & inPins.get(1).state.getValue()));
        
        System.out.println("And"+outPins.get(0).state.get());
    }
// @WebMethod(operationName = "toString")
    public String toString() {
        return "AND Gate";

    }
 
   

}
