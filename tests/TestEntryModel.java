import com.scottejames.downsman.data.exceptions.NonEntityModel;
import com.scottejames.downsman.data.model.ScoutModel;
import com.scottejames.downsman.data.model.TeamModel;
import com.scottejames.downsman.data.service.ServiceManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEntryModel {

    @Test
    public void addScoutToModel() throws NonEntityModel {
        ServiceManager sm = ServiceManager.getInstance();

        ScoutModel scott = new ScoutModel("scott","james","scottejames@gmail.com");
        TeamModel team = new TeamModel();

        sm.getTeamService().add(team);
        assertThrows(NonEntityModel.class,()->
            team.addTeamMember(scott),"Tried to add scout to team that is not in scout service exception shoudl be thrown");
        sm.getScoutService().add(scott);
        team.addTeamMember(scott);

        assertEquals(team.teamSize(),1,"Team should have one memeber added");





    }
}
