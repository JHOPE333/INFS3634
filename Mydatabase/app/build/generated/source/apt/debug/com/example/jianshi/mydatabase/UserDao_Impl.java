package com.example.jianshi.mydatabase;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfQuestion;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfQuestion;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuestion = new EntityInsertionAdapter<Question>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Question`(`qid`,`question`,`Answer`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Question value) {
        stmt.bindLong(1, value.getQid());
        if (value.getQuestion() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getQuestion());
        }
        if (value.getAnswer() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAnswer());
        }
      }
    };
    this.__deletionAdapterOfQuestion = new EntityDeletionOrUpdateAdapter<Question>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Question` WHERE `qid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Question value) {
        stmt.bindLong(1, value.getQid());
      }
    };
  }

  @Override
  public void insertAll(Question... questions) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfQuestion.insert(questions);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Question questions) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfQuestion.handle(questions);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Question> getAll() {
    final String _sql = "SELECT * FROM Question";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfQid = _cursor.getColumnIndexOrThrow("qid");
      final int _cursorIndexOfQuestion = _cursor.getColumnIndexOrThrow("question");
      final int _cursorIndexOfAnswer = _cursor.getColumnIndexOrThrow("Answer");
      final List<Question> _result = new ArrayList<Question>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Question _item;
        final String _tmpQuestion;
        _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
        final String _tmpAnswer;
        _tmpAnswer = _cursor.getString(_cursorIndexOfAnswer);
        _item = new Question(_tmpQuestion,_tmpAnswer);
        final int _tmpQid;
        _tmpQid = _cursor.getInt(_cursorIndexOfQid);
        _item.setQid(_tmpQid);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String loadAllbyIds(Integer userIds) {
    final String _sql = "SELECT question FROM Question WHERE qid IN (?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userIds == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, userIds);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getString(0);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getAnswer(Integer userIds) {
    final String _sql = "SELECT Answer FROM Question WHERE qid IN (?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userIds == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, userIds);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getString(0);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
