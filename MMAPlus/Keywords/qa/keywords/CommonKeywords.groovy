package qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import qa.constants.ProjectConstants
import qa.struct.TaggedChillersRemark
import qa.struct.VisitedCategoryData
import qa.struct.VisitedChillerProductsCategoryData
import qa.struct.VisitedShopDataInfo
import com.googlecode.javacv.CanvasFrame.Exception
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import io.appium.java_client.MobileElement
import io.appium.java_client.TouchAction
import java.text.SimpleDateFormat
import java.time.Duration

import org.openqa.selenium.Point
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class CommonKeywords {

	@Keyword
	//select route day to start the day
	def selectday(){
		Calendar calendar = Calendar.getInstance()
		int day = calendar.get(Calendar.DAY_OF_WEEK)
		Mobile.tap(findTestObject("Object Repository/DashboardScreenElements/DaysDropdownMenu" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		Mobile.verifyElementExist(findTestObject("Object Repository/DashboardScreenElements/Validate_DaysListScreen" , [('package') : ProjectConstants.PACKAGENAME]), 0)
		if(day == 1){
			findRoute("Sunday")
		}
		else if(day == 2){
			findRoute("Monday")
		}
		else if(day == 3){
			findRoute("Tuesday")
		}
		else if(day == 4){
			findRoute("Wednesday")
		}
		else if(day == 5){
			findRoute("Thursday")
		}
		else if(day == 6){
			findRoute("Friday")
		}
		else if(day == 7){
			findRoute("Saturday")
		}
	}
	//find route day
	def findRoute(String route){
		int totalroutes = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= totalroutes; i++){
			MobileElement _route = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView["+i+"]")
			String routetext = _route.getText()
			if(routetext.equalsIgnoreCase(route)){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView["+i+"]").click();
				break
			}
		}
	}
	@Keyword
	def checkPlanogramAvailability(){
		Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_OKButton" , [('package') : ProjectConstants.PACKAGENAME]), 0, FailureHandling.OPTIONAL)
	}
	@Keyword
	//find planogram button and click after visiting category products
	def closePlanogram(){
		int totalbuttons = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<= totalbuttons; i++){
			MobileElement button = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String buttontext = button.getText()
			if(buttontext.equalsIgnoreCase("close")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.Button[1]").click()
				break
			}
			else{}
		}
	}
	@Keyword
	//find picture image view and click after visiting category products
	def visitPictureImageViewButton(){
		int totalbuttons = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<= totalbuttons; i++){
			MobileElement button = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String buttontext = button.getText()
			if(buttontext.equalsIgnoreCase("Picture")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.ImageButton[1]").click()
				takePicture()
				break
			}
			else{}
		}
	}
	@Keyword
	//find back button and click
	def visitBackImageViewButton(){
		int totalbuttons = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<= totalbuttons; i++){
			MobileElement button = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String buttontext = button.getText()
			if(buttontext.equalsIgnoreCase("Back")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.ImageButton[1]").click()
				break
			}
			else{}
		}
	}
	@Keyword
	//find planogram button and check availability of planogram and click on planogram close button
	def visitPlanogramImageViewButton(){
		int totalbuttons = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<= totalbuttons; i++){
			MobileElement button = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			String buttontext = button.getText()
			if(buttontext.equalsIgnoreCase("Planogram")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+i+"]/android.widget.ImageButton[1]").click()
				Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_OKButton" , [('package') : ProjectConstants.PACKAGENAME]), 0, FailureHandling.OPTIONAL)
				totalbuttons = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/*").size()
				for(int j=1; j<= totalbuttons; j++){
					button = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+j+"]/android.widget.TextView[1]")
					buttontext = button.getText()
					if(buttontext.equalsIgnoreCase("Close")){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+j+"]/android.widget.Button[1]").click()
						break
					}
					else{}
				}
				break
			}
			else{}
		}
	}
	@Keyword
	//validate picture screen view and take picture
	def static takePicture(){
		if(Mobile.verifyElementExist(findTestObject("Object Repository/CommonScreenElements/Validate_CameraScreen", [('package') : ProjectConstants.PACKAGENAME]), 0, FailureHandling.OPTIONAL)){
			//			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/Camera_flashButton", [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/Camera_TakePictureButton", [('package') : ProjectConstants.PACKAGENAME]), 0)
			Mobile.delay(5)
			Mobile.tap(findTestObject("Object Repository/CommonScreenElements/Camera_DoneButton", [('package') : ProjectConstants.PACKAGENAME]), 0)
		}
		else{
		}
	}
	//find x location coordinate when visit products in category
	def static getXPoint(){
		Point point = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]").getLocation()
		int xlocation = point.getX()
		return xlocation+1
	}
	//visit overwrite popup when overwrite category
	def static visitPopUpForOverwriting(){
		Mobile.tap(findTestObject("Object Repository/CommonScreenElements/InfoPopUp_YesButton", [('package') : ProjectConstants.PACKAGENAME]), 0, FailureHandling.OPTIONAL)
	}
}