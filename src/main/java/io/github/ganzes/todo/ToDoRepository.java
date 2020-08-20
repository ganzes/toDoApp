package io.github.ganzes.todo;

import io.github.ganzes.HibernateUtil;
import io.github.ganzes.lang.Lang;

import java.util.List;

public class ToDoRepository {
    List<ToDo> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.createQuery("from ToDo ", ToDo.class).list();

        transaction.commit();
        session.close();
        return result;
    }
}
