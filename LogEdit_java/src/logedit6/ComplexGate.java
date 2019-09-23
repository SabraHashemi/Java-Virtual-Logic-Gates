/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;



import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;


 class ComplexGate extends Gate 
{
    
 int in;
 int out;
 
 Pin [] inpins;
 Pin [] outpins;
  int h;
  int w = 50;
  String name2;
  ComplexGate thisg=this;
  Rectangle rect;
  GatesGroup gg ;
          
   ComplexGate() {
      
    }

    ComplexGate(boolean enabled ) {
      
      super(enabled);
   
      
    }
    

    @Override
    void addBehavior() {
        
        
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
           if(!selected)
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
                     /*
                       andGate. setFill(new Color(1, 1, 1, 1.0));
                       selected=false;
                       removeSelection(thisg); */
                       
                   }
                 else  if(!selected)
                   {
                
                name.setStyle(" -fx-effect: dropshadow( three-pass-box , rgba(0.5,0.5,0.6,0.6) , 10, 0.0 , 0 , 1 );");
               rect. setFill(new Color(0.5, 0.5, 0.6, 1.0));
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

    @Override
    void setStyle() {
        
     
       
    }

    @Override
    void logic() {
     for(int i=0 ; i<in ; i++)
                 {
                 gg.inPins.get(i).state.bind( inPins.get(i).state);
                 }
                 for(int j=0; j<out ; j++)
                 {
                       outPins.get(j).state.bind( gg.inPins.get(j).state);
                 }
    }

    @Override
    void makeSkin() {
     gg= (GatesGroup)FullApp.getRepository().get(RepositoryItem.gatesGroup);
     gg.getPins();
     in=LogicAppController.in;
     out=LogicAppController.out;
     name2= LogicAppController.name2;
     inpins = new Pin[in];
     outpins = new Pin[out];
     
        
       
        if(in>=out)
        {
             h= (in+1)*30;
            
        }
        if(out>in)
        {
            h= (out+1)*30;
        }
        rect = new Rectangle(64,0,105,h);
     
       rect.setFill(new Color(0.9,0.68,0.68,1.0));
       rect.setStroke(new Color(0,0,0,1));
       getChildren().add(rect);
       for(int i =0; i< in ;i++)
       {
          inpins[i] = new Pin(14.2, (i+1)*30, 12.0, new Color(1.0, 0.0, 1.0, 1.0), "inPin"+i);
          getChildren().add(inpins[i]);
          Line line = new Line(26,(i+1)*30,64,(i+1)*30);
          getChildren().add(line);
          inPins.add(inpins[i]);
       }
       for(int j=0; j<out; j++)
       {
           outpins[j] = new Pin(219, (j+1)*30, 12, new Color(1.0, 1.0, 0.0, 1.0),"outPin"+j); 
           getChildren().add(outpins[j]);
            Line line2 = new Line(165,(j+1)*30, 205,(j+1)*30);
            getChildren().add(line2);
           outPins.add(outpins[j]);
       }
       
      
    }
    
     @Override
    public String toString()
    {
        return "ComplexGate : " +name2;
    }
    
    
    
}
