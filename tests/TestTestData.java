import com.scottejames.downsman.data.service.ServiceManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTestData {


    @Test
    public void testContentsOfTestData(){

        ServiceManager sm = ServiceManager.getInstance();
        sm.createTestData();

        assertEquals(sm.getScoutService().getAll().size(),3,"Three scouts in test data");
        assertEquals(sm.getTeamService().getAll().size(),2, "Two teams in test data");
    }
}
