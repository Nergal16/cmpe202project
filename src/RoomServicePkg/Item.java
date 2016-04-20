package RoomServicePkg;

import java.text.DecimalFormat;

/**
 * Created by home on 4/19/2016.
 */
public class Item implements RoomService {
    private String description;
    private Double price;

    public Item (String d, Double p){
        description = d;
        price = p;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void printItems() {
        DecimalFormat fmt = new DecimalFormat("0.00");
        System.out.println(description + " " + fmt.format(price));
    }

    @Override
    public void addChild(RoomService item) {
        // leaf element - no implementation
    }

    @Override
    public void removeChild(RoomService item) {
        // leaf element - no implementation
    }

    @Override
    public RoomService getChild(int childIndex) {
        // leaf element - no implementation
        return null;
    }
}
