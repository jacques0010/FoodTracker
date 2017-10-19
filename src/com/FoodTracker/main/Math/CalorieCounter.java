package com.FoodTracker.main.Math;

public class CalorieCounter {
    /**
     * A static class to help compute the number of calories giving the # of protein, carbs, and fats
     **/
    private CalorieCounter() {
    }

    /**
     * Returns the number of the total calories
     * @param protein
     * @param carbs
     * @param fats
     * @return The number of total calories in a food based off the parameters
     */
    public static float calories(float protein, float carbs, float fats) {
        return pCalorlies(protein) + cCalorlies(carbs) + fCalorlies(fats);
    }

    /**
     * Returns the number of calories from protein
     * @param protein
     * @return Number of calories from an amount of protein.
     */
    public static float pCalorlies(float protein) {
        return protein * 4;
    }

    /**
     * Returns the number of calories from carbs
     * @param carbs
     * @return Number of calories from carbs
     */
    public static float cCalorlies(float carbs) {
        return carbs * 4;
    }

    /**
     * Returns the number of calories from fats
     * @param fats
     * @return Number of calories from fats
     */
    public static float fCalorlies(float fats) {
        return fats * 9;
    }
}
