/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.service;

import java.util.ArrayList;
import java.util.List;

import team.dailymealjournal.dao.JournalDao;
import team.dailymealjournal.dto.JournalDto;
import team.dailymealjournal.model.Journal;

/**
 * Service used to handle journal transactions.
 * @author Kim Agustin
 * @version 0.01
 * Version History
 * [07/28/2015] 0.01 – Kim Agustin – Initial codes.
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
            input.setErrorList(new ArrayList<String>());
            input.getErrorList().add("database error!");
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
        return this.dao.getJournal(new JournalDto().getDateCreated());
    }

    /**
     * Method used to update a journal.
     * @param input - journal to update.
     * @return JournalDto - if transaction was unsuccessful, contains list of errors.
     */
    public JournalDto editJournal(JournalDto input) {
        Journal journal = setModelValues(input);

        if(!this.dao.editJournal(journal)) {
            input.setErrorList(new ArrayList<String>());
            input.getErrorList().add("database error!");
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
            input.setErrorList(new ArrayList<String>());
            input.getErrorList().add("database error!");
        }

        return input;
    }
    
    private Journal setModelValues(JournalDto input) {
        Journal journal = new Journal();
        journal.setDateCreated(input.getDateCreated());
        return journal;
    }

}