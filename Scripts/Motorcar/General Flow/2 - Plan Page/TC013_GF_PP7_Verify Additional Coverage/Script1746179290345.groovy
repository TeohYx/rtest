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
import java.text.DecimalFormat as DecimalFormat

def addOnTitle = GlobalVariable.rules_addOn
	
def addOnPrice = [
	'before': [],
	'after': []
	]

WebUI.callTestCase(findTestCase('Reusable Module/Page Flow/TC005_RM_PF_Direct to Plan Page Specific'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.getText(findTestObject('Motorcar and Motorcycle/Plan Page/1 - Quotation Details/txt_Total Payable'))

for (title in addOnTitle) {
	WebUI.enhancedClick(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/button_AddOn', [('addon'): title]))
}
	
// Separated because the amount will change with different add on combination.
for (title in addOnTitle) {
	String priceText = WebUI.getText(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/text_AddOnPrice', [('price'): title]))
	double priceAmount = Double.parseDouble(priceText.replaceAll('[^0-9.]', ''))
	
	if (title == 'Accident Protection for You and Your Passenger') {
		addOnPrice['after'].add(priceAmount)
	} else {
		addOnPrice['before'].add(priceAmount)
	}
}

double totalAddOnAmountBefore = addOnPrice['before'].sum()
double totalAddOnAmountAfter = addOnPrice['after'].sum()

// Calculation 
DecimalFormat df = new DecimalFormat('#.##')

String NCDText = WebUI.getText(findTestObject('Motorcar and Motorcycle/Plan Page/1 - Quotation Details/txt_NCD Text'))
String basicPremium = WebUI.getText(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/1 - Quotation Details/txt_Basic Premium'))
String grossPremium = WebUI.getText(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/1 - Quotation Details/txt_Gross Premium'))
String grossPremiumDiscount = WebUI.getText(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/1 - Quotation Details/txt_Gross Premium_Discount Text'))
String grossPremiumServiceTax = WebUI.getText(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/1 - Quotation Details/txt_Gross Premium_Service Tax Text'))
String stampDuty = WebUI.getText(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/1 - Quotation Details/txt_Stamp Duty'))
String totalMotorcyclePremium = WebUI.getText(findTestObject('Object Repository/Motorcar and Motorcycle/Plan Page/1 - Quotation Details/txt_Total Payable'))

println([NCDText, basicPremium, grossPremium, grossPremiumDiscount, grossPremiumServiceTax, stampDuty, totalMotorcyclePremium])
println(addOnPrice)

int NCDAmount = Integer.parseInt(NCDText.replaceAll('[^0-9]', ''))
double NCDPercent = NCDAmount / 100
double basicPremiumAmount = Double.parseDouble(basicPremium.replaceAll('[RM,]', ''))
double grossPremiumDiscountAmount = Double.parseDouble(grossPremiumDiscount.replaceAll('[^0-9.]', '')) / 100
double grossPremiumServiceTaxAmount = Double.parseDouble(grossPremiumServiceTax.replaceAll('[^0-9.]', '')) / 100
double stampDutyAmount = Double.parseDouble(stampDuty.replaceAll('[RM,]', ''))
String totalMotorcyclePremiumAmount = totalMotorcyclePremium.replaceAll('[RM,]', '')

double calculatedGrossPremium = basicPremiumAmount * (1 - NCDPercent) + totalAddOnAmountBefore
double calculatedDiscount = calculatedGrossPremium * grossPremiumDiscountAmount
double calculatedServiceTax = (calculatedGrossPremium - calculatedDiscount) * grossPremiumServiceTaxAmount

WebUI.takeScreenshot()
WebUI.closeBrowser()

double result = (df.format(((calculatedGrossPremium - calculatedDiscount) + calculatedServiceTax) + stampDutyAmount + totalAddOnAmountAfter)).toDouble()

assert (result >= totalMotorcyclePremiumAmount.toDouble() - 0.01) && (result <= totalMotorcyclePremiumAmount.toDouble() + 0.01)

