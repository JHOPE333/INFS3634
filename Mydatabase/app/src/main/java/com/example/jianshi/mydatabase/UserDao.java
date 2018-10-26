package com.example.jianshi.mydatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM Question")
    List<Question> getAll();

    @Query("SELECT question FROM Question WHERE qid IN (:userIds)")
    String loadAllbyIds (Integer userIds);

    @Query("SELECT Answer FROM Question WHERE qid IN (:userIds)")
    String getAnswer (Integer userIds);

    @Insert
    void insertAll(Question... questions);

    @Delete
    void delete(Question questions);
}