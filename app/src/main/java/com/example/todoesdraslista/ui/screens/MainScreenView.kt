package com.example.todoesdraslista.ui.screens

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoesdraslista.data.TarefaRequest
import com.example.todoesdraslista.R
import com.example.todoesdraslista.data.TarefaSingleton
import com.example.todoesdraslista.ui.components.TarefaItemView
import androidx.compose.ui.text.input.TextFieldValue
import com.example.todoesdraslista.model.Tarefa


@Composable
fun MainScreenView(tarefaRequest:TarefaRequest) {
    val isUrgent = remember { mutableStateOf(false) }
    var tarefaadicionada = remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSecondary
                )
            }
        },
        bottomBar = {
            Column {
                //LINHA DO URGENT  E DO SWITCH
                Row {
                    Text(
                        text= stringResource(id = R.string.lbl_urgent),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left ,
                        color = MaterialTheme.colors.onSecondary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 10.dp, end = 5.dp)
                    )
                    Switch(
                        checked =isUrgent.value ,
                        onCheckedChange ={isUrgent.value = !isUrgent.value},
                        colors = SwitchDefaults.colors(
                            uncheckedThumbColor = MaterialTheme.colors.secondary,
                            checkedThumbColor = MaterialTheme.colors.onSecondary
                        ) ,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 30.dp, end = 50.dp)

                    )


                }
                Row {
                    TextField(
                        value = tarefaadicionada.value,
                        onValueChange = { value ->
                            tarefaadicionada.value = value
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colors.onSecondary),
                        placeholder = { Text(stringResource(id = R.string.input_mensagem)) },
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 10.dp)
                            .background(MaterialTheme.colors.primary)
                            .fillMaxWidth() ,
                        maxLines = 5,
                    )

                }
                Row (
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 10.dp, end = 10.dp, bottom = 25.dp))
                {


                    Button(onClick = {
                        tarefaRequest.adicionarTarefa(Tarefa(isUrgent = isUrgent.value, description =tarefaadicionada.value.text, isDone = false))
                        tarefaadicionada.value=TextFieldValue("")
                        isUrgent.value = false



                    },Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxWidth()
                        .padding(start = 1.dp, end = 1.dp, bottom = 5.dp)
                        .height(60.dp)


                        ,
                        colors=ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.onBackground,
                            contentColor=MaterialTheme.colors.primary,

                            )

                    ) {
                        Text(stringResource(id = R.string.botao_ok),color = MaterialTheme.colors.primary)

                    }
                }

            }

        }

    ){ innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            Column() {
                LazyColumn() {
                    items(TarefaSingleton.getTarefas()){ tarefa ->
                        TarefaItemView(tarefa = tarefa,tarefaRequest)
                    }
                }
            }
        }
    }
}

/*


@Composable
fun InputDescrevaaTarefa(tarefaRequest: TarefaRequest)
{
    var tarefaadicionada = remember { mutableStateOf(TextFieldValue("")) }
    Row {

        SelectionContainer {
            TextField(
                value = tarefaadicionada.value,
                onValueChange = { value ->
                    tarefaadicionada.value = value
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colors.onSecondary),
                placeholder = { Text(stringResource(id = R.string.input_mensagem)) },
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp, top = 3.dp, bottom = 50.dp)
                    .background(MaterialTheme.colors.primary)
                    .fillMaxWidth() ,
                maxLines = 5,
            )
        }

    }


}
*/

/*


@Composable
fun InputDescrevaaTarefa(tarefaRequest: TarefaRequest)
{
    var tarefaadicionada = remember { mutableStateOf(TextFieldValue("")) }
    Row {

        SelectionContainer {
            TextField(
                value = tarefaadicionada.value,
                onValueChange = { value ->
                    tarefaadicionada.value = value
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colors.onSecondary),
                placeholder = { Text(stringResource(id = R.string.input_mensagem)) },
                modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp, top = 3.dp, bottom = 50.dp)
                    .background(MaterialTheme.colors.primary)
                    .fillMaxWidth() ,
                maxLines = 5,
            )
        }

    }


}
*/







