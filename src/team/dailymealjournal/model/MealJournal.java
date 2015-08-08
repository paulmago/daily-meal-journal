package team.dailymealjournal.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;
import org.slim3.datastore.json.Json;

@Model(schemaVersion = 1)
public class MealJournal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    @Json(ignore = true)
    private Key key;

    @Attribute(version = true)
    @Json(ignore = true)
    private Long version;
    
    private long mealJournalId;
    private long mealId;
    private int quantity;
    
    @Attribute(persistent = true)
    private ModelRef<Journal> journalRef = new ModelRef<Journal>(Journal.class);
 
    public long getMealJournalId() {
        return mealJournalId;
    }

    public void setMealJournalId(long mealJournalId) {
        this.mealJournalId = mealJournalId;
    }

    public long getMealId() {
        return mealId;
    }

    public void setMealId(long mealId) {
        this.mealId = mealId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the key.
     *
     * @return the key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key
     *            the key
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version
     *            the version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

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

    public ModelRef<Journal> getJournalRef() {
        return journalRef;
    }
}
