package controllerPackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelPackage.ShowTableModel;

/**
 * Servlet implementation class ShowTableController
 */
@WebServlet("/ShowTableController")
public class ShowTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTableController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		ShowTableModel.setTableName(request.getParameter("tableNameEditText").toString());
		doGet(request, response);
	}

}
