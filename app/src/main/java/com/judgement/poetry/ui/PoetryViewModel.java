package com.judgement.poetry.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.judgement.poetry.database.Poetry;
import com.judgement.poetry.database.PoetryRepository;

import java.util.ArrayList;
import java.util.List;

public class PoetryViewModel extends AndroidViewModel {
    private PoetryRepository poetryRepository;
    private MutableLiveData<List<Poetry>> poetryLive;

    public PoetryViewModel(@NonNull Application application) {
        super(application);
        poetryRepository = new PoetryRepository(application);
    }

    public LiveData<List<Poetry>> getAllPoetry() {
        return poetryRepository.getAllPoetry();
    }

    public MutableLiveData<List<Poetry>> getPoetryLive() {
        if (poetryLive == null) {
            poetryLive = new MutableLiveData<>();
        }
        return poetryLive;
    }

    public MutableLiveData<List<Poetry>> resetPoetryLive() {
        poetryLive = getPoetryLive();
        ArrayList<Poetry> poetryArrayList = new ArrayList<>();
        poetryArrayList.add(new Poetry("无","无","无","输入关键字，开始飞花令"));
        poetryLive.setValue(poetryArrayList);
        return poetryLive;
    }

    public LiveData<List<Poetry>> getPoetryWithPattern(String pattern) {
        poetryRepository.getPoetryWithPattern(pattern,poetryLive);
        return poetryLive;
    }
}
