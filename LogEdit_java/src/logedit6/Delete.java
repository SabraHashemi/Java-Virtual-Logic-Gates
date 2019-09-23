/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logedit6;


import javafx.scene.Node;


public class Delete {
    
  
    public Delete()
    {
      
       
    }
    
    public void delete()
    {
        
        for(Node node: FullApp.gatesg.getChildren() )
        {
         //   Gate.gatesg.getChildren().remove(node);
            FullApp.gatesg.getChildren().get(FullApp.gatesg.getChildren().indexOf(node)).setVisible(false);
          
        }
            GatesGroup gatesg1 = new GatesGroup();
           FullApp.gatesg=gatesg1;
            FullApp.i=0;
           
         for(Gate sgate : FullApp.sgates)
                {
                    for(Gate gate : FullApp.gates)
                    {
                        if(sgate.iconName.equals(gate.iconName))
                        {
                            FullApp.gates.remove(gate);
                            break;
                        }
                    }
                     sgate.setVisible(false);
                   
                    for(int i=0 ; i<sgate.getParent().getChildrenUnmodifiable().size();i++)
                    {
                        
                        if(!( sgate.getParent().getChildrenUnmodifiable().get(i) instanceof Gate ) )
                        {
                             sgate.getParent().getChildrenUnmodifiable().get(i).setVisible(false);
                        }
                    }
                   
                 
                  
                }
    }
    
}
