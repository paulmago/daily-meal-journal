/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO used to store a single journal for
 * client side operations.
 * @author Kim Agustin
 * @version 0.01
 * Version History
 * [07/28/2015] 0.01 – Kim Agustin – Initial codes.
 */
public class MealJournalDto {

    /**
     * List of errors.
     */
    private List<String> errorList = new ArrayList<String>();
    
    /**
     * Meal Journal ID.
     */
    private long mealJournalId;
    
    /**
     * Meal ID reference.
     */
    private long mealId;
    
    /**
     * Quantity of meal.
     */
    private int quantity;
        
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
     * Retrieve mealJournalId
     * @return the mealJournalId
     */
    public long getMealJournalId() {
        return mealJournalId;
    }

    /**
     * Set mealJournalId
     * @param mealJournalId the mealJournalId to set
     */
    public void setMealJournalId(long mealJournalId) {
        this.mealJournalId = mealJournalId;
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
     * Retrieve quantity
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set quantity
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
