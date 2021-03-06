package qa.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import qa.constants.ProjectConstants
import qa.struct.QuestionsData
import qa.struct.MissingCategoryData
import qa.struct.ProductCategoryWithProducts
import qa.struct.QuestionsData
import qa.struct.UnmatchedItems
import qa.struct.VisitedCategoryData
import qa.struct.VisitedShopDataInfo
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import io.appium.java_client.MobileElement

public class SurveyKeywords {

	@Keyword
	def visitQuestionCategories(int flag){
		MissingCategoryData missingcategory = new MissingCategoryData()
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareSurveyQuestionCategories()
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setMissing_auditquestioncategories(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestioncategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setMissing_auditquestioncategories(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestioncategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setMissing_auditquestioncategories(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestioncategories_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else{
		}
		int questioncategorieslist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/*").size()
		for(int i=1; i<= questioncategorieslist; i++){
			MobileElement questioncategory = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]/android.widget.TextView[1]")
			ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY = questioncategory.getText()
			if(questioncategory.getText().equalsIgnoreCase("Competition Tracking")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				if(flag == 1){
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Survey/CompetitionTracking/VisitQuestions"), null)
				}
				else{
					Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Survey/CompetitionTracking/OverwriteQuestions"), null)
				}
			}
			else if(questioncategory.getText().equalsIgnoreCase("Survey Questions")){
				ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout["+i+"]").click()
				Mobile.callTestCase(findTestCase("Test Cases/ShopOpen/Survey/SurveyQuestions/VisitSurveyQuestions"), null)
			}
		}
	}
	@Keyword
	def visitCompetitionTrackingQuestions(){
		MissingCategoryData missingcategory = new MissingCategoryData()
		int index = 0
		ArrayList<String> displayedquestions = new ArrayList<String>()
		ArrayList<String> expectedquestions = new ArrayList<String>()
		ArrayList<QuestionsData> visitedquestions = new ArrayList<QuestionsData>()
		ArrayList<QuestionsData> expectedquestionslist = LoadDataKeywords.loadSurveyQuestionsList()
		for(int i=0; i< expectedquestionslist.size(); i++){
			expectedquestions.add(expectedquestionslist.get(i).getQuestion())
		}
		int questionlist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<= questionlist; i++){
			QuestionsData visitedquestion = new QuestionsData()
			ArrayList<QuestionsData> expectedsimilarquestions = new ArrayList<QuestionsData>()
			if(i == questionlist){
				Mobile.swipe(2, 500, 2, 400)
			}
			MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String questiontext = question.getText()
			displayedquestions.add(questiontext)
			visitedquestion.setQuestion(questiontext)
			for(int j=0; j< expectedquestionslist.size(); j++){
				QuestionsData expectedquestion = expectedquestionslist.get(j)
				if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext)){
					expectedsimilarquestions.add(expectedquestionslist.get(j))
				}
			}
			if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
				if(expectedsimilarquestions.size() > 0){
					if(expectedsimilarquestions.size() == 1){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						visitedquestion.setQuestionoption("Y")
						if(expectedsimilarquestions.get(0).getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
							visitedquestion.setQuestionoption_takepicture("Y")
						}
						else{
							visitedquestion.setQuestionoption_takepicture("N")
						}
					}
					else{
						boolean flag = false
						for(int q=0; q< expectedsimilarquestions.size(); q++){
							QuestionsData _question = expectedsimilarquestions.get(q)
							if(_question.getQuestionoption().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("Y")
								if(_question.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
									flag = true
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								break
							}
						}
						if(flag == false){
							visitedquestion.setQuestionoption_takepicture("N")
						}
					}
				}
				else{
					visitedquestion.setQuestionoption("Y")
					visitedquestion.setQuestionoption_takepicture("Not Mention")
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
					Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
					CommonKeywords.takePicture()
				}
				visitedquestions.add(visitedquestion)
			}
			else{
				if(expectedsimilarquestions.size() > 0){
					if(expectedsimilarquestions.size() == 1){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						visitedquestion.setOverwrite_questionoption("Y")
						if(expectedsimilarquestions.get(0).getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
							visitedquestion.setOverwrite_questionoption_takepicture("Y")
						}
						else{
							visitedquestion.setOverwrite_questionoption_takepicture("N")
						}
					}
					else{
						boolean flag = false
						for(int q=0; q< expectedsimilarquestions.size(); q++){
							QuestionsData _question = expectedsimilarquestions.get(q)
							if(_question.getQuestionoption().equalsIgnoreCase("Y")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("Y")
								if(_question.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
									flag = true
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								break
							}
						}
						if(flag == false){
							visitedquestion.setOverwrite_questionoption_takepicture("N")
						}
					}
				}
				else{
					visitedquestion.setOverwrite_questionoption("Y")
					visitedquestion.setOverwrite_questionoption_takepicture("Not Mention")
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
					Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
					CommonKeywords.takePicture()
				}
				visitedquestions.add(visitedquestion)
			}
			Mobile.verifyElementText(findTestObject('ShopOpen/Survey/Validate_QuestionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Questions')
		}
		while(true){
			ArrayList<QuestionsData> expectedsimilarquestions = new ArrayList<QuestionsData>()
			QuestionsData visitedquestion = new QuestionsData()
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
			MobileElement itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String itemtextbeforeswipe = itembeforeswipe.getText()
			Mobile.swipe(2, 548, 2, 400)
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
			MobileElement itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String itemtextafterswipe = itemafterswipe.getText()
			if(itemtextbeforeswipe.equals(itemtextafterswipe)){
				break
			}
			else{
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				visitedquestion.setQuestion(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					QuestionsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext)){
						expectedsimilarquestions.add(expectedquestionslist.get(j))
					}
				}
				if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
							Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
							Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
							visitedquestion.setQuestionoption("Y")
							if(expectedsimilarquestions.get(0).getQuestionoption_takepicture().equalsIgnoreCase("Y")){
								CommonKeywords.takePicture()
								visitedquestion.setQuestionoption_takepicture("Y")
							}
							else{
								visitedquestion.setQuestionoption_takepicture("N")
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								QuestionsData _question = expectedsimilarquestions.get(q)
								if(_question.getQuestionoption().equalsIgnoreCase("Y")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setQuestionoption("Y")
									if(_question.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setQuestionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setQuestionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setQuestionoption("Y")
						visitedquestion.setQuestionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
				}
				else{
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
							Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
							Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
							visitedquestion.setOverwrite_questionoption("Y")
							if(expectedsimilarquestions.get(0).getQuestionoption_takepicture().equalsIgnoreCase("Y")){
								CommonKeywords.takePicture()
								visitedquestion.setOverwrite_questionoption_takepicture("Y")
							}
							else{
								visitedquestion.setOverwrite_questionoption_takepicture("N")
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								QuestionsData _question = expectedsimilarquestions.get(q)
								if(_question.getQuestionoption().equalsIgnoreCase("Y")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setOverwrite_questionoption("Y")
									if(_question.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setOverwrite_questionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setOverwrite_questionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setOverwrite_questionoption("Y")
						visitedquestion.setOverwrite_questionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
				}
			}
			Mobile.verifyElementText(findTestObject('ShopOpen/Survey/Validate_QuestionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Questions')
		}
		ArrayList<String> _expectedquestions = new HashSet<String>(expectedquestions)
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareLists(_expectedquestions, displayedquestions)
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setQuestionCategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
					missingcategory.setMissing_auditquestions(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setQuestionCategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
					missingcategory.setMissing_auditquestions(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setQuestionCategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
					missingcategory.setMissing_auditquestions(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else{
		}
		VisitedCategoryData visitedcategory = new VisitedCategoryData()
		ProductCategoryWithProducts productcategorywithproducts = new ProductCategoryWithProducts()
		productcategorywithproducts.setProductcategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
		productcategorywithproducts.setSurveyquestions(visitedquestions)
		visitedcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
		visitedcategory.setProductcategorywithproducts(productcategorywithproducts)
		for(int i=0; i< ProjectConstants.visitedshopdatainfo.size(); i++){
			if(ProjectConstants.visitedshopdatainfo.get(i).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)){
				VisitedShopDataInfo visitedshopdata = ProjectConstants.visitedshopdatainfo.get(i)
				ArrayList<VisitedCategoryData> visitedcategoriesdata = visitedshopdata.getVisitedcategoriesdata()
				if(visitedcategoriesdata != null){
					boolean maincategory_flag = false
					for(int k=0; k<visitedcategoriesdata.size(); k++){
						VisitedCategoryData visitedcategorydata = visitedcategoriesdata.get(k)
						if(visitedcategorydata.getMaincategory().equals(visitedcategory.getMaincategory())){
							maincategory_flag = true
							ArrayList<ProductCategoryWithProducts> existingsubcategorylist = visitedcategorydata.getProductcategorywithproducts()
							for(int l=0; l< existingsubcategorylist.size(); l++){
								ProductCategoryWithProducts existingsubcategory = existingsubcategorylist.get(l)
								boolean subcategory_flag = false
								if(existingsubcategory.getProductcategory().equals(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)){
									subcategory_flag = true
									ArrayList<QuestionsData> questions = existingsubcategory.getSurveyquestions()
									for(int ex=0; ex< questions.size(); ex++){
										QuestionsData ex_question = questions.get(ex)
										for(int ds=0; ds< visitedquestions.size(); ds++){
											QuestionsData ds_question = visitedquestions.get(ds)
											if(ex_question.getQuestion().equals(ds_question.getQuestion())){
												if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
													ex_question.setQuestionoption(ds_question.getQuestionoption())
													ex_question.setQuestionoption_takepicture(ds_question.getQuestionoption_takepicture())
												}
												else{
													ex_question.setOverwrite_questionoption(ds_question.getOverwrite_questionoption())
													ex_question.setOverwrite_questionoption_takepicture(ds_question.getOverwrite_questionoption_takepicture())
												}
												break
											}
										}
									}
								}
								if(subcategory_flag == false){
									visitedcategorydata.setProductcategorywithproducts(productcategorywithproducts)
									break
								}
							}
						}
					}
					if(maincategory_flag == false){
						ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategory)
						break
					}
				}
				else{
					ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategory)
					break
				}
			}
		}
	}
	@Keyword
	def overwriteCompetitionTrackingQuestions(){
		MissingCategoryData missingcategory = new MissingCategoryData()
		int index = 0
		ArrayList<String> displayedquestions = new ArrayList<String>()
		ArrayList<String> expectedquestions = new ArrayList<String>()
		ArrayList<QuestionsData> visitedquestions = new ArrayList<QuestionsData>()
		ArrayList<QuestionsData> expectedquestionslist = LoadDataKeywords.loadSurveyQuestionsList()
		for(int i=0; i< expectedquestionslist.size(); i++){
			expectedquestions.add(expectedquestionslist.get(i).getQuestion())
		}
		int questionlist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
		for(int i=1; i<= questionlist; i++){
			QuestionsData visitedquestion = new QuestionsData()
			ArrayList<QuestionsData> expectedsimilarquestions = new ArrayList<QuestionsData>()
			if(i == questionlist){
				Mobile.swipe(2, 500, 2, 400)
			}
			MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String questiontext = question.getText()
			displayedquestions.add(questiontext)
			visitedquestion.setQuestion(questiontext)
			for(int j=0; j< expectedquestionslist.size(); j++){
				QuestionsData expectedquestion = expectedquestionslist.get(j)
				if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext)){
					expectedsimilarquestions.add(expectedquestionslist.get(j))
				}
			}
			if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
				if(expectedsimilarquestions.size() > 0){
					if(expectedsimilarquestions.size() == 1){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						visitedquestion.setQuestionoption("Y")
						if(expectedsimilarquestions.get(0).getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
							visitedquestion.setQuestionoption_takepicture("Y")
						}
						else{
							visitedquestion.setQuestionoption_takepicture("N")
						}
					}
					else{
						boolean flag = false
						for(int q=0; q< expectedsimilarquestions.size(); q++){
							QuestionsData _question = expectedsimilarquestions.get(q)
							if(_question.getQuestionoption().equalsIgnoreCase("N")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setQuestionoption("N")
								if(_question.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
									flag = true
									CommonKeywords.takePicture()
									visitedquestion.setQuestionoption_takepicture("Y")
								}
								break
							}
						}
						if(flag == false){
							visitedquestion.setQuestionoption_takepicture("N")
						}
					}
				}
				else{
					visitedquestion.setQuestionoption("Y")
					visitedquestion.setQuestionoption_takepicture("Not Mention")
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
					Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
					CommonKeywords.takePicture()
				}
				visitedquestions.add(visitedquestion)
			}
			else{
				if(expectedsimilarquestions.size() > 0){
					if(expectedsimilarquestions.size() == 1){
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						visitedquestion.setOverwrite_questionoption("Y")
						if(expectedsimilarquestions.get(0).getQuestionoption_takepicture().equalsIgnoreCase("Y")){
							CommonKeywords.takePicture()
							visitedquestion.setOverwrite_questionoption_takepicture("Y")
						}
						else{
							visitedquestion.setOverwrite_questionoption_takepicture("N")
						}
					}
					else{
						boolean flag = false
						for(int q=0; q< expectedsimilarquestions.size(); q++){
							QuestionsData _question = expectedsimilarquestions.get(q)
							if(_question.getQuestionoption().equalsIgnoreCase("N")){
								ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
								Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
								Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
								visitedquestion.setOverwrite_questionoption("N")
								if(_question.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
									flag = true
									CommonKeywords.takePicture()
									visitedquestion.setOverwrite_questionoption_takepicture("Y")
								}
								break
							}
						}
						if(flag == false){
							visitedquestion.setOverwrite_questionoption_takepicture("N")
						}
					}
				}
				else{
					visitedquestion.setOverwrite_questionoption("Y")
					visitedquestion.setOverwrite_questionoption_takepicture("Not Mention")
					ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+i+"]").click()
					Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
					CommonKeywords.takePicture()
				}
				visitedquestions.add(visitedquestion)
			}
			Mobile.verifyElementText(findTestObject('ShopOpen/Survey/Validate_QuestionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Questions')
		}
		while(true){
			ArrayList<QuestionsData> expectedsimilarquestions = new ArrayList<QuestionsData>()
			QuestionsData visitedquestion = new QuestionsData()
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
			MobileElement itembeforeswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String itemtextbeforeswipe = itembeforeswipe.getText()
			Mobile.swipe(2, 548, 2, 400)
			index = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*").size()
			MobileElement itemafterswipe = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
			String itemtextafterswipe = itemafterswipe.getText()
			if(itemtextbeforeswipe.equals(itemtextafterswipe)){
				break
			}
			else{
				MobileElement question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
				String questiontext = question.getText()
				displayedquestions.add(questiontext)
				visitedquestion.setQuestion(questiontext)
				for(int j=0; j< expectedquestionslist.size(); j++){
					QuestionsData expectedquestion = expectedquestionslist.get(j)
					if(expectedquestion.getQuestion().equalsIgnoreCase(questiontext)){
						expectedsimilarquestions.add(expectedquestionslist.get(j))
					}
				}
				if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
							Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
							Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
							visitedquestion.setQuestionoption("Y")
							if(expectedsimilarquestions.get(0).getQuestionoption_takepicture().equalsIgnoreCase("Y")){
								CommonKeywords.takePicture()
								visitedquestion.setQuestionoption_takepicture("Y")
							}
							else{
								visitedquestion.setQuestionoption_takepicture("N")
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								QuestionsData _question = expectedsimilarquestions.get(q)
								if(_question.getQuestionoption().equalsIgnoreCase("N")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setQuestionoption("N")
									if(_question.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setQuestionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setQuestionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setQuestionoption("Y")
						visitedquestion.setQuestionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
				}
				else{
					if(expectedsimilarquestions.size() > 0){
						if(expectedsimilarquestions.size() == 1){
							ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
							Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
							Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
							visitedquestion.setOverwrite_questionoption("Y")
							if(expectedsimilarquestions.get(0).getQuestionoption_takepicture().equalsIgnoreCase("Y")){
								CommonKeywords.takePicture()
								visitedquestion.setOverwrite_questionoption_takepicture("Y")
							}
							else{
								visitedquestion.setOverwrite_questionoption_takepicture("N")
							}
						}
						else{
							boolean flag = false
							for(int q=0; q< expectedsimilarquestions.size(); q++){
								QuestionsData _question = expectedsimilarquestions.get(q)
								if(_question.getQuestionoption().equalsIgnoreCase("N")){
									ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
									Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
									Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
									visitedquestion.setOverwrite_questionoption("N")
									if(_question.getQuestionoption_takepicture().equalsIgnoreCase("Y")){
										flag = true
										CommonKeywords.takePicture()
										visitedquestion.setOverwrite_questionoption_takepicture("Y")
									}
									break
								}
							}
							if(flag == false){
								visitedquestion.setOverwrite_questionoption_takepicture("N")
							}
						}
					}
					else{
						visitedquestion.setOverwrite_questionoption("Y")
						visitedquestion.setOverwrite_questionoption_takepicture("Not Mention")
						ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.Spinner["+index+"]").click()
						Mobile.verifyElementExist(findTestObject("ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
						Mobile.tap(findTestObject("ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
						CommonKeywords.takePicture()
					}
					visitedquestions.add(visitedquestion)
				}
			}
			Mobile.verifyElementText(findTestObject('ShopOpen/Survey/Validate_QuestionsScreen', [('package') : ProjectConstants.PACKAGENAME]), 'Questions')
		}
		ArrayList<String> _expectedquestions = new HashSet<String>(expectedquestions)
		UnmatchedItems unmatcheditems = CompareDataKeywords.compareLists(_expectedquestions, displayedquestions)
		if(unmatcheditems.getStatus() == 2){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setQuestionCategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
					missingcategory.setMissing_auditquestions(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_NOTMATCH)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == 1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setQuestionCategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
					missingcategory.setMissing_auditquestions(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MORE)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else if(unmatcheditems.getStatus() == -1){
			for(int j=0; j<ProjectConstants.missingshopdatainfo.size(); j++){
				if(ProjectConstants.missingshopdatainfo.get(j).getShopname().equalsIgnoreCase(ProjectConstants.CURRENTVISITING_SHOPNAME)) {
					missingcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
					missingcategory.setQuestionCategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
					missingcategory.setMissing_auditquestions(unmatcheditems.getItems())
					missingcategory.setMissing_auditquestions_errormessage(ProjectConstants.MESSAGEFOR_ITEMSARE_MISSING)
					ProjectConstants.missingshopdatainfo.get(j).setMissingCategoriesData(missingcategory, "")
					break
				}
				else{
				}
			}
		}
		else{
		}
		VisitedCategoryData visitedcategory = new VisitedCategoryData()
		ProductCategoryWithProducts productcategorywithproducts = new ProductCategoryWithProducts()
		productcategorywithproducts.setProductcategory(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)
		productcategorywithproducts.setSurveyquestions(visitedquestions)
		visitedcategory.setMaincategory(ProjectConstants.CURRENTVISITING_MAINCATEGORY)
		visitedcategory.setProductcategorywithproducts(productcategorywithproducts)
		for(int i=0; i< ProjectConstants.visitedshopdatainfo.size(); i++){
			if(ProjectConstants.visitedshopdatainfo.get(i).getShopname().equals(ProjectConstants.CURRENTVISITING_SHOPNAME)){
				VisitedShopDataInfo visitedshopdata = ProjectConstants.visitedshopdatainfo.get(i)
				ArrayList<VisitedCategoryData> visitedcategoriesdata = visitedshopdata.getVisitedcategoriesdata()
				if(visitedcategoriesdata != null){
					boolean maincategory_flag = false
					for(int k=0; k<visitedcategoriesdata.size(); k++){
						VisitedCategoryData visitedcategorydata = visitedcategoriesdata.get(k)
						if(visitedcategorydata.getMaincategory().equals(visitedcategory.getMaincategory())){
							maincategory_flag = true
							ArrayList<ProductCategoryWithProducts> existingsubcategorylist = visitedcategorydata.getProductcategorywithproducts()
							for(int l=0; l< existingsubcategorylist.size(); l++){
								ProductCategoryWithProducts existingsubcategory = existingsubcategorylist.get(l)
								boolean subcategory_flag = false
								if(existingsubcategory.getProductcategory().equals(ProjectConstants.CURRENTVISITING_QUESTIONCATEGORY)){
									subcategory_flag = true
									ArrayList<QuestionsData> questions = existingsubcategory.getSurveyquestions()
									for(int ex=0; ex< questions.size(); ex++){
										QuestionsData ex_question = questions.get(ex)
										for(int ds=0; ds< visitedquestions.size(); ds++){
											QuestionsData ds_question = visitedquestions.get(ds)
											if(ex_question.getQuestion().equals(ds_question.getQuestion())){
												if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
													ex_question.setQuestionoption(ds_question.getQuestionoption())
													ex_question.setQuestionoption_takepicture(ds_question.getQuestionoption_takepicture())
												}
												else{
													ex_question.setOverwrite_questionoption(ds_question.getOverwrite_questionoption())
													ex_question.setOverwrite_questionoption_takepicture(ds_question.getOverwrite_questionoption_takepicture())
												}
												break
											}
										}
									}
								}
								if(subcategory_flag == false){
									visitedcategorydata.setProductcategorywithproducts(productcategorywithproducts)
									break
								}
							}
						}
					}
					if(maincategory_flag == false){
						ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategory)
						break
					}
				}
				else{
					ProjectConstants.visitedshopdatainfo.get(i).setVisitedcategoriesdata(visitedcategory)
					break
				}
			}
		}
	}
	@Keyword
	def visitSurveyQuestions(){
		int index
		MobileElement listview = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]")
		ArrayList<MobileElement> questionslist = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
		//visit fields
		for(int i=0; i< questionslist.size(); i++){
			MobileElement question = questionslist.get(i)
			if(i == questionslist.size()-1){
				question.click()
				CommonKeywords.takePicture()
				Mobile.verifyElementExist(findTestObject('ShopOpen/Survey/Validate_SurveyQuestionsScreen' , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
			else if(question.getTagName().equalsIgnoreCase("android.widget.LinearLayout")){
				MobileElement _question = ProjectConstants.DRIVER.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout["+(i+1)+"]/android.widget.FrameLayout[1]/android.widget.EditText[1]")
				if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
					String val = String.valueOf(10+i)
					_question.setValue(val)
				}
				else{
					String val = String.valueOf(20+i)
					_question.setValue(val)
				}
				Mobile.hideKeyboard()
			}
			else{
				if(ProjectConstants.SCENARIO.equalsIgnoreCase("first visit")){
					question.click()
					Mobile.verifyElementExist(findTestObject("Object Repository/ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("Object Repository/ShopOpen/Survey/RemarksPopup_Yes", [('package') : ProjectConstants.PACKAGENAME]), 0)
				}
				else{
					question.click()
					Mobile.verifyElementExist(findTestObject("Object Repository/ShopOpen/Survey/Validate_RemarksPopup", [('package') : ProjectConstants.PACKAGENAME]), 0)
					Mobile.tap(findTestObject("Object Repository/ShopOpen/Survey/RemarksPopup_No", [('package') : ProjectConstants.PACKAGENAME]), 0)
				}
			}
		}
		while(true){
			ArrayList<MobileElement> list = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
			MobileElement itembeforeswipe = list.get(list.size()-1)
			itembeforeswipe = itembeforeswipe.findElementByClassName("android.widget.TextView")
			String itemtextbeforeswipe = itembeforeswipe.getText()
			Mobile.swipe(10,450,10,200)
			list = ProjectConstants.DRIVER.findElementsByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/*")
			MobileElement itemafterswipe = list.get(list.size()-1)
			itemafterswipe = itemafterswipe.findElementByClassName("android.widget.TextView")
			String itemtextafterswipe = itemafterswipe.getText()
			if(itemtextbeforeswipe.equalsIgnoreCase(itemtextafterswipe)){
				break
			}
			else{
				itemafterswipe.click()
				CommonKeywords.takePicture()
				Mobile.verifyElementExist(findTestObject('ShopOpen/Survey/Validate_SurveyQuestionsScreen' , [('package') : ProjectConstants.PACKAGENAME]), 0)
			}
		}
	}
}
