package RoomServicePkg;

import java.util.ArrayList;

/**
 * Created by cmpe 202 on 4/28/2016.
 */
public abstract class EntreState implements RoomServiceControllerState {
    RoomServiceController controller;
    public EntreState(RoomServiceController controller){
        this.controller = controller;
    }



    @Override
    public void addBaconToEntreList(ArrayList<RoomService> entreList, PriceDecorator priceDecorator) {
        if (entreList != null){
            if (controller.billDecorator == null){
                priceDecorator= new Bacon("Bacon", controller.baconPrice, entreList.get(entreList.size() - 1));
            } else {
                priceDecorator = new Bacon("Bacon", controller.baconPrice, (RoomService) controller.billDecorator);
            }
            controller.billDecorator = priceDecorator;
            entreList.get(entreList.size() - 1).addChild((RoomService) priceDecorator);
        }
    }

    @Override
    public void addEggToEntreList(ArrayList<RoomService> entreList, PriceDecorator priceDecorator) {
        if (entreList != null){
            if (controller.billDecorator == null){
                priceDecorator= new Bacon("Egg", controller.eggPrice, entreList.get(entreList.size() - 1));
            } else {
                priceDecorator = new Bacon("Egg", controller.eggPrice, (RoomService) controller.billDecorator);
            }
            controller.billDecorator = priceDecorator;
            entreList.get(entreList.size() - 1).addChild((RoomService) priceDecorator);
        }
    }

    @Override
    public void addMushroomToEntreList(ArrayList<RoomService> entreList, PriceDecorator priceDecorator) {
        if (entreList != null){
            if (controller.billDecorator == null){
                priceDecorator= new Bacon("Mushroom", controller.mushPrice, entreList.get(entreList.size() - 1));
            } else {
                priceDecorator = new Bacon("Mushroom", controller.mushPrice, (RoomService) controller.billDecorator);
            }
            controller.billDecorator = priceDecorator;
            entreList.get(entreList.size() - 1).addChild((RoomService) priceDecorator);
        }
    }
}
