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
    Integer baconCnt = 0;
    Integer eggCnt = 0;
    Integer fishCnt = 0;
    Double bill = 0.0;
    PriceDecorator billDecorator = null;

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
        PriceDecorator baconDecorator;
        if (burgerList != null){
            if (billDecorator == null){
                baconDecorator = new Bacon("Bacon", 3.50, burgerList.get(burgerList.size() - 1));
            } else {
                baconDecorator = new Bacon("Bacon", 3.50, (RoomService) billDecorator);
            }
            billDecorator = baconDecorator;
            burgerList.get(burgerList.size() - 1).addChild((RoomService) baconDecorator);
        }
        baconCnt++;
    }

    public void addEgg(){
        PriceDecorator eggDecorator;
        if (burgerList != null){
            if (billDecorator == null){
                eggDecorator = new Egg("Egg", 2.45, burgerList.get(burgerList.size() - 1));
            } else {
                eggDecorator = new Egg("Egg", 2.45, (RoomService) billDecorator);
            }
            billDecorator = eggDecorator;
            burgerList.get(burgerList.size() - 1).addChild((RoomService) eggDecorator);
        }
        eggCnt++;
    }

    public void newMarketFish(){

        currentOrder.addChild(new WildMarketFish("Alaskan Halibut", 15.5));
        fishCnt++;
    }

    public Double finishOrder(){
        bill = 0.0;
        if (burgerList != null){
            for (RoomService burger : burgerList){
                currentOrder.addChild(burger);
            }
        }

        if (billDecorator != null){
            bill = billDecorator.getPrice() + (burgerList.size() - 1) * 11.1 + fishCnt * 15.5;
        } else {
            if (burgerList != null){
                bill = burgerList.size() * 11.1;
            }
            bill += fishCnt * 15.5;
        }
        //Composite prints bill
        currentOrder.printItems();
        //Decorator gets prioe
        System.out.print("Final bill: $" + bill.toString());
        System.out.println(); System.out.println();

        // clear all billing
        burgerList = null;
        billDecorator = null;
        fishCnt = 0;

        return bill;
    }




}
