package RoomServicePkg;

/**
 * Created by home on 4/28/2016.
 */
public class SaladState extends EntreState{
    RoomServiceController controller;
    public SaladState(RoomServiceController controller){
        super(controller);
    }
    @Override
    public void doAddOnBacon(PriceDecorator priceDecorator) {
        addItemToEntreList(controller.saladList, priceDecorator);
        controller.baconCnt++;
    }

    @Override
    public void doAddOnEgg(PriceDecorator priceDecorator) {
        addItemToEntreList(controller.saladList, priceDecorator);
        controller.eggCnt++;
    }

    @Override
    public void doAddOnMushroom(PriceDecorator priceDecorator) {
        addItemToEntreList(controller.saladList, priceDecorator);
        controller.mushCnt++;
    }
}
