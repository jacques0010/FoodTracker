package com.FoodTracker.main.DateUtils;

import com.FoodTracker.main.FoodUtils.Food;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This object stores information about the foods eaten for the day. It stores all the foods for that day in an arrayList called foods.
 * From the list of foods, the amount of the total nutrients eaten in the day can be calculated.
 */

public class Day implements Serializable {
    private static final long serialVersionUID = 3252032211915592670L;
    private HashMap<String, ArrayList<Object>> foodIndex;

    public Day() {
        foodIndex = new HashMap<>();
    }

    /**
     * Adds a food to the HashMap foods using a food object.
     *
     * @param food
     */
    public void addFood(Food food) {
        if (foodIndex.containsKey(food.getNAME())) {
            foodIndex.get(food.getNAME()).add(1, (int) foodIndex.get(food.getNAME()).get(1) + 1);
        } else {
            ArrayList<Object> list = new ArrayList<>();
            list.add(0, food);
            list.add(1, 1);
            foodIndex.put(food.getNAME(), list);
        }
    }

    /**
     * Adds a food to the HashMap foods by creating a new food object.
     *
     * @param name
     * @param carbs
     * @param protein
     * @param fats
     */
    public void addFood(String name, float protein, float carbs, float fats) {
        if (foodIndex.containsKey(name)) {
            foodIndex.get(name).add(1, (int) foodIndex.get(name).get(1) + 1);
        } else {
            ArrayList<Object> list = new ArrayList<>();
            list.add(0, new Food(name, protein, carbs, fats));
            list.add(1, 1);
            foodIndex.put(name, list);
        }
    }

    /**
     * Returns the total amount of protein eaten that day
     *
     * @return Total protein eaten that day in grams as a float
     */
    public float getTotalProtein() {
        float result = 0;
        if (foodIndex.isEmpty()) return 0;
        if (result < 0) return 0;
        else {
            for (ArrayList<Object> list : foodIndex.values()) {
                result += ((Food) list.get(0)).getPROTIEN() * ((int) list.get(1));
            }
            return result;
        }
    }

    /**
     * Returns the total amount of carbs eaten that day
     *
     * @return Total carbs eaten that day in grams as a float
     */
    public float getTotalCarbs() {
        float result = 0;
        if (foodIndex.isEmpty()) return 0;
        if (result < 0) return 0;
        else {
            for (ArrayList<Object> list : foodIndex.values()) {
                result += ((Food) list.get(0)).getCARBS() * ((int) list.get(1));
            }
            return result;
        }
    }

    /**
     * Returns the total amount of fats eaten that day
     *
     * @return Total fats eaten that day in grams as a float
     */
    public float getTotalFats() {
        float result = 0;
        if (foodIndex.isEmpty()) return 0;
        if (result < 0) return 0;
        else {
            for (ArrayList<Object> list : foodIndex.values()) {
                result += ((Food) list.get(0)).getFATS() * ((int) list.get(1));
            }
            return result;
        }
    }

    /**
     * Returns the HashMap of the foods stored in this day.
     *
     * @return The HashMap of foods
     */

    public HashMap<String, ArrayList<Object>> getFoodIndex() {
        return foodIndex;
    }

    public int getTimesEaten(String foodName) {
        return (int) foodIndex.get(foodName).get(1);
    }

    public Food getFood(String foodName) {
        return (Food) foodIndex.get(foodName).get(0);
    }

    public void setTimesEaten(String foodName, int num) {
        if (num == 0){
            foodIndex.remove(foodName);
        }else
            foodIndex.get(foodName).set(1, num);

    }

    /**
     * @return
     */

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (ArrayList<Object> arrayList : foodIndex.values()) {
            string.append(((Food) arrayList.get(0)).getNAME() + "\n");
        }
        return string.toString();
    }
}
