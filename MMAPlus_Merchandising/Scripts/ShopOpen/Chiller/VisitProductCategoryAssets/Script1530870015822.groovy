import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.ct.qa.constants.ProjectConstants as ProjectConstants
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

'validate product category detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Chiller/Validate_ProductCategoryAssetsScreen'), 'Facing')

'tap on product category "Facing"'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Chiller/Facing'), 0)

'Validate product category facing screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Chiller/Validate_FacingScreen'), 'Facing')

'visit products with facing'
CustomKeywords.'com.ct.qa.keywords.ChannelProductsDataKeywords.visitChannelWiseProductsData'(ProjectConstants.CHANNEL_CHILLER_FACING, 
    'Facing')

'tap on submit button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Chiller/SubmitButton'), 0)

'validate product category detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Chiller/Validate_ProductCategoryAssetsScreen'), 'Facing')

'tap on "Stock Taking"'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Chiller/StockTaking'), 0)

'validate stock taking detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Chiller/Validate_StockTakingScreen'), 'Stock Taking')

'visit products with stock taking'
CustomKeywords.'com.ct.qa.keywords.ChannelProductsDataKeywords.visitChannelWiseProductsData'(ProjectConstants.CHANNEL_CHILLER_STOCKTAKING, 
    'Stock Taking')

'tap on submit button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Chiller/SubmitButton'), 0)

'validate product category detail screen appearance'
MobileBuiltInKeywords.verifyElementText(findTestObject('ShopOpen/Chiller/Validate_ProductCategoryAssetsScreen'), 'Facing')

'tap on back button'
MobileBuiltInKeywords.tap(findTestObject('ShopOpen/Chiller/ChillerNotAllocatedProductCategory_BackButton'), 0)

