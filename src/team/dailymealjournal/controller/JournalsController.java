/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONArray;
import org.slim3.repackaged.org.json.JSONObject;

import team.dailymealjournal.dto.MealJournalDto;
import team.dailymealjournal.model.Journal;
import team.dailymealjournal.model.Meal;
<<<<<<< HEAD
=======
import team.dailymealjournal.model.MealJournal;
>>>>>>> migzisreallywewwhat/integrated
import team.dailymealjournal.meta.MealJournalMeta;
import team.dailymealjournal.service.JournalService;
import team.dailymealjournal.service.MealJournalService;
import team.dailymealjournal.service.MealService;

/**
 * Service used to handle journal & meal journal transactions.
 * @author Kim Agustin
<<<<<<< HEAD
 * @version 0.01
 * Version History
 * [07/28/2015] 0.01 – Kim Agustin – Initial codes.
 * [08/07/2015] 0.02 – Kim Agustin – Refactored controller to handle meal journal transactions (GET)
 * [08/08/2015] 0.02 – Kim Agustin – Added POST, PUT, DELETE methods
=======
 * @version 0.04
 * Version History
 * [07/28/2015] 0.01 – Kim Agustin – Initial codes.
 * [08/07/2015] 0.02 – Kim Agustin – Refactored controller to handle meal journal transactions (GET)
 * [08/08/2015] 0.03 – Kim Agustin – Added POST, PUT, DELETE methods
 * [08/17/2015] 0.04 – Kim Agustin – Fixed GET by meal journal ID
>>>>>>> migzisreallywewwhat/integrated
 */
public class JournalsController extends Controller {
    
    /**
     * The JournalService to use.
     * Holds the method for adding a journal.
     */
    private JournalService journalService = new JournalService();
    
    /**
     * The MealJournalService to use.
     * Holds the method for adding a journal.
     */
    private MealJournalService mealJournalService = new MealJournalService();

    @Override
    public Navigation run() throws Exception {
        response.setContentType("application/json");
        
        String json = "{}";
        
        if (isGet()) {
            if(null != requestScope("id")) {
                long id = asLong("id");
<<<<<<< HEAD
                Journal journal = journalService.getJournal(id);
                if (null != journal)
                    json = journalToJson(journal).toString();
=======
                MealJournal mealJournal = mealJournalService.getMealJournal(id);
                if (null != mealJournal) {
                    JSONObject mealJournalJson = new JSONObject(MealJournalMeta.get().modelToJson(mealJournal));
                    populateJournalJsonWithMeals(mealJournalJson);
                    json = mealJournalJson.toString();
                }
>>>>>>> migzisreallywewwhat/integrated
            }
            else {
                List<Journal> journalList = journalService.getJournalList();
                if (null != journalList) {
                    json = journalsToJson(journalList).toString();
                }
            }
        }
        else if (isPost() || isPut() || isDelete()) {
            MealJournalDto dto = new MealJournalDto();
            JSONObject journalJson = null;
            try {
                if (isDelete()) {
                    journalJson = new JSONObject();
                    
                    dto.setMealJournalId(this.asLong("mealJournalId"));
                    dto = mealJournalService.deleteMealJournal(dto);
                } else {
                    journalJson = new JSONObject((String) this.requestScope("data"));
                    
                    dto.setMealId(journalJson.getLong("mealId"));
                    dto.setQuantity(journalJson.getInt("quantity"));
                    
                    if (isPut()) {
                        dto.setMealJournalId(journalJson.getLong("mealJournalId"));
                        dto = mealJournalService.editMealJournal(dto);
                    } else
                        dto = mealJournalService.addMealJournal(dto);
                }
            }
            catch (Exception e) {
                dto.getErrorList().add("Server controller error: " + e.getMessage());
                if (journalJson == null) {
                    journalJson = new JSONObject();
                    journalJson.put("errorList", dto.getErrorList());
                }
            }
            
            json = journalJson.toString();
        }
        response.getWriter().write(json);
        return null;
    }
    
    
    // JSON parser for GET, to be refactored
    private static JSONArray journalsToJson(List<Journal> journalList) throws Exception {
        Map<String, Object> m = new HashMap<String, Object>();
        JSONArray jsonList = new JSONArray();
        Calendar calendar = Calendar.getInstance();
        for(Journal journal : journalList) {
            calendar.setTime(journal.getDateCreated());
            m.put("dateCreated", calendar.getTimeInMillis());
            m.put("journals", new JSONArray(MealJournalMeta.get().modelsToJson(journal.getMealJournalListRef().getModelList())));
            jsonList.put(m);
        }
        populateJournalsJson(jsonList);
        return jsonList;
    }
    
<<<<<<< HEAD
    private static JSONObject journalToJson(Journal journal) throws Exception {
        JSONObject json = new JSONObject();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(journal.getDateCreated());
        json.put("dateCreated", calendar.getTimeInMillis());
        json.put("journals", new JSONArray(MealJournalMeta.get().modelsToJson(journal.getMealJournalListRef().getModelList())));
        populateJournalJsonWithMeals(json);
        return json;
    }
    
    private static boolean populateJournalsJson(JSONArray jsonList) throws Exception{
=======
    private static boolean populateJournalsJson(JSONArray jsonList) throws Exception {
>>>>>>> migzisreallywewwhat/integrated
        boolean succesful = false;
        for (int i = 0, count = jsonList.length(); i < count; i++) {
            JSONArray journals = jsonList.getJSONObject(i).getJSONArray("journals");
            for (int j = 0, jCount = journals.length(); j < jCount; j++) {
                populateJournalJsonWithMeals(journals.getJSONObject(j));
            }
        }
        return succesful;
    }
    
<<<<<<< HEAD
    private static boolean populateJournalJsonWithMeals(JSONObject journal) throws Exception{
=======
    private static boolean populateJournalJsonWithMeals(JSONObject journal) throws Exception {
>>>>>>> migzisreallywewwhat/integrated
        boolean succesful = false;
        MealService mealService = new MealService();
        Meal meal = mealService.getMeal(journal.getLong("mealId"));
        journal.put("name", meal.getName());
        journal.put("unit", meal.getUnit());
        journal.put("calories", meal.getCalories());
        succesful = true;
        return succesful;
    }
}
