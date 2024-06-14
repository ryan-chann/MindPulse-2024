package edu.mingjun.mindpulse.controller;

/* 
    Acronym:
        T = Entity Type, 
        ID = Entity Id Type
*/ 

public interface BaseController<T, ID> {
    void create(T entity);

    void get(ID id);

    void update(T entity);

    void delete(ID id);
}
