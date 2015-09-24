/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.dao;

import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.FilterCriterion;
import org.slim3.memcache.Memcache;

import team.dailymealjournal.meta.MealJournalMeta;
import team.dailymealjournal.model.Journal;
import team.dailymealjournal.model.MealJournal;
import team.dailymealjournal.service.JournalService;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

/**
* Dao used to access the datastore for mealJournal transactions.s
* @author Kim Agustin
* @version 0.04
* Version History
* [07/28/2015] 0.01 – Kim Agustin – Initial codes.
* [08/30/2015] 0.02 – Kim Agustin – Updated addMealJournal algorithm.
* [08/30/2015] 0.03 – Kim Agustin – Changed deleteMealJournal into cascading.
* [09/06/2015] 0.04 – Kim Agustin – Added memcache support.
*/
public class MealJournalDao {

    /**
     * Method used to save a mealJournal.
     * @param JournalModel - Today's date, if already in datastore.
     * @param MealJournalModel - MealJournal to be saved.
     * @return Boolean - true, if mealJournal is saved; otherwise, false.
     */
    public boolean addMealJournal(Journal journalModel, MealJournal mealJournalModel) {
        boolean result = true;
        try {
            if (null == journalModel) {
                // if first entry for the day, create new journal
                Key parentKey = Datastore.allocateId(Journal.class);
                journalModel = new Journal();
                journalModel.setKey(parentKey);
                journalModel.setJournalId(parentKey.getId());
                journalModel.setDateCreated(JournalService.getCurrentDate());
            }
            
            // Manually allocate key
            Key key = Datastore.allocateId(journalModel.getKey(), MealJournal.class);
            mealJournalModel.setKey(key);
            mealJournalModel.setMealJournalId(key.getId());
            mealJournalModel.getJournalRef().setModel(journalModel);
            
            // start transaction
            Transaction tx = Datastore.beginTransaction();
            Datastore.put(journalModel, mealJournalModel);
            tx.commit();

            // delete cache
            Memcache.delete("mealJournals");
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * Method used to retrieve list of MealJournals.
     * @return List<MealJournal> - list of MealJournals.
     */
    @SuppressWarnings("unchecked")
    public List<MealJournal> getAllMealJournals() {
        List<MealJournal> mealJournals = (List<MealJournal>) Memcache.get("mealJournals");
        if (null == mealJournals) {
            MealJournalMeta meta = new MealJournalMeta();
            mealJournals = Datastore.query(meta).asList();
            Memcache.put("mealJournals", mealJournals);
        }
        return mealJournals;
    }
    
    /**
     * Method used to retrieve a MealJournal using its ID.
     * @param long mealJournalId
     * @return MealJournal.
     */
    public MealJournal getMealJournal(long mealJournalId) {
        MealJournalMeta meta = new MealJournalMeta();
        FilterCriterion mainFilter = meta.mealJournalId.equal(mealJournalId);
        return Datastore.query(meta).filter(mainFilter).asSingle();
    }

    /**
     * Method used to edit a mealJournal.
     * @param MealJournalModel - MealJournal to save.
     * @return Boolean - true, if mealJournal is saved; otherwise, false.
     */
    public boolean editMealJournal(MealJournal mealJournalModel) {
        boolean result = true;
        MealJournalMeta meta = new MealJournalMeta();
        FilterCriterion mainFilter = meta.mealJournalId.equal(mealJournalModel.getMealJournalId());

        try {
            MealJournal originalMealJournalModel = Datastore.query(meta).filter(mainFilter).asSingle();
            if (originalMealJournalModel != null) {
                originalMealJournalModel.setMealId(mealJournalModel.getMealId());
                originalMealJournalModel.setQuantity(mealJournalModel.getQuantity());
                
                // start transaction
                Transaction tx = Datastore.beginTransaction();
                Datastore.put(originalMealJournalModel);
                tx.commit();
                
                // delete cache
                Memcache.delete("mealJournals");
            } else {
                result = false;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * Method used to delete a mealJournal.
     * @param MealJournalModel - mealJournal to delete.
     * @return Boolean - true, if mealJournal is deleted; otherwise, false.
     */
    public boolean deleteMealJournal(MealJournal mealJournalModel) {
        boolean result = true;
        MealJournalMeta meta = new MealJournalMeta();
        FilterCriterion mainFilter = meta.mealJournalId.equal(mealJournalModel.getMealJournalId());

        try {
            MealJournal originalMealJournalModel = Datastore.query(meta).filter(mainFilter).asSingle();
                        
            if (originalMealJournalModel != null) {
                // find out if parent should be deleted or not
                boolean deleteAll = true;
                Journal journal = new JournalService().getJournal(originalMealJournalModel.getKey().getParent().getId());
                if (journal.getMealJournalListRef().getModelList().size() > 1) {
                    deleteAll = false;
                }
                
                // start transaction
                Transaction tx = Datastore.beginTransaction();
                if (deleteAll)
                    Datastore.deleteAll(originalMealJournalModel.getKey().getParent());
                else
                    Datastore.delete(originalMealJournalModel.getKey());
                tx.commit();
                
                // delete cache
                Memcache.delete("mealJournals");
            } else {
                result = false;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
