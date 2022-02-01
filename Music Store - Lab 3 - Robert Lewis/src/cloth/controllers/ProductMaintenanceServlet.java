package cloth.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cloth.data.ProductIO;

@WebServlet("/productMaint")
public class ProductMaintenanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("products", ProductIO.getProducts());
		req.getRequestDispatcher("/productMaintenance.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();

		String list = gson.toJson(ProductIO.getProducts());

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.print(list);
		out.flush();
	}

}
