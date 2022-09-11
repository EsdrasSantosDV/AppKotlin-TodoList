package com.example.todoesdraslista.data

import com.example.todoesdraslista.model.Tarefa
import androidx.compose.runtime.mutableStateListOf

object TarefaSingleton {

    private val tasks = mutableStateListOf<Tarefa>()
    fun updateTarefaList(tasks: ArrayList<Tarefa>) {
        this.tasks.clear()
        this.tasks.addAll(tasks)
    }
    fun getTarefas(): List<Tarefa> {
        return this.tasks
    }


        
    
    
    
    
    
    
    
    
    
    
    

}