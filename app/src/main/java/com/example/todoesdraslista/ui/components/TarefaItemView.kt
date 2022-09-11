package com.example.todoesdraslista.ui.components

import android.widget.CheckBox
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.todoesdraslista.model.Tarefa
import com.example.todoesdraslista.ui.theme.TodoEsdrasListaTheme
import com.example.todoesdraslista.R
import com.example.todoesdraslista.data.TarefaRequest
import com.example.todoesdraslista.data.TarefaSingleton
import com.example.todoesdraslista.ui.components.ShowDialog

@Composable
fun TarefaItemView(tarefa: Tarefa,tarefaRequest:TarefaRequest?) {

    var tamanhomaximo = remember { mutableStateOf(1)};
    val longPressCounter =remember{ mutableStateOf(0)}
    val visible =remember{ mutableStateOf(false)}

    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 2.5.dp)
            .fillMaxWidth()




    ) {
        ShowDialog(visible = visible,tarefa = tarefa, tarefaRequest = tarefaRequest)
        Row (
            modifier = Modifier
                .height(intrinsicSize = IntrinsicSize.Min)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            visible.value = true;
                        },
                        onTap = {
                            if (tamanhomaximo.value == 1) tamanhomaximo.value =
                                1000 else tamanhomaximo.value = 1
                        }


                    )
                }

        ){
            BoxUrgente(tarefa)
            var maxLines = tamanhomaximo.value
            TextodaTarefa(tarefa,maxLines,
                Modifier
                    .weight(1f)
                    .fillMaxWidth()

                    .align(alignment = Alignment.CenterVertically)
            )
            CheckBoxTarefa(tarefa,
                Modifier
                    .weight(1f)
                    .align(alignment = Alignment.CenterVertically),tarefaRequest=tarefaRequest)
            TextoDone(tarefa)

        }


    }
}


//
//@Composable
//fun alertDialog(): ((Offset) -> Unit)? {
//
//    val context = LocalContext.current
//    val openDialog = remember { mutableStateOf(true) }
//
//    if (openDialog.value)
//    {
//        AlertDialog(
//            onDismissRequest = { openDialog.value = false },
//            title = { Text(text = "AlertDialog", color = Color.Black) },
//            text = { Text(text = "Voce deseja realmente excluir esta tarefa", color = Color.Black) },
//
//            confirmButton = {
//
//                TextButton(
//                    onClick = {
//                        openDialog.value = false
//                        Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show()
//                    }) {
//                    Text(text = "OK", color = Color.Black)
//                }
//
//            },
//            dismissButton = {
//                TextButton(
//                    onClick = {
//                        openDialog.value = false
//                        Toast.makeText(context, "Cancel", Toast.LENGTH_SHORT).show()
//                    }) {
//                    Text(text = "Cancel", color = Color.Black)
//                }
//            },
//            backgroundColor = Color.Green,
//            contentColor = Color.White
//        )
//    }
//}

@Composable
fun ShowDialog(visible: MutableState<Boolean>,tarefa : Tarefa, tarefaRequest : TarefaRequest?)
{
    if(visible.value)
    {
        AlertDialog(
            onDismissRequest = { visible.value=false },
            dismissButton = {
                TextButton(onClick = { visible.value = false}) {
                    Text(text= "Cancel",
                        color = Color(0xFF03DAC5))
                }
            },
            confirmButton = {
                TextButton(onClick = { visible.value =false
                                tarefaRequest?.deleteTarefa(tarefa=tarefa)



                }) {
                    Text(text="OK",
                        color = Color(0xFF03DAC5)
                    )
                }
            },
            title = {Text(text="EXCLUIR TAREFA")},
            text= { Text(text = "Voce deseja realmente excluir esta tarefa")}

        )


    }

}



@Composable
fun CheckBoxTarefa(tarefa:Tarefa,modifier: Modifier,tarefaRequest: TarefaRequest?)
{
    val checkedState = remember { mutableStateOf(tarefa.isDone) }
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = {
            checkedState.value = !checkedState.value
            tarefa.isDone = checkedState.value
            if (checkedState.value) {
                tarefaRequest?.updateTarefa(tarefa, "t")
            } else {
                tarefaRequest?.updateTarefa(tarefa, "f")
            }

        },

            colors = CheckboxDefaults.colors(
            checkedColor= Color(0xFF1CA2CA),
            uncheckedColor=Color(0xFF000000),
            checkmarkColor=Color(0xFFF7F7F7),
            disabledColor=Color(0xFF250152),

            ),
            modifier =modifier

    )

}


@Composable
fun BoxUrgente(tarefa:Tarefa)
{
    if(tarefa.isUrgent)
    {
        Box(modifier =
        Modifier
            .background(Color.Red)
            .width(20.dp)
            .fillMaxHeight()

        )
    }
    else
    {
        Box(modifier =
        Modifier
            .background(Color.Green)
            .width(20.dp)
            .fillMaxHeight()
        )
    }


}

@Composable
fun TextodaTarefa(tarefa:Tarefa,maxLines:Int,modifier: Modifier)
{
        Text(
            tarefa.description,
            overflow = TextOverflow.Ellipsis,
            maxLines=maxLines,
            modifier = modifier


    )

}


@Composable
fun TextoDone(tarefa:Tarefa)
{
    Text(
        stringResource(id = R.string.lbl_done),
        color = MaterialTheme.colors.onSecondary
    )
}



@Preview
@Composable
fun PreviewTarefaItemView() {
    val tarefa = Tarefa(false, "JOGAR CS GO COM OS AMIGOS", true)
    TodoEsdrasListaTheme(darkTheme = false) {
         // tarefa.rate = 1.5f
        TarefaItemView(tarefa,null)
    }
}