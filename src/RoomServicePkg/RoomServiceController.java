package RoomServicePkg;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Haroldo Filho on 4/15/2016.
 */
public class RoomServiceController {
    private static RoomServiceController ourInstance = new RoomServiceController();

    public static RoomServiceController getInstance() {
        return ourInstance;
    }

    private RoomServiceController() {
    }
    RoomService currentOrder;
    ArrayList<RoomService> burgerList = null;

    private RoomServiceScreen menuScreen = new RoomServiceScreen(this);

    public void setMenuScreenFrame(JFrame frame){
        menuScreen.setFrame(frame);
    }

    public void startNewOrder(Integer roomNumber){
        menuScreen.draw();
        currentOrder = new RoomServiceOrder("Order for room: " + roomNumber.toString(), 0.0);
    }

    public void newBurger(){
        if (burgerList == null){
            burgerList = new ArrayList<RoomService>();
        }
        burgerList.add(new MominetteBurger("MominetteBurger", 11.1));
    }

    public void addBacon(){
        if (burgerList != null){
            burgerList.get(burgerList.size() - 1).addChild(new Bacon("Bacon", 3.50, burgerList.get(burgerList.size() - 1)));
        }
    }

    public void addEgg(){
        if (burgerList != null){
            burgerList.get(burgerList.size() - 1).addChild(new Bacon("Egg", 2.45, burgerList.get(burgerList.size() - 1)));
        }
    }

    public void newMarketFish(){
        currentOrder.addChild(new WildMarketFish("Alaskan Halibut", 15.5));
    }

    public void finishOrder(){
        if (burgerList != null){
            for (RoomService burger : burgerList){
                currentOrder.addChild(burger);
            }
        }

    }


}
