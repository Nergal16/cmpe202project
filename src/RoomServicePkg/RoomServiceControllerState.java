package RoomServicePkg;

import java.util.ArrayList;

/**
 * Created by cmpe 202 on 4/28/2016.
 */
public interface RoomServiceControllerState {
    void doAddOnBacon(PriceDecorator priceDecorator);
    void doAddOnEgg(PriceDecorator priceDecorator);
    void doAddOnMushroom(PriceDecorator priceDecorator);
    void addBaconToEntreList(ArrayList<RoomService> entreList, PriceDecorator priceDecorator);
    void addEggToEntreList(ArrayList<RoomService> entreList, PriceDecorator priceDecorator);
    void addMushroomToEntreList(ArrayList<RoomService> entreList, PriceDecorator priceDecorator);
}
