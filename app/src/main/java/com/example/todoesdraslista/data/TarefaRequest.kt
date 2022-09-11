package com.example.todoesdraslista.data

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.todoesdraslista.model.Tarefa
import org.json.JSONArray
import org.json.JSONObject

class TarefaRequest(context: Context) {

    private val queue = Volley.newRequestQueue(context)
    companion object {
        private val URL = "http://10.0.2.2:8000"
        private val GET_TASKS = "/tasks"
        private val POST_TASKS = "/tasks/new"
        private val DELETE_TASK = "/tasks/del"
        private val UPDATE_TASK = "/tasks/done"

    }

    fun startTasksRequest() {
        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                tasksRequest()
                handler.postDelayed(this, 2000)
            }
        })
    }

    private fun tasksRequest() {
        val jsonRequest = JsonArrayRequest(
            Request.Method.GET,
            URL + GET_TASKS,
            null,
            { response ->
                val taskList = JSONArrayToTarefaList(response)
                TarefaSingleton.updateTarefaList(taskList)
            },
            { error ->
                Log.e("TASKREQERR", "Task request error: ${error}")
            }
        )
        this.queue.add(jsonRequest)
    }

    fun JSONArrayToTarefaList(jsonArray: JSONArray): ArrayList<Tarefa> {
        val tarefaList = ArrayList<Tarefa>()
        for(i in 0..(jsonArray.length() - 1)) {
            val jsonObject = jsonArray.getJSONObject(i)
            val tarefa = Tarefa(
                jsonObject.getBoolean("is_urgent"),
                jsonObject.getString("description"),
                jsonObject.getBoolean("is_done"),
                jsonObject.getInt("id"),
            )
            tarefaList.add(tarefa)
        }
        return tarefaList
    }

    fun adicionarTarefa(tarefa: Tarefa)
    {
        val jsonArrayRequest = JsonObjectRequest(
            Request.Method.POST,
            URL + POST_TASKS,
            this.tarefatoJSON(tarefa),
            { response ->
                Log.d("RequestResponse", response.toString())
                this.tasksRequest()
            },
            { volleyError ->
                Log.e("RequestTaskError", "Connection error. ${volleyError.toString()}")
            }
        )

        this.queue.add(jsonArrayRequest);
            
    }

    fun tarefatoJSON(tarefa:Tarefa):JSONObject{
        val jsonObject=JSONObject();
        jsonObject.put("description",tarefa.description);
        jsonObject.put("is_done",tarefa.isDone);
        jsonObject.put("is_urgent",tarefa.isUrgent);

        return jsonObject;

    }

    fun deleteTarefa(tarefa: Tarefa) {
        val jsonArrayRequest = JsonObjectRequest(
            Request.Method.DELETE,
            URL + DELETE_TASK + '/' + tarefa.id,
            this.tarefatoJSON(tarefa),
            { response ->
                Log.d("RequestResponse", response.toString())
                this.tasksRequest()
            },
            { volleyError ->
                Log.e("RequestTaskError", "Connection error. ${volleyError.toString()}")
            }
        )

        this.queue.add(jsonArrayRequest);
    }

    fun updateTarefa(tarefa: Tarefa, state: String) {
        val jsonArrayRequest = JsonObjectRequest(
            Request.Method.PUT,
            URL + UPDATE_TASK + '/' + state + '/' + tarefa.id,
            this.tarefatoJSON(tarefa),
            { response ->
                Log.d("RequestResponse", response.toString())
                this.tasksRequest()
            },
            { volleyError ->
                Log.e("RequestTaskError", "Connection error. ${volleyError.toString()}")
            }
        )

        this.queue.add(jsonArrayRequest);
    }




}