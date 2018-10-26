package com.example.jianshi.mydatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
//This class is needed for room DB
@Database(entities = {Question.class /*ModuleChecker.class*/}, version = 1)
    public abstract class AppDatabase extends RoomDatabase {
        public abstract UserDao userDao();
    }
