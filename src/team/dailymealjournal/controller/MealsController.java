/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.controller;

import java.util.List;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;
import org.slim3.repackaged.org.json.JSONObject;

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
 * [08/07/2015] 0.02 - Kim Agustin - Migration to slim3_1.0.16
 * [08/07/2015] 0.03 - Kim Agustin - Merged CRUD controllers into one
 */
public class MealsController extends Controller {
    
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
        response.setContentType("application/mealJson");
        
        MealDto dto = new MealDto();
        JSONObject mealJson = null;
        String json = "{}";
        
        if (isGet()) {
            if(null != requestScope("id")) {
                long id = asLong("id");
                Meal meal = service.getMeal(id);
                if (null != meal)
                    mealJson = new JSONObject(meta.modelToJson(meal));
                json = mealJson.toString();
            }
            else {
                JSONArray jsonArray = null;
                List<Meal> mealList = service.getMealList();
                if (null != mealList)
                    jsonArray = new JSONArray(meta.modelsToJson(mealList));
                json = jsonArray.toString();
            }
        } else if (isPost() || isPut() || isDelete()) {
            try {
                if (isDelete()) {
                    mealJson = new JSONObject();
                    
                    dto.setMealId(this.asLong("id"));
                    dto = service.deleteMeal(dto);
                } else {
                    mealJson = new JSONObject((String) this.requestScope("data"));
                    
                    dto.setName(mealJson.getString("name"));
                    dto.setDefaultQuantity(mealJson.getInt("defaultQuantity"));
                    dto.setCalories(mealJson.getInt("calories"));
                    dto.setUnit(mealJson.getString("unit"));
                    
                    if (isPut()) {
                        dto.setMealId(mealJson.getLong("mealId"));
                        dto = service.editMeal(dto);
                    } else
                        dto = this.service.addMeal(dto);
                }
            } catch (Exception e) {
                dto.getErrorList().add("Server controller error: " + e.getMessage());
                if (mealJson == null) {
                    mealJson = new JSONObject();
                    mealJson.put("errorList", dto.getErrorList());
                }
            }
            
            json = mealJson.toString();
        }
        response.getWriter().write(json);
        return null;
    }
}
