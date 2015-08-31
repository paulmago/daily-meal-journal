package team.dailymealjournal.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.json.Json;

/**
 * Model for meal.
 * @author Kim Agustin
 * @version 0.03
 * Version History
 * [07/27/2015] 0.01 – Kim Agustin – Initial codes.
 * [08/31/2015] 0.02 – Kim Agustin – Changed calories from int to double.
 * [08/31/2015] 0.03 – Kim Agustin – Added documentation.
 */
@Model(schemaVersion = 1)
public class Meal implements Serializable {

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
     * Meal ID - allocated by key.
     */
    private long mealId;
    
    /**
     * Meal name - name of the meal.
     */
    private String name;
    
    /**
     * Meal unit - unit of the meal.
     * eg. piece, slice, serving, etc. 
     */
    private String unit;
    
    /**
     * Meal default quantity - default quantity of meal when adding a meal to the journal.
     */
    private int defaultQuantity;
    
    /**
     * Meal calories - amount of calories based on the default quantity.
     */
    private double calories;

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
     * Returns the meal name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the meal name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the meal unit.
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the meal unit.
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Returns the calories.
     * @return calories
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Sets the calories.
     * @param calories
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * Returns the default quantity.
     * @return defaultQuantity
     */
    public int getDefaultQuantity() {
        return defaultQuantity;
    }

    /**
     * Sets the default quantity.
     * @param defaultQuantity
     */
    public void setDefaultQuantity(int defaultQuantity) {
        this.defaultQuantity = defaultQuantity;
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
        Meal other = (Meal) obj;
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
