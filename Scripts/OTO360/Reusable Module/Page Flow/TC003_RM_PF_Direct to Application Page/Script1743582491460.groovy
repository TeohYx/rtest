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
 * 1. TC002_RM_PF_Direct to Plan Page
 * 2. TC002_RM_F_Plan Type
 * 3. Click button to Application Page
 * -------------------------------------------------------------------------
 * Data:
 *
 * 1. TC001_RM_F_Quotation Form
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
  		emailForRenewal: String (Required)
   }
 *
 * 2. TC002_RM_F_Plan Type
 * params = {
  		planClass: int (Required)
   }
 */
Map defaultValues = [:]

try {
    params = (defaultValues + params)
}
catch (Exception e) {
    params = defaultValues
} 

String toApplication = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "submit.toApplication", "EN")

//WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Page Flow/TC002_RM_PF_Direct to Plan Page'), [('params') : params], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC002_RM_F_Plan Type'), [('params') : params], FailureHandling.STOP_ON_FAILURE)

WebUI.enhancedClick(findTestObject('Object Repository/OTO360/General/dybutton_submit_submit',
	[('submit'): toApplication]))

WebUI.delay(3)
