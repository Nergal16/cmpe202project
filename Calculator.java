import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
* Calculator implemented to make it more convenient for users to calculate any cost.
* Supports four basic functons.
*/   

class Calculator{
    
    //Define variable and operator
    static double firVariable=0,secVariable=0,result=0;
    static int operator=0;
    
    Calculator(){
    //Design Calculator GUI with Listener  
    }

    public void Add(){
      result = firVariable + secVariable;
    }

    public void Minus(){
      result = firVariable - secVariable;
    }

    public void Divide(){
      result = firVariable/secVariable;
    }

    public void Multi(){
      result = firVariable*secVariable;
    }

}
