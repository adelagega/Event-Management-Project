package service;

import java.util.List;

public interface GenericService<T>{
    T get(int id);
    List<T> getAll();
    void save(T t);
    void update(T t, String[] params);
    void delete(T t);
}
