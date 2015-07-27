/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.controller.meal;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

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
    
    /**
     * The MealMeta to use.
     * Responsible for converting the models to JSON format.
     */
    private MealMeta meta = MealMeta.get();

    @Override
    public Navigation run() throws Exception {        
        List<Meal> mealList = service.getMealList();
        response.getWriter().write(meta.modelsToJson(mealList));
        return null;
    }
}
