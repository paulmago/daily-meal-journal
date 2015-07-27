/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.controller.meal;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import team.dailymealjournal.dto.MealDto;
import team.dailymealjournal.model.Meal;
import team.dailymealjournal.meta.MealMeta;
import team.dailymealjournal.service.MealService;

/**
 * Service used to handle meal transactions.
 * @author Kim Agustin
 * @version 0.01
 * Version History
 * [07/27/2015] 0.01 – Kim Agustin – Initial codes.
 */
public class AllController extends Controller {
    
    /**
     * The MealService to use.
     * Holds the method for adding a meal.
     */
    private MealService service = new MealService();
    private MealMeta meta = MealMeta.get();

    @Override
    public Navigation run() throws Exception {
        MealDto meal = new MealDto();
        
        meal.setName("Shrimp");
        meal.setUnit("pieces");
        meal.setCalories(240);
        meal.setDefaultQuantity(6);
        service.addMeal(meal);
        
        meal.setName("Egg");
        meal.setUnit("pieces");
        meal.setCalories(300);
        meal.setDefaultQuantity(1);
        service.addMeal(meal);
        
        List<Meal> mealList = service.getMealList();
        response.getWriter().write(meta.modelsToJson(mealList));
        return null;
    }
}
