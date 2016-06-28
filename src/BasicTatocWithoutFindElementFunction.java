import java.util.List;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class BasicTatocWithoutFindElementFunction {
	static WebDriver driver = new FirefoxDriver();
	JavascriptExecutor js=(JavascriptExecutor) driver;;
	  
	 public static void main(String args[])
	   {	
		    BasicTatocWithoutFindElementFunction basic=new BasicTatocWithoutFindElementFunction();
			driver.get("http://10.0.1.86/tatoc/basic/grid/gate");
		    String element, element1;
	     	List<WebElement> web= basic.selectElement("div.greenbox");
		    web.get(0).click();
		    driver.switchTo().frame(0);
   		    List<WebElement> web1=basic.selectElement("div#answer");
		    element=web1.get(0).getAttribute("class");
		    driver.switchTo().frame("child");
    		List<WebElement> web2=basic.selectElement("div#answer");
		    element1=web2.get(0).getAttribute("class");
		  while (true) 
		   {
			   driver.switchTo().defaultContent();
			   driver.switchTo().frame(0); 
			   List<WebElement> web6=basic.selectElement("a");
			  if (!(element.equals(element1))) 
			   {
				
				web6.get(0).click();
				driver.switchTo().frame("child");
				web1 = basic.selectElement("div#answer");
				element1=web1.get(0).getAttribute("class");
				} 
			 else 
			   {
				web6.get(1).click();
				break;
			   }
		   }
			web2=basic.selectElement("#dragbox");
			web1=basic.selectElement("#dropbox");
		    
		  Actions builder = new Actions(driver);  // Configure the Action
		  Action dragAndDrop = builder.clickAndHold(web2.get(0))
		    .moveToElement(web1.get(0))
		    .release(web1.get(0))
		    .build();  // Get the action
		    dragAndDrop.perform();
		    web=basic.selectElement("a");
		    web.get(0).click();
		    web1=basic.selectElement("a");
		    web1.get(0).click();
		    String winHandleBefore = driver.getWindowHandle();
		    for(String winHandle : driver.getWindowHandles()){
		        driver.switchTo().window(winHandle);
		    }
		    web2=basic.selectElement("#name");
		    web2.get(0).sendKeys("heyaa");
		    web2=basic.selectElement("input#submit");
		    web2.get(0).click();
		    driver.switchTo().window(winHandleBefore);
		    web1=basic.selectElement("a");
		    web1.get(1).click();
		   	web1=basic.selectElement("a");
		    web1.get(0).click();
		    web2=basic.selectElement("#token");
		    String s2=web2.get(0).getText();
		    String s3=s2.substring(7);
		    Cookie name = new Cookie("Token", s3); //Create new cookie
			driver.manage().addCookie(name);  //Add cookie
			web1=basic.selectElement("a");
    		web1.get(1).click();
		    
	  }
	public List selectElement(String id)
	{
		List<WebElement> script=(List<WebElement>) js.executeScript("return document.querySelectorAll('"+id+"')");
		return script;
		
	}
	
}