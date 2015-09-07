package team.dailymealjournal.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.json.Json;

<<<<<<< HEAD
@Model(schemaVersion = 1)
public class Meal implements Serializable {

    private static final long serialVersionUID = 1L;

=======
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
>>>>>>> migzisreallywewwhat/integrated
    @Attribute(primaryKey = true)
    @Json(ignore = true)
    private Key key;

<<<<<<< HEAD
=======
    /**
     * Version
     */
>>>>>>> migzisreallywewwhat/integrated
    @Attribute(version = true)
    @Json(ignore = true)
    private Long version;
    
<<<<<<< HEAD
    private long mealId;
    private String name;
    private String unit;
    private int calories;
    private int defaultQuantity;

    /**
     * Returns the key.
     *
     * @return the key
=======
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
>>>>>>> migzisreallywewwhat/integrated
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
<<<<<<< HEAD
     *
     * @param key
     *            the key
=======
     * @param key
>>>>>>> migzisreallywewwhat/integrated
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Returns the version.
<<<<<<< HEAD
     *
     * @return the version
=======
     * @return version
>>>>>>> migzisreallywewwhat/integrated
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
<<<<<<< HEAD
     *
     * @param version
     *            the version
=======
     * @param version
>>>>>>> migzisreallywewwhat/integrated
     */
    public void setVersion(Long version) {
        this.version = version;
    }

<<<<<<< HEAD
=======
    /**
     * Returns the meal ID.
     * @return mealId
     */
>>>>>>> migzisreallywewwhat/integrated
    public long getMealId() {
        return mealId;
    }

<<<<<<< HEAD
=======
    /**
     * Sets the meal ID.
     * @param mealId
     */
>>>>>>> migzisreallywewwhat/integrated
    public void setMealId(long mealId) {
        this.mealId = mealId;
    }
    
<<<<<<< HEAD
=======
    /**
     * Returns the meal name.
     * @return name
     */
>>>>>>> migzisreallywewwhat/integrated
    public String getName() {
        return name;
    }

<<<<<<< HEAD
=======
    /**
     * Sets the meal name.
     * @param name
     */
>>>>>>> migzisreallywewwhat/integrated
    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
=======
    /**
     * Returns the meal unit.
     * @return unit
     */
>>>>>>> migzisreallywewwhat/integrated
    public String getUnit() {
        return unit;
    }

<<<<<<< HEAD
=======
    /**
     * Sets the meal unit.
     * @param unit
     */
>>>>>>> migzisreallywewwhat/integrated
    public void setUnit(String unit) {
        this.unit = unit;
    }

<<<<<<< HEAD
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

=======
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
>>>>>>> migzisreallywewwhat/integrated
    public int getDefaultQuantity() {
        return defaultQuantity;
    }

<<<<<<< HEAD
=======
    /**
     * Sets the default quantity.
     * @param defaultQuantity
     */
>>>>>>> migzisreallywewwhat/integrated
    public void setDefaultQuantity(int defaultQuantity) {
        this.defaultQuantity = defaultQuantity;
    }

<<<<<<< HEAD
=======
    /**
     * Auto-generated function
     */
>>>>>>> migzisreallywewwhat/integrated
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

<<<<<<< HEAD
=======
    /**
     * Auto-generated function
     */
>>>>>>> migzisreallywewwhat/integrated
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
