package io.github.god99me.chapter1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by L.M.Y on 2017/4/27.
 */
// 通过 WebServlet 达成零配置 web.xml
@WebServlet("/hello")
public class HelloServlet extends HttpServlet{

    // control + N 自动生成提示
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateFormat dataFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String currentTime = dataFormat.format(new Date());
        req.setAttribute("currentTime", currentTime); // servlet - jsp hook
        req.getRequestDispatcher("/WEB-INF/view/hello.jsp").forward(req, resp);
        // debug 模式下运行，可以实现类的热部署以及断点调试
        // 对于社区版可能需要多一些配置达到相同的效果
    }
}
