package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dao.DailyTreasuryYieldCurveDao;
import persistence.objects.DailyTreasuryYieldCurve;

/**
 * Servlet implementation class DataServlet
 */
@WebServlet("/DataServlet")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DailyTreasuryYieldCurve yieldCurve = DailyTreasuryYieldCurveDao.retrieve();
		
		if(yieldCurve != null) {
			response.setContentType("text/plain");
	        PrintWriter printWriter  = response.getWriter();
//	        printWriter.println("month1	" + yieldCurve.getMonth1());
//	        printWriter.println("month3	" + yieldCurve.getMonth3());
//	        printWriter.println("month6	" + yieldCurve.getMonth6());
//	        printWriter.println("year1	" + yieldCurve.getYear1());
//	        printWriter.println("year2	" + yieldCurve.getYear2());
//	        printWriter.println("year3	" + yieldCurve.getYear3());
//	        printWriter.println("year5	" + yieldCurve.getYear5());
//	        printWriter.println("year7	" + yieldCurve.getYear7());
//	        printWriter.println("year10	" + yieldCurve.getYear10());
//	        printWriter.println("year20	" + yieldCurve.getYear20());
//	        printWriter.println("year30	" + yieldCurve.getYear30());
	        
	        //TODO Fix this - will need to convert and store dates in database
	        printWriter.println("01/18/2016	" + yieldCurve.getMonth1());
	        printWriter.println("03/18/2016	" + yieldCurve.getMonth3());
	        printWriter.println("06/18/2016	" + yieldCurve.getMonth6());
	        printWriter.println("12/18/2016	" + yieldCurve.getYear1());
	        printWriter.println("12/18/2017	" + yieldCurve.getYear2());
	        printWriter.println("12/18/2018	" + yieldCurve.getYear3());
	        printWriter.println("12/18/2020	" + yieldCurve.getYear5());
	        printWriter.println("12/18/2022	" + yieldCurve.getYear7());
	        printWriter.println("12/18/2025	" + yieldCurve.getYear10());
	        printWriter.println("12/18/2035	" + yieldCurve.getYear20());
	        printWriter.println("12/18/2045	" + yieldCurve.getYear30());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
