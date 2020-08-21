package io.github.ganzes.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ganzes.lang.LangRepository;
import io.github.ganzes.lang.LangService;
import io.github.ganzes.lang.LangServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ToDo", urlPatterns = {"/api/todos/*"})
public class ToDoServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(LangServlet.class);

    private ToDoRepository repository;
    private ObjectMapper mapper;

    /**
     * Servlet cotainer needs it.
     */
    @SuppressWarnings("unused")
    public ToDoServlet(){
        this(new ToDoRepository(), new ObjectMapper());
    }

    ToDoServlet(ToDoRepository repository, ObjectMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Got request with parameters " + req.getParameterMap());
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), repository.findAll());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var pathInfo = req.getPathInfo();

        try {
            var toDoId = Integer.valueOf(pathInfo.substring(1));
            var toDo = repository.toggleToDo(toDoId);
            resp.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(resp.getOutputStream(), toDo);
        } catch (NumberFormatException e){
            logger.warn("Wrong path used: " + pathInfo);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var newToDo = mapper.readValue(req.getInputStream(), ToDo.class);
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), repository.addToDo(newToDo));
    }
}

