package RoomServicePkg;

/**
 * Created by Haroldo Filho on 4/14/2016.
 */

// Interface for Component class in Composite pattern for RoomServicePkg
public interface RoomService {
    public void printItems();
    public void addChild(RoomService item);
    public void removeChild(RoomService item);
    public void getChild(int childIndex);
}
