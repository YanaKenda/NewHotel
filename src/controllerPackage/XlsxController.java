package controllerPackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import documentsPackage.XLSX;
import modelPackage.ShowTableModel;

/**
 * Servlet implementation class XlsxController
 */
@WebServlet("/XlsxController")
public class XlsxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XlsxController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("tableNameKey", ShowTableModel.getCurrentTableName());
		request.setAttribute("columnNamesListKey", ShowTableModel.getTitles(ShowTableModel.getCurrentTableName()));
		request.setAttribute("valuesListKey", ShowTableModel.getValues(ShowTableModel.getCurrentTableName()));
		request.setAttribute("stepKey", ShowTableModel.getNumberOfValues(ShowTableModel.getCurrentTableName()));
		request.getRequestDispatcher("VIEW/JSP/view_table.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		XLSX.createXSLX(ShowTableModel.getCurrentTableName(), 
				ShowTableModel.getTitles(ShowTableModel.getCurrentTableName()), 
				ShowTableModel.getValues(ShowTableModel.getCurrentTableName()));
		doGet(request, response);
	}

}
