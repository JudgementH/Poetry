package com.judgement.poetry.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PoetryDao_Impl implements PoetryDao {
  private final RoomDatabase __db;

  public PoetryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public LiveData<List<Poetry>> getAllPoetry() {
    final String _sql = "SELECT * FROM poetry";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"poetry"}, false, new Callable<List<Poetry>>() {
      @Override
      public List<Poetry> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPoetryName = CursorUtil.getColumnIndexOrThrow(_cursor, "poetryName");
          final int _cursorIndexOfPoetName = CursorUtil.getColumnIndexOrThrow(_cursor, "poetName");
          final int _cursorIndexOfDynasty = CursorUtil.getColumnIndexOrThrow(_cursor, "dynasty");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final List<Poetry> _result = new ArrayList<Poetry>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Poetry _item;
            final String _tmpPoetryName;
            _tmpPoetryName = _cursor.getString(_cursorIndexOfPoetryName);
            final String _tmpPoetName;
            _tmpPoetName = _cursor.getString(_cursorIndexOfPoetName);
            final String _tmpDynasty;
            _tmpDynasty = _cursor.getString(_cursorIndexOfDynasty);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            _item = new Poetry(_tmpPoetryName,_tmpPoetName,_tmpDynasty,_tmpContent);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public List<Poetry> getPoetryWithPattern(final String pattern) {
    final String _sql = "SELECT * FROM poetry WHERE content LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (pattern == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, pattern);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPoetryName = CursorUtil.getColumnIndexOrThrow(_cursor, "poetryName");
      final int _cursorIndexOfPoetName = CursorUtil.getColumnIndexOrThrow(_cursor, "poetName");
      final int _cursorIndexOfDynasty = CursorUtil.getColumnIndexOrThrow(_cursor, "dynasty");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final List<Poetry> _result = new ArrayList<Poetry>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Poetry _item;
        final String _tmpPoetryName;
        _tmpPoetryName = _cursor.getString(_cursorIndexOfPoetryName);
        final String _tmpPoetName;
        _tmpPoetName = _cursor.getString(_cursorIndexOfPoetName);
        final String _tmpDynasty;
        _tmpDynasty = _cursor.getString(_cursorIndexOfDynasty);
        final String _tmpContent;
        _tmpContent = _cursor.getString(_cursorIndexOfContent);
        _item = new Poetry(_tmpPoetryName,_tmpPoetName,_tmpDynasty,_tmpContent);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
