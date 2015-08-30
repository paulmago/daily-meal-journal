/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */
package team.dailymealjournal.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DTO used to store a single journal for
 * client side operations.
 * @author Kim Agustin
 * @version 0.01
 * Version History
 * [07/27/2015] 0.01 – Kim Agustin – Initial codes.
 */
public class JournalDto {

    /**
     * List of errors.
     */
    private List<String> errorList = new ArrayList<String>();
    
    /**
     * Journal ID.
     */
    private long journalId;
    
    /**
     * Date journal was created.
     */
    private Date dateCreated;
    
    /**
     * Retrieve errorList
     * @return the errorList
     */
    public List<String> getErrorList() {
        return errorList;
    }
    
    /**
     * Set errorList
     * @param errorList the errorList to set
     */
    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
    
    /**
     * Retrieve journalId
     * @return the journalId
     */
    public long getJournalId() {
        return journalId;
    }

    /**
     * Set journalId
     * @param journalId the journalId to set
     */
    public void setJournalId(long journalId) {
        this.journalId = journalId;
    }

    /**
     * Retrieve dateCreated
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Set dateCreated
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
