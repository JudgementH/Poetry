package com.judgement.poetry.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.regex.Pattern;

public class PoetryRepository {
    private PoetryDao poetryDao;

    public PoetryRepository(Context context) {
        poetryDao = PoetryDatabase.getINSTANCE(context.getApplicationContext()).getPoetryDao();
    }

    public LiveData<List<Poetry>> getAllPoetry() {
        return poetryDao.getAllPoetry();
    }

    public List<Poetry> getPoetryWithPattern(String pattern) {
        return poetryDao.getPoetryWithPattern("%" + pattern + "%");

    }


    static class PatternAsyncTask extends AsyncTask<String, Void, List<Poetry>> {
        private PoetryDao poetryDao;
        private List<Poetry> poetryList;

        public PatternAsyncTask(PoetryDao poetryDao, List<Poetry> poetryList) {
            this.poetryList = poetryList;
            this.poetryDao = poetryDao;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            poetryList.clear();
            poetryList.add(new Poetry("无", "无", "无", "少女祈祷中"));
        }

        @Override
        protected List<Poetry> doInBackground(String... strings) {
            return poetryDao.getPoetryWithPattern("%" + strings + "%");
        }

        @Override
        protected void onPostExecute(List<Poetry> poetries) {
            super.onPostExecute(poetries);
            poetryList.clear();
            poetryList.addAll(poetries);
        }
    }

}
