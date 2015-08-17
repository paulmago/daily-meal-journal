package team.dailymealjournal.controller.journals;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import team.dailymealjournal.dto.MealJournalDto;
import team.dailymealjournal.service.MealJournalService;

public class DeleteController extends Controller {

    @Override
    public Navigation run() throws Exception {
        MealJournalDto dto = new MealJournalDto();
        dto.setMealJournalId(316);
        dto = new MealJournalService().deleteMealJournal(dto);
        return null;
    }
}
