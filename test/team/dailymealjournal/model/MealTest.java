package team.dailymealjournal.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MealTest extends AppEngineTestCase {

    private Meal model = new Meal();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
