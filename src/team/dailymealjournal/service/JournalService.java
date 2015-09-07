/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.service;

<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.util.Calendar;
import java.util.Date;
>>>>>>> migzisreallywewwhat/integrated
import java.util.List;

import team.dailymealjournal.dao.JournalDao;
import team.dailymealjournal.dto.JournalDto;
import team.dailymealjournal.model.Journal;

/**
 * Service used to handle journal transactions.
 * @author Kim Agustin
<<<<<<< HEAD
 * @version 0.01
 * Version History
 * [07/28/2015] 0.01 – Kim Agustin – Initial codes.
=======
 * @version 0.03
 * Version History
 * [07/28/2015] 0.01 – Kim Agustin – Initial codes.
 * [08/30/2015] 0.02 – Kim Agustin – Added static method to get current date.
 * [08/31/2015] 0.03 – Kim Agustin – Changed message on DAO operation failure.
>>>>>>> migzisreallywewwhat/integrated
 */
public class JournalService {

    /**
     * The JournalService to use.
     * Holds the method for transacting with the datastore.
     */
    JournalDao dao = new JournalDao();

    /**
     * Method used to add a journal.
     * @param input - journal to add.
     * @return JournalDto - if transaction was unsuccessful, contains list of errors.
     */
    public JournalDto addJournal(JournalDto input) {
        Journal journal = setModelValues(input);

        if(!this.dao.addJournal(journal)) {
<<<<<<< HEAD
            input.setErrorList(new ArrayList<String>());
            input.getErrorList().add("database error!");
=======
            input.getErrorList().add("An unexpected error occured!");
>>>>>>> migzisreallywewwhat/integrated
        }

        return input;
    }

    /**
     * Method used to retrieve list of journals.
     * @return List<Journal> - list of journals.
     */
    public List<Journal> getJournalList() {
        return this.dao.getAllJournals();
    }
    
    /**
     * Method used to retrieve a Journal using its ID.
     * @param long journalId
     * @return Journal.
     */
    public Journal getJournal(long journalId) {
        return this.dao.getJournal(journalId);
    }
    
    /**
     * Method used to retrieve a today's Journal.
     * @param long journalId
     * @return Journal.
     */
    public Journal getCurrentJournal() {
<<<<<<< HEAD
        return this.dao.getJournal(new JournalDto().getDateCreated());
=======
        return this.dao.getJournal(getCurrentDate());
>>>>>>> migzisreallywewwhat/integrated
    }

    /**
     * Method used to update a journal.
     * @param input - journal to update.
     * @return JournalDto - if transaction was unsuccessful, contains list of errors.
     */
    public JournalDto editJournal(JournalDto input) {
        Journal journal = setModelValues(input);

        if(!this.dao.editJournal(journal)) {
<<<<<<< HEAD
            input.setErrorList(new ArrayList<String>());
            input.getErrorList().add("database error!");
=======
            input.getErrorList().add("An unexpected error occured!");
>>>>>>> migzisreallywewwhat/integrated
        }

        return input;
    }

    /**
     * Method used to delete a journal.
     * @param input - journal to delete.
     * @return JournalDto - if transaction was unsuccessful, contains list of errors.
     */
    public JournalDto deleteJournal(JournalDto input) {
        Journal journal = new Journal();
        journal.setJournalId(input.getJournalId());

        if(!this.dao.deleteJournal(journal)) {
<<<<<<< HEAD
            input.setErrorList(new ArrayList<String>());
            input.getErrorList().add("database error!");
=======
            input.getErrorList().add("An unexpected error occured!");
>>>>>>> migzisreallywewwhat/integrated
        }

        return input;
    }
    
<<<<<<< HEAD
=======
    /**
     * Method used to transfer values from DTO to model.
     * @param input - container of values from request.
     * @return Journal - model with all values from DTO.
     */
>>>>>>> migzisreallywewwhat/integrated
    private Journal setModelValues(JournalDto input) {
        Journal journal = new Journal();
        journal.setDateCreated(input.getDateCreated());
        return journal;
    }
<<<<<<< HEAD
=======
    
    /**
     * Method used to get current date.
     * @return Date - current date.
     */
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
>>>>>>> migzisreallywewwhat/integrated

}