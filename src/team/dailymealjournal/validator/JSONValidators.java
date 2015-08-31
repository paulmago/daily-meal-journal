package team.dailymealjournal.validator;

import java.util.ArrayList;
import java.util.List;

import org.slim3.controller.validator.Errors;
import org.slim3.controller.validator.Validator;
import org.slim3.controller.validator.Validators;
import org.slim3.repackaged.org.json.JSONException;
import org.slim3.repackaged.org.json.JSONObject;

import team.dailymealjournal.util.Util;

/**
 * Validators that accepts JSONObject instead of a Map or HttpServletRequest.
 * @author Kim Agustin
 * @version 0.01
 * Version History
 * [08/31/2015] 0.01 – Kim Agustin – Initial codes.
 */
public class JSONValidators extends Validators {
    
    /**
     * The List<String> to use.
     * Holds all arguments added to validate inputs.
     */
    private List<String> argList = new ArrayList<>();
    
    /**
     * Constructor that accepts JSONObject.
     * Calls the super(Map<String, Object>) constructor to assign values to parent field 
     * @param JSONObject parameters - inputs to be validated.
     */
    public JSONValidators(JSONObject parameters) throws NullPointerException, JSONException {
        super(Util.jsonToMap(parameters));
    }
    
    /**
     * Overridden method to add argument to argList.
     * @param CharSequence name - argument to be validated.
     * @param Validator... validators - list of validation conditions
     * @return Validators - returns itself from the super.add() method
     */
    @Override
    public Validators add(CharSequence name, Validator... validators) throws NullPointerException {
        argList.add(name.toString());
        return super.add(name, validators);
    }
    
    /**
     * Method.that places all messages of invalid inputs into a list
     * @param List<String> errorList - reference to DTO errorList.
     */
    public void addErrorsTo(List<String> errorList) {
        Errors errors = this.getErrors();
        for (String arg : argList) {
            String error = errors.get(arg);
            if (null != error) {
                errorList.add(error);
            }
        }
    }
}
