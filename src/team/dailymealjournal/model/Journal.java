package team.dailymealjournal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.json.Json;

@Model(schemaVersion = 1)
public class Journal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    @Json(ignore = true)
    private Key key;

    @Attribute(version = true)
    @Json(ignore = true)
    private Long version;
    
    private long journalId;
    private Date dateCreated = new Date();
    
    @Attribute(persistent = false)
    @Json(ignore = true)
    private InverseModelListRef<MealJournal, Journal> mealJournalListRef = 
        new InverseModelListRef<MealJournal, Journal>(MealJournal.class, "journalRef", this);
    
    @Attribute(lob = true)
    private List<MealJournal> mealJournals;
    
    public long getJournalId() {
        return journalId;
    }

    public void setJournalId(long journalId) {
        this.journalId = journalId;
    }
    
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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
        Journal other = (Journal) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }

    public InverseModelListRef<MealJournal, Journal> getMealJournalListRef() {
        return mealJournalListRef;
    }

    public List<MealJournal> getMealJournals() {
        return mealJournals;
    }

    public void setMealJournals(List<MealJournal> mealJournals) {
        this.mealJournals = mealJournals;
    }
}
