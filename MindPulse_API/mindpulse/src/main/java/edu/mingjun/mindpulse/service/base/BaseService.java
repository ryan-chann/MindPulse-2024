package edu.mingjun.mindpulse.service;

/* 
    Acronym:
        T = Entity Type, 
        ID = Entity Id Type
*/

public interface BaseService<T, ID> {

    void save(T entity);

    T get(ID id);

    void update(T entity);

    void delete(ID id);
}
