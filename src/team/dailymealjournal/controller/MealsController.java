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
<<<<<<< HEAD
=======
import team.dailymealjournal.validator.JSONValidators;
>>>>>>> migzisreallywewwhat/integrated

/**
 * Service used to handle meal transactions.
 * @author Kim Agustin
<<<<<<< HEAD
 * @version 0.01
 * Version History
 * [07/27/2015] 0.01 – Kim Agustin – Initial codes.
 * [08/07/2015] 0.02 - Kim Agustin - Migration to slim3_1.0.16
 * [08/07/2015] 0.03 - Kim Agustin - Merged CRUD controllers into one
=======
 * @version 0.05
 * Version History
 * [07/27/2015] 0.01 – Kim Agustin – Initial codes.
 * [08/07/2015] 0.02 – Kim Agustin – Migration to slim3_1.0.16.
 * [08/07/2015] 0.03 – Kim Agustin – Merged CRUD controllers into one.
 * [08/31/2015] 0.04 – Kim Agustin – Added validation support.
 * [08/31/2015] 0.05 – Kim Agustin – Restructured controller flow.
>>>>>>> migzisreallywewwhat/integrated
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
<<<<<<< HEAD

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
=======
    
    /**
     * The MealDto to use.
     * Wrapper for input data of model.
     * Also contains the errors list.
     */
    private MealDto dto = new MealDto();

    @Override
    public Navigation run() throws Exception {
        String json = "";
        
        if (isGet()) {
            json = performGet();
        } else if (isDelete()) {
            json = performDelete();
        } else if (isPost()) {
            json = performPost();
        } else if (isPut()) {
            json = performPut();
        }
        
        if (dto.getErrorList().size() > 0) {
            // if errors are found, replace whole JSON string to errorList
            json = new JSONObject().put("errorList", dto.getErrorList()).toString();
        }
        response.setContentType("application/json");
        response.getWriter().write(json);
        return null;
    }
    
    private String performGet() {
        String json = "";
        JSONValidators validators = new JSONValidators(this.request);
        
        try {
            if(null != requestScope("id")) {
                validators.add("id", validators.longType());
                if (validators.validate()) {
                    long id = asLong("id");
                    JSONObject mealJson = null;
                    Meal meal = service.getMeal(id);
                    if (null != meal)
                        mealJson = new JSONObject(meta.modelToJson(meal));
                    json = mealJson.toString();
                } else {
                    validators.addErrorsTo(dto.getErrorList());
                }
            } else {
>>>>>>> migzisreallywewwhat/integrated
                JSONArray jsonArray = null;
                List<Meal> mealList = service.getMealList();
                if (null != mealList)
                    jsonArray = new JSONArray(meta.modelsToJson(mealList));
                json = jsonArray.toString();
            }
<<<<<<< HEAD
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
=======
        } catch (Exception e) {
            dto.getErrorList().add("An unexpected error occured!");
        }
        
        return json;
    }
    
    private String performDelete() {
        String json = "";
        JSONValidators validators = new JSONValidators(this.request);
        
        try {
            validators.add("mealId", validators.required(), validators.longType());
            if (validators.validate()) {
                dto.setMealId(this.asLong("mealId"));
                dto = service.deleteMeal(dto);
            }
        } catch (Exception e) {
            dto.getErrorList().add("An unexpected error occured!");
        }
        
        validators.addErrorsTo(dto.getErrorList());
        return json;
    }
    
    private String performPost() {
        String json = "";
        JSONObject mealJson = null;
        JSONValidators validators = new JSONValidators(this.request);
        
        try {
            validators.add("data", validators.required("Request must be done with post data."));
            if (validators.validate()) {
                mealJson = new JSONObject((String) this.requestScope("data"));
                
                validators = new JSONValidators(mealJson);
                validators.add("name", validators.required());
                validators.add("unit", validators.required());
                validators.add("calories", validators.required(), validators.doubleType());
                validators.add("defaultQuantity", validators.required(), validators.integerType());

                if (validators.validate()) {
                    dto.setName(mealJson.getString("name"));
                    dto.setDefaultQuantity(mealJson.getInt("defaultQuantity"));
                    dto.setCalories(mealJson.getDouble("calories"));
                    dto.setUnit(mealJson.getString("unit"));
                    dto = this.service.addMeal(dto);
                }
            }
        } catch (Exception e) {
            dto.getErrorList().add("An unexpected error occured!");
        }
        
        validators.addErrorsTo(dto.getErrorList());
        return json;
    }
    
    private String performPut() {
        String json = "";
        JSONObject mealJson = null;
        JSONValidators validators = new JSONValidators(this.request);
        
        try {
            validators.add("data", validators.required("Request must be done with put data."));
            if (validators.validate()) {
                mealJson = new JSONObject((String) this.requestScope("data"));
                
                validators = new JSONValidators(mealJson);
                validators.add("name", validators.required());
                validators.add("unit", validators.required());
                validators.add("calories", validators.required(), validators.doubleType());
                validators.add("defaultQuantity", validators.required(), validators.integerType());
                validators.add("mealId", validators.required(), validators.longType());

                if (validators.validate()) {
                    dto.setName(mealJson.getString("name"));
                    dto.setDefaultQuantity(mealJson.getInt("defaultQuantity"));
                    dto.setCalories(mealJson.getDouble("calories"));
                    dto.setUnit(mealJson.getString("unit"));
                    dto.setMealId(mealJson.getLong("mealId"));
                    dto = this.service.editMeal(dto);
                }
            }
        } catch (Exception e) {
            dto.getErrorList().add("An unexpected error occured!");
        }
        
        validators.addErrorsTo(dto.getErrorList());
        return json;
>>>>>>> migzisreallywewwhat/integrated
    }
}
