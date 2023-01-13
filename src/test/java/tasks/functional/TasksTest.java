package tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver AcessarApp(){
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
@Test
public void SalvarTarefa(){
	WebDriver driver = AcessarApp();
	driver.findElement(By.id("addTodo")).click();
	driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
	driver.findElement(By.id("dueDate")).sendKeys("30/12/2023");
	
	driver.findElement(By.id("saveButton")).click();
	
	String mensagem = driver.findElement(By.id("message")).getText();
	Assert.assertEquals("Sucess!", mensagem);
	driver.quit();
}

@Test
public void SemTask() {
	WebDriver driver = AcessarApp();
	
	driver.findElement(By.id("addTodo")).click();
	driver.findElement(By.id("dueDate")).sendKeys("30/12/2023");
	driver.findElement(By.id("saveButton")).click();
	String mensagem = driver.findElement(By.id("message")).getText();
	Assert.assertEquals("Fill the task description", mensagem);
	driver.quit();
}

@Test
public void SemData() {
	WebDriver driver = AcessarApp();
	
	driver.findElement(By.id("addTodo")).click();
	driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
	driver.findElement(By.id("saveButton")).click();
	String mensagem = driver.findElement(By.id("message")).getText();
	Assert.assertEquals("Fill the due date", mensagem);
	driver.quit();
}

@Test
public void DataInvalida()  {
	WebDriver driver = AcessarApp();
	
	driver.findElement(By.id("addTodo")).click();
	driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
	driver.findElement(By.id("dueDate")).sendKeys("30/12/2010");
	
	driver.findElement(By.id("saveButton")).click();
	
	String mensagem = driver.findElement(By.id("message")).getText();
	Assert.assertEquals("Due date must not be in past", mensagem);
	driver.quit();
}
}
