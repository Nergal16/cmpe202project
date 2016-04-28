package booking;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RoomVariety {
List<Integer> available() throws SQLException;
List<Integer> list = new ArrayList<Integer>();
}
