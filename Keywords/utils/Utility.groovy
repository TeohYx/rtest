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
import java.time.LocalDate
import java.time.Period

public class Utility {
	// All

	/**
	 * 
	 * @param productRow The name of the product
	 * @param productName The specific name in the product
	 * @param version The specific version of the result
	 * @return String The specific version of the result
	 */
	@Keyword
	def getDynamicRepoInfo(String productRow, String productName, String version) {
		TestData data = findTestData("ObjectRepository")
		data.changeSheet("Dynamic")

		def header = data.getColumnNames()

		if (!header.contains(version)) {
			return [
				'status': 'error',
				'text': "The given version do not match the header of the file."
			]
		}

		for (int i = 1; i <= data.getRowNumbers(); i++) {
			String product = data.getValue("Product", i)
			String name = data.getValue("Name", i)

			if (product == productRow && name == productName) {
				String versionValue = data.getValue(version, i)

				return versionValue
			}
		}

		// If the version cannot be found, means the given data cannot be found in the file.
		String errorText = "The version of ${version} on Product: ${productRow} and Name: ${productName} cannot be located."
		return [
			'status': 'error',
			'text': errorText
		]
	}

	/**
	 * Return today date information depends on input
	 * @param returnType 1: year; 2: month; 3: day; 0: DOB (DDMMYYYY) (default)
	 * @return
	 */
	@Keyword
	def getTodayDate(int returnType = 0) {
		LocalDate today = LocalDate.now()

		String year = today.year.toString()
		String month = today.monthValue.toString()
		String day = today.dayOfMonth.toString()

		if (returnType == 1) {
			return year
		} else if (returnType == 2) {
			return month
		} else if (returnType == 3) {
			return day
		} else {
			String DOB = "${day}${month}${year}"

			return DOB
		}
	}

	/**
	 * Return date from n day
	 * @param addDay day from today, can be negative
	 * @param returnType 1: year; 2: month; 3: day; 0: DOB (DDMMYYYY) (default)
	 * @return
	 */
	@Keyword
	def getSpecificDate(int addDay, int returnType = 0) {
		LocalDate specificDay = LocalDate.now().plusDays(addDay)

		String year = specificDay.year.toString()
		String month = specificDay.monthValue.toString()
		String day = specificDay.dayOfMonth.toString()

		if (returnType == 1) {
			return year
		} else if (returnType == 2) {
			return month
		} else if (returnType == 3) {
			return day
		} else {
			month = String.format("%02d", month.toInteger())
			day = String.format("%02d", day.toInteger())

			String DOB = "${day}${month}${year}"

			return DOB
		}
	}

	/**
	 * Get the input of age range, and return edge case for each value range
	 * @param ageRange regex for 2 value (>=18->=70)
	 '>' or '<': Does not include the number. eg: >18 means 19 or above; <18 means below 18 (18year0day does not included)
	 '>=' or '<=': Included the number. eg: >=18 means 18 or above; <=18 means below 19 (maximum of 18 - 18years364days or below)
	 'd': Indicate the number is day instead of year.
	 * @param [
	 returnType: 1,  1: NRIC; 2: DOB
	 ageAfterBirthday: false,   true: Uses current age idea; false: Uses Age after birthday idea.
	 eg: Current age: 18 -> 06/03/2007 (positive)
	 Age after birthday: 18 -> 06/03/2008 (positive)
	 ]
	 * @return [
	 'positive': String,
	 'negative': String
	 ]
	 */
	@Keyword
	def obtainAgeEdgeCaseAgeToICRange(String ageRange, Map params = [:]) {
		def defaults = [
			returnType: 1,
			ageAfterBirthday: false,
		]

		def options = defaults + params

		def requiredParameter = []

		if (requiredParameter.any { it == null }) {
			return [
				'status': 'error',
				'text': 'Some required parameter does not provided.'
			]
		}


		def agePeriod = ageRange.split('-')

		def resultCase = [
			'positive': [],
			'negative': []
		]
		agePeriod.each { ageRegex ->
			def result = obtainAgeEdgeCaseAgeToIC(ageRegex, params = options)

			resultCase.each { cases, _ ->
				if (cases == 'positive') {
					resultCase[cases].addAll(result[cases])
				} else if (cases == 'negative') {
					resultCase[cases].addAll(result[cases])
				}
			}
		}

		return resultCase
	}

	/**
	 * Get the input of age, and will return 2 data: 1 pass edge case, and 1 negative case
	 * eg: 18 -> Today Date: 2025/03/06 (Pass: 2007/03/06; Fail: 2007/03/07)
	 * @param ageRegex regex for one value (eg: >=18)
	 '>' or '<': Does not include the number. eg: >18 means 19 or above; <18 means below 18 (18year0day does not included)
	 '>=' or '<=': Included the number. eg: >=18 means 18 or above; <=18 means below 19 (maximum of 18 - 18years364days or below)
	 'd': Indicate the number is day instead of year.
	 * @param [
	 returnType: 1,  1: NRIC; 2: DOB; 3: Age
	 ageAfterBirthday: false,   true: Uses current age idea; false: Uses Age after birthday idea. 
	 eg: Current age: 18 -> 06/03/2007 (positive)
	 Age after birthday: 18 -> 06/03/2008 (positive)
	 ]
	 * @return [
	 'positive': String,
	 'negative': String
	 ]
	 */
	@Keyword
	def obtainAgeEdgeCaseAgeToIC(String ageRegex, Map params = [:]) {
		def defaults = [
			returnType: 1,
			ageAfterBirthday: false,
		]

		def options = defaults + params

		def requiredParameter = []

		if (requiredParameter.any { it == null }) {
			return [
				'status': 'error',
				'text': 'Some required parameter does not provided.'
			]
		}

		int age = ageRegex.findAll(/\d+/)[0].toInteger()	// Get the numerical value that representing year/day

		LocalDate today = LocalDate.now()

		LocalDate positiveCase = today
		LocalDate negativeCase = today

		if (ageRegex =~ /\d(?=d)/) {
			// Check if the number is day instead of year
			positiveCase = positiveCase.minusDays(age)
			negativeCase = positiveCase.plusDays(1)
		} else {
			// The number is year
			positiveCase = today.minusYears(age)
			negativeCase = positiveCase

			// The inclusive/exclusive need to be considered. If it is about days, then it can be passed.
			if (ageRegex =~ /<(?=\d)/) {
				// Number Exclusive for < (maximum)
				// eg: 18age; Today: 06/03/2025; Positive: 07/03/2007 (17yo364days); Negative: 06/03/2007 (18yo)
				positiveCase = positiveCase.plusDays(1)
			} else if (ageRegex =~ />(?=\d)/) {
				// Number Exclusive for > (minimum)
				// eg: 18age; Today: 06/03/2025; Positive: 06/03/2006 (19yo); Negative: 07/03/2006 (18yo364days)
				positiveCase = positiveCase.minusYears(1)
				negativeCase = negativeCase.minusYears(1).plusDays(1)
			} else if (ageRegex =~ /<=(?=\d)/) {
				// Number Inclusive for <= (maximum)
				// eg: 18age; Today: 06/03/2025; Positive: 07/03/2006 (18yo364days); Negative: 06/03/2006 (19yo)
				positiveCase = positiveCase.minusYears(1).plusDays(1)
				negativeCase = negativeCase.minusYears(1)
			} else if (ageRegex =~ />=(?=\d)/) {
				// Number Inclusive for >= (minimum)
				// eg: 18age; Today: 06/03/2025; Positive: 06/03/2007 (18yo); Negative: 07/03/2007 (17yo364days)
				negativeCase = negativeCase.plusDays(1)
			} else {
				return [
					'status': 'error',
					'text': '\'<\', \'>\', \'<=\' or \'>-\' are required but is not found.'
				]
			}
		}

		// Different way of considering holder age. (CurrentAge or AgeAfterBirthday)
		LocalDate positiveCaseFinal = options.ageAfterBirthday ? positiveCase.plusYears(1) : positiveCase
		LocalDate negativeCaseFinal = options.ageAfterBirthday ? negativeCase.plusYears(1) : negativeCase

		Map type = [
			'positive': positiveCaseFinal,
			'negative': negativeCaseFinal
		]

		Map returnData = [:]
		type.each { cases, data ->
			String year = data.year
			String month = String.format("%02d", data.monthValue)
			String day = String.format("%02d", data.dayOfMonth)

			String output = ""
			if (options.returnType == 2) {
				output = "${day}/${month}/${year}"
			} else if (options.returnType == 3) {
				Period period = Period.between(today, data)
				int ageInYear = Math.abs(period.getYears())

				year = String.format("%02d", (data.year % 100))
				String ic = "${year}${month}${day}070605"

				output = [
					ic: ic,
					age: ageInYear
				]
				//				output = [ic, ageInYear]
			} else {
				year = String.format("%02d", (data.year % 100))

				output = "${year}${month}${day}070605"
			}

			returnData[cases] = output
		}

		returnData['status'] = 'pass'
		return returnData
	}

	/**
	 * Process the value to make it inclusive for minimum, exclusive for maximum. Eg (18-81) means equal or more than 18 (inclusive of 18); less than 81 (exclusive of 81).
	 * @param ageRange string regex
	 * @param params
	 * @return [
	 'minimum': int,
	 'maximum': int
	 ]
	 * 
	 */
	@Keyword
	def obtainAgeRange(String ageRange, Map params = [:]) {
		def defaults = [
			ageAfterBirthday: false,
		]

		def options = defaults + params

		def requiredParameter = []

		if (requiredParameter.any { it == null }) {
			return [
				'status': 'error',
				'text': 'Some required parameter does not provided.'
			]
		}

		def valueList = (ageRange =~ /\d+/).collect { it.toInteger()}

		Map result = [:]

		// Process the value to make it inclusive. Eg (18-80) means equal or more than 18; equal or less than 80.
		valueList.each { value ->
			if (ageRange =~ /<(?=\d)/) {
				result['minimum'] = valueList[0]
				result['maximum'] = valueList[1]
			} else if (ageRange =~ />(?=\d)/) {
				result['minimum'] = valueList[0] + 1
				result['maximum'] = valueList[1]
			} else if (ageRange =~ /<=(?=\d)/) {
				result['minimum'] = valueList[0]
				result['maximum'] = valueList[1] + 1
			} else if (ageRange =~ />=(?=\d)/) {
				result['minimum'] = valueList[0]
				result['maximum'] = valueList[1]
			} else {
				return [
					'status': 'error',
					'text': '\'<\', \'>\', \'<=\' or \'>-\' are required but is not found.'
				]
			}
		}

		return result
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
