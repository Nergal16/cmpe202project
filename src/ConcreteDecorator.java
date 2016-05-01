import javax.swing.*;

/**
 * Created by cmpe 202 - group 4 on 4/30/16.
 */
public class ConcreteDecorator extends Decorator {

    private String addedState;

    public ConcreteDecorator(IComponent c)
    {
        super( c ) ;
    }

    public void operation()
    {
        super.operation() ;
        addedBehavior() ;
    }

    private void addedBehavior() {
        //create Scrollbar for textArea that is used on the manager GUI
        scrollBar = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollBar.setEnabled(true);
        scrollBar.setVisible(true);
        frame.add(scrollBar);
        scrollBar.setBounds(50, 500, 500, 50);
    }
}
