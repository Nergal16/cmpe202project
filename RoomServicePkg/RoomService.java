package RoomServicePkg;

/**
 * Created by cmpe 202 - group 4 on 4/12/16.
 */
// Interface for Component class in Composite pattern for RoomServicePkg
public interface RoomService {
    public Double getPrice();
    public void printItems();
    public void addChild(RoomService item);
    public void removeChild(RoomService item);
    public RoomService getChild(int childIndex);
}
