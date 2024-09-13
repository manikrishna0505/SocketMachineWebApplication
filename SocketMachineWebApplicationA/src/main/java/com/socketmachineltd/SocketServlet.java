package com.socketmachineltd;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/requestQuote")
public class SocketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handling GET Request
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Socket Quote Request</h2>");
        response.getWriter().println("<form method='POST' action='requestQuote'>");
        response.getWriter().println("Socket Type: <select name='socketType'>" +
            "<option value='A'>Type A</option>" +
            "<option value='B'>Type B</option>" +
            "<option value='C'>Type C</option>" +
            "</select><br/>");
        response.getWriter().println("Quantity: <input type='number' name='quantity'><br/>");
        response.getWriter().println("Name: <input type='text' name='name'><br/>");
        response.getWriter().println("Email: <input type='email' name='email'><br/>");
        response.getWriter().println("<input type='submit' value='Request Quote'>");
        response.getWriter().println("</form></body></html>");
    }

    // Handling POST Request
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String socketType = request.getParameter("socketType");
        String quantityStr = request.getParameter("quantity");
        String customerName = request.getParameter("name");
        String customerEmail = request.getParameter("email");

        // Simple validation
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            quantity = -1; // Invalid input
        }

        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        
        if (quantity > 0) {
            double totalPrice = getPrice(socketType, quantity);
            response.getWriter().println("<h2>Quote Details</h2>");
            response.getWriter().println("Customer: " + customerName + "<br/>");
            response.getWriter().println("Email: " + customerEmail + "<br/>");
            response.getWriter().println("Socket Type: " + socketType + "<br/>");
            response.getWriter().println("Quantity: " + quantity + "<br/>");
            response.getWriter().println("Total Price: $" + totalPrice + "<br/>");
        } else {
            response.getWriter().println("<h2>Error</h2>");
            response.getWriter().println("Invalid quantity entered.");
        }
        
        response.getWriter().println("</body></html>");
    }

    private double getPrice(String socketType, int quantity) {
        double pricePerSocket = 0.0;
        switch (socketType) {
            case "A":
                pricePerSocket = 10.0;
                break;
            case "B":
                pricePerSocket = 15.0;
                break;
            case "C":
                pricePerSocket = 20.0;
                break;
        }
        return pricePerSocket * quantity;
    }
}
