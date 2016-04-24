package RoomServicePkg;

import java.util.ArrayList;

/**
 * Created by Haroldo Filho on 4/14/2016.
 */

// This class is the composite in the Composite Pattern for RoomServicePkg
public class RoomServiceOrder implements RoomService {

    private ArrayList<RoomService> items = new ArrayList<RoomService>();
    private String description;

    public RoomServiceOrder(String item){
        description = item;
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
    public void getChild(int childIndex) {
        items.get(childIndex);
    }
}
