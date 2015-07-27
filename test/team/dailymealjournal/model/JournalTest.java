package team.dailymealjournal.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class JournalTest extends AppEngineTestCase {

    private Journal model = new Journal();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
