package com.anubhav87.mvvmtutorial.utils

import androidx.lifecycle.MutableLiveData

class ObservableModel {

    val appBarRequired = MutableLiveData<Boolean>()

    val backActionRequired = MutableLiveData<Boolean>()

    val appBarTitle = MutableLiveData<String>()

    val ballBackgroundRequired = MutableLiveData<Boolean>()

    val backActionLabel = MutableLiveData<String>()

    val appBarTitleColor = MutableLiveData<Int>()
}