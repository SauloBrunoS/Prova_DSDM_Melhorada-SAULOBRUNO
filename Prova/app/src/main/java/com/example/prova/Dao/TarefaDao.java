package com.example.prova.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.prova.model.Tarefa;
import java.util.List;

@Dao
public interface TarefaDao {
    @Query("SELECT * FROM tarefa")
    List<Tarefa> getAll();

    @Insert
    long insert(Tarefa tarefa);

    @Query("SELECT * FROM Tarefa WHERE id = :id")
    Tarefa getById(int id);

    @Update
    void update(Tarefa tarefa);


}