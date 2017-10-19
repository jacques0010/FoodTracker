package com.FoodTracker.main.FoodUtils;

import java.io.Serializable;

/**
 *  Food value object that stores name and nutrients of the food
 */
public class Food implements Serializable {
    private static final long serialVersionUID = 8880114633837352780L;
    private final String NAME;

    private final float CARBS;
    private final float PROTIEN;
    private final float FATS;

    /**
     * Creates Food object
     * @param name - Name of food
     * @param protien - Amount of protein in food
     * @param carbs - Amount of carbs in food
     * @param fats - Amount of fats in food
     */
    public Food(String name, float protien, float carbs, float fats) {
        NAME = name;
        CARBS = carbs;
        PROTIEN = protien;
        FATS = fats;
    }

    /**
     * Returns carbs in Food
     * @return Carbs in Food
     */

    public float getCARBS() {
        return CARBS;
    }

    /**
     * Returns protein in Food
     * @return protein in Food
     */
    public float getPROTIEN() {
        return PROTIEN;
    }

    /**
     * Returns fats in Food
     * @return fats in Food
     */
    public float getFATS() {
        return FATS;
    }

    /**
     * Returns name of Food
     * @return Name of Food
     */
    public String getNAME() {
        return NAME;
    }
}
