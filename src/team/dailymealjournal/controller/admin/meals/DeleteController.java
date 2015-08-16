/* ------------------------------------------------------------------------------
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Copyright (C) Miguelito™ - All Rights Reserved 2015
 * --------------------------------------------------------------------------- */

/**
 * Controller for deleting meals.
 * @author Andrew Paul Mago
 * @version 0.01
 * Version History
 * [08/15/2015] 0.01 – Andrew Paul Mago – initial codes.
 */

package team.dailymealjournal.controller.admin.meals;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class DeleteController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("/admin/meal_detail.html");
    }
}
