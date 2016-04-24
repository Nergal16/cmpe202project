package RoomServicePkg;

import java.text.DecimalFormat;

/**
 * Created by home on 4/14/2016.
 */
public class AddOnItem implements RoomService {
    private String description;
    private Double price;

    public AddOnItem (String d, Double p){
        description = d;
        price = p;
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
    public void getChild(int childIndex) {
        // leaf element - no implementation
    }
}
