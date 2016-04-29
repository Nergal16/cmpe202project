package RoomServicePkg;

import java.util.ArrayList;

/**
 * Created by home on 4/28/2016.
 */
public interface RoomServiceControllerState {
    void doAddOnBacon(PriceDecorator priceDecorator);
    void doAddOnEgg(PriceDecorator priceDecorator);
    void doAddOnMushroom(PriceDecorator priceDecorator);
    void addItemToEntreList(ArrayList<RoomService> entreList, PriceDecorator priceDecorator);
}
