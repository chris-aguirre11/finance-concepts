package curves;

import persistence.dao.DailyTreasuryYieldCurveDao;
import persistence.dao.ZeroCurveDao;
import persistence.objects.DailyTreasuryYieldCurve;
import persistence.objects.ZeroCurve;

public class ZeroCurveBuilder {
	
	public static double[] zeroCurveRates;
	
	/**
	 * Generates a zero curve from the yieldCurveRates and investmentAmount
	 */
	public static void calculateZeroCurveRates(double[] yieldCurveRates, double investmentAmount) {
		
		zeroCurveRates = new double[yieldCurveRates.length]; 
		
		//Set 1st Zero Curve Rate to be the same as 1st Yield Curve Rate
		zeroCurveRates[0] = yieldCurveRates[0];
		
		int max = yieldCurveRates.length;
		for(int i = 2; i <= max; i++) {
			
			double yieldRateForCurrentYearPoint = yieldCurveRates[i-1]; //Use year2 and year3 for those years respectively
			
			double[] currentPresentValues = new double[yieldCurveRates.length];
			
			for(int y = 1; y <= i; y++) {
				if(y == i) {
					
					double sumOfPreviousPresentValues = 0.0;
					for(int z = 0; z < currentPresentValues.length; z++) {
						sumOfPreviousPresentValues += currentPresentValues[z];
					}
					
					double leftSideOfEquation = 1 - sumOfPreviousPresentValues;
					double leftSideOfEquation1 = Math.pow(leftSideOfEquation, (1.0/y));
					double lastCashFlowAmount = 1 + yieldRateForCurrentYearPoint;
					double rightSideOfEquation1 = Math.pow(lastCashFlowAmount, (1.0/y));
					double rightSideOfEquation2 = rightSideOfEquation1 - leftSideOfEquation1;
					double zeroRateForCurrentYearPoint = rightSideOfEquation2 / leftSideOfEquation1;
					
					zeroCurveRates[i - 1] = zeroRateForCurrentYearPoint;
				}
				else {
					double currentPresentValue = yieldRateForCurrentYearPoint 
							/ Math.pow((investmentAmount + zeroCurveRates[y-1]), y);
					
					currentPresentValues[y-1] = currentPresentValue;
				}
			}
		}
	}
	
	public static void persistZeroCurveToDb() {
		ZeroCurve zeroCurve = new ZeroCurve();
		
		zeroCurve.setMonth1(0.0);
		zeroCurve.setMonth3(0.0);
		zeroCurve.setMonth6(0.0);
		zeroCurve.setYear1(zeroCurveRates[0]);
		zeroCurve.setYear2(zeroCurveRates[1]);
		zeroCurve.setYear3(zeroCurveRates[2]);
		zeroCurve.setYear5(zeroCurveRates[4]);
		zeroCurve.setYear7(zeroCurveRates[6]);
		zeroCurve.setYear10(zeroCurveRates[9]);
		zeroCurve.setYear20(zeroCurveRates[19]);
		zeroCurve.setYear30(zeroCurveRates[29]);	
		
		ZeroCurveDao.register(zeroCurve);
	}
}
