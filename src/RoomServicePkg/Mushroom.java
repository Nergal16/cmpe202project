package RoomServicePkg;

/**
 * Created by Haroldo Filho on 4/14/2016.
 */
public class Mushroom extends AddOnItem{
    private Double addedPrice;
    public Mushroom(String d, Double p, RoomService item) {

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
