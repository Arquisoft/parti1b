package asw.DBManagement;

import asw.Application;
import asw.DBManagement.DBManagementParticipants.GetParticipant;
import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.repository.CitizenDBRepository;
import asw.participants.citizenInfo.ParticipantsLogin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class GetParticipantTest {

    @Autowired
    private CitizenDBRepository repository;

    @Autowired
    private GetParticipant getParticipant;

    private CitizenDB johnDoe;

    private ParticipantsLogin johnDoeLogin;


    @Before
    public void setUp() throws Exception {
        Date bornDate;

        try {
            bornDate = new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-01");
            johnDoe = new CitizenDB("John", "Doe", "john@doe.net", bornDate, "Phobos", "Martian", "123456789", "CIUDADANO");
            johnDoeLogin = new ParticipantsLogin(johnDoe.getMail(),johnDoe.getPassword());
            repository.save(johnDoe);

        } catch (ParseException e) {

            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getCiudadano() throws Exception {
        assertThat(johnDoe).isNotNull();
        assertThat(getParticipant.getCiudadano(johnDoe.getMail())).isNotNull();
    }

    @Test
    public void getCiudadano1() throws Exception {
        assertThat(johnDoe).isNotNull();
        assertThat(getParticipant.getCiudadano(johnDoeLogin)).isNotNull();
    }
}