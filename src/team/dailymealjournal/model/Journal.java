package team.dailymealjournal.model;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.json.Json;

/**
 * Model for journal date.
 * @author Kim Agustin
 * @version 0.02
 * Version History
 * [07/27/2015] 0.01 – Kim Agustin – Initial codes.
 * [08/31/2015] 0.02 – Kim Agustin – Added documentation.
 */
@Model(schemaVersion = 1)
public class Journal implements Serializable {

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
     * Journal ID - allocated by key.
     */
    private long journalId;
    
    /**
     * Journal date created - date when the journal was added.
     */
    private Date dateCreated;
    
    /**
     * MealJournal references - reference that indicate that Journal is the ancestor of MealJournal.
     */
    @Attribute(persistent = false)
    @Json(ignore = true)
    private InverseModelListRef<MealJournal, Journal> mealJournalListRef = 
        new InverseModelListRef<MealJournal, Journal>(MealJournal.class, "journalRef", this);
        
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
     * Returns the journal ID.
     * @return journalId
     */
    public long getJournalId() {
        return journalId;
    }

    /**
     * Sets the journal ID.
     * @param journalId
     */
    public void setJournalId(long journalId) {
        this.journalId = journalId;
    }
    
    /**
     * Returns the date the journal was created.
     * @return dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the date the journal was created.
     * @param dateCreated
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    /**
     * Returns the list of references to the journal's children.
     * @return mealJournalListRef
     */
    public InverseModelListRef<MealJournal, Journal> getMealJournalListRef() {
        return mealJournalListRef;
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
}
