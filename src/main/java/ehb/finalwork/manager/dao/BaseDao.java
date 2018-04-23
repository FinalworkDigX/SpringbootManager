package ehb.finalwork.manager.dao;

import ehb.finalwork.manager.error.CustomNotFoundException;

import java.util.List;

public interface BaseDao<T, U> {
    List<T> getAll();
    T getById(String id) throws CustomNotFoundException;
    T create(U entityDto) throws Exception;
    T update(T entity);
    void delete(String id) throws CustomNotFoundException;
}
