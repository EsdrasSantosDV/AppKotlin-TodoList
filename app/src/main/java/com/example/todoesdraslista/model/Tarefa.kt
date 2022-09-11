package com.example.todoesdraslista.model

import android.os.Parcel
import android.os.Parcelable

class Tarefa (
    val isUrgent: Boolean,
    val description: String,
    var isDone: Boolean=false,
    val id: Int? = 0,
){


}