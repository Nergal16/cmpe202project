package RoomServicePkg;

/**
 * Created by Haroldo Filho on 4/14/2016.
 */
public abstract class AddOnItem extends Item implements PriceDecorator{

    private RoomService roomServiceItem;
    public AddOnItem(String d, Double p, RoomService item) {
        super(d, p);
        roomServiceItem = item;
    }
    public Double getAddedPrice(){
        return roomServiceItem.getPrice();
    }


}
