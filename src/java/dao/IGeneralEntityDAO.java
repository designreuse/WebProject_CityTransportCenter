/*
 * Title: Epam Web-project: City Transport Center. 
 * Description: General interface for All DAO-s
 * 
 */
package dao;

import java.util.List;


/**
 *
 * @author Dmytro Deinichenko
 */

public interface IGeneralEntityDAO<T> {

    boolean create(T entity);

    boolean delete(T entity);

    boolean update(T entity);

    T findById(int uId);

    List<T> findAll();
}
