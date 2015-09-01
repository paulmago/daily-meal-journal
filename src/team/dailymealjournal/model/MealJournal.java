package team.dailymealjournal.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.json.Json;

/**
 * Model for individual meal journal.
 * @author Kim Agustin
 * @version 0.03
 * Version History
 * [07/27/2015] 0.01 – Kim Agustin – Initial codes.
 * [08/31/2015] 0.02 – Kim Agustin – Added documentation.
 * [09/01/2015] 0.03 – Kim Agustin – Added JSON ignore to journal reference.
 */
@Model(schemaVersion = 1)
public class MealJournal implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Datastore Key
     */
    @Attribute(primaryKey = true)
    @Json(ignore = true)
    private Key key;

    /**
     * Version
     */
    @Attribute(version = true)
    @Json(ignore = true)
    private Long version;
    
    /**
     * Meal journal ID - allocated by key.
     */
    private long mealJournalId;
    
    /**
     * Meal ID - ID reference of meal selected.
     */
    private long mealId;
    
    /**
     * Meal journal quantity - the quantity the user is intending to eat.
     */
    private int quantity;
    
    /**
     * Journal reference - reference to parent entity.
     */
    @Attribute(persistent = true)
    @Json(ignore = true)
    private ModelRef<Journal> journalRef = new ModelRef<Journal>(Journal.class);
 
    /**
     * Returns the key.
     * @return key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     * @param key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     * @return version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     * @param version
     */
    public void setVersion(Long version) {
        this.version = version;
    }
    
    /**
     * Returns the meal journal ID.
     * @return mealJournalId
     */
    public long getMealJournalId() {
        return mealJournalId;
    }

    /**
     * Sets the meal journal ID.
     * @param mealJournalId
     */
    public void setMealJournalId(long mealJournalId) {
        this.mealJournalId = mealJournalId;
    }

    /**
     * Returns the meal ID.
     * @return mealId
     */
    public long getMealId() {
        return mealId;
    }

    /**
     * Sets the meal ID.
     * @param mealId
     */
    public void setMealId(long mealId) {
        this.mealId = mealId;
    }

    /**
     * Returns the quantity of meal.
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of meal.
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Returns the reference to parent.
     * @return journalRef
     */
    public ModelRef<Journal> getJournalRef() {
        return journalRef;
    }

    /**
     * Auto-generated function
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    /**
     * Auto-generated function
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MealJournal other = (MealJournal) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }
}
