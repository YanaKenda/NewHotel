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
 * Servlet implementation class BufDeleteFromTableController
 */
@WebServlet("/BufDeleteFromTableController")
public class BufDeleteFromTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BufDeleteFromTableController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("primaryKeysList", ShowTableModel.getPrimaryKeys(ShowTableModel.getCurrentTableName()));
		request.setAttribute("tableNameKey", ShowTableModel.getCurrentTableName());
		request.setAttribute("numberOfPrimaryKeys", ShowTableModel.getPrimaryKeys(ShowTableModel.getCurrentTableName()).size());
		request.getRequestDispatcher("VIEW/JSP/delete_from_table.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UpdateTableModel.deleteFromTable(ShowTableModel.getCurrentTableName(), request.getParameter("deleteCellKey"));
		doGet(request, response);
	}

}
