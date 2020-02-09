package com.judgement.poetry.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PoetryDao {

    @Query("SELECT * FROM poetry")
    LiveData<List<Poetry>> getAllPoetry();

    @Query("SELECT * FROM poetry WHERE content LIKE :pattern")
    List<Poetry> getPoetryWithPattern(String pattern);
}
