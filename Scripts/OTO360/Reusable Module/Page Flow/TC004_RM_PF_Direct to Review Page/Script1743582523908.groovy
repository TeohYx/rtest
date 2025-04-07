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
 * 1. TC003_RM_PF_Direct to Aplication Page
 * 2. TC003_RM_F_Vehicle Details Form
 * 3. TC004_RM_F_Personal Details Form
 * 4. TC005_RM_F_Bank Details Form
 * 5. Click button to Review Page
 * -------------------------------------------------------------------------
 * Data:
 *
 * 1. TC003_RM_PF_Direct to Aplication Page
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
  		planClass: int (Required)
   }
   
   2. TC003_RM_F_Vehicle Details Form (No data at the moment)
   
   3. TC004_RM_F_Personal Details Form
    * params = {
		  name : String,
		  race : String,
		  gender : String,
		  mobileNumber : String,
		  email: String,
		  address1: String,
		  address2: String,
		  city: String,
		  postcode: String
   }
   
   4. TC005_RM_F_Bank Details Form
 * params = {
  		bankName: string (Required),
  		savingAccount: string (Required)
   }
 */
Map defaultValues = [:]

try {
    params = (defaultValues + params)
}
catch (Exception e) {
    params = defaultValues
} 

String toReview = CustomKeywords.'utils.Utility.getDynamicRepoInfo'('OTO360', 'submit.toReview', 'EN')

//WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Page Flow/TC003_RM_PF_Direct to Application Page'), [('params') : params], FailureHandling.STOP_ON_FAILURE)

//WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC001_RM_F_Quotation Form'), [('params') : params], FailureHandling.STOP_ON_FAILURE)
//
//WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC002_RM_F_Plan Type'), [('params') : params], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC003_RM_F_Vehicle Details Form'), [('params') : params], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC004_RM_F_Personal Details Form'), [('params') : params], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC005_RM_F_Bank Details Form'), [('params') : params], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Object Repository/OTO360/General/dybutton_submit_submit', [('submit') : toReview]))

WebUI.delay(3)
