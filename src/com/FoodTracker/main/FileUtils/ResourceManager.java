package com.FoodTracker.main.FileUtils;

import com.FoodTracker.main.DateUtils.Date;
import com.FoodTracker.main.DateUtils.Day;
import com.FoodTracker.main.FoodUtils.Food;

import java.util.HashMap;

/**
 * This manages the resources used by the controllers.
 * It allows a class to get a reference to information anywhere in the program.
 */

public class ResourceManager {
    private static ResourceManager ourInstance = new ResourceManager();

    private LoadFile LF = LoadFile.getInstance();
    private HashMap<Date, Day> days = (HashMap<Date, Day>) LF.load("Dates.ser"); //todo handle case unsafe casting
    private HashMap<String, Food> foods = (HashMap<String, Food>) LF.load("Foods.ser");

    private Date today = new Date();

    public static ResourceManager getInstance() {
        return ourInstance;
    }

    private ResourceManager() {
    }

    /**
     * Creates a new day if the file is new/empty.
     * @return the hashmap of "days"
     */
    public HashMap<Date, Day> getDays() {
        if (days.isEmpty()) {
            System.err.println("No days saved! Starting from today!");
            days.put(today, new Day());
        }
        return days;
    }

    /**
     * returns a reference to the current Date.
     * @return today
     */
    public Date getToday() {
        return today;
    }

    public HashMap<String, Food> getFoods() {
        return foods;
    }
}
