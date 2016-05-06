package RoomServicePkg;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by cmpe 202 - group 4 on 4/12/16.
 */
public class RoomServiceController {
    // price list
    public Double burgerPrice = 12.0;
    public Double fishPrice   = 15.5;
    public Double saladPrice  = 11.0;
    public Double eggPrice    = 2.45;
    public Double baconPrice  = 3.50;
    public Double mushPrice   = 3.00;


    private static RoomServiceController ourInstance = new RoomServiceController();
    public static RoomServiceController getInstance() {
        return ourInstance;
    }
    private RoomServiceControllerState burgerState;
    private RoomServiceControllerState saladState;
    private RoomServiceControllerState currentState;

    private RoomServiceController() {
        burgerState = new BurgerState(this);
        saladState = new SaladState(this);
        // start in burger state by default;
        currentState = new NoEntreeState(this);
    }
    RoomService currentOrder;
    ArrayList<RoomService> burgerList = null;
    ArrayList<RoomService> saladList = null;
    Integer baconCnt = 0;
    Integer eggCnt = 0;
    Integer mushCnt = 0;
    Integer fishCnt = 0;
    Double bill = 0.0;
    PriceDecorator billDecorator = null;

    private RoomServiceScreen menuScreen = new RoomServiceScreen(this);

    public void setMenuScreenFrame(JFrame frame, Container pane){
        menuScreen.setFrame(frame, pane);
    }

    public void startNewOrder(Integer roomNumber){
        bill = 0.0;
        // start menu screen
        menuScreen.draw();

        // start order
        currentOrder = new RoomServiceOrder("Order for room: " + roomNumber.toString(), 0.0);
    }

    public void newBurger(){
        if (burgerList == null){
            burgerList = new ArrayList<RoomService>();
        }
        burgerList.add(new MominetteBurger("MominetteBurger", burgerPrice));
        currentState = burgerState;
    }

    public void GrilledAppleSalad(){
        if (saladList == null){
            saladList = new ArrayList<RoomService>();
        }
        saladList.add(new GrilledAppleSalad("GrilledAppleSalad", saladPrice));
        currentState = saladState;
    }


    public void addBacon(){
        PriceDecorator baconDecorator = null;
/*        if (burgerList != null){
            if (billDecorator == null){
                baconDecorator = new Bacon("Bacon", baconPrice, burgerList.get(burgerList.size() - 1));
            } else {
                baconDecorator = new Bacon("Bacon", baconPrice, (RoomService) billDecorator);
            }
            billDecorator = baconDecorator;
            burgerList.get(burgerList.size() - 1).addChild((RoomService) baconDecorator);
        }*/
        currentState.doAddOnBacon(baconDecorator);
        baconCnt++;
    }

    public void addEgg(){
        PriceDecorator eggDecorator = null;
/*        if (burgerList != null){
            if (billDecorator == null){
                eggDecorator = new Egg("Egg", eggPrice, burgerList.get(burgerList.size() - 1));
            } else {
                eggDecorator = new Egg("Egg", eggPrice, (RoomService) billDecorator);
            }
            billDecorator = eggDecorator;
            burgerList.get(burgerList.size() - 1).addChild((RoomService) eggDecorator);
        }*/

        currentState.doAddOnEgg(eggDecorator);
        eggCnt++;
    }

    public void addMushroom(){
        PriceDecorator mush = null;
/*        if (burgerList != null){
            if (billDecorator == null){
                mush = new Egg("Mushroom", mushPrice, burgerList.get(burgerList.size() - 1));
            } else {
                mush = new Egg("Mushroom", mushPrice, (RoomService) billDecorator);
            }
            billDecorator = mush;
            burgerList.get(burgerList.size() - 1).addChild((RoomService) mush);
        }*/
        currentState.doAddOnMushroom(mush);
        mushCnt++;
    }

    public void newMarketFish(){

        currentOrder.addChild(new WildMarketFish("Alaskan Halibut", fishPrice));
        fishCnt++;
    }

    public Double finishOrder(){
        bill = 0.0;
        if (burgerList != null) {
            for (RoomService burger : burgerList) {
                currentOrder.addChild(burger);
            }
            if (billDecorator != null) {
                bill += billDecorator.getPrice() + (burgerList.size() - 1) * burgerPrice + fishCnt * fishPrice;
            } else {
                if (burgerList != null) {
                    bill += burgerList.size() * burgerPrice;
                }
            }
        }

        if (saladList != null){
            for (RoomService salad : saladList) {
                currentOrder.addChild(salad);
            }
            if (billDecorator != null) {
                bill += billDecorator.getPrice() + (saladList.size() - 1) * saladPrice + fishCnt * fishPrice;
            } else {
                if (saladList != null) {
                    bill += saladList.size() * saladPrice;
                }
            }
        }

        bill += fishCnt * fishPrice;
        //Composite prints bill
        currentOrder.printItems();
        //Decorator gets prioe
        System.out.print("Final bill: $" + bill.toString());
        System.out.println(); System.out.println();

        // clear all billing
        burgerList = null;
        saladList = null;
        billDecorator = null;
        fishCnt = 0;

        return bill;
    }

    void clearBill(){
        bill = 0.0;
    }




}
