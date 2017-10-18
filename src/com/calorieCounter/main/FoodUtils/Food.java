package com.calorieCounter.main.FoodUtils;

import java.io.Serializable;

/**
 *  Food object that simply stores name and nutrients
 */
public class Food implements Serializable {
    private static final long serialVersionUID = 8880114633837352780L;
    private final String NAME;

    private final float CARBS;
    private final float PROTIEN;
    private final float FATS;

    public Food(String name, float protien, float carbs, float fats) {
        NAME = name;
        CARBS = carbs;
        PROTIEN = protien;
        FATS = fats;
    }

    public float getCARBS() {
        return CARBS;
    }

    public float getPROTIEN() {
        return PROTIEN;
    }

    public float getFATS() {
        return FATS;
    }

    public String getNAME() {
        return NAME;
    }
}
