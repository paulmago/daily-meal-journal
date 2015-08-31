/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO used to store a single meal for admin side operations.
 * @author Kim Agustin
 * @version 0.02
 * Version History
 * [07/27/2015] 0.01 – Kim Agustin – Initial codes.
 * [08/31/2015] 0.02 – Kim Agustin – Changed calories from int to double.
 */
public class MealDto {

    /**
     * List of errors.
     */
    private List<String> errorList = new ArrayList<String>();
    
    /**
     * Meal ID.
     */
    private long mealId;
    
    /**
     * Meal name.
     */
    private String name;
    
    /**
     * Unit of meal serving.
     */
    private String unit;
    
    /**
     * Quantitative measure of meal calories.
     */
    private double calories;
    
    /**
     * Default quantity of meal serving.
     */
    private int defaultQuantity;
        
    /**
     * Retrieve errorList
     * @return the errorList
     */
    public List<String> getErrorList() {
        return errorList;
    }
    
    /**
     * Set errorList
     * @param errorList the errorList to set
     */
    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
    
    /**
     * Retrieve mealId
     * @return the mealId
     */
    public long getMealId() {
        return mealId;
    }

    /**
     * Set mealId
     * @param mealId the mealId to set
     */
    public void setMealId(long mealId) {
        this.mealId = mealId;
    }
    
    /**
     * Retrieve name
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set name
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Retrieve unit
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }
    
    /**
     * Set unit
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    /**
     * Retrieve calories
     * @return the calories
     */
    public double getCalories() {
        return calories;
    }
    
    /**
     * Set calories
     * @param calories the calories to set
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }
    
    /**
     * Retrieve defaultQuantity
     * @return the defaultQuantity
     */
    public int getDefaultQuantity() {
        return defaultQuantity;
    }
    
    /**
     * Set defaultQuantity
     * @param defaultQuantity the defaultQuantity to set
     */
    public void setDefaultQuantity(int defaultQuantity) {
        this.defaultQuantity = defaultQuantity;
    }
}
