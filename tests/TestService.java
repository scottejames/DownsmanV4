import com.scottejames.downsman.data.model.Model;
import com.scottejames.downsman.data.model.ScoutModel;
import com.scottejames.downsman.data.model.TeamModel;
import com.scottejames.downsman.data.service.ScoutService;
import com.scottejames.downsman.data.service.Service;
import com.scottejames.downsman.data.service.ServiceManager;
import com.scottejames.downsman.data.service.TeamService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestService {

    @Test
    public void testAddToService(){
        Model m = new Model();
        assertEquals(m.getId(),0, "Initialised model has id of zero");
        Service s = new Service();
        s.add(m);
        assertNotEquals(m.getId(),0,"Model added to service should have id thats non zero");

        Model m1 = s.getById(m.getId());
        assertEquals(m, m1, " getting object back from service should match service");

    }

    @Test
    public void testRemovalFromService(){
        Service s = new Service();
        assertEquals(s.size(),0,"Start with nothing in the serivice");
        s.add(new Model());
        s.add(new Model());
        assertEquals(s.size(),2, "added two items should have two in service");
        Model m = s.getById(1);
        s.remove(m);
        assertEquals(s.size(), 1,"removed one from list of two - leaves one");
        s.removeById(2);
        assertEquals(s.size(),0,"Removed last entry by ID");
    }

    @Test
    public void testAddScoutWithName(){
        ScoutService s = new ScoutService();
        ScoutModel scott = new ScoutModel("Scott");
        ScoutModel andrew = new ScoutModel("Andrew");

        s.add(scott);
        s.add(andrew);

        assertEquals(s.size(),2,"Added two scouts to service");

        ScoutModel result = s.getById(scott.getId());
        assertEquals(result.getName(),"Scott");

    }

    @Test
    public void testServiceManager(){
        ServiceManager sm = ServiceManager.getInstance();
        ScoutService ss = sm.getScoutService();
        TeamService ts = sm.getTeamService();

        ss.add(new ScoutModel("Fred"));
        ts.add(new TeamModel());

        assertEquals(sm.getScoutService().size(), 1," added then pulled back by getInstance()");

    }
}

