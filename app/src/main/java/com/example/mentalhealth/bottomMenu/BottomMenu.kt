package com.example.mentalhealth.bottomMenu

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mentalhealth.ui.theme.*

@Composable
fun BottomMenu(
    navController: NavController,
    items: List<BottomMenuItem>,
    modifier: Modifier = Modifier,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(AccentColor)
            .padding(20.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                navController = navController,
                item = item,
                isSelected = index == selectedItemIndex,
                route = item.route
            ) {
                selectedItemIndex = index
                navController.navigate(item.route)
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    navController: NavController,
    item: BottomMenuItem,
    isSelected: Boolean = false,
    route: String,//TODO
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (isSelected)
                        Color.Transparent//SelectedButtonAccentColor
                    else
                        Color.Transparent
                )
                .padding(5.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if(isSelected) SelectedColor else UnselectedColor,
                modifier = Modifier.size(35.dp)
            )
        }
    }
}