package kh.test.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KhTestSelenium {

	public KhTestSelenium() {
	}

	public static void main(String[] args) {
		System.getProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver dr1 = new ChromeDriver();// 크롬창을 열기 위한 준비 -> 열기
		WebDriverWait w1 = new WebDriverWait(dr1, 10); // 위 크롬창이 실제 열릴 때 까지 10초 기다려줌.
		
		dr1.get("https://www.iei.or.kr/login/login.kh"); // 열린 크롬창에 url 이동함
		WebElement e1 = dr1.findElement(By.id("id"));
		WebElement e2 = dr1.findElement(By.id("password"));
		e1.sendKeys("내 id 적는 부분");
		e2.sendKeys("내 비밀번호 적는 부분");
		
		JavascriptExecutor jsexe = (JavascriptExecutor)dr1; // 업캐스팅
		jsexe.executeScript("fnLogin()");
		// 페이지 로딩될 때까지 기다려야 다음 js 실행됨.
		w1.until(ExpectedConditions.titleContains("KH정보교육원 - 마이페이지"));
		jsexe.executeScript("location.href='/login/currBoard.kh'");
		
		// 페이지 로딩될 때까지 기다려야 다음 js 실행됨.
		w1.until(ExpectedConditions.titleContains("KH정보교육원 - 우리반 게시판"));
		jsexe.executeScript("fnWriteForm()");
		
		// 페이지 로딩될 때까지 기다려야 하는데.. title이라는 것을 클릭할 수 있는지 확인 될 때까지 기다림.
		w1.until(ExpectedConditions.elementToBeClickable(By.id("title")));
		WebElement e3 = dr1.findElement(By.id("title"));
		e3.sendKeys("제목");
		
		// wysiwyg 프레임으로 이동
		dr1.switchTo().frame("tx_canvas_wysiwyg");
		// 내용입력창
		WebElement e4 = dr1.findElement(By.className("tx-content-container"));
		// 내용입력
		//e4.sendKeys("내용입력~ 아이프레임사용된 입력창-frame~~~ 이동");
		e4.sendKeys("내용");
		
		// 부모프레임. 원본프레임으로 이동
		dr1.switchTo().parentFrame();
		// 완료버튼 누른 행동
		jsexe.executeScript("javascript:fnRegister()");
		
		// popup 창으로 이동해 확인
		dr1.switchTo().alert().accept();
		
		// 크롬창 닫고  resource 닫기
		dr1.close();
		
		// 참고
		for(int i=1; i<=2; i++) {
			for(int j=1; j<=5; j++) {
				WebElement e = dr1.findElement(By.cssSelector("#content > div:nth-child(2) > div > div > div > ul > li:nth-child("+i+") > ul > li:nth-child(\"+j+\") > div.song_area > div.song > a"));
				WebElement e10 = dr1.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div/div/ul/li[1]/ul/li[1]/div[3]/div[2]/span/span[1]/span/a"));
				String innertext = e.getText();
				
				e.click();
				// image 내려받아 저장
				// 이게 정말 필요하면 따로 답변 주면서, 이정도 선에서 수업 마친다. 
				// back 도 하고 
				jsexe.executeScript("history.back();");
			}
		}
		// 곡제목 selector
		// #content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(1) > div.song_area > div.song > a
		// #content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(2) > div.song_area > div.song > a
		// #content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(3) > div.song_area > div.song > a
		// #content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(4) > div.song_area > div.song > a
		// #content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(5) > div.song_area > div.song > a
		// #content > div:nth-child(2) > div > div > div > ul > li:nth-child(2) > ul > li:nth-child(1) > div.song_area > div.song > a
		
		// 가수 selector
		// #content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(1) > div.song_area > div.artist > span > span:nth-child(1) > span > a
		// #content > div:nth-child(2) > div > div > div > ul > li:nth-child(1) > ul > li:nth-child(2) > div.song_area > div.artist > span > span:nth-child(1) > span > a
		
		// xpath
		//*[@id="content"]/div[1]/div/div/div/ul/li[1]/ul/li[1]/div[3]/div[1]/a
		//*[@id="content"]/div[1]/div/div/div/ul/li[1]/ul/li[2]/div[3]/div[1]/a
		// 중간생략
		//*[@id="content"]/div[1]/div/div/div/ul/li[1]/ul/li[5]/div[3]/div[1]/a
		//*[@id="content"]/div[1]/div/div/div/ul/li[2]/ul/li[1]/div[3]/div[1]/a
		
	}

}
