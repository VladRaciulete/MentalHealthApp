package com.example.mentalhealth.presentation

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.mentalhealth.ui.theme.*
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mentalhealth.R
import com.example.mentalhealth.domain.model.Goal
import com.example.mentalhealth.utils.Constants
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

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
                containerColor = if (isExpanded) FocusedTextFieldColor else Color.Transparent,
                disabledTextColor = if (isExpanded) FocusedTextFieldTextColor else UnfocusedTextFieldTextColor,
                disabledBorderColor = if (isExpanded) FocusedBorderColor else UnfocusedBorderColor,
                disabledLabelColor = if (isExpanded) FocusedLabelColor else UnfocusedLabelColor,
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    label: String,
    selectedDate: State<String>,
    onDateSelected: (String) -> Unit,
) {
    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    val dateSplit = selectedDate.value.split("-")

    if (dateSplit.size > 2) {
        mYear = dateSplit[2].toInt()
        mMonth = dateSplit[1].toInt() - 1
        mDay = dateSplit[0].toInt()
    } else {
        mYear = mCalendar.get(Calendar.YEAR)
        mMonth = mCalendar.get(Calendar.MONTH)
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    }

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker,
          mYear: Int,
          mMonth: Int,
          mDayOfMonth: Int ->
            onDateSelected(convertToDate(mYear, mMonth, mDayOfMonth))
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
        OutlinedTextField(
            value = selectedDate.value,
            label = {
                Text(
                    text = label,
                    color = UnfocusedTextWhiteColor
                )
            },
            enabled = false,
            onValueChange = {
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                disabledTextColor = UnfocusedTextFieldTextColor,
                disabledBorderColor = UnfocusedBorderColor,
                disabledLabelColor = UnfocusedLabelColor,
                errorTextColor = ErrorTextColor
            ),
            modifier = Modifier
                .clickable {
                    mDatePickerDialog.show()
                }
        )
    }
}

@Composable
fun CustomProgressIndicator() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor.copy(alpha = 0.7f))
    ) {
        CircularProgressIndicator(color = AccentColor)
    }
}

@Composable
fun JournalDatePicker(
    text: String,
    date: String,
    onDateSelected: (String) -> Unit,
) {
    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    val dateSplit = date.split("-")

    if (dateSplit.size > 2) {
        mYear = dateSplit[2].toInt()
        mMonth = dateSplit[1].toInt() - 1
        mDay = dateSplit[0].toInt()
    } else {
        mYear = mCalendar.get(Calendar.YEAR)
        mMonth = mCalendar.get(Calendar.MONTH)
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    }

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        {
            _: DatePicker,
            mYear: Int,
            mMonth: Int,
            mDayOfMonth: Int ->
            onDateSelected(convertToDate(mYear, mMonth, mDayOfMonth))
        },
        mYear,
        mMonth,
        mDay
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                color = TextWhiteColor,
                fontSize = 18.sp
            )
            Text(
                text = formatDate(date),
                color = TextWhiteColor,
                fontSize = 16.sp,
                modifier = Modifier
                    .clickable {
                        mDatePickerDialog.show()
                    }
            )
        }
    }
}

fun convertToDate(year: Int, month: Int, day: Int): String {
    val calendar = Calendar.getInstance().apply { set(year, month, day) }
    val dateFormat = SimpleDateFormat(Constants.APP_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(calendar.time)
}

fun formatDate(dateString: String): String {
    val todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern(Constants.APP_DATE_FORMAT))

    val inputFormat = SimpleDateFormat(Constants.APP_DATE_FORMAT, Locale.getDefault())
    val outputFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
    val todayFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

    val date: Date = try {
        inputFormat.parse(dateString) ?: return "Invalid date"
    } catch (e: Exception) {
        return "Invalid date"
    }

    if (dateString == todayDate) {
        return "Today, ${todayFormat.format(date)}"
    }

    return outputFormat.format(date)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePicker(
    label: String,
    state: MutableState<String>
) {
    var timeString = ""
    var isExpanded by remember { mutableStateOf(false) }

    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _: TimePicker, hour: Int, minute: Int ->
            timeString = if (hour < 9) "0$hour" else hour.toString()
            timeString = if (minute < 9) "$timeString:0$minute" else "$timeString:$minute"

            state.value = timeString
            isExpanded = false
        },
        7,
        0,
        false
    ).apply {
        setOnCancelListener {
            isExpanded = false
        }
    }

    OutlinedTextField(
        value = state.value,
        label = {
            Text(
                text = label
            )
        },
        enabled = false,
        onValueChange = { newValue ->
            state.value = newValue
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = if (isExpanded) FocusedTextFieldColor else Color.Transparent,
            disabledTextColor = if (isExpanded) FocusedTextFieldTextColor else UnfocusedTextFieldTextColor,
            disabledBorderColor = if (isExpanded) FocusedBorderColor else UnfocusedBorderColor,
            disabledLabelColor = if (isExpanded) FocusedLabelColor else UnfocusedLabelColor,
        ),
        modifier = Modifier
            .clickable {
                timePickerDialog.show()
                isExpanded = true
            }
    )
}

@Composable
fun RatingStars(
    text: String,
    state: MutableState<Int>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(BackgroundColor)
            .fillMaxWidth()
    ) {
        Text(text = text, color = TextWhiteColor, fontSize = 20.sp)

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {
            for (i in 1..5) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "Star",
                    tint = if (state.value >= i) StarSelectedColor else StarUnselectedColor,
                    modifier = Modifier
                        .clickable {
                            state.value = i
                        }
                        .padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun CustomLevelPicker(
    text: String,
    state: MutableState<Int>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = text,
            color = TextWhiteColor,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 15.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            for (i in 1..5) {
                Box(
                    modifier = Modifier
                        .background(
                            if (state.value == i) ItemSelectedColor else ItemUnselectedColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(vertical = 8.dp, horizontal = 18.dp)
                        .clickable {
                            state.value = i
                        }
                ) {
                    Text(
                        text = i.toString(),
                        color = TextWhiteColor,
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FeelingsPicker(
    text: String,
    list: List<String>,
    state: MutableState<String>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            color = TextWhiteColor,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(15.dp))

        FlowRow(
            modifier = Modifier
                .fillMaxWidth(0.9f)
        ) {
            list.forEachIndexed { index, item ->
                Column {
                    Box(
                        modifier = Modifier
                            .background(
                                if (state.value == item) ItemSelectedColor else ItemUnselectedColor,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                            .clickable {
                                state.value = item
                            }
                    ) {
                        Text(
                            text = item,
                            color = TextWhiteColor
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun CustomSpacerBorder(
    upSpace: Int,
    downSpace: Int,
    color: Color,
) {
    Spacer(modifier = Modifier.height(upSpace.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color)
    )
    Spacer(modifier = Modifier.height(downSpace.dp))
}

@Composable
fun CustomToggle(
    text: String,
    state: MutableState<Map<String, Boolean>>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            color = TextWhiteColor,
            fontSize = 20.sp
        )

        state.value.map { entry ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text(
                    text = entry.key,
                    color = TextWhiteColor
                )

                Switch(
                    checked = entry.value,
                    onCheckedChange = {
                        state.value = state.value.toMutableMap().apply {
                            put(entry.key, it)
                        }
                    },
                    colors = SwitchColors(
                        checkedThumbColor = TextWhiteColor,
                        checkedTrackColor = AccentColor,
                        checkedBorderColor = FocusedBorderColor,
                        checkedIconColor = Color.Transparent,
                        uncheckedThumbColor = Color.Gray,
                        uncheckedTrackColor = UncheckedTrackColor,
                        uncheckedBorderColor = FocusedBorderColor,
                        uncheckedIconColor = Color.Transparent,
                        disabledCheckedThumbColor = Color.Transparent,
                        disabledCheckedTrackColor = Color.Transparent,
                        disabledCheckedBorderColor = Color.Transparent,
                        disabledCheckedIconColor = Color.Transparent,
                        disabledUncheckedThumbColor = Color.Transparent,
                        disabledUncheckedTrackColor = Color.Transparent,
                        disabledUncheckedBorderColor = Color.Transparent,
                        disabledUncheckedIconColor = Color.Transparent
                    )
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGoals(
    text: String,
    state: MutableState<List<Goal>>
) {
    var addingGoal by remember { mutableStateOf(false) }
    var goalDescription by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            color = TextWhiteColor,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        state.value.forEach { goal ->
            OutlinedTextField(
                value = goal.description,
                enabled = false,
                onValueChange = {},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledTextColor = FocusedTextFieldTextColor,
                    disabledBorderColor = UnfocusedBorderColor,
                    errorTextColor = ErrorTextColor
                ),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            state.value = state.value.toMutableList().apply { remove(goal) }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = "Delete",
                            tint = Color.Red
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        if (addingGoal) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = goalDescription,
                    onValueChange = { newValue ->
                        goalDescription = newValue
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.goal_description),
                            color = UnfocusedTextWhiteColor
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedTextColor = TextWhiteColor
                    ),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                state.value = (state.value + Goal(
                                    ("goal${state.value.size + 1}"),
                                    goalDescription,
                                    0
                                )).toMutableList()
                                addingGoal = false
                                goalDescription = ""
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = "Delete",
                                tint = Color.Green
                            )
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    addingGoal = false
                    goalDescription = ""
                }
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
        } else {
            Button(
                onClick = {
                    addingGoal = true
                }
            ) {
                Text(text = stringResource(id = R.string.add_goal))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGoalsProgress(
    text: String,
    state: MutableState<List<Goal>>
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        state.value.forEach { goal ->
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = goal.description,
                    enabled = false,
                    onValueChange = {},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledTextColor = FocusedTextFieldTextColor,
                        disabledBorderColor = UnfocusedBorderColor,
                        errorTextColor = ErrorTextColor
                    ),
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
                CustomDropDownMenu2(
                    value = "${goal.progress}%",
                    itemList = Constants.progressValues,
                    onItemSelected = { selectedProgress ->
                        val updatedList = state.value.toMutableList()
                        val index = updatedList.indexOfFirst { it == goal }
                        if (index != -1) {
                            updatedList[index] = goal.copy(progress = selectedProgress)
                            state.value = updatedList
                        }
                    },
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropDownMenu2(
    value: String,
    itemList: List<Int>,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        OutlinedTextField(
            value = value,
            enabled = false,
            onValueChange = {
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = if (isExpanded) FocusedTextFieldColor else Color.Transparent,
                disabledTextColor = if (isExpanded) FocusedTextFieldTextColor else UnfocusedTextFieldTextColor,
                disabledBorderColor = if (isExpanded) FocusedBorderColor else UnfocusedBorderColor,
                disabledLabelColor = if (isExpanded) FocusedLabelColor else UnfocusedLabelColor,
                errorTextColor = ErrorTextColor
            ),
            textStyle = TextStyle(textAlign = TextAlign.Center),
            modifier = modifier
                .clickable {
                    isExpanded = !isExpanded
                }
        )

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = !isExpanded
            },
            modifier = Modifier.background(TextFieldBackgroundColor)
        ) {
            itemList.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(text = item.toString(), color = TextWhiteColor)
                    },
                    onClick = {
                        isExpanded = !isExpanded
                        onItemSelected(item)
                    }
                )
            }
        }
    }
}

@Composable
fun CustomTimeRangePicker(
    state: MutableState<String>,
    list: List<String>,
    onItemSelected: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .background(
                    color = AccentColor2,
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            for (item in list) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .background(
                            if (state.value == item) AccentColor else Color.Transparent,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable {
                            state.value = item
                            onItemSelected(item)
                        }
                ) {
                    Text(
                        text = item,
                        color = TextWhiteColor,
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CustomPercentageComponent(
    title: String,
    items: Map<String,Int>
){
    var totalValue = 0
    for (item in items) {
        totalValue += item.value
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(JournalEntryBackgroundColor2)
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = title,
            color = TextWhiteColor,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        for (item in items) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 5.dp)
            ) {
                val fraction = (item.value.toDouble() / totalValue).toFloat()

                Text(
                    text = item.key,
                    color = TextWhiteColor
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = AccentColor2,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .align(Alignment.Start)
                ){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction)
                            .padding(2.dp)
                            .background(
                                color = AccentColor,
                                shape = RoundedCornerShape(8.dp)
                            )
                    ){
                        Text(text = "")
                    }
                }
            }
        }
    }
}

private fun filterList(query: String, occupationsList: List<String>): List<String> {
    return occupationsList.filter { it.contains(query, ignoreCase = true) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAutoComplete(
    label: String,
    selectedItem: State<String>,
    list: List<String>,
    onItemSelected: (String) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    var textFieldWidth by remember { mutableStateOf(0.dp) }

    val currentDensity = LocalDensity.current

    Column(
        modifier = Modifier
            .clickable(
                onClick = {
                    isExpanded = false
                }
            )
    ) {
        Row {
            OutlinedTextField(
                label = {
                    Text(text = label)
                },
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    textFieldWidth = with(currentDensity) { coordinates.size.width.toDp() }
                },
                value = selectedItem.value,
                onValueChange = {
                    onItemSelected(it)
                    isExpanded = true
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = if (isExpanded) FocusedTextFieldColor else Color.Transparent,
                    disabledTextColor = if (isExpanded) FocusedTextFieldTextColor else UnfocusedTextFieldTextColor,
                    disabledBorderColor = if (isExpanded) FocusedBorderColor else UnfocusedBorderColor,
                    disabledLabelColor = if (isExpanded) FocusedLabelColor else UnfocusedLabelColor,
                    focusedTextColor = TextWhiteColor,
                    unfocusedTextColor = UnfocusedTextWhiteColor
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = { isExpanded = !isExpanded }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_down_arrow),
                            modifier = Modifier.size(24.dp),
                            contentDescription = "DownArrow",
                            tint = Color.White
                        )
                    }
                }
            )
        }

        AnimatedVisibility(
            visible = isExpanded
        ) {
            Card(
                modifier = Modifier.width(textFieldWidth),
                shape = RoundedCornerShape(10.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .heightIn(max = 150.dp)
                        .background(FocusedTextFieldColor)
                ) {
                    if (selectedItem.value.isNotEmpty()) {
                        items(
                            list.filter {
                                it.lowercase()
                                    .contains(selectedItem.value.lowercase()) || it.lowercase().contains("others")
                            }
                            .sorted()
                        ) {
                            AutoCompleteItem(title = it) { title ->
                                isExpanded = false
                                onItemSelected(title)
                            }
                        }
                    } else {
                        items(
                            list.sorted()
                        ) {
                            AutoCompleteItem(title = it) { title ->
                                isExpanded = false
                                onItemSelected(title)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AutoCompleteItem(
    title: String,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 16.sp)
    }
}

