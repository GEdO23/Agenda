package br.com.alura.agenda.dao;

import androidx.annotation.NonNull;

import java.util.List;

public interface AbstractDAO<T> {
    List<T> getAll();
    T getById(@NonNull T t);
    void save(@NonNull T t);
    void update(@NonNull T t);
    void remove(@NonNull T t);
}
