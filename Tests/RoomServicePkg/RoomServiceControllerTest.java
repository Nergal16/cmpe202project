package RoomServicePkg;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;

/**
 * Created by home on 4/20/2016.
 */
public class RoomServiceControllerTest {
    private RoomServiceController testController;
    private Double burgerP, saladP, baconP, eggP, fishP;
    @Before
    public void setUp() throws Exception {
        testController = RoomServiceController.getInstance();
        testController.setMenuScreenFrame(new JFrame(), new Container());
        baconP = testController.baconPrice;
        burgerP = testController.burgerPrice;
        saladP = testController.saladPrice;
        eggP = testController.eggPrice;
        fishP = testController.fishPrice;
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void startNewOrder() throws Exception {
        testController.startNewOrder(101);
        testController.newBurger();
        testController.addBacon();

        assert(testController.finishOrder() == burgerP + baconP );
    }


    @Test
    public void newBurger() throws Exception {
        testController.startNewOrder(101);
        testController.newBurger();
        Double testf = testController.finishOrder();
        Double testb = burgerP;
        Double testd = abs(testf - testb);
        assert(testd == 0.0) ;
    }

    @Test
    public void addBacon() throws Exception {
        testController.startNewOrder(101);
        testController.newBurger();
        testController.addBacon();

        assert(testController.finishOrder() == burgerP + baconP);
    }

    @Test
    public void addEgg() throws Exception {
        testController.startNewOrder(101);
        testController.newBurger();
        testController.addBacon();
        testController.addBacon();
        testController.addEgg();

        assert(testController.finishOrder() == burgerP + baconP + baconP + eggP);
    }

    @Test
    public void newMarketFish() throws Exception {
        testController.startNewOrder(101);
        testController.newMarketFish();

        Double testf = testController.finishOrder();
        Double testb = fishP;
        Double testd = abs(testf - testb);
        assert(testd == 0.0);

    }


}