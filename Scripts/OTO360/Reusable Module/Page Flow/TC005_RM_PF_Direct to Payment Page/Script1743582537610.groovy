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

/**
 * @author 80012455
 *
 * Access to Test Case
 * 1. TC004_RM_PF_Direct to Review Page
 * 2. TC006_RM_PF_Make Payment
 * -------------------------------------------------------------------------
 * Data:
 *
 * 1. TC004_RM_PF_Direct to Review Page
 * params = {
  		idType: MYKAD (String),
  		ic: 000928070605 (String),
  		vehicleNumber: VOA0005 (String),
  		passport: (Optional) (String),
  		nationality: (Optional) (String),
  		dateOfBirth: (Optional) (String),
  		armyIdNo: (Optional) (String),
  		policeIdNo: (Optional) (String),
  		column_set: (Optional) (String),
  		emailForRenewal: String (Required),
  		planClass: int (Required),
	    name : String,
	    race : String,
	    gender : String,
	    mobileNumber : String,
	    email: String,
	    address1: String,
	    address2: String,
	    city: String,
	    postcode: String,
  		bankName: string (Required),
  		savingAccount: string (Required)
   }
 * 
   2. TC007_RM_F_Review Form
   params = {
  		declaration: Boolean (Required),
  		marketingConsent: Boolean (Required),
  		paymentMethod: TestObject (Required)
   }
   
   3. TC006_RM_PF_Make Payment
   params = {
		  toSuccess: Boolean (required)
   }
 */
Map defaultValues = [:]

try {
    params = (defaultValues + params)
}
catch (Exception e) {
    params = defaultValues
} 

String toPayment = CustomKeywords.'utils.Utility.getDynamicRepoInfo'('OTO360', 'submit.toPayment', 'EN')

//WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC001_RM_F_Quotation Form'), [('params') : params], FailureHandling.STOP_ON_FAILURE)
WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Page Flow/TC004_RM_PF_Direct to Review Page'), [('params') : params], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC007_RM_F_Review Form'), [('params') : params], FailureHandling.STOP_ON_FAILURE)

//WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC002_RM_F_Plan Type'), [('params') : params], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC003_RM_F_Vehicle Details Form'), [('params') : params], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC004_RM_F_Personal Details Form'), [('params') : params], 
//    FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC005_RM_F_Bank Details Form'), [('params') : params], FailureHandling.STOP_ON_FAILURE)
//
WebUI.enhancedClick(findTestObject('Object Repository/OTO360/General/dybutton_submit_submit', [('submit') : toPayment]))
WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Page Flow/TC006_RM_PF_Make Payment'), [('params') : params], FailureHandling.STOP_ON_FAILURE)

