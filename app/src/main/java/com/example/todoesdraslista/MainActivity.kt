package com.example.todoesdraslista

import android.os.Build

import android.os.Bundle

import android.view.WindowInsetsController

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import com.example.todoesdraslista.data.TarefaRequest



import com.example.todoesdraslista.ui.screens.MainScreenView
import com.example.todoesdraslista.ui.theme.TodoEsdrasListaTheme


class MainActivity : ComponentActivity() {
    private lateinit var tarefaRequest: TarefaRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.tarefaRequest = TarefaRequest(context = this)
        this.tarefaRequest.startTasksRequest()
        setContent {
            TodoEsdrasListaTheme {
                this.SetupUIConfigs()
                MainScreenView(this.tarefaRequest)
            }
        }
    }
    @Composable
    private fun SetupUIConfigs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (MaterialTheme.colors.isLight) {
                window.decorView
                    .windowInsetsController?.setSystemBarsAppearance(
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                    )
            } else {
                window.decorView
                    .windowInsetsController?.setSystemBarsAppearance(
                        0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                    )
            }
        }
        window.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()
    }
}

