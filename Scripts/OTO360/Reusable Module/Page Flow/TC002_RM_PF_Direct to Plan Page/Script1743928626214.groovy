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
 * 1. TC008_Motor Coverage Form
 * 2. TC009_Fill in Vehicle Details Form
 * 2. TC010_Renewal Email Form
 * -------------------------------------------------------------------------
 * Data:
 *
 * 1. TC008_Motor Coverage Form
 * params = {
  		idType: MYKAD (String),
  		ic: 000928070605 (String),
  		vehicleNumber: VOA0005 (String),
  		passport: (Optional) (String),
  		nationality: (Optional) (String),
  		dateOfBirth: (Optional) (String),
  		armyIdNo: (Optional) (String),
  		policeIdNo: (Optional) (String),
  		column_set: (Optional) (String)
   }
   
   2. TC009_Fill in Vehicle Details Form
   -
   
   3. TC010_Renewal Email Form
   params = {
		emailForRenewal: String (Required)
   }
 */

infoColumn = 'default'

Map defaultValues = [
		('vehicleNumber') : CustomKeywords.'utils.Utility.getOTOInfo'('vehicleNumber', infoColumn), 
	]

try {
	params = (defaultValues + params)
}
catch (Exception e) {
	params = defaultValues
}

String proceedPlan = CustomKeywords.'utils.Utility.getDynamicRepoInfo'("OTO360", "submit.toPlan", "EN")

WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Page Flow/TC001_RM_C_Open Application'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC008_Motor Coverage Form'), [('params') : params], FailureHandling.STOP_ON_FAILURE)

if (params['vehicleNumber'] in GlobalVariable.rules_eligiblePlateNumber) {
	WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC009_Fill in Vehicle Details Form'), [('params') : params], FailureHandling.STOP_ON_FAILURE)
	
	WebUI.enhancedClick(findTestObject('Object Repository/OTO360/General/dybutton_submit_submit',
		[('submit'): proceedPlan]))
	WebUI.delay(3)
} else if (params['vehicleNumber'] in GlobalVariable.rules_purchasedPlateNumber) {
	WebUI.callTestCase(findTestCase('OTO360/Reusable Module/Form/TC010_Renewal Email Form'), [('params') : params], FailureHandling.STOP_ON_FAILURE)
} else {
	
}



