package com.calorieCounter.main.DateUtils;

import com.calorieCounter.main.FoodUtils.Food;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This object stores information about the foods eaten for the day. It stores all the foods for that day in an arrayList called foods.
 * From the list of foods, the amount of the total nutrients eaten in the day can be calculated.
 */

public class Day implements Serializable {
    private static final long serialVersionUID = 3252032211915592670L;
    private ArrayList<Food> foods;

    public Day() {
        foods = new ArrayList<>();
    }

    /**
     * Adds a food to the arrayList foods using a food object.
     * @param food
     */
    public void addFood(Food food) {
        foods.add(food);
    }

    /**
     * Adds a food to the arrayList foods by creating a new food object.
     * @param name
     * @param carbs
     * @param protein
     * @param fats
     */
    public void addFood(String name, float carbs, float protein, float fats) {
        foods.add(new Food(name, carbs, protein, fats));
    }

    /**
     * Returns the total amount of protein eaten that day
     * @return Total protein eaten that day in grams as a float
     */
    public float getTotalProtein() {
        float result = 0;
        if (foods.isEmpty()) return 0;
        for (Food food : foods) {
            result += food.getPROTIEN();
        }
        if (result < 0) return 0;
        else return result;
    }
    /**
     * Returns the total amount of carbs eaten that day
     * @return Total carbs eaten that day in grams as a float
     */
    public float getTotalCarbs() {
        float result = 0;
        if (foods.isEmpty()) return 0;
        for (Food food : foods) {
            result += food.getCARBS();
        }
        if (result < 0) return 0;
        else return result;
    }
    /**
     * Returns the total amount of fats eaten that day
     * @return Total fats eaten that day in grams as a float
     */
    public float getTotalFats() {
        float result = 0;
        if (foods.isEmpty()) return 0;
        for (Food food : foods) {
            result += food.getFATS();
        }
        if (result < 0) return 0;

        else return result;
    }

    /**
     * Returns the arrayList of the foods stored in this day.
     * @return The arrayList of foods
     */
    public ArrayList<Food> getFoods() {
        return foods;
    }

    /**
     *
     * @return
     */

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Food food : foods) {
            string.append(food.getNAME() + "\n");
        }
        return string.toString();
    }
}
