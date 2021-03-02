package com.champion.theo.tp_retrofit.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.champion.theo.tp_retrofit.models.Article
import com.champion.theo.tp_retrofit.repositories.ArticleRepository
import java.util.concurrent.Executors

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val text: LiveData<String> = _text
}