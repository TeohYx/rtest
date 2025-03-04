package utils

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.Keys as Keys
import internal.GlobalVariable

import com.kms.katalon.core.configuration.RunConfiguration
import groovy.text.SimpleTemplateEngine

import com.kms.katalon.core.testobject.ConditionType

public class Utility {
	// Process template with variables
	String process(String template, Map binding) {
		def engine = new SimpleTemplateEngine()
		return engine.createTemplate(template).make(binding).toString()
	}

	@Keyword
	def getXPath(String objectName, String binding) {
		TestData data = findTestData('ObjectRepository')
		data.changeSheet('Object Repository')

		for (int i=1; i<=data.getRowNumbers(); i++) {
			if (data.getValue('objectName', i) == objectName) {
				String dataXPath = data.getValue('xpath', i)
				String dataBinding = data.getValue('binding', i)
				Map bindingMap = [:]
				bindingMap[dataBinding] = binding

				String xpathResult = process(dataXPath, bindingMap)

				def xpathLocator = new TestObject('dynamicObject').addProperty('xpath', ConditionType.EQUALS, xpathResult)
				println(xpathLocator.xpaths)
				return xpathLocator
			}
		}
	}

	/**
	 * Some input will refresh on input click/ input out
	 * @param to TestObject
	 * @param text text to input
	 */
	@Keyword
	def enterText(TestObject to, String text) {
		WebUI.clearText(to)
		WebUI.sendKeys(to, Keys.chord(Keys.CONTROL, 'a'));
		WebUI.sendKeys(to, text);
	}

	// Travel


	// Motorcar


	// Motorcycle


	// Hohh

	/**
	 * Operation to evenly distribute the amount to every contents.
	 * If isPositiveCase is true, then the sum of list provided are exactly the 'amount'.
	 * If isPositiveCase is false, then adjustment is made to make it not met the range requirement. 
	 * @param rangeOfAmount List contains min and max of the total content amount ranges. eg: [min, max]
	 * @param numberOfDistribution
	 * @param isPositiveCase boolean true: test for valid case; false: test for invalid case
	 * @return inputAmountList List <List> List of list that contains the distribution of the amount to every contents
	 */
	@Keyword
	def amountDistribution(def rangeOfAmount, int numberOfDistribution, boolean isPositiveCase) {
		// Divide amount by number.
		// Round the result down to nearest 1000.
		// Multiply the discarded hundred amount and the numberOfDistributton. The result is add to the first index.
		// Return a list and length of numberOfDistribution
		def inputAmountList = []
		rangeOfAmount.eachWithIndex { amount, index ->
			int roundOffDigit = 1000
			int smallestEdgeCaseValue = 1  // A smallest value that make the value to fail the case. (eg: minimum = 10000; value = 10000-1000=9000 -> fail)

			double dividedAmount = amount / numberOfDistribution

			def roundedAmount = (dividedAmount / roundOffDigit).toInteger() * roundOffDigit // Round to nearest 1000
			def differenceTotal = (dividedAmount - roundedAmount) * numberOfDistribution

			def addedAmount = roundedAmount + differenceTotal

			def inputAmount = [roundedAmount.toInteger()] * numberOfDistribution
			inputAmount[0] = addedAmount.toInteger()

			if (!isPositiveCase) {
				int smallestValue = smallestEdgeCaseValue * roundOffDigit
				if (index == 0) {
					inputAmount[0] -= smallestValue
				} else if (index == 1) {
					inputAmount[0] += smallestValue
				}
			}

			inputAmountList.add(inputAmount)
		}
		return inputAmountList
	}
}
