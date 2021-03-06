package RoomServicePkg;

/**
 * Created by cmpe 202 - group 4 on 4/12/16.
 */
public class Egg  extends AddOnItem{
    private Double addedPrice;
    public Egg(String d, Double p, RoomService item) {

        super(d, p, item);
    }

    @Override
    public Double getPrice() {
        addedPrice = super.getAddedPrice(); // price from main component passed in constructor
        return addedCustomPrice(addedPrice);  //
    }

    private Double addedCustomPrice(Double price){
        return price + super.getPrice();  // price of this component
    }

}
