package controllerPackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelPackage.ShowTableModel;
import modelPackage.UpdateTableModel;

/**
 * Servlet implementation class AddToTableController
 */
@WebServlet("/AddToTableController")
public class AddToTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToTableController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("columnInfoListKey", UpdateTableModel.getTitlesInfo(ShowTableModel.getCurrentTableName()));
		request.setAttribute("tableNameKey", ShowTableModel.getCurrentTableName());
		request.getRequestDispatcher("VIEW/JSP/add_to_table.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShowTableModel.setTableName(request.getParameter("updateTableNameEditText").toString());
		doGet(request, response);
	}


}
