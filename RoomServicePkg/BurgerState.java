package RoomServicePkg;

/**
 * Created by cmpe 202 on 4/28/2016.
 */
public class BurgerState extends EntreState{
    RoomServiceController controller;
    public BurgerState(RoomServiceController controller){
        super(controller);
    }

    @Override
    public void doAddOnBacon(PriceDecorator priceDecorator) {
        addItemToEntreList(controller.burgerList, priceDecorator);
        controller.baconCnt++;
    }

    @Override
    public void doAddOnEgg(PriceDecorator priceDecorator) {
        addItemToEntreList(controller.burgerList, priceDecorator);
        controller.eggCnt++;
    }

    @Override
    public void doAddOnMushroom(PriceDecorator priceDecorator) {
        addItemToEntreList(controller.burgerList, priceDecorator);
        controller.mushCnt++;
    }
}
