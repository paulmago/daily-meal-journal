/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.dao;

import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.FilterCriterion;

import team.dailymealjournal.meta.MealMeta;
import team.dailymealjournal.model.Meal;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

/**
* Dao used to access the datastore for meal transactions.s
* @author Kim Agustin
* @version 0.01
* Version History
* [07/27/2015] 0.01 – Kim Agustin – Initial codes.
*/
public class MealDao {

    /**
     * Method used to save a meal.
     * @param MealModel - Meal to be saved.
     * @return Boolean - true, if meal is saved; otherwise, false.
     */
    public boolean addMeal(Meal mealModel) {
        boolean result = true;
        try {
            Transaction tx = Datastore.beginTransaction();
            //Manually allocate key
            Key key = Datastore.allocateId(KeyFactory.createKey("Account", "Default"), "Meal");
            mealModel.setKey(key);
            mealModel.setMealId(key.getId());
            Datastore.put(mealModel);
            tx.commit();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * Method used to retrieve list of Meals.
     * @return List<Meal> - list of Meals.
     */
    public List<Meal> getAllMeals() {
        MealMeta meta = new MealMeta();
        Key parentKey = KeyFactory.createKey("Account", "Default");
        return Datastore.query(meta ,parentKey).asList();
    }
    
    /**
     * Method used to retrieve a Meal using its ID.
     * @param long mealId
     * @return Meal.
     */
    public Meal getMeal(long mealId) {
        MealMeta meta = new MealMeta();
        Key parentKey = KeyFactory.createKey("Account", "Default");
        FilterCriterion mainFilter = meta.mealId.equal(mealId);
        return Datastore.query(meta ,parentKey).filter(mainFilter).asSingle();
    }

    /**
     * Method used to edit a meal.
     * @param MealModel - Meal to save.
     * @return Boolean - true, if meal is saved; otherwise, false.
     */
    public boolean editMeal(Meal mealModel) {
        boolean result = true;
        MealMeta meta = new MealMeta();
        FilterCriterion mainFilter = meta.mealId.equal(mealModel.getMealId());

        try {
            Meal originalMealModel = Datastore.query(meta).filter(mainFilter).asSingle();
            if (originalMealModel != null) {
                originalMealModel.setName(mealModel.getName());
                originalMealModel.setUnit(mealModel.getUnit());
                originalMealModel.setCalories(mealModel.getCalories());
                originalMealModel.setDefaultQuantity(mealModel.getDefaultQuantity());
                Transaction tx = Datastore.beginTransaction();
                Datastore.put(originalMealModel);
                tx.commit();
            } else {
                result = false;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * Method used to delete a meal.
     * @param MealModel - meal to delete.
     * @return Boolean - true, if meal is deleted; otherwise, false.
     */
    public boolean deleteMeal(Meal mealModel) {
        boolean result = true;
        MealMeta meta = new MealMeta();
        FilterCriterion mainFilter = meta.mealId.equal(mealModel.getMealId());

        try {
            Meal originalMealModel = Datastore.query(meta).filter(mainFilter).asSingle();
            if (originalMealModel != null) {
                Transaction tx = Datastore.beginTransaction();
                Datastore.delete(originalMealModel.getKey());
                tx.commit();
            } else {
                result = false;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
