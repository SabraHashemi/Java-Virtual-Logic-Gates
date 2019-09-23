/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;



import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author admin
 */
class TimerGate extends Gate implements Runnable {

 Circle timerGate;
     TimerGate thisg = this;
    static BooleanProperty value ;
  
    
    
    TimerGate() {
        
    }

    TimerGate(boolean enabled) {
        super(enabled);
        value = new SimpleBooleanProperty();
    }

    @Override
    void makeSkin() {
        
        iconName = "Timer.gif";

    timerGate = new Circle(140,42,60);
    timerGate.setFill(new Color(0.5,0.4,0.6,1));
    
       Pin  outPin = new Pin(217, 42, 12, new Color(1.0, 1.0, 0.0, 1.0),"outPin");
         
        getChildren().add(timerGate);
     
        getChildren().add(outPin);
  
        outPins.add(outPin);
        
       

    }

 
    @Override
    void addBehavior() {
    
         value.addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
               if(t1==true)
               {
                   outPins.get(0).setFill(  new Color(0.1,0.3,0.7,1.0)  );
               }
               else if(t1==false)
               {
                 outPins.get(0).setFill( new Color(1.0,0.16,0.16,1.0));
               }
            }
        });
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
                     
                       timerGate. setFill(new Color(1,0,0.4,1));
                       selected=false;
                       removeSelection(thisg); 
                       
                   }
                 else  if(!selected)
                   {
                name.setStyle(" -fx-effect: dropshadow( three-pass-box , rgba(0.5,0.5,0.6,0.6) , 10, 0.0 , 0 , 1 );");
                timerGate. setFill(new Color(0.5, 0.5, 0.6, 1.0));
                selected=true;
                LogicAppController.name.setText(thisg.toString());
                 LogicAppController.dragX.setText(thisg.dragX+ "");
                  LogicAppController.dragY.setText(thisg.dragY+ "");
                  
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

    @Override
    void setStyle() {
    }

    @Override
    void logic() {
       this.run();
    }

    public String toString() {
        return "Timer Gate";

    }
    
    
  

    @Override
    public void run() {
       
        while(true)
        {
            if(enabled)
            {
               System.out.println(value);
                value.setValue(!value.getValue()) ;
              
                 try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException ex) {
            }
            }
        }
    }
}
