import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import qa.constants.ProjectConstants as ProjectConstants
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

'validate display space available detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Nestrade/Validate_NestradePorductCategoryScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Category:Nestrade')

'overwrite products categories'
CustomKeywords.'qa.keywords.ChannelProductsDataKeywords.visitNestradeProductsCategoriesWithNSFD'(2)

'visit products categories'
CustomKeywords.'qa.keywords.CommonKeywords.visitPlanogramImageViewButton'()

'validate display space available detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Nestrade/Validate_NestradePorductCategoryScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Category:Nestrade')

'visit products categories'
CustomKeywords.'qa.keywords.CommonKeywords.visitPictureImageViewButton'()

'validate display space available detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Nestrade/Validate_NestradePorductCategoryScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    'Category:Nestrade')

'visit products categories'
CustomKeywords.'qa.keywords.CommonKeywords.visitBackImageViewButton'()

'validate shop\'s category screen appearance'
MobileBuiltInKeywords.verifyElementExist(findTestObject('ShopOpen/Validate_ShopCategoriesListScreen', [('package') : ProjectConstants.PACKAGENAME]), 
    0)

