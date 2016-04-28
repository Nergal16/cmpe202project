package reviews;

/*
 * Created by Mandeep Kaur
 *  on 04/19/2016
 *  Interface in composite pattern
 */

public interface Review {
	    public void printItems();
	    public void addChild(MainReviews item);
	    public void removeChild(MainReviews item);
	    public void getChild(int childIndex);
}
