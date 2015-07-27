/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.controller.meal;

import java.util.Map;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;
import org.slim3.util.RequestMap;

import team.dailymealjournal.dto.MealDto;
import team.dailymealjournal.service.MealService;

/**
 * Service used to handle meal transactions.
 * @author Kim Agustin
 * @version 0.01
 * Version History
 * [07/27/2015] 0.01 – Kim Agustin – Initial codes.
 */
public class AddController extends Controller {
    
    /**
     * The MealService to use.
     * Holds the method for adding a meal.
     */
    private MealService service = new MealService();

    @Override
    public Navigation run() throws Exception {
        Map<String,Object> input = new RequestMap(this.request);
        MealDto mealDto = new MealDto();
        BeanUtil.copy(input, mealDto);
        service.addMeal(mealDto);
        return null;
    }
}
