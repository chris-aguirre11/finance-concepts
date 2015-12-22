package curves;

public class ZeroCurveBuilder {
	
	public static void main(String[] args) {
		double[] yieldCurveRates = {
				0.05, 0.06, 0.07, 0.08, 0.09
		};
		
		double[] zeroCurveRates = ZeroCurveBuilder.calculateZeroCurveRates(yieldCurveRates, 1.0);
		System.out.println();
	}
	
	/**
	 * Generates a zero curve from the yieldCurveRates and investmentAmount
	 */
	public static double[] calculateZeroCurveRates(double[] yieldCurveRates, double investmentAmount) {
		
		double[] zeroCurveRates = new double[yieldCurveRates.length]; 
		
		//Set 1st Zero Curve Rate to be the same as 1st Yield Curve Rate
		zeroCurveRates[0] = yieldCurveRates[0];
		
		int max = yieldCurveRates.length;
		for(int i = 2; i <= max; i++) {
			
			double yieldRateForCurrentYearPoint = yieldCurveRates[i-1];
			
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
		return zeroCurveRates;
	}
}
