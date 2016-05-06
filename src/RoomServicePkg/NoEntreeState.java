package RoomServicePkg;

/**
 * Created by home on 5/1/2016.
 */
public class NoEntreeState extends EntreState{

    public NoEntreeState(RoomServiceController controller){
        super(controller);
    }

    @Override
    public void doAddOnBacon(PriceDecorator priceDecorator) {

    }

    @Override
    public void doAddOnEgg(PriceDecorator priceDecorator) {

    }

    @Override
    public void doAddOnMushroom(PriceDecorator priceDecorator) {

    }
}

