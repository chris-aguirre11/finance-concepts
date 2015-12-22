package curves;

import java.util.List;

public class ZeroCurveBuilder {
	
	double[] yieldCurveRates;
	double rZ1 = 0.0; //Zero Rate for Year 1
	double investmentAmount = 1.0;
	double[] zeroCurveRates;
	
	public ZeroCurveBuilder(double[] yieldCurveRates, double investmentAmount) {
		this.yieldCurveRates = yieldCurveRates;
		this.investmentAmount = investmentAmount;
	}
	
	public static void main(String[] args) {
		double[] yieldCurveRates = {
				0.05, 0.06, 0.07
		};
		
		ZeroCurveBuilder zBuilder = new ZeroCurveBuilder(yieldCurveRates, 1.0);
		zBuilder.calculateZeroRates();
	}
	
	/**
	 * Generates a zero curve from the yieldCurve Rates
	 */	
	public double[] calculateZeroRates() {
		
		zeroCurveRates = new double[yieldCurveRates.length]; 
		
		//Calcuate rZ1
		zeroCurveRates[0] = yieldCurveRates[0];
		rZ1 = zeroCurveRates[0];
		
		
		int max = 3;
		for(int i = 2; i <= max; i++) {
			
			double yieldRateForCurrentYearPoint = yieldCurveRates[i-1];
			
			double[] currentPresentValues = new double[yieldCurveRates.length];
			
			for(int y = 1; y <= i; y++) {
				if(y == i) {
					double currentPresentValue = (1 + yieldRateForCurrentYearPoint) 
							/ Math.pow((investmentAmount + zeroCurveRates[y-1]), y);
					
					currentPresentValues[y-1] = currentPresentValue;
				}
				double currentPresentValue = yieldRateForCurrentYearPoint 
						/ Math.pow((investmentAmount + zeroCurveRates[y-1]), y);
				
				currentPresentValues[y-1] = currentPresentValue;
				
				
			}
			
		}
		
		
		
		return zeroCurveRates;
		
	}

}
