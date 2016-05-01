package RoomServicePkg;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

/**
 * Created by home on 4/20/2016.
 */
public class RoomServiceControllerTest {
    private RoomServiceController testController;
    @Before
    public void setUp() throws Exception {
        testController = RoomServiceController.getInstance();
        testController.setMenuScreenFrame(new JFrame());
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void startNewOrder() throws Exception {
        testController.startNewOrder(101);
        testController.newBurger();
        testController.addBacon();

        assert(testController.finishOrder() == 14.6);
    }


    @Test
    public void newBurger() throws Exception {
        testController.startNewOrder(101);
        testController.newBurger();
        assert(testController.finishOrder() == 11.1);
    }

    @Test
    public void addBacon() throws Exception {
        testController.startNewOrder(101);
        testController.newBurger();
        testController.addBacon();

        assert(testController.finishOrder() == 14.6);
    }

    @Test
    public void addEgg() throws Exception {
        testController.startNewOrder(101);
        testController.newBurger();
        testController.addBacon();
        testController.addBacon();
        testController.addEgg();

        assert(testController.finishOrder() == (13.55 + 3.5 + 3.5));
    }

    @Test
    public void newMarketFish() throws Exception {
        testController.startNewOrder(101);
        testController.newMarketFish();


        assert(testController.finishOrder() == 15.5);

    }


}