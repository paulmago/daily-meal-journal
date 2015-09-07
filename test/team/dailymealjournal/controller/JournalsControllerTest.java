package team.dailymealjournal.controller;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class JournalsControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
<<<<<<< HEAD
        tester.start("/journal");
=======
        tester.start("/journals");
>>>>>>> migzisreallywewwhat/integrated
        JournalsController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
    }
}
