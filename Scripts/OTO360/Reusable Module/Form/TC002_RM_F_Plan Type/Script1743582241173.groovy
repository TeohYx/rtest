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
 * Plan Form
 * @author 80012455
 * 
 * 1. Info
 * - Plan Class: silver, gold, platinum
 * 
 * -------------------------------------------------------------------------
 * Data - By the default the data is set to choose on silver plan
 * 
 * params = {
  		planClass: int (Required)
   }
 * 
 */
def infoColumn = 'default'

Map defaultValues = [ // Original parameter names
    	('planClass') : CustomKeywords.'utils.Utility.getOTOInfo'('planClass', infoColumn)
	]
println(defaultValues)
try {
    params = (defaultValues + params)
}
catch (Exception e) {
    params = defaultValues
} 

def planClass = [
		1: findTestObject('Object Repository/OTO360/Plan Page/button_silverPlan'),
		2: findTestObject('Object Repository/OTO360/Plan Page/button_goldPlan'),
		3: findTestObject('Object Repository/OTO360/Plan Page/button_platinumPlan')
	]
//println(params['planClass'])
int selectedPlanClass = params['planClass'].toInteger()
WebUI.enhancedClick(planClass[selectedPlanClass])

