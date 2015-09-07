/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito� - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.service;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

import team.dailymealjournal.dao.MealJournalDao;
import team.dailymealjournal.dto.JournalDto;
=======
import java.util.List;

import team.dailymealjournal.dao.MealJournalDao;
>>>>>>> migzisreallywewwhat/integrated
import team.dailymealjournal.dto.MealJournalDto;
import team.dailymealjournal.model.Journal;
import team.dailymealjournal.model.MealJournal;

/**
 * Service used to handle mealJournal transactions.
 * @author Kim Agustin
<<<<<<< HEAD
 * @version 0.01
 * Version History
 * [07/27/2015] 0.01 � Kim Agustin � Initial codes.
=======
 * @version 0.06
 * Version History
 * [07/27/2015] 0.01 � Kim Agustin � Initial codes.
 * [08/17/2015] 0.02 � Kim Agustin � Added support for cascading delete.
 * [08/17/2015] 0.03 � Kim Agustin � Fixed edit bug
 * [08/30/2015] 0.04 � Kim Agustin � Removed cascading delete because of semantic bug.
 * [08/30/2015] 0.05 � Kim Agustin � Changed adding journal and moved creating of new journal to DAO.
 * [08/31/2015] 0.06 � Kim Agustin � Changed message on DAO operation failure.
>>>>>>> migzisreallywewwhat/integrated
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
<<<<<<< HEAD
        if(null == currentJournal) {
            journalService.addJournal(new JournalDto());
            currentJournal = journalService.getCurrentJournal();
        }

        if(!this.dao.addMealJournal(currentJournal, mealJournal)) {
            input.setErrorList(new ArrayList<String>());
            input.getErrorList().add("database error!");
=======

        if(!this.dao.addMealJournal(currentJournal, mealJournal)) {
            input.getErrorList().add("An unexpected error occured!");
>>>>>>> migzisreallywewwhat/integrated
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
<<<<<<< HEAD

        if(!this.dao.editMealJournal(mealJournal)) {
            input.setErrorList(new ArrayList<String>());
            input.getErrorList().add("database error!");
=======
        mealJournal.setMealJournalId(input.getMealJournalId());

        if(!this.dao.editMealJournal(mealJournal)) {
            input.getErrorList().add("An unexpected error occured!");
>>>>>>> migzisreallywewwhat/integrated
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
<<<<<<< HEAD

        if(!this.dao.deleteMealJournal(mealJournal)) {
            input.setErrorList(new ArrayList<String>());
            input.getErrorList().add("database error!");
        }

        return input;
    }
    
=======
        
        if(!this.dao.deleteMealJournal(mealJournal)) {
            input.getErrorList().add("An unexpected error occured!");
        }
        
        return input;
    }
    
    /**
     * Method used to transfer values from DTO to model.
     * @param input - container of values from request.
     * @return MealJournal - model with all values from DTO.
     */
>>>>>>> migzisreallywewwhat/integrated
    private MealJournal setModelValues(MealJournalDto input) {
        MealJournal mealJournal = new MealJournal();
        mealJournal.setMealId(input.getMealId());
        mealJournal.setQuantity(input.getQuantity());
        return mealJournal;
    }

}