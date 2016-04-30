package RoomServicePkg;

/**
 * Created by cmpe 202 - group 4 on 4/12/16.
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
