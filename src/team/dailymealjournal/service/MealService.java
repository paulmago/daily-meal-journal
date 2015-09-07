/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.service;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> migzisreallywewwhat/integrated
import java.util.List;

import team.dailymealjournal.dao.MealDao;
import team.dailymealjournal.dto.MealDto;
import team.dailymealjournal.model.Meal;

/**
 * Service used to handle meal transactions.
 * @author Kim Agustin
<<<<<<< HEAD
 * @version 0.01
 * Version History
 * [07/27/2015] 0.01 – Kim Agustin – Initial codes.
 * [08/07/2015] 0.01 – Kim Agustin – Fixed edit service.
=======
 * @version 0.03
 * Version History
 * [07/27/2015] 0.01 – Kim Agustin – Initial codes.
 * [08/07/2015] 0.02 – Kim Agustin – Fixed edit service.
 * [08/31/2015] 0.03 – Kim Agustin – Changed message on DAO operation failure.
>>>>>>> migzisreallywewwhat/integrated
 */
public class MealService {

    /**
     * The MealService to use.
     * Holds the method for transacting with the datastore.
     */
    MealDao dao = new MealDao();

    /**
     * Method used to add a meal.
     * @param input - meal to add.
     * @return MealDto - if transaction was unsuccessful, contains list of errors.
     */
    public MealDto addMeal(MealDto input) {
        Meal meal = setModelValues(input);

        if(!this.dao.addMeal(meal)) {
<<<<<<< HEAD
            input.setErrorList(new ArrayList<String>());
            input.getErrorList().add("database error!");
=======
            input.getErrorList().add("An unexpected error occured!");
>>>>>>> migzisreallywewwhat/integrated
        }

        return input;
    }

    /**
     * Method used to retrieve list of meals.
     * @return List<Meal> - list of meals.
     */
    public List<Meal> getMealList() {
        return this.dao.getAllMeals();
    }
    
    /**
     * Method used to retrieve a Meal using its ID.
     * @param long mealId
     * @return Meal.
     */
    public Meal getMeal(long mealId) {
        return this.dao.getMeal(mealId);
    }

    /**
     * Method used to update a meal.
     * @param input - meal to update.
     * @return MealDto - if transaction was unsuccessful, contains list of errors.
     */
    public MealDto editMeal(MealDto input) {
        Meal meal = setModelValues(input);
        meal.setMealId(input.getMealId());

        if(!this.dao.editMeal(meal)) {
<<<<<<< HEAD
            input.setErrorList(new ArrayList<String>());
            input.getErrorList().add("database error!");
=======
            input.getErrorList().add("An unexpected error occured!");
>>>>>>> migzisreallywewwhat/integrated
        }

        return input;
    }

    /**
     * Method used to delete a meal.
     * @param input - meal to delete.
     * @return MealDto - if transaction was unsuccessful, contains list of errors.
     */
    public MealDto deleteMeal(MealDto input) {
        Meal meal = new Meal();
        meal.setMealId(input.getMealId());

        if(!this.dao.deleteMeal(meal)) {
<<<<<<< HEAD
            input.setErrorList(new ArrayList<String>());
            input.getErrorList().add("database error!");
=======
            input.getErrorList().add("An unexpected error occured!");
>>>>>>> migzisreallywewwhat/integrated
        }

        return input;
    }
    
<<<<<<< HEAD
=======
    /**
     * Method used to transfer values from DTO to model.
     * @param input - container of values from request.
     * @return Meal - model with all values from DTO.
     */
>>>>>>> migzisreallywewwhat/integrated
    private Meal setModelValues(MealDto input) {
        Meal meal = new Meal();
        meal.setName(input.getName());
        meal.setUnit(input.getUnit());
        meal.setCalories(input.getCalories());
        meal.setDefaultQuantity(input.getDefaultQuantity());
        return meal;
    }

}