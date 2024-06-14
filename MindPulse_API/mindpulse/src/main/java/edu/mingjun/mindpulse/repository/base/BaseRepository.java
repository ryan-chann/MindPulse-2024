package edu.mingjun.mindpulse.repository;

/* 
    Acronym:
        T = Entity Type, 
        ID = Entity Id Type
*/ 

public interface BaseRepository<T, ID> {
    
    void save(T entity);

    T get(ID id);

    void update(T entity);

    void delete(ID id);

}