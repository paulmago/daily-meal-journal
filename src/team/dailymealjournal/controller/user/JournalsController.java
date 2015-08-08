package team.dailymealjournal.controller.user;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class JournalsController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("journals.html");
    }
}
