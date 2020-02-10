package com.judgement.poetry.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class PoetryRepository {
    private PoetryDao poetryDao;

    public PoetryRepository(Context context) {
        poetryDao = PoetryDatabase.getINSTANCE(context.getApplicationContext()).getPoetryDao();
    }

    public LiveData<List<Poetry>> getAllPoetry() {
        return poetryDao.getAllPoetry();
    }

    public void getPoetryWithPattern(String pattern,MutableLiveData<List<Poetry>> poetryList) {
        AsyncTask<Void, Void, List<Poetry>> task = new PatternAsyncTask(poetryDao,poetryList,pattern);
        task.execute();
    }


    static class PatternAsyncTask extends AsyncTask<Void, Void, List<Poetry>> {
        private PoetryDao poetryDao;
        private MutableLiveData<List<Poetry>> poetryListLive;
        private String pattern;

        public PatternAsyncTask(PoetryDao poetryDao, MutableLiveData<List<Poetry>> poetryListLive, String pattern) {
            this.poetryListLive = poetryListLive;
            this.poetryDao = poetryDao;
            this.pattern = pattern;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            poetryListLive.setValue(new ArrayList<Poetry>());
            poetryListLive.getValue().add(new Poetry("无", "无", "无", "少女祈祷中"));
        }

        @Override
        protected List<Poetry> doInBackground(Void ...voids) {
            return poetryDao.getPoetryWithPattern("%" + pattern + "%");
        }

        @Override
        protected void onPostExecute(List<Poetry> poetries) {
            super.onPostExecute(poetries);
            poetryListLive.setValue(new ArrayList<Poetry>());
            for(Poetry p:poetries){
                poetryListLive.getValue().addAll(p.getKeySentence(pattern));
            }
            if(poetryListLive.getValue().isEmpty()){
                poetryListLive.getValue().add(new Poetry("无","无","无","没有匹配的诗句"));
            }
        }
    }

}
