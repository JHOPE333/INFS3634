package com.example.jianshi.mydatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
//This class is needed for room DB and initiates the table
@Database(entities = {Question.class}, version = 1)
    public abstract class AppDatabase extends RoomDatabase {
        public abstract UserDao userDao();
    }
