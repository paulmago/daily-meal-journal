package team.dailymealjournal.controller.meal;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.BeanUtil;

import team.dailymealjournal.dto.MealDto;
import team.dailymealjournal.service.MealService;

/**
 * Service used to handle meal transactions.
 * @author Kim Agustin
 * @version 0.01
 * Version History
 * [07/27/2015] 0.01 – Kim Agustin – Initial codes.
 */
public class DeleteController extends Controller {

    /**
     * The MealService to use.
     * Holds the method for adding a meal.
     */
    private MealService service = new MealService();
    
    @Override
    public Navigation run() throws Exception {
        MealDto mealDto = new MealDto();
        BeanUtil.copy(this.request, mealDto);
        service.deleteMeal(mealDto);
        return null;
    }
}
