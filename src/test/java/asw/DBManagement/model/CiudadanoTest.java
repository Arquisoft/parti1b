package asw.DBManagement.model;

import asw.Application;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class CiudadanoTest {

    private CitizenDB johnDoe;

    private String nombre;
    private String apellidos;
    private String email;
    private Date fechaNacimiento;
    private String residencia;
    private String nacionalidad;
    private String dni;
    private String password;


    @Before
    public void setUp() throws Exception {
        Date bornDate = null;
        nombre = "John";
        apellidos = "Doe";
        email = "john@doe.net";
        try {
            bornDate = new SimpleDateFormat("yyyy-MM-dd").parse("1970-01-01");
            fechaNacimiento = bornDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        residencia = "Phobos";
        nacionalidad = "Martian";
        dni = "12345678X";
        password = "password";

        johnDoe = new CitizenDB(nombre, apellidos, email, bornDate, residencia, nacionalidad, dni, "ADMIN");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getNombre() throws Exception {
        assertThat(johnDoe.getName()).isEqualTo(nombre);
    }

    @Test
    public void setNombre() throws Exception {
        johnDoe.setName("nombre");
        assertThat(johnDoe.getName()).isEqualTo("nombre");
    }

    @Test
    public void getApellidos() throws Exception {
        assertThat(johnDoe.getSurname()).isEqualTo(apellidos);
    }

    @Test
    public void setApellidos() throws Exception {
        johnDoe.setSurname("apellidos");
        assertThat(johnDoe.getSurname()).isEqualTo("apellidos");
    }

    @Test
    public void getEmail() throws Exception {
        assertThat(johnDoe.getMail()).isEqualTo(email);
    }

    @Test
    public void setEmail() throws Exception {
        johnDoe.setMail("email");
        assertThat(johnDoe.getMail()).isEqualTo("email");
    }

    @Test
    public void getFechaNacimiento() throws Exception {
        assertThat(johnDoe.getBirthday()).isEqualTo(fechaNacimiento);
    }

    @Test
    public void setFechaNacimiento() throws Exception {
        Date newBornDate;
        try {
            newBornDate = new SimpleDateFormat("yyyy-MM-dd").parse("1942-01-01");
            johnDoe.setBirthday(newBornDate);
            assertThat(johnDoe.getBirthday()).isEqualTo(newBornDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getResidencia() throws Exception {
        assertThat(johnDoe.getAddress()).isEqualTo(residencia);
    }

    @Test
    public void setResidencia() throws Exception {
        johnDoe.setAddress("Deimos");
        assertThat(johnDoe.getAddress()).isEqualTo("Deimos");
    }

    @Test
    public void getNacionalidad() throws Exception {
        assertThat(johnDoe.getNationality()).isEqualTo(nacionalidad);
    }

    @Test
    public void setNacionalidad() throws Exception {
        johnDoe.setNationality("Earth");
        assertThat(johnDoe.getNationality()).isEqualTo("Earth");
    }

    @Test
    public void getDni() throws Exception {
        assertThat(johnDoe.getDNI()).isEqualTo(dni);
    }

    @Test
    public void setDni() throws Exception {
        johnDoe.setDNI("87654321");
        assertThat(johnDoe.getDNI()).isEqualTo("87654321");
    }

    @Test
    public void getPassword() throws Exception {
        assertThat(johnDoe.getPassword()).isEqualTo(password);
    }

    @Test
    public void setPassword() throws Exception {
        johnDoe.setPassword("Password");
        assertThat(johnDoe.getPassword()).isEqualTo("Password");
    }
}