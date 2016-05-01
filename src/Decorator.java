/**
 * Created by cmpe 202 - group 4 on 4/30/16.
 */
public class Decorator extends ManagerGUI implements IComponent {
    private IComponent component;

    public Decorator( IComponent c )
    {
        component = c ;
    }

    public void operation()
    {
        component.operation() ;
    }
}
