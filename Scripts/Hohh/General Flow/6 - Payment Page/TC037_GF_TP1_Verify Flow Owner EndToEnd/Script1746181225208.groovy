import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil

/**
 * Verify End-to-end for owner 
 *
 * @pass The e-policy number are correct (FJ), the product name are correct ("Houseowner and HouseHolder Insurance") and the success icon are present.
 * @fail TOne of the passing criteria is not met.
 *
 */

params = [
		('ownerClass') : findTestObject('Object Repository/Hohh/Quotation Page/dyselection_getQuotation_text,class',
			[('text') : GlobalVariable.dyobj_ORQP003['quotationClass'], ('class') : GlobalVariable.dyobj_ORQP001['owner']]),
	]

WebUI.callTestCase(findTestCase('Hohh/Reusable Module/Page Flow/TC005_RM_PF_Direct to Payment Page'), [('params') : params], FailureHandling.STOP_ON_FAILURE)

isPaymentSuccess = WebUI.verifyElementPresent(findTestObject('Object Repository/Hohh/Payment Page/imgvalidation_Payment Success',
	[('text') : GlobalVariable.dyobj_ORPyP002['paymentIcon']]), 3, FailureHandling.OPTIONAL)

boolean havePolicyNumber = false
if (WebUI.verifyElementPresent(findTestObject('Object Repository/Hohh/Payment Page/dyvalidation_Policy Number_text',
		[('text') : GlobalVariable.dyobj_ORPyP003['policyNumber']]), 3, FailureHandling.OPTIONAL)) {
	String policy = WebUI.getText(findTestObject('Object Repository/Hohh/Payment Page/dyvalidation_Policy Number_text',
		[('text') : GlobalVariable.dyobj_ORPyP003['policyNumber']]))
	havePolicyNumber = policy.contains(GlobalVariable.dyobj_ORPyP002['policyNumberPrefix'])
}

boolean isCorrectProduct = false
if (WebUI.verifyElementPresent(findTestObject('Object Repository/Hohh/Payment Page/dyvalidation_Product_text',
		[('text') : GlobalVariable.dyobj_ORPyP003['product']]), 3, FailureHandling.OPTIONAL)) {
	String product = WebUI.getText(findTestObject('Object Repository/Hohh/Payment Page/dyvalidation_Product_text',
		[('text') : GlobalVariable.dyobj_ORPyP003['product']]))
	isCorrectProduct = product == GlobalVariable.dyobj_ORPyP002['product']
}

WebUI.takeScreenshot()
WebUI.closeBrowser()

assert isPaymentSuccess && havePolicyNumber && isCorrectProduct : 
	KeywordUtil.markError("""One of the criteria have error:
Present of payment success text: ${isPaymentSuccess}
Correct display of policy number: ${havePolicyNumber}
Correct display of product name: ${isCorrectProduct}""")
	