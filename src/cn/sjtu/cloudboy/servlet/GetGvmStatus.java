package cn.sjtu.cloudboy.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetGvmStatus extends HttpServlet {

	private static final long serialVersionUID = 1L;
	//private static Logger logger = Logger.getLogger(GetGvmStatus.class.getName());

	public GetGvmStatus() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-------GetGvmStatus---------");

		RequestDispatcher rd = request.getRequestDispatcher("/status.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}

	}
}
