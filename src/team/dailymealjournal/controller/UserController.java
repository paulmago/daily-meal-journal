package team.dailymealjournal.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class UserController extends Controller {

    @Override
    protected Navigation run() throws Exception {
<<<<<<< HEAD
        return redirect("/user/journals");
=======
        return forward("meal_journal.jsp");
>>>>>>> migzisreallywewwhat/integrated
    }

}
