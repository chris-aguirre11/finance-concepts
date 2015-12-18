package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import curves.CurveRetriever;
import enums.CurveTypes;

/**
 * Servlet implementation class RetrieveDataServlet
 */
@WebServlet("/RetrieveDataServlet")
public class RetrieveDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String yieldCurveSelection = request.getParameter("yieldCurveSelection"); 
		
		if(yieldCurveSelection.equalsIgnoreCase("Daily Treasury Yield Curve")) {
			CurveRetriever.retrieveCurve(CurveTypes.DAILY_TREASURY_YIELD_CURVE);
			CurveRetriever.persistDailyTreasuryYieldCurveToDb();
		}
//		 response.setContentType("text/html");
//		response.sendRedirect("test1.jsp");
	}
}
