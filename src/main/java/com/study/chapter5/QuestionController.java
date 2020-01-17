package com.study.chapter5;

import com.study.chapter2.BooleanQuestion;
import com.study.chapter2.Question;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.Clock;
import java.util.List;
import java.util.function.Consumer;

public class QuestionController {
    private Clock clock = Clock.systemUTC();

    private static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("postgres-ds");
    }

    public Question find(Integer id) {
        return em().find(Question.class, id);
    }

    public List<Question> getAll() {
        return em().createQuery("select q from Question q", Question.class).getResultList();
    }

    public List<Question> findWithMatchingText(String text) {
        return em()
                .createQuery(
                        "select q from Question q where q.text like '%" + text + "%'", Question.class)
                .getResultList();
    }

    public int addPercentileQuestion(String text, String[] answerChoices) {
        return persist((Persistable) new PercentileQuestion(text, answerChoices));
    }

    public int addBooleanQuestion(String text) {
        return persist((Persistable) new BooleanQuestion(text));
    }

    void setClock(Clock clock) {
        this.clock = clock;
    }

    void deleteAll() {
        executeInTransaction(
                (em) -> em.createNativeQuery("delete from Question").executeUpdate());
    }

    private void executeInTransaction(Consumer<EntityManager> func) {
        EntityManager em = em();

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            func.accept(em);
            transaction.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            transaction.rollback();
        }
        finally {
            em.close();
        }
    }

    private int persist(Persistable object) {
        object.setCreateTimestamp(clock.instant());
        executeInTransaction((em) -> em.persist(object));
        return object.getId();
    }

    private EntityManager em() {
        return getEntityManagerFactory().createEntityManager();
    }
}
