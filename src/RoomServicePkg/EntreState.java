package RoomServicePkg;

import java.util.ArrayList;

/**
 * Created by home on 4/28/2016.
 */
public abstract class EntreState implements RoomServiceControllerState {
    RoomServiceController controller;
    public EntreState(RoomServiceController controller){
        this.controller = controller;
    }



    @Override
    public void addItemToEntreList(ArrayList<RoomService> entreList, PriceDecorator priceDecorator) {
        if (entreList != null){
            if (controller.billDecorator == null){
                priceDecorator= new Bacon("Bacon", 3.50, entreList.get(entreList.size() - 1));
            } else {
                priceDecorator = new Bacon("Bacon", 3.50, (RoomService) controller.billDecorator);
            }
            controller.billDecorator = priceDecorator;
            entreList.get(entreList.size() - 1).addChild((RoomService) priceDecorator);
        }
    }
}
