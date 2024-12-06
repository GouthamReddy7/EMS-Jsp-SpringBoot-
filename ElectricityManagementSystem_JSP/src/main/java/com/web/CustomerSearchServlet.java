package com.web;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.service.CustomerAPIservice;


@WebServlet("/CustomerSearchServlet")
public class CustomerSearchServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the email parameter from the request
        String email = request.getParameter("email");
        System.out.println("Email received: " + email);

        // Prepare the JSON payload for the API request
        Map<String, String> custMap = new HashMap<>();
        custMap.put("email", email);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(custMap);

        // Call the API to get customer info
        String customerInfo = CustomerAPIservice.getCustomer(jsonPayload);
        System.out.println("API Response: " + customerInfo);

        // Check if customer info is found
        if (customerInfo != null && !customerInfo.isEmpty()) {
            // Parse the JSON response into a Customer object
            Gson gson = new Gson();
            Type customerType = new TypeToken<Customer>() {}.getType();
            Customer customer = gson.fromJson(customerInfo, customerType);

            // Set the customer attribute for the JSP
            request.setAttribute("customer", customer);
        } else {
            // Set an error message for the JSP if the customer is not found
            request.setAttribute("errorMessage", "No customer found with the provided email.");
        }

        // Forward the request to the JSP
        RequestDispatcher rd = request.getRequestDispatcher("searchCustomer.jsp");
        rd.forward(request, response);
    }
}
