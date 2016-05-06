package RoomServicePkg;

/**
 * Created by cmpe 202 on 4/28/2016.
 */
public class SaladState extends EntreState{

    public SaladState(RoomServiceController controller){
        super(controller);
    }
    @Override
    public void doAddOnBacon(PriceDecorator priceDecorator) {
        addBaconToEntreList(controller.saladList, priceDecorator);
        controller.baconCnt++;
    }

    @Override
    public void doAddOnEgg(PriceDecorator priceDecorator) {
        addEggToEntreList(controller.saladList, priceDecorator);
        controller.eggCnt++;
    }

    @Override
    public void doAddOnMushroom(PriceDecorator priceDecorator) {
        addMushroomToEntreList(controller.saladList, priceDecorator);
        controller.mushCnt++;
    }
}
