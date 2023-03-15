
package com.example.film.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class HibernateDAO {

    private SessionFactory sessionFactory;

    public <T> T create(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
        return entity;
    }
    public<T> List<T> findQuery(Class<T> tClass,String query){
        Session session = sessionFactory.openSession();
        List<T> results = session.createNativeQuery(query,tClass).list();
        session.close();
        return results;
    }

    public <T> T findById(Class<T> clazz, Serializable id) {
        Session session = sessionFactory.openSession();
        T entity = (T) session.get(clazz, id);
        session.close();
        return entity;
    }

    public <T> List<T> findAll(Class<T> tClass) {
        Session session = sessionFactory.openSession();
        List<T> results = session.createCriteria(tClass).list();
        session.close();
        return results;
    }

    public <T> List<T> findWhere(T entity) {
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity).ignoreCase();
        List<T> results = session.createCriteria(entity.getClass()).add(example).list();
        session.close();
        return results;
    }

    public <T> List<T> recherche(T entity,String mot){
        Session session= null;
        List<T> list = null;
        try{
            session = this.sessionFactory.openSession();
            list = session.createCriteria(entity.getClass())
                    .add(
                            Restrictions.or(
                                    Restrictions.ilike("titre",mot, MatchMode.ANYWHERE),
                                    Restrictions.ilike("body",mot,MatchMode.ANYWHERE)
                            )
                    ).list();
        }catch (Exception e){e.printStackTrace();}
        return list;
    }

    public <T> List<T> findField(Class<T> tClass, String field, String value) {
        Session session = sessionFactory.openSession();
        Criteria crit = session.createCriteria(tClass);
        crit.add(Restrictions.eq(field, value));
        List<T> results = crit.list();
        return results;
    }

    public <T> List<T> findField(Class<T> tClass, String field, Integer value) {
        Session session = sessionFactory.openSession();
        Criteria crit = session.createCriteria(tClass);
        crit.add(Restrictions.eq(field, value));
        List<T> results = crit.list();
        return results;
    }

    public <T> List<T> paginateWhere(T entity, int offset, int size) {
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity).ignoreCase();
        List<T> results = session.createCriteria(entity.getClass())
            .add(example)
            .setFirstResult(offset)
            .setMaxResults(offset + size).list();
        session.close();
        return results;
    }

    public <T> List<T> paginate(Class<T> clazz, int offset, int size) {
        Session session = sessionFactory.openSession();
        List<T> results = session.createCriteria(clazz)
            .setFirstResult(offset)
            .setMaxResults(offset + size).list();
        session.close();
        return results;
    }

    public <T> List<T> paginate(Class<T> clazz, int offset, int size, String orderBy, boolean orderAsc) {
        Session session = sessionFactory.openSession();
        Order order = (orderAsc) ? Order.asc(orderBy) : Order.desc(orderBy);
        List<T> results = session.createCriteria(clazz)
            .addOrder(order)
            .setFirstResult(offset)
            .setMaxResults(offset + size).list();
        session.close();
        return results;
    }

    public void deleteById(Class tClass, Serializable id) {
        delete(findById(tClass, id));
    }

    public void delete(Object entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    public <T> T update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
        return entity;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
