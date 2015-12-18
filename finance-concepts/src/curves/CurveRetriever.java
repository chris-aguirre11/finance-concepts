package curves;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import persistence.dao.DailyTreasuryYieldCurveDao;
import persistence.objects.DailyTreasuryYieldCurve;
import enums.CurveTypes;

public class CurveRetriever {
	
	static String DAILY_TREASURY_YIELD_CURVE_URL = 
			"https://www.treasury.gov/resource-center/data-chart-center/interest-rates/Datasets/yield.xml";
	
	static List<Double> currentTreasuryYieldRates = new ArrayList<Double>();
	
//	public static void main(String[] args) {
//		DailyTreasuryYieldCurveDao.retrieve();
//		
//	}
	
	public static void persistDailyTreasuryYieldCurveToDb() {
		DailyTreasuryYieldCurve treasuryCurve = new DailyTreasuryYieldCurve();
		
		treasuryCurve.setMonth1(currentTreasuryYieldRates.get(0));
		treasuryCurve.setMonth3(currentTreasuryYieldRates.get(1));
		treasuryCurve.setMonth6(currentTreasuryYieldRates.get(2));
		treasuryCurve.setYear1(currentTreasuryYieldRates.get(3));
		treasuryCurve.setYear2(currentTreasuryYieldRates.get(4));
		treasuryCurve.setYear3(currentTreasuryYieldRates.get(5));
		treasuryCurve.setYear5(currentTreasuryYieldRates.get(6));
		treasuryCurve.setYear7(currentTreasuryYieldRates.get(7));
		treasuryCurve.setYear10(currentTreasuryYieldRates.get(8));
		treasuryCurve.setYear20(currentTreasuryYieldRates.get(9));
		treasuryCurve.setYear30(currentTreasuryYieldRates.get(10));
		
		DailyTreasuryYieldCurveDao.register(treasuryCurve);
	}

	public static void retrieveCurve(CurveTypes curveType) {
		if(curveType == CurveTypes.DAILY_TREASURY_YIELD_CURVE) {
			retrieveDailyTreasuryYieldCurve();
		}
	}

	private static void retrieveDailyTreasuryYieldCurve() {
		try {
//			URL treasuryUrl = new URL("https://www.treasury.gov/resource-center/data-chart-center/interest-rates/Datasets/yield.xml");
//	        BufferedReader in = new BufferedReader(new InputStreamReader(treasuryUrl.openStream()));
//
//	        StringBuilder urlString = new StringBuilder();
//	        String line = null;
//	        while ((line = in.readLine()) != null)
//	        	urlString.append(line);
//	        in.close();
	        
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        factory.setIgnoringComments(true);
	        factory.setIgnoringElementContentWhitespace(true);
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        Document doc = builder.parse(DAILY_TREASURY_YIELD_CURVE_URL);
	        
	        Element root = doc.getDocumentElement();
	        Node lastGWeekOfMonthNode = root.getFirstChild().getLastChild();
	        
	       
	        
	        NodeList lastGWeekOfMonthChildren = lastGWeekOfMonthNode.getChildNodes();
	        for(int i = 0; i < lastGWeekOfMonthChildren.getLength(); i++) {
	        	if(lastGWeekOfMonthChildren.item(i).getNodeName() == "LIST_G_NEW_DATE") {
	        		Node lastGNewDate = lastGWeekOfMonthChildren.item(i).getLastChild();
	        		NodeList lastGNewDateChildren = lastGNewDate.getChildNodes();
	        		for(int y = 0; y < lastGNewDateChildren.getLength(); y++) {
	        			if(lastGNewDateChildren.item(y).getNodeName() == "LIST_G_BC_CAT") {
	        				Node gBcCat = lastGNewDateChildren.item(y).getFirstChild();
	        				NodeList gBcCatChildren = gBcCat.getChildNodes();
	        				for(int x = 0; x < gBcCatChildren.getLength()-1; x++) {
	        					//System.out.println(gBcCatChildren.item(x).getTextContent());
	        					currentTreasuryYieldRates.add(
	        							Double.parseDouble(gBcCatChildren.item(x).getTextContent()));
	        				}
		        		}
	        		}
	        	}
	        }
		} 
		catch (Exception e) {
			System.out.println("Error in retreving Daily Treasury Yield Curve");
			e.printStackTrace();
		}
	}

}
