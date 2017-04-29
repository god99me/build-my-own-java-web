package io.github.god99me.chapter2.controller;

import io.github.god99me.chapter2.model.Customer;
import io.github.god99me.chapter2.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by L.M.Y on 2017/4/28.
 */
@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    // todo 被映射到了 chapter1 路径下
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService(); // 后续可以使用单例模式进行优化
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList = customerService.getCustomerList();
        req.setAttribute("customerList", customerList);
        req.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(req, resp);
    }
}
