package io.github.ganzes.todo;

import io.github.ganzes.HibernateUtil;

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

    ToDo toggleToDo (Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.get(ToDo.class, id);
        result.setDone(!result.isDone());

        transaction.commit();
        session.close();
        return result;
    }
}
