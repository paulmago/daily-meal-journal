package team.dailymealjournal.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MealJournalTest extends AppEngineTestCase {

    private MealJournal model = new MealJournal();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
