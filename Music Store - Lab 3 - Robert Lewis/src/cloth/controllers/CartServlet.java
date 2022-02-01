package cloth.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import cloth.data.ProductIO;
import cloth.models.CartEntry;
import cloth.models.Product;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static List<CartEntry> getCartEntries(HttpServletRequest req) {
		HttpSession session = req.getSession();

		@SuppressWarnings("unchecked")
		List<CartEntry> entries = (List<CartEntry>) session.getAttribute("cartList");

		if (entries == null) {
			entries = new ArrayList<CartEntry>();
			session.setAttribute("cartList", entries);
		}

		return entries;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log("GOT HIT");
		Gson gson = new Gson();

		String list = gson.toJson(getCartEntries(req));

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.print(list);
		out.flush();
		log("Went through JSON");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log("POST is being CALLED");
		String code = req.getParameter("productCode");
		log("productCode: " + code);
		String qty = req.getParameter("qty");
		log("qty: " + qty);
		Product product = ProductIO.getProduct(code);

		List<CartEntry> entries = getCartEntries(req);
		boolean alreadyInCart = false;

		for (int i = 0; i < entries.size(); i++) {
			CartEntry entry = entries.get(i);

			if (entry.getProduct().getCode().equals(product.getCode())) {

				if (qty != null) {
					log("qty == null");
					int amount = Integer.parseInt(qty);
					log("amount: " + amount);
					if (amount == 0) {
						log("removed from list");
						entries.remove(i);
					} else {
						log("sets qty");
						entry.setQty(amount);
					}
				} else {
					log("adds one more to qty");
					entry.setQty(entry.getQty() + 1);
				}
				alreadyInCart = true;
				log("alreadyInCart");
				break;
			}
		}

		if (!alreadyInCart) {
			log("Went in last if statment");
			entries.add(new CartEntry(product, 1));
		}

		resp.sendRedirect("cart.html");
	}

}
