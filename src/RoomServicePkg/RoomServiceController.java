package RoomServicePkg;

import java.util.ArrayList;

/**
 * Created by Haroldo Filho on 4/15/2016.
 */
public class RoomServiceController {
    RoomService currentOrder;
    ArrayList<RoomService> burgerList = null;

    public void startNewOrder(Integer roomNumber){
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
