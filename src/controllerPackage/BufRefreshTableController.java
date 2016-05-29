package controllerPackage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelPackage.ShowTableModel;
import modelPackage.UpdateTableModel;

/**
 * Servlet implementation class BufRefreshTableController
 */
@WebServlet("/BufRefreshTableController")
public class BufRefreshTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BufRefreshTableController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("primaryKeysList", ShowTableModel.getPrimaryKeys(ShowTableModel.getCurrentTableName()));
		request.setAttribute("numberOfPrimaryKeys", ShowTableModel.getPrimaryKeys(ShowTableModel.getCurrentTableName()).size());
		request.setAttribute("columnInfoListKey", UpdateTableModel.getTitlesInfo(ShowTableModel.getCurrentTableName()));
		request.setAttribute("tableNameKey", ShowTableModel.getCurrentTableName());
		request.getRequestDispatcher("VIEW/JSP/refresh_table.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//UpdateTableModel.refreshTable(ShowTableModel.getCurrentTableName(), request.getParameter("deleteCellKey"));
		ArrayList<String>updateValues=new ArrayList<String>();
		int size = Integer.parseInt(request.getParameter("cellsNumber"));
		for(int i = 0; i < size; i++) {
			updateValues.add(request.getParameter("" + i));
		}
		UpdateTableModel.refreshTable(ShowTableModel.getCurrentTableName(), updateValues, request.getParameter("refreshCellKey"));
		doGet(request, response);
	}

}
