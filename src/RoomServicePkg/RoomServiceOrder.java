package src.RoomServicePkg;

import java.util.ArrayList;

/**
 * Created by Haroldo Filho on 4/14/2016.
 */

// This class is the composite in the Composite Pattern for RoomServicePkg
public class RoomServiceOrder implements RoomService {

    private ArrayList<RoomService> items = new ArrayList<RoomService>();
    private String description;
    private Double price;

    public RoomServiceOrder(String item, Double itemPrice){
        price = itemPrice;
        description = item;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void printItems() {
        // for test only TODO: delete log message after UI is done
        System.out.println(description);
        for (RoomService item : items){
            item.printItems();
        }
    }

    @Override
    public void addChild(RoomService item) {
        items.add(item);
    }

    @Override
    public void removeChild(RoomService item) {
        items.remove(item);
    }

    @Override
    public RoomService getChild(int childIndex) {
        return items.get(childIndex);
    }
}
