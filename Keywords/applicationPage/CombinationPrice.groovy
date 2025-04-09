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

import bsh.This

import java.text.SimpleDateFormat as SimpleDateFormat
import java.text.DecimalFormat
import java.math.RoundingMode

import internal.GlobalVariable

public class CombinationPrice {
	public int planType = 0

	public double premiumRateDiscount = 0.25
	public double covidDiscount = 0.25
	public double adventurousDiscount = 0.25

	public int totalCaseCount = 0
	public int totalCount = 0
	public int successCount = 0
	public int failedCount = 0
	public def errorLog = []
	public def failedLog = []

	public int stopperPeriod = 3

	public def sdf = new SimpleDateFormat('dd/MM/yyyy')
	public def startDate = new Date()

	public String planWorkbook = 'PlanCombination'
	public String planSheet = 'Plan'
	public String adventurousSheet = 'Adventurous Activities'
	public String covidSheet = 'Covid'
	public String groupSheet = 'Group'
	public String dateDuration = 'DateDuration'

	public String planTypeHeader = 'planType'
	public String tripTypeHeader = 'tripType'
	public String areaHeader = 'area'
	public String planClassHeader = 'planClass'
	public String dateClassHeader = 'dateClass'
	public String premiumRateHeader = 'premiumRate'
	public String additionalWeekHeader = 'additionalWeek'
	public String dateDurationHeader = 'dateDuration'
	public String adventurousActivitiesHeader = 'adventurousActivities'
	public String covidHeader = 'covid'
	public String adultCountHeader = 'adultCount'
	public String childCountHeader = 'childCount'
	public String startDateString = 'startDate'
	public String endDateString = 'endDate'

	/**
	 * Act as a constructor for this class
	 * @param planType The type of plan of Travel product
	 * @return The object of this class
	 */
	@Keyword
	def initiateCombinationPrice(int planType) {
		def cP = new CombinationPrice()

		cP.planType = planType
		return cP
	}

	/**
	 * Calculate the total number of test case before perform API testing
	 * @param CPObj An object of CombinationPrice that have its own set of primitives
	 * @return None
	 */
	@Keyword
	def countTestCase(CombinationPrice CPObj) {
		int planAmount = 0
		int additionalWeekPlanCounter = 0
		int adventurousAmount = 0
		int covidAmount = 0
		int additionalWeekCovidCounter = 0
		int weekCounter = 0

		int totalTestCase = 0

		TestData data = findTestData(CPObj.planWorkbook)

		if (CPObj.planType != 5) {
			data.changeSheet(CPObj.planSheet)

			for (int i = 1; i <= data.getRowNumbers(); i++) {
				int pT = data.getValue(CPObj.planTypeHeader, i).toInteger()
				int dC = data.getValue(CPObj.dateClassHeader, i).toInteger()

				if (pT == CPObj.planType) {
					planAmount ++
					if (dC == 5) {
						additionalWeekPlanCounter ++
					}
				}
			}

			data.changeSheet(CPObj.dateDuration)

			for (int i = 1; i <= data.getRowNumbers(); i++) {
				int week = data.getValue(CPObj.dateClassHeader, i).toInteger()

				if (week == 5) {
					weekCounter ++
				}
			}

			data.changeSheet(CPObj.adventurousSheet)

			for (int i = 1; i <= data.getRowNumbers(); i++) {
				int pT = data.getValue(CPObj.planTypeHeader, i).toInteger()

				if (pT == CPObj.planType) {
					adventurousAmount ++
				}
			}

			data.changeSheet(CPObj.covidSheet)

			for (int i = 1; i <= data.getRowNumbers(); i++) {
				int pT = data.getValue(CPObj.planTypeHeader, i).toInteger()
				int week = data.getValue(CPObj.dateClassHeader, i).toInteger()

				if (pT == CPObj.planType) {
					covidAmount ++
					if (week == 5) {
						additionalWeekCovidCounter ++
					}
				}
			}

			totalTestCase =
					planAmount +
					additionalWeekPlanCounter * (weekCounter - 1) +
					adventurousAmount +
					covidAmount +
					additionalWeekCovidCounter * (weekCounter - 1)
		} else {
			data.changeSheet(CPObj.groupSheet)

			totalTestCase = data.getRowNumbers()
		}

		CPObj.totalCaseCount = totalTestCase
	}

	/**
	 * 
	 * Mainly called from main test case to start the API testing process
	 * @param CPObj An object of CombinationPrice that have its own set of primitives
	 * @return None
	 */
	@Keyword
	def planPriceChecker(CombinationPrice CPObj) {

		TestData data = findTestData(CPObj.planWorkbook)

		if (CPObj.planType == 5) {
			data.changeSheet(CPObj.groupSheet)

			runExcelTestCases(CPObj, data, CPObj.groupSheet)
		} else {
			data.changeSheet(CPObj.planSheet)

			runExcelTestCases(CPObj, data, CPObj.planSheet)

			data.changeSheet(CPObj.adventurousSheet)

			runExcelTestCases(CPObj, data, CPObj.adventurousSheet)

			data.changeSheet(CPObj.covidSheet)

			runExcelTestCases(CPObj, data, CPObj.covidSheet)
		}
	}

	/**
	 * 
	 * This method read a whole sheet given row by row, and perform UI API testing on GetTravelPlanPriceBreakdown
	 * @param CPObj An object of CombinationPrice that have its own set of primitives
	 * @param data The data from Data Files
	 * @param sheetName The specific sheet that current on
	 * @return None
	 */
	def runExcelTestCases(CombinationPrice CPObj, TestData data, String sheetName) {
		int stopper = 0

		String[] columns = data.getColumnNames()

		// Test case for adventurous activities add on
		for (int i = 1; i <= data.getRowNumbers(); i++) {
			Map columnItem = [:]

			int pT = Integer.parseInt(data.getValue(CPObj.planTypeHeader, i))

			if (pT == CPObj.planType) {
				stopper ++
				// Stop for a while after few run to prevent API crash
				if (stopper % CPObj.stopperPeriod == 0) {
					stopper = 0

					Thread.sleep(5000)
				}

				// Store row information of the current sheet of Data Files in the map
				for (columnName in columns) {
					if (columnName == CPObj.premiumRateHeader ||
							columnName == CPObj.additionalWeekHeader ||
							columnName == CPObj.adventurousActivitiesHeader ||
							columnName == CPObj.covidHeader) {
						columnItem[columnName] = data.getValue(columnName, i).toDouble()
					} else {
						columnItem[columnName] = data.getValue(columnName, i).toInteger()
					}
				}

				// Some information are constant for some sheet, and is specify here
				if (sheetName == CPObj.planSheet) {

					columnItem[CPObj.adultCountHeader] = 1
					columnItem[CPObj.childCountHeader] = 0
				} else if (sheetName == CPObj.adventurousSheet) {

					columnItem[CPObj.tripTypeHeader] = 1
					columnItem[CPObj.dateClassHeader] = 1
					columnItem[CPObj.adultCountHeader] = 1
					columnItem[CPObj.childCountHeader] = 0
				} else if (sheetName == CPObj.covidSheet) {

					columnItem[CPObj.tripTypeHeader] = 1
					columnItem[CPObj.planClassHeader] = 1
					columnItem[CPObj.adultCountHeader] = 1
					columnItem[CPObj.childCountHeader] = 0
				} else if (sheetName == CPObj.groupSheet) {

					columnItem[CPObj.tripTypeHeader] = 1
					columnItem[CPObj.areaHeader] = 1
					columnItem[CPObj.planClassHeader] = 1
					columnItem[CPObj.dateClassHeader] = 1
					columnItem[CPObj.adultCountHeader] = 1
					columnItem[CPObj.childCountHeader] = 0
				}

				columnItem[CPObj.startDateString] = CPObj.sdf.format(CPObj.startDate)
				def dateDurationList = getDateDuration(columnItem[CPObj.dateClassHeader], CPObj)

				// Get the number of possible duration and perform API testing for each of them
				for (duration in dateDurationList) {
					columnItem[CPObj.endDateString] = CPObj.sdf.format(CPObj.startDate + (duration-1))

					// GET API
					def APIResponse = getAPIResponse(columnItem, CPObj)

					// API Fail to obtained
					if (APIResponse.failedLog) {
						CPObj.failedCount ++
						CPObj.failedLog.add(APIResponse.failedLog)

						CPObj.totalCount ++
						println("Progress: ${CPObj.totalCount}/${CPObj.totalCaseCount}; Failed: ${CPObj.failedCount}")
						continue
					}

					// FAIL CASE (discount not apply/ not correct)
					if (!APIResponse.errorLog.isEmpty()) {
						CPObj.failedCount ++
						CPObj.errorLog.add(APIResponse.errorLog)

						CPObj.totalCount ++
						println("Progress: ${CPObj.totalCount}/${CPObj.totalCaseCount}; Failed: ${CPObj.failedCount}")
						continue
					}

					def totalPremium = APIResponse.totalPremium

					// Calculate total premium rate
					def premiumRate
					double addOnDiscount = 0
					if (sheetName == CPObj.planSheet) {

						double totalPremiumAfterAdditionalWeek = calculatePriceAfterAdditionalWeek(columnItem[CPObj.premiumRateHeader], columnItem[CPObj.additionalWeekHeader], duration)
						premiumRate = totalPremiumAfterAdditionalWeek
					} else if (sheetName == CPObj.adventurousSheet) {

						premiumRate = APIResponse.premiumRate

						addOnDiscount = columnItem[CPObj.adventurousActivitiesHeader] * (1 - CPObj.adventurousDiscount)
					} else if (sheetName == CPObj.covidSheet) {

						premiumRate = APIResponse.premiumRate

						double totalCovidAfterAdditionalWeek = calculatePriceAfterAdditionalWeek(columnItem[CPObj.covidHeader], columnItem[CPObj.additionalWeekHeader], duration)
						double covidDiscount = totalCovidAfterAdditionalWeek * (1 - CPObj.covidDiscount)
						addOnDiscount = covidDiscount
					} else if (sheetName == CPObj.groupSheet) {

						double premiumRateAfterAddGroup = calculatePriceAfterGroup(APIResponse.premiumRate, columnItem[CPObj.adultCountHeader], columnItem[CPObj.childCountHeader])
						premiumRate = premiumRateAfterAddGroup
					} else {
					}

					def calculatedTotalPremium = priceChange(APIResponse.premiumRate, APIResponse.discountAmount, APIResponse.taxAmount, APIResponse.stampDuty, addOnDiscount)

					DecimalFormat df = new DecimalFormat("#.##")
					df.setRoundingMode(RoundingMode.HALF_UP)

					double calculatedTotalPremiumRounded = df.format(calculatedTotalPremium).toDouble()
					double totalPremiumRounded = df.format(totalPremium).toDouble()

					// Compare premium rate with error range of +- 0.01
					if (calculatedTotalPremiumRounded >= df.format(totalPremiumRounded - 0.01).toDouble() && calculatedTotalPremiumRounded <= df.format(totalPremiumRounded + 0.01).toDouble()) {
						CPObj.successCount ++
					} else {
						CPObj.failedCount ++
						CPObj.errorLog.add([
							columnItem[CPObj.tripTypeHeader],
							columnItem[CPObj.planTypeHeader],
							columnItem[CPObj.areaHeader],
							columnItem[CPObj.planClassHeader],
							columnItem[CPObj.dateClassHeader],
							columnItem[CPObj.startDateString],
							columnItem[CPObj.endDateString]
						])
					}

					CPObj.totalCount ++
					println("Progress: ${CPObj.totalCount}/${CPObj.totalCaseCount}; Failed: ${CPObj.failedCount}")
				}
			}
		}
	}

	/**
	 * 
	 * @param premiumRate The basic premium rate
	 * @param adultCount The number of adult in buying the product
	 * @param childCount The number of child in buying the product
	 * @return The premium rate after considered the number of adult and number of child
	 */
	def calculatePriceAfterGroup(double premiumRate, int adultCount, int childCount) {
		return premiumRate * adultCount + 0 * childCount
	}

	/**
	 * 
	 * @param premiumPrice The basic premium rate
	 * @param discountAmount The discount that will apply in basic premium rate
	 * @param taxAmount The tax applied to the holder
	 * @param stampDuty The stamp duty applied to the holder
	 * @param addOn The extra add on such as adventurous activites or covid
	 * @return The total or final premium rate
	 */
	def priceChange(double premiumPrice, double discountAmount, double taxAmount, double stampDuty, double addOn = 0) {
		return premiumPrice - discountAmount + taxAmount + stampDuty + addOn
	}

	/**
	 * 
	 * @param datePeriod The duration of additional day, starting by 30
	 * @return The dateClass that will be used to compare with the column 'dateClass' in 'DateDuration' sheet of 'PlanCombination' Data Files
	 */
	def dateToDateClassConversion(int datePeriod){
		switch(datePeriod) {
			case 1..5:
				return 1
			case 6..10:
				return 2
			case 11..18:
				return 3
			case 19..30:
				return 4
			case { it > 30}:
				return 5
			default:
				return 0
		}
	}

	@Keyword
	def singlePlanPriceChecker(int planType, int tripType, int area, int planClass, int datePeriod, boolean haveAdventurousActivities, boolean haveCovid) {
		int totalCount = 0
		int successCount = 0
		int failedCount = 0
		def errorLog = []
		def failedLog = []
		def addOn = []

		boolean isSuccess = true

		def sdf = new SimpleDateFormat('dd/MM/yyyy')
		def startDate = new Date()

		TestData dataPlan = findTestData('PlanCombination')
		TestData dataAdventurous = findTestData('PlanCombination')
		TestData dataCovid = findTestData('PlanCombination')

		dataPlan.changeSheet('Plan')
		dataAdventurous.changeSheet('Adventurous Activities')
		dataCovid.changeSheet('Covid')

		// Test case for basic plan combinations
		for (int i = 1; i <= dataPlan.getRowNumbers(); i++) {
			int pT = Integer.parseInt(dataPlan.getValue('planType', i))
			int tT = dataPlan.getValue('tripType', i).toInteger()
			int a = dataPlan.getValue('area', i).toInteger()
			int pC = dataPlan.getValue('planClass', i).toInteger()
			int dC = dataPlan.getValue('dateClass', i).toInteger()

			int dateClass = dateToDateClassConversion(datePeriod)
			if (pT == planType && tT == tripType && a == area && pC == planClass && dC == dateClass) {
				double p = (dataPlan.getValue('premiumRate', i)).toDouble()
				double aW = (dataPlan.getValue('additionalWeek', i).toDouble())

				def startDateItem = sdf.format(startDate)
				def endDateItem = sdf.format(startDate + (datePeriod-1))

				if (haveAdventurousActivities) {
					addOn.add(6)

					for (int j = 1; j <= dataAdventurous.getRowNumbers(); j++) {
						pT = Integer.parseInt(dataAdventurous.getValue('PlanType', j))
						a = dataAdventurous.getValue('area', j).toInteger()
						pC = dataAdventurous.getValue('planClass', j).toInteger()

						if (pT == planType && a == area && pC == planClass) {
							double aA = (dataAdventurous.getValue('adventurousActivities', j).toDouble())

							if (haveCovid) {
								addOn.add(1)

								// Test case for covid add on
								for (int k = 1; k <= dataCovid.getRowNumbers(); k++) {
									pT = Integer.parseInt(dataCovid.getValue('planType', k))
									a = dataCovid.getValue('area', k).toInteger()
									dC = dataCovid.getValue('dateClass', k).toInteger()

									if (pT == planType && a == area && dC == dateClass) {
										double covid = (dataCovid.getValue('covid', k)).toDouble()
										double cAW = (dataCovid.getValue('additionalWeek', k).toDouble())
										println([
											tT,
											pT,
											a,
											pC,
											dC,
											startDateItem,
											endDateItem,
											addOn
										])
										def APIResponse = getAPIResponse(tT, pT, a, pC, startDateItem, endDateItem, addOn)

										if (APIResponse.isFailed || APIResponse.isDiscountRateFailed) {
											failedCount ++
											errorLog.add([
												tT,
												pT,
												a,
												pC,
												dC,
												startDateItem,
												endDateItem
											])

											continue
										}


										double premiumAfterAdditionalWeek = calculatePriceAfterAdditionalWeek(p, aW, datePeriod)
										double aADiscount = aA * 0.75
										double totalCovidAfterAdditionalWeek = calculatePriceAfterAdditionalWeek(covid, cAW, datePeriod)
										double covidDiscount = totalCovidAfterAdditionalWeek * 0.75

										println([
											p,
											aW,
											premiumAfterAdditionalWeek,
											APIResponse.discountAmount,
											APIResponse.taxAmount,
											APIResponse.stampDuty,
											aADiscount,
											covidDiscount
										])

										def calculatedTotalPremium =
												premiumAfterAdditionalWeek -
												APIResponse.discountAmount +
												APIResponse.taxAmount +
												APIResponse.stampDuty +
												aADiscount +
												covidDiscount

										def totalPremium = APIResponse.totalPremium

										DecimalFormat df = new DecimalFormat("#.##")
										df.setRoundingMode(RoundingMode.HALF_UP)

										double calculatedTotalPremiumRounded = df.format(calculatedTotalPremium).toDouble()
										double totalPremiumRounded = df.format(totalPremium).toDouble()
										println(calculatedTotalPremiumRounded)
										println(totalPremiumRounded)
										println([i, j, k])
										if (calculatedTotalPremiumRounded >= df.format(totalPremiumRounded - 0.01).toDouble() && calculatedTotalPremiumRounded <= df.format(totalPremiumRounded + 0.01).toDouble()) {
											isSuccess = true
										} else {
											isSuccess = false
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return isSuccess
	}

	/**
	 * 
	 * @param price The basic premium rate 
	 * @param additionalWeekPrice The additional price calculated from addition week
	 * @param duration The duration of additional day, starting by 30
	 * @return
	 */
	def calculatePriceAfterAdditionalWeek(double price, double additionalWeekPrice, int duration) {
		double additionalWeeks = (duration - 30) / 7
		int additionalWeeksToPay = Math.ceil(additionalWeeks)

		double additionalPrice = price + additionalWeekPrice * additionalWeeksToPay

		return additionalPrice
	}

	/**
	 * @param dateClass The class of the date representing different period
	 * @param CPObj An object of CombinationPrice that have its own set of primitives
	 * @return The list of date duration based on the dateClass, where the information is obtained from the 'DateDuration' sheet of 'PlanCombination' Data Files
	 */
	def getDateDuration(int dateClass, CombinationPrice CPObj) {
		TestData data = findTestData(CPObj.planWorkbook)

		data.changeSheet(CPObj.dateDuration)

		def dateDurationList = []

		for (int i = 1; i <= data.getRowNumbers(); i++) {
			int date = data.getValue(CPObj.dateClassHeader, i).toInteger()

			if (date == dateClass) {
				int dateDuration = data.getValue(CPObj.dateDurationHeader, i).toInteger()

				dateDurationList.add(dateDuration)
			}
		}

		return dateDurationList
	}

	/**
	 * Take necessary input and feed into a request body, then send a POST request to GetTravelPlanPriceBreakdown API to get back appropriate response
	 * @param mapItem A map that contains all the necessary input 
	 * @param CPObj An object of CombinationPrice that have its own set of primitives
	 * @return mapping of necessary information
	 */
	def getAPIResponse(Map mapItem, CombinationPrice CPObj) {

		// Manage selectedAddOnId
		def selectedAddOnId = []

		if (mapItem.containsKey(CPObj.adventurousActivitiesHeader)) {
			selectedAddOnId = [6]
		}

		if (mapItem.containsKey(CPObj.covidHeader)) {
			selectedAddOnId = [1]
		}

		int failedCount = 0
		def failedLog = []
		def errorLog = []
		Boolean isFailed = false
		Boolean isDiscountRateFailed = false

		def premiumRate = 0
		def discountAmount = 0
		def discountRate = 0
		def taxAmount = 0
		def stampDuty = 0
		def totalPremium = 0

		String requestBody = """
						{
							"productCode": "BPT",
							"tripType": "${mapItem['tripType']}",
							"planType": "${mapItem['planType']}",
							"area": "${mapItem['area']}",
							"planClass": ${mapItem['planClass']},
							"adultCount": ${mapItem['adultCount']},
							"childCount": ${mapItem['childCount']},
							"startDate": "${mapItem['startDate']}",
							"endDate": "${mapItem['endDate']}",
							"selectedAddOnId": $selectedAddOnId,
							"language": "en",
							"isStaff": false,
							"agentType": 0,
							"countryCode": "MYS",
							"isMAEM2U": false
						}
					"""
		//		println(mapItem)
		//		println(requestBody)
		//		System.in.read()


		String apiUrl = GlobalVariable.api_GetTravelPlanPriceBreakdown

		def response = (new applicationPage.API().callApi(apiUrl, requestBody))

		// Check response
		if ((new applicationPage.API().getApiStatus(response)) == 200) {
			def message = (new applicationPage.API().getApiMessage(response))
			def data = (new applicationPage.API().getApiData(response))

			// Check if the price displayed in price breakdown correctly
			premiumRate = data?.plan?.premiumRate
			discountRate = data?.plan?.discount[0]?.discountRate
			discountAmount = data?.plan?.discountAmount
			taxAmount = data?.taxAmount
			stampDuty = data?.stampDuty
			totalPremium = data?.totalPremium

			// Sometimes the api did not make the discount, therefore check if discount have value
			if (discountAmount == null || discountAmount == "0") {
				isFailed = true
			}
			isDiscountRateFailed = (discountRate !== null && discountRate === 0.25) ? true : false

			if (isFailed || isDiscountRateFailed) {
				errorLog.add([
					mapItem[CPObj.tripTypeHeader],
					mapItem[CPObj.planTypeHeader],
					mapItem[CPObj.areaHeader],
					mapItem[CPObj.planClassHeader],
					mapItem[CPObj.startDateString],
					mapItem[CPObj.endDateString],
					selectedAddOnId
				])
			}
		} else {
			failedCount ++
			failedLog.add([
				mapItem[CPObj.tripTypeHeader],
				mapItem[CPObj.planTypeHeader],
				mapItem[CPObj.areaHeader],
				mapItem[CPObj.planClassHeader],
				mapItem[CPObj.startDateString],
				mapItem[CPObj.endDateString],
				selectedAddOnId
			])
		}

		def result = [
			premiumRate : premiumRate,
			discountAmount : discountAmount,
			taxAmount : taxAmount,
			stampDuty : stampDuty,
			totalPremium : totalPremium,
			failedCount : failedCount,
			failedLog : failedLog,
			errorLog : errorLog
		]

		return result
	}

	// For single test case
	def getAPIResponse(int tripTypeItem, int planTypeItem, int areaItem, int planClassItem, def startDateItem, def endDateItem, def addOn = []) {
		int failedCount = 0
		def failedLog = []
		Boolean isFailed = false
		Boolean isDiscountRateFailed = false

		def premiumRate = 0
		def discountAmount = 0
		def discountRate = 0
		def taxAmount = 0
		def stampDuty = 0
		def totalPremium = 0

		String requestBody = """
						{
							"productCode": "BPT",
							"tripType": "$tripTypeItem",
							"planType": "$planTypeItem",
							"area": "$areaItem",
							"planClass": $planClassItem,
							"adultCount": 1,
							"childCount": 0,
							"startDate": "$startDateItem",
							"endDate": "$endDateItem",
							"selectedAddOnId": $addOn,
							"language": "en",
							"isStaff": false,
							"agentType": 0,
							"countryCode": "MYS",
							"isMAEM2U": false
						}
					"""

		String apiUrl = GlobalVariable.api_GetTravelPlanPriceBreakdown

		def response = (new applicationPage.API().callApi(apiUrl, requestBody))

		// Check response
		if ((new applicationPage.API().getApiStatus(response)) == 200) {
			def message = (new applicationPage.API().getApiMessage(response))
			def data = (new applicationPage.API().getApiData(response))

			// Check if the price displayed in price breakdown correctly
			premiumRate = data?.plan?.premiumRate
			discountRate = data?.plan?.discount[0]?.discountRate
			discountAmount = data?.plan?.discountAmount
			taxAmount = data?.taxAmount
			stampDuty = data?.stampDuty
			totalPremium = data?.totalPremium

			println(discountRate)

			// Sometimes the api did not make the discount, therefore check if discount have value
			if (discountAmount == null || discountAmount == "0") {
				isFailed = true
			}

			isDiscountRateFailed = (discountRate !== null && discountRate === 0.25) ? true : false
		} else {
			failedCount ++
			failedLog.add([
				tripTypeItem,
				planTypeItem,
				areaItem,
				planClassItem,
				startDateItem,
				endDateItem,
				addOn
			])
		}

		def result = [
			premiumRate : premiumRate,
			discountAmount : discountAmount,
			taxAmount : taxAmount,
			stampDuty : stampDuty,
			totalPremium : totalPremium,
			failedCount : failedCount,
			failedLog : failedLog,
			isFailed : isFailed,
			isDiscountRateFailed : isDiscountRateFailed,
		]

		return result
	}
}
