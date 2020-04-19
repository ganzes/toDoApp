package io.github.ganzes;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Hello", urlPatterns = {"/api/*"})
public class HelloServlet extends HttpServlet {
    private static final String NAME_PARA = "name";
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BasicConfigurator.configure();

        logger.info("Got request with parameters " + req.getParameterMap());

        String name = Optional.ofNullable(req.getParameter(NAME_PARA)).orElse("World");

        resp.getWriter().write("Hello " + name + "!");

        /*String name = req.getParameter(NAME_PARA);

        if (name != null){
            resp.getWriter().write("Hello " + name + "!");
        } else resp.getWriter().write("Hello World!");*/



    }
}
