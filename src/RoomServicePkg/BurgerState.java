package RoomServicePkg;

/**
 * Created by cmpe 202 on 4/28/2016.
 */
public class BurgerState extends EntreState{

    public BurgerState(RoomServiceController controller){
        super(controller);
    }

    @Override
    public void doAddOnBacon(PriceDecorator priceDecorator) {
        addBaconToEntreList(controller.burgerList, priceDecorator);
        controller.baconCnt++;
    }

    @Override
    public void doAddOnEgg(PriceDecorator priceDecorator) {
        addEggToEntreList(controller.burgerList, priceDecorator);
        controller.eggCnt++;
    }

    @Override
    public void doAddOnMushroom(PriceDecorator priceDecorator) {
        addMushroomToEntreList(controller.burgerList, priceDecorator);
        controller.mushCnt++;
    }
}
