package com.example.registerapp.screens

            import androidx.compose.foundation.Image
            import androidx.compose.foundation.background
            import androidx.compose.foundation.gestures.snapping.SnapPosition.Center
            import androidx.compose.foundation.layout.Arrangement
            import androidx.compose.foundation.layout.Box
            import androidx.compose.foundation.layout.Column
            import androidx.compose.foundation.layout.PaddingValues
            import androidx.compose.foundation.layout.Spacer
            import androidx.compose.foundation.layout.aspectRatio
            import androidx.compose.foundation.layout.fillMaxSize
            import androidx.compose.foundation.layout.fillMaxWidth
            import androidx.compose.foundation.layout.height
            import androidx.compose.foundation.layout.padding
            import androidx.compose.foundation.layout.size
            import androidx.compose.foundation.shape.CircleShape
            import androidx.compose.material3.Button
            import androidx.compose.material3.MaterialTheme
            import androidx.compose.material3.Text
            import androidx.compose.runtime.Composable
            import androidx.compose.ui.Alignment
            import androidx.compose.ui.Modifier
            import androidx.compose.ui.draw.clip
            import androidx.compose.ui.geometry.Offset
            import androidx.compose.ui.graphics.Brush
            import androidx.compose.ui.graphics.Color
            import androidx.compose.ui.res.painterResource
            import androidx.compose.ui.text.font.FontWeight
            import androidx.compose.ui.text.style.TextAlign
            import androidx.compose.ui.unit.dp
            import androidx.compose.ui.unit.sp
            import com.example.registerapp.R

@Composable
            fun WelcomeScreen(
                modifier: Modifier = Modifier
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
//                        .padding(20.dp)
//                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
//                                    Color(0xFFD0BCFF),
                                    Color(0xFFCCC2DC),
                                    Color(0xFFFEF7FF),
                                    Color(0xFFFEF7FF),
                                )
                            )
                        ),
                        verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Welcome!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 46.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 1.dp)
                            .padding(bottom = 1.dp)
                            .align(Alignment.CenterHorizontally),

                        )

                    Text(
                        text = "Join me on this journey of learning and " +
                                "exploring the world of Android development!",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp)
                            .align(Alignment.CenterHorizontally),
                        color = MaterialTheme.colorScheme.onBackground
                    )



                    Image(
                        painter = painterResource(id = R.drawable.figma_solo),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
//                            .fillMaxWidth(0.25f)
                            .aspectRatio(1f),
//                            .fillMaxSize()
//                            .padding(20.dp)
//                            .size(200.dp)
//                            .clip(CircleShape),
                        alignment = Alignment.Center,
                    )


                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 16.dp),
                        onClick = { /* Navigate to Register Screen */ }
                    ) {
                        Text(
                            text = "Get Started",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(PaddingValues(vertical = 8.dp,
                                    horizontal = 16.dp))
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))



//                    Button(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 20.dp)
//                            .padding(bottom = 16.dp),
//                        onClick = { /* Navigate to Register Screen */ }
//                    ) {
//                        Text(
//                            text = "Register",
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.SemiBold,
//                            modifier = Modifier
//                                .padding(PaddingValues(vertical = 8.dp,
//                                    horizontal = 16.dp))
//                        )
//                    }

                }
            }