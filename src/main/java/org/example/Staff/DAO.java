package org.example.Staff;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Boolean create(T neObj);

    Optional<T> read(Integer id);
    Optional<T> read(T objToRead);

    List<Optional<T>> readAll();

    Boolean update(Integer id, T updatedObj);


    Boolean delete(Integer id);


}
