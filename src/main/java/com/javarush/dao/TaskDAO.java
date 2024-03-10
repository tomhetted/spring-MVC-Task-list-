package com.javarush.dao;

import com.javarush.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@Repository
@RequiredArgsConstructor
public class TaskDAO {

    private final SessionFactory sessionFactory;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<TaskEntity> getAll(int offset, int limit) {
        Query<TaskEntity> query = getSession().createQuery("select t from TaskEntity t", TaskEntity.class);
        query.setMaxResults(limit);
        query.setFirstResult(offset);
        return query.getResultList();
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public TaskEntity getById(Long id) {
        Query<TaskEntity> query = getSession().createQuery("select t from TaskEntity t where t.id=:ID", TaskEntity.class);
        query.setParameter("ID", id);
        return query.getSingleResult();
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void saveOrUpdate(TaskEntity task) {
        getSession().persist(task);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void delete(TaskEntity task) {
        getSession().remove(task);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) {
        TaskEntity task = getSession().get(TaskEntity.class, id);
        if (isNull(task)) {
            throw new RuntimeException("Task not found");
        }
        getSession().remove(task);
    }

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public int getAllCount() {
        Query<Long> query = getSession().createQuery("select count(t) from TaskEntity t", Long.class);
        return Math.toIntExact(query.getSingleResult());
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


}
