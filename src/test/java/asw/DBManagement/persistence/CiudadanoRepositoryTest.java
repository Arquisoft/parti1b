package asw.DBManagement.persistence;

import asw.Application;
import asw.DBManagement.model.CitizenDB;
import asw.DBManagement.repository.CitizenDBRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class CiudadanoRepositoryTest {

    @Autowired
    CitizenDBRepository ciudadanoRepository;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void findByEmail() throws Exception {
        CitizenDB ciudadano = ciudadanoRepository.findByMail("testtesttest");
        assertThat(ciudadano).isNull();
    }

}