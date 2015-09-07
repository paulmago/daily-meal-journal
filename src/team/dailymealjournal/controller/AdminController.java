package team.dailymealjournal.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class AdminController extends Controller {

    @Override
    protected Navigation run() throws Exception {
<<<<<<< HEAD
        return redirect("/admin/meals");
=======
        return forward("meal_management.jsp");
>>>>>>> migzisreallywewwhat/integrated
    }
    
}
