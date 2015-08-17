/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.service;

import java.util.ArrayList;
import java.util.List;

import team.dailymealjournal.dao.MealJournalDao;
import team.dailymealjournal.dto.JournalDto;
import team.dailymealjournal.dto.MealJournalDto;
import team.dailymealjournal.model.Journal;
import team.dailymealjournal.model.MealJournal;

/**
 * Service used to handle mealJournal transactions.
 * @author Kim Agustin
 * @version 0.01
 * Version History
 * [07/27/2015] 0.01 – Kim Agustin – Initial codes.
 */
public class MealJournalService {

    /**
     * The MealJournalService to use.
     * Holds the method for transacting with the datastore.
     */
    MealJournalDao dao = new MealJournalDao();
    
    /**
     * The JournalService to use.
     * Holds the method for adding a journal.
     */
    private JournalService journalService = new JournalService();

    /**
     * Method used to add a mealJournal.
     * @param input - mealJournal to add.
     * @return MealJournalDto - if transaction was unsuccessful, contains list of errors.
     */
    public MealJournalDto addMealJournal(MealJournalDto input) {
        MealJournal mealJournal = setModelValues(input);
        Journal currentJournal = journalService.getCurrentJournal();
        if(null == currentJournal) {
            journalService.addJournal(new JournalDto());
            currentJournal = journalService.getCurrentJournal();
        }

        if(!this.dao.addMealJournal(currentJournal, mealJournal)) {
            input.setErrorList(new ArrayList<String>());
            input.getErrorList().add("database error!");
        }

        return input;
    }

    /**
     * Method used to retrieve list of mealJournals.
     * @return List<MealJournal> - list of mealJournals.
     */
    public List<MealJournal> getMealJournalList() {
        return this.dao.getAllMealJournals();
    }
    
    /**
     * Method used to retrieve a MealJournal using its ID.
     * @param long mealJournalId
     * @return MealJournal.
     */
    public MealJournal getMealJournal(long mealJournalId) {
        return this.dao.getMealJournal(mealJournalId);
    }

    /**
     * Method used to update a mealJournal.
     * @param input - mealJournal to update.
     * @return MealJournalDto - if transaction was unsuccessful, contains list of errors.
     */
    public MealJournalDto editMealJournal(MealJournalDto input) {
        MealJournal mealJournal = setModelValues(input);

        if(!this.dao.editMealJournal(mealJournal)) {
            input.setErrorList(new ArrayList<String>());
            input.getErrorList().add("database error!");
        }

        return input;
    }

    /**
     * Method used to delete a mealJournal.
     * @param input - mealJournal to delete.
     * @return MealJournalDto - if transaction was unsuccessful, contains list of errors.
     */
    public MealJournalDto deleteMealJournal(MealJournalDto input) {
        MealJournal mealJournal = new MealJournal();
        mealJournal.setMealJournalId(input.getMealJournalId());

        if(!this.dao.deleteMealJournal(mealJournal)) {
            input.setErrorList(new ArrayList<String>());
            input.getErrorList().add("database error!");
        }
        
        Journal currentJournal = journalService.getCurrentJournal();
        if(null != currentJournal && currentJournal.getMealJournalListRef().getModelList().size() < 1)
        {
            JournalDto currentJournalDto = new JournalDto();
            currentJournalDto.setJournalId(currentJournal.getJournalId());
            journalService.deleteJournal(currentJournalDto);
        }
        return input;
    }
    
    private MealJournal setModelValues(MealJournalDto input) {
        MealJournal mealJournal = new MealJournal();
        mealJournal.setMealId(input.getMealId());
        mealJournal.setQuantity(input.getQuantity());
        return mealJournal;
    }

}