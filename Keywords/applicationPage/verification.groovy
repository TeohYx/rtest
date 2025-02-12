package applicationPage

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

import internal.GlobalVariable

import java.time.*


public class verification {
	@Keyword
	def verifyNRICwithDOB(String NRICInput, String DOB) {
		/* If the IC year is larger than the current year, the year in DOB will be 19' instead of 20.
		 * For example, 101010071110 (10) in IC with current year (2024) will result in 2010 as year in DOB.
		 * 
		 * Returns: Lists of two date: date from NRIC and date from DOB in format of list: [day, month, year]
		 */
		String date1 = NRICInput.substring(0, 6)

		String year = date1.substring(0, 2)
		String originalYear = convertYear(year)
		String month = date1.substring(2, 4)
		String day = date1.substring(4, 6)

		String[] splitdate1 = [day, month, originalYear]
		String[] date2 = DOB.split("/")

		return [splitdate1, date2]
	}

	@Keyword
	def verifyNRICwithGender(String NRIC) {
		int NRICNumber = Integer.parseInt(NRIC)

		return NRICNumber % 2
	}

	def convertYear(String year) {
		/* If the IC year is larger than the current year, the year in DOB will be 19' instead of 20.
		 * For example, 101010071110 (10) in IC with current year (2024) will result in 2010 as year in DOB.
		 */
		String thisYear = Year.now()

		int shortFormLength = 2

		int beginIndex = thisYear.length() - shortFormLength

		String thisYearShort = thisYear.substring(beginIndex, thisYear.length())
		int thisYearShortInt = Integer.parseInt(thisYearShort)

		int givenYearInt = Integer.parseInt(year)

		String originalYear = givenYearInt > thisYearShortInt ? '19' + year : '20' + year

		return originalYear
	}
}

