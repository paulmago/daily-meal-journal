/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.dao;

import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.FilterCriterion;
import org.slim3.memcache.Memcache;

import team.dailymealjournal.meta.MealMeta;
import team.dailymealjournal.model.Meal;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

/**
* Dao used to access the datastore for meal transactions.s
* @author Kim Agustin
* @version 0.02
* Version History
* [07/27/2015] 0.01 – Kim Agustin – Initial codes.
* [09/06/2015] 0.02 – Kim Agustin – Added memcache support.
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
            // Manually allocate key
            Key key = Datastore.allocateId("Meal");
            mealModel.setMealId(key.getId());
            
            // start transaction
            Transaction tx = Datastore.beginTransaction();
            Datastore.put(mealModel);
            tx.commit();
            
            // delete cache
            Memcache.delete("meals");
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * Method used to retrieve list of Meals.
     * @return List<Meal> - list of Meals.
     */
    @SuppressWarnings("unchecked")
    public List<Meal> getAllMeals() {
        List<Meal> meals = (List<Meal>) Memcache.get("meals");
        if (null == meals) {
            MealMeta meta = new MealMeta();
            meals = Datastore.query(meta).asList();
            Memcache.put("meals", meals);
        }
        return meals;
    }
    
    /**
     * Method used to retrieve a Meal using its ID.
     * @param long mealId
     * @return Meal.
     */
    public Meal getMeal(long mealId) {
        MealMeta meta = new MealMeta();
        FilterCriterion mainFilter = meta.mealId.equal(mealId);
        return Datastore.query(meta).filter(mainFilter).asSingle();
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
                
                // start transaction
                Transaction tx = Datastore.beginTransaction();
                Datastore.put(originalMealModel);
                tx.commit();
                
                // delete cache
                Memcache.delete("meals");
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
                // start transaction
                Transaction tx = Datastore.beginTransaction();
                Datastore.delete(originalMealModel.getKey());
                tx.commit();
                
                // delete cache
                Memcache.delete("meals");
            } else {
                result = false;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
