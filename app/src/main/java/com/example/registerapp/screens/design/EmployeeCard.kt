package com.example.registerapp.screens.design

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.registerapp.R
import com.example.registerapp.database.api.Employee
import com.example.registerapp.theme.RegisterAppTheme

@Composable
fun EmployeeCard(
    modifier: Modifier = Modifier,
    employee: Employee? = null
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(horizontal = 16.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0EBFC)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ){
                if(employee != null){
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(employee.imageUrl)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.profile),
                        error = painterResource(R.drawable.profile),
                        contentDescription = "Profile image of ${employee.fullName}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }else{
                    Card(
                        modifier = Modifier.fillMaxSize(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFAFA6C5)),
                        shape = CircleShape
                    ) {}
                }
            }


            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                if(employee != null){
                    Text(
                        text = employee.fullName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1A1A1A),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = employee.email,
                        fontSize = 13.sp,
                        color = Color(0xFF666666),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }else{
                    for(index in 0 until 2){
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(if(index == 0) 0.6f else 0.8f)
                                .height(if (index == 0) 16.dp else 12.dp)
                                .padding(bottom = if(index == 0) 4.dp else 0.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0))
                        ) {}
                    }
                }
            }

//            if (employee != null){
//                Card(
//                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE1BEE7)),
//                    modifier = Modifier.padding(start = 8.dp)
//                ) {
//                    Text(
//                        text = "#${employee.id}",
//                        fontSize = 11.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = Color(0xFF4A148C),
//                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
//                    )
//                }
//            }

        }
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun EmployeeCardPreview() {
    RegisterAppTheme {
        Column {
            // Con datos
            EmployeeCard(
                employee = Employee(
                    id = 1001,
                    imageUrl = "https://hub.dummyapis.com/Image?text=CA&height=120&width=120",
                    firstName = "María",
                    lastName = "Garcés",
                    email = "m.garces@gmail.com"
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            EmployeeCard(employee = null)
        }
    }
}
