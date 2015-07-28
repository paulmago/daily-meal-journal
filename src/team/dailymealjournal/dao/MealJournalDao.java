/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.dao;

import java.util.List;

import org.slim3.datastore.Datastore;
import org.slim3.datastore.FilterCriterion;

import team.dailymealjournal.meta.MealJournalMeta;
import team.dailymealjournal.model.MealJournal;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

/**
* Dao used to access the datastore for mealJournal transactions.s
* @author Kim Agustin
* @version 0.01
* Version History
* [07/28/2015] 0.01 – Kim Agustin – Initial codes.
*/
public class MealJournalDao {

    /**
     * Method used to save a mealJournal.
     * @param MealJournalModel - MealJournal to be saved.
     * @return Boolean - true, if mealJournal is saved; otherwise, false.
     */
    public boolean addMealJournal(MealJournal mealJournalModel) {
        boolean result = true;
        try {
            Transaction tx = Datastore.beginTransaction();
            //Manually allocate key
            Key key = Datastore.allocateId(KeyFactory.createKey("Account", "Default"), "MealJournal");
            mealJournalModel.setKey(key);
            mealJournalModel.setMealJournalId(key.getId());
            Datastore.put(mealJournalModel);
            tx.commit();
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * Method used to retrieve list of MealJournals.
     * @return List<MealJournal> - list of MealJournals.
     */
    public List<MealJournal> getAllMealJournals() {
        MealJournalMeta meta = new MealJournalMeta();
        Key parentKey = KeyFactory.createKey("Account", "Default");
        return Datastore.query(meta ,parentKey).asList();
    }
    
    /**
     * Method used to retrieve a MealJournal using its ID.
     * @param long mealJournalId
     * @return MealJournal.
     */
    public MealJournal getMealJournal(long mealJournalId) {
        MealJournalMeta meta = new MealJournalMeta();
        Key parentKey = KeyFactory.createKey("Account", "Default");
        FilterCriterion mainFilter = meta.mealJournalId.equal(mealJournalId);
        return Datastore.query(meta ,parentKey).filter(mainFilter).asSingle();
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
                Transaction tx = Datastore.beginTransaction();
                Datastore.put(originalMealJournalModel);
                tx.commit();
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
                Transaction tx = Datastore.beginTransaction();
                Datastore.delete(originalMealJournalModel.getKey());
                tx.commit();
            } else {
                result = false;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
