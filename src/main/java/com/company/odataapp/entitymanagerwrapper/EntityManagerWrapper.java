package com.company.odataapp.entitymanagerwrapper;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

class EntityManagerWrapper implements EntityManager {

    private EntityManager delegate;

    public EntityManagerWrapper(EntityManager delegate) {
        this.delegate = delegate;
    }

    @Override
    public void persist(Object entity) {
        delegate.persist(entity);
    }

    @Override
    public <T> T merge(T entity) {
        return delegate.merge(entity);
    }

    @Override
    public void remove(Object entity) {
        delegate.remove(entity);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey) {
        return delegate.find(entityClass, primaryKey);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) {
        return delegate.find(entityClass, primaryKey, properties);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockModeType) {
        return delegate.find(entityClass, primaryKey, lockModeType);
    }

    @Override
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockModeType, Map<String, Object> properties) {
        return delegate.find(entityClass, primaryKey, lockModeType, properties);
    }

    @Override
    public <T> T getReference(Class<T> entityClass, Object primaryKey) {
        return delegate.getReference(entityClass, primaryKey);
    }

    @Override
    public void flush() {
        delegate.flush();
    }

    @Override
    public FlushModeType getFlushMode() {
        return delegate.getFlushMode();
    }

    @Override
    public void setFlushMode(FlushModeType flushModeType) {
        delegate.setFlushMode(flushModeType);
    }

    @Override
    public void lock(Object entity, LockModeType lockModeType) {
        delegate.lock(entity, lockModeType);
    }

    @Override
    public void lock(Object entity, LockModeType lockModeType, Map<String, Object> properties) {
        delegate.lock(entity, lockModeType, properties);
    }

    @Override
    public void refresh(Object entity) {
        delegate.refresh(entity);
    }

    @Override
    public void refresh(Object entity, Map<String, Object> properties) {
        delegate.refresh(entity, properties);
    }

    @Override
    public void refresh(Object entity, LockModeType lockModeType) {
        delegate.refresh(entity, lockModeType);
    }

    @Override
    public void refresh(Object entity, LockModeType lockModeType, Map<String, Object> properties) {
        delegate.refresh(entity, lockModeType, properties);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public void detach(Object entity) {
        delegate.detach(entity);
    }

    @Override
    public boolean contains(Object entity) {
        return delegate.contains(entity);
    }

    @Override
    public LockModeType getLockMode(Object entity) {
        return delegate.getLockMode(entity);
    }

    @Override
    public void setProperty(String propertyName, Object value) {
        delegate.setProperty(propertyName, value);
    }

    @Override
    public Map<String, Object> getProperties() {
        return delegate.getProperties();
    }

    @Override
    public Query createQuery(String sqlString) {
        return delegate.createQuery(sqlString);
    }

    @Override
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
        return delegate.createQuery(criteriaQuery);
    }

    @Override
    public Query createQuery(CriteriaUpdate UpdateQuery) {
        return delegate.createQuery(UpdateQuery);
    }

    @Override
    public Query createQuery(CriteriaDelete deleteQuery) {
        return delegate.createQuery(deleteQuery);
    }

    @Override
    public <T> TypedQuery<T> createQuery(String sqlString, Class<T> resultClass) {
        return delegate.createQuery(sqlString, resultClass);
    }

    @Override
    public Query createNamedQuery(String name) {
        return delegate.createNamedQuery(name);
    }

    @Override
    public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
        return delegate.createNamedQuery(name, resultClass);
    }

    @Override
    public Query createNativeQuery(String sqlString) {
        return delegate.createNativeQuery(sqlString);
    }

    @Override
    public Query createNativeQuery(String sqlString, Class resultClass) {
        return delegate.createNativeQuery(sqlString, resultClass);
    }

    @Override
    public Query createNativeQuery(String sqlString, String resultSetMapping) {
        return delegate.createNativeQuery(sqlString, resultSetMapping);
    }

    @Override
    public StoredProcedureQuery createNamedStoredProcedureQuery(String name) {
        return delegate.createNamedStoredProcedureQuery(name);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName) {
        return delegate.createStoredProcedureQuery(procedureName);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class... resultClasses) {
        return delegate.createStoredProcedureQuery(procedureName, resultClasses);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings) {
        return delegate.createStoredProcedureQuery(procedureName, resultSetMappings);
    }

    @Override
    public void joinTransaction() {
        delegate.joinTransaction();
    }

    @Override
    public boolean isJoinedToTransaction() {
        return delegate.isJoinedToTransaction();
    }

    @Override
    public <T> T unwrap(Class<T> cls) {
        return delegate.unwrap(cls);
    }

    @Override
    public Object getDelegate() {
        return delegate.getDelegate();
    }

    @Override
    public void close() {
        delegate.close();
    }

    @Override
    public boolean isOpen() {
        return delegate.isOpen();
    }

    @Override
    public EntityTransaction getTransaction() {
        return delegate.getTransaction();
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return delegate.getEntityManagerFactory();
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return delegate.getCriteriaBuilder();
    }

    @Override
    public Metamodel getMetamodel() {
        return delegate.getMetamodel();
    }

    @Override
    public <T> EntityGraph<T> createEntityGraph(Class<T> rootType) {
        return delegate.createEntityGraph(rootType);
    }

    @Override
    public EntityGraph<?> createEntityGraph(String graphName) {
        return delegate.createEntityGraph(graphName);
    }

    @Override
    public EntityGraph<?> getEntityGraph(String graphName) {
        return delegate.getEntityGraph(graphName);
    }

    @Override
    public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass) {
        return delegate.getEntityGraphs(entityClass);
    }
}