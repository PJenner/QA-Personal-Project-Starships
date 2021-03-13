package com.bae.personalprojectstarships.selenium;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Sql(scripts = { "classpath:SQLImplementation.sql",
		"classpath:starship-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class SeleniumStarshipsTest {

	@LocalServerPort
	private int port;

	private RemoteWebDriver driver;

	@BeforeEach
	void setup() {
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true);
		this.driver = new ChromeDriver(options);
		this.driver.manage().window().maximize();
	}

	@Test
	void test() {
		this.driver.get("http://127.0.0.1:5501/frontend/index.html");

		WebElement title = this.driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div"));

		assertThat(title.getText()).isEqualTo("LCARS");
	}

	@Test
	void testCreate() throws InterruptedException {
		this.driver.get("http://127.0.0.1:5501/frontend/index.html");
		WebElement enterStar = this.driver.findElement(By.xpath("//*[@id=\"starShipName\"]"));

		enterStar.click();

		enterStar.sendKeys("Selenium");

		WebElement enterModel = this.driver.findElement(By.xpath("//*[@id=\"starShipModel\"]"));

		enterModel.click();

		enterModel.sendKeys("Y7");

		WebElement enterAge = this.driver.findElement(By.xpath("//*[@id=\"starShipAge\"]"));

		enterAge.click();

		enterAge.sendKeys("300");

		WebElement Create = this.driver.findElement(By.xpath("//*[@id=\"starshipForm\"]/button[1]"));

		Create.click();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		Thread.sleep(2000);
		WebElement newCard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"output\"]/div[1]/div")));

		Assertions.assertTrue(newCard.getText().contains("Selenium"));
	}

	@Test
	void testUpdate() throws InterruptedException {
		this.driver.get("http://127.0.0.1:5501/frontend/index.html");
		Thread.sleep(2000);
		WebElement updateButton = driver.findElement(By.xpath("//*[@id=\"output\"]/div/div/div[2]/a[3]"));
		JavascriptExecutor jse2 = driver;
		jse2.executeScript("arguments[0].scrollIntoView()", updateButton);
		Thread.sleep(2000);
		updateButton.click();

		WebElement updateEnterName = this.driver.findElement(By.xpath("//*[@id=\"starShipNameModal\"]"));

		updateEnterName.click();
		updateEnterName.sendKeys("Selenium Tested");

		WebElement updateEnterModel = this.driver.findElement(By.xpath("//*[@id=\"starShipModelModal\"]"));

		updateEnterModel.click();
		updateEnterModel.sendKeys("MG");

		WebElement updateEnterAge = this.driver.findElement(By.xpath("//*[@id=\"starShipAgeModal\"]"));

		updateEnterAge.click();
		updateEnterAge.sendKeys("12");

		WebElement updateUpdateButton = this.driver.findElement(By.xpath("//*[@id=\"updateModalBtn\"]"));

		updateUpdateButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		Thread.sleep(2000);
		WebElement updatedCard = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"output\"]/div/div")));

		Assertions.assertTrue(updatedCard.getText().contains("Selenium Tested"));
	}

	@Test
	void testDelete() throws InterruptedException {

		this.driver.get("http://127.0.0.1:5501/frontend/index.html");

		Thread.sleep(2000);
		WebElement deleteButton = driver.findElement(By.xpath("//*[@id=\"output\"]/div/div/div[2]/a[1]"));
		JavascriptExecutor jse2 = driver;
		jse2.executeScript("arguments[0].scrollIntoView()", deleteButton);

		deleteButton.click();
		Thread.sleep(2000);
		WebElement body = driver.findElement(By.id("maincontainer"));

		assertThat(body.getText()).doesNotContain("Enterprise");
	}

	@AfterEach
	void tearDown() {
		this.driver.close();
	}

}
