package asw.selenium;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import asw.util.SeleniumUtils;

@RunWith(SpringJUnit4ClassRunner.class)

public class test1 {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = SeleniumUtils.getDriver("test1"); 
    baseUrl = "http://localhost:8090/";
    driver.get(baseUrl);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
  }

  @Test
  public void testNakamura() throws Exception {
    
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("nakamura@gmail.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("123456");
    driver.findElement(By.id("logearse")).click();
    
    String texto = "Página de administrador";
    SeleniumUtils.textoPresentePagina(driver, texto);
    
  }
  
  @Test
  public void testValduvieco() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("valduvieco@gmail.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("123456");
    driver.findElement(By.id("logearse")).click();
    
    String texto = "Página de usuario";
    SeleniumUtils.textoPresentePagina(driver, texto);
    
   
  }
  @Test
  public void testNoExiste() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("paco@gmail.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("123456");
    driver.findElement(By.id("logearse")).click();
    
    String texto = "Página de error";
    SeleniumUtils.textoPresentePagina(driver, texto);
    
  }
  
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
  
}
