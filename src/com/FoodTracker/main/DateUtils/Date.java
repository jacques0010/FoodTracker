package com.FoodTracker.main.DateUtils;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Date is a custom object for storing dates.
 */
public class Date implements Comparable, Serializable{
    private static final long serialVersionUID = 6888946530616161894L;
    private final int YEAR, MONTH, DAY;
    private static final int hashSeed  = 63;
    private LocalDate date = LocalDate.now();

    public Date() {
        this.YEAR = date.getYear();
        this.MONTH = date.getMonthValue();
        this.DAY = date.getDayOfMonth();
    }

    /**
     * Returns the value of YEAR
     * @return YEAR
     */
    public int getYear() {
        return YEAR;
    }

    /**
     * Returns the value of MONTH
     * @return MONTH
     */

    public int getMonth() {
        return MONTH;
    }

    /**
     * Returns the value of DAY
     * @return DAY
     */
    public int getDay() {
        return DAY;
    }

    /**
     * Returns a String with the date in Month/Day/Year format
     * @return "Month/Day/Year"
     */

    @Override
    public String toString(){
        return MONTH + "/" + DAY + "/" + YEAR;
    }

    /**
     * Compares Date objects using day then month then year
     * @param o object being compared
     * @return -1 if date came before the date that invoked compareTo, 0 if the same day, 1 if date came after
     * @see Comparable#compareTo(Object)
     * @throws ClassCastException
     */
    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Date)) {
            throw new ClassCastException("Objects cannot be compared!");
        } else {
            Date dateObj = (Date) o;
            if (!this.equals(dateObj)) {
                if (dateObj.getDay() < this.DAY || dateObj.getMonth() < this.MONTH || dateObj.getYear() < this.YEAR) return 1;
                else return -1;
            }else return 0;
        }
    }

    /**
     * Checks if the object is equal to the object invoking equals
     * @param obj object being compared
     * @return the result of the comparison of Day, Month, Year
     * <p> returns false if objects are not of the same type
     */
    @Override
    public boolean equals(Object obj){
        Date dateObj;
        if (!(obj instanceof Date)){
            return false;
        }else{
            dateObj = (Date) obj;
        }
        return  dateObj.getDay() == this.getDay() && dateObj.getMonth() == this.getMonth() && dateObj.getYear() == this.getYear();
    }

    /**
     * returns a hash code based off the day, month, and year
     * @return hash code
     */
    @Override
    public int hashCode(){
        int result = hashSeed;
        result = 31 * result + DAY;
        result = 31 * result + MONTH;
        result = 31 * result + YEAR;
        return result;
    }
}
