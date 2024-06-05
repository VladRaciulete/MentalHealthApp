package com.example.mentalhealth.presentation

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.mentalhealth.ui.theme.*
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropDownMenu(
    itemList: List<String>,
    placeholder: String,
    selectedItem: State<String>,
    onItemSelected: (String) -> Unit,
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = selectedItem.value,
            label = {
                Text(text = placeholder, color = UnfocusedTextWhiteColor)
            },
            enabled = false,
            onValueChange = {
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedTextColor = UnfocusedTextWhiteColor,
                containerColor = if(!isExpanded) BackgroundColor else TextFieldBackgroundColor,
                disabledTextColor = if(!isExpanded) UnfocusedTextWhiteColor else TextWhiteColor,
                disabledBorderColor = UnfocusedTextWhiteColor,
                errorTextColor = ErrorTextColor
            ),
            modifier = Modifier
                .clickable {
                    isExpanded = !isExpanded
                },
            isError = isError
        )

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = !isExpanded
            },
            modifier = Modifier.background(TextFieldBackgroundColor)
        ) {
            itemList.forEach { string ->
                DropdownMenuItem(
                    text = {
                           Text(text = string, color = TextWhiteColor)
                    },
                    onClick = {
                        isExpanded = !isExpanded
                        onItemSelected(string)
                    }
                )
            }
        }
    }
}


@Composable
fun CustomDatePicker (
    selectedDate: State<String>,
    onDateSelected: (String) -> Unit,
) {
    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    var dateSplit = selectedDate.value.split("-")

    if(dateSplit.size > 2){
        mYear = dateSplit[2].toInt()
        mMonth = dateSplit[1].toInt() - 1
        mDay = dateSplit[0].toInt()
    }
    else {
        mYear = mCalendar.get(Calendar.YEAR)
        mMonth = mCalendar.get(Calendar.MONTH)
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    }

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker,
          mYear: Int,
          mMonth: Int,
          mDayOfMonth: Int -> onDateSelected("$mDayOfMonth-${mMonth+1}-$mYear")
        },
        mYear,
        mMonth,
        mDay
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Selected Date:", color = TextWhiteColor)
            Text(text = selectedDate.value, color = TextWhiteColor)
        }

        Button(
            onClick = {
            mDatePickerDialog.show()
            },
            colors = ButtonDefaults.buttonColors(
            //containerColor = Color(0XFF0F9D58)
            )
        ) {
            Text(text = "Select Date", color = TextWhiteColor)
        }
    }
}

@Composable
fun CustomProgressIndicator(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor.copy(alpha = 0.7f))
    ){
        CircularProgressIndicator(color = AccentColor)
    }
}
