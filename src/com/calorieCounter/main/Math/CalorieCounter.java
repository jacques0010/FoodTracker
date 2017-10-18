package com.calorieCounter.main.Math;

public class CalorieCounter {
    /**
     * Simply computes the number of calories giving the # of protein, carbs, and fats
     **/
    private CalorieCounter() {
    }

    public static float calories(float protein, float carbs, float fats) {
        return pCalorlies(protein) + cCalorlies(carbs) + fCalorlies(fats);
    }

    public static float pCalorlies(float protein) {
        return protein * 4;
    }

    public static float cCalorlies(float carbs) {
        return carbs * 4;
    }

    public static float fCalorlies(float fats) {
        return fats * 9;
    }
}
