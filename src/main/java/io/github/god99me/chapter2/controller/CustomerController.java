//package io.github.god99me.chapter2.controller;
//
//import io.github.god99me.chapter2.model.Customer;
//import io.github.god99me.chapter2.service.CustomerService;
//
//import java.util.Map;
//
///**
// * Created by L.M.Y on 2017/4/30.
// */
//// 将 5 个 servlet 打包到一个 controller 中
//@Controller
//public class CustomerController {
//
//    @Inject
//    private CustomerService customerService;
//
//    /*
//     * 进入客户列表界面
//     */
//    @Action("get:/customer")
//    public View index(Param param) {
//        List<Customer> customerList = customerService.getCustomerList();
//        return new View("customer.jsp").addModel("customerList", customerList); // View 封装 jsp
//    }
//
//    /*
//     * 显示客户的基本信息
//     */
//    @Action("get:/customer_show")
//    public View show(Param param) {
//        long id = param.getLong("id");
//        Customer customer = customerService.getCustomer(id);
//        return new View("customer_show.jsp").addModel("customer", customer);
//    }
//
//    /*
//     * 进入创建客户界面
//     */
//    @Action("get:/customer_create")
//    public View create(Param param) {
//        return new View("customer_create.jsp");
//    }
//
//    /*
//     * 处理创建客户请求
//     */
//    @Action("post:/customer_create")
//    public Data createSubmit(Param param) {
//        Map<String, Object> fieldMap = param.getMap();
//        boolean result = customerService.createCustomer(fieldMap);
//        return new Data(result); // Data 封装 json
//    }
//
//    /*
//     * 进入编辑客户界面
//     */
//    @Action("get:/customer_edit")
//    public View edit(Param param) {
//        long id = param.getLong("id");
//        Customer customer = customerService.getCustomer(id);
//        return new View("customer_edit.jsp").addModel("customer", customer);
//    }
//
//    /*
//     * 处理编辑客户请求
//     */
//    @Action("put:/customer_edit")
//    public Data editSubmit(Param param) {
//        long id = param.getLong("id");
//        Map<String, Object> fieldMap = param.getMap();
//        boolean result = customerService.updateCustomer(id, fieldMap);
//        return new Data(result);
//    }
//
//    /*
//     * 处理删除客户请求
//     */
//    @Action("delete:/customer_edit")
//    public Data delete(Param param) {
//        long id = param.getLong("id");
//        boolean result = customerService.deleteCustomer(id);
//        return new Data(result);
//    }
//}
