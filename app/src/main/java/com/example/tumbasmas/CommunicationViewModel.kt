package com.example.tumbasmas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CommunicationViewModel: ViewModel() {
    private val mName= MutableLiveData<String>()
    val name: LiveData<String>
    get() = mName

    private val mUsername = MutableLiveData<String>()
    val username: LiveData<String>
    get() = mUsername

    fun setName(name:String){
        mName.value = name
    }
    fun setUsername(username:String){
        mUsername.value = username
    }
}