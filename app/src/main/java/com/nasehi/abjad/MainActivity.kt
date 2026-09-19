package com.nasehi.abjad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                CompositionLocalProvider(
                    LocalLayoutDirection provides LayoutDirection.Rtl
                ) {
                    AbjadApplication()
                }
            }
        }
    }
}


// ================================
// صفحه شروع برنامه
// ================================

@Composable
fun AbjadApplication() {

    var showSplash by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(3000)
        showSplash = false
    }

    if (showSplash) {
        SplashScreen()
    } else {
        AbjadApp()
    }
}


@Composable
fun SplashScreen() {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF172554)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "✦",
                fontSize = 70.sp,
                color = Color(0xFFFFD54F)
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "محاسبات ابجد و ارتعاش اسم",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(35.dp))

            Text(
                text = "سلامت کده",
                fontSize = 17.sp,
                color = Color(0xFFD1D5DB)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "دکتر لیلا هیزجی",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD54F)
            )

            Spacer(modifier = Modifier.height(45.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(0.65f),
                color = Color(0x55FFFFFF)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "طراحی و توسعه نرم‌افزار",
                fontSize = 15.sp,
                color = Color(0xFFD1D5DB)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "دکتر محمدحسن ناصحی",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "DR.NASEHI",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD54F)
            )
        }
    }
}


// ================================
// جدول ابجد کبیر
// ================================

val abjadValues = mapOf(

    'ا' to 1,
    'آ' to 1,
    'أ' to 1,
    'إ' to 1,

    'ب' to 2,
    'پ' to 2,

    'ج' to 3,
    'چ' to 3,

    'د' to 4,

    'ه' to 5,
    'ة' to 5,

    'و' to 6,

    'ز' to 7,
    'ژ' to 7,

    'ح' to 8,

    'ط' to 9,

    'ی' to 10,
    'ي' to 10,
    'ى' to 10,

    'ک' to 20,
    'ك' to 20,
    'گ' to 20,

    'ل' to 30,

    'م' to 40,

    'ن' to 50,

    'س' to 60,

    'ع' to 70,

    'ف' to 80,

    'ص' to 90,

    'ق' to 100,

    'ر' to 200,

    'ش' to 300,

    'ت' to 400,

    'ث' to 500,

    'خ' to 600,

    'ذ' to 700,

    'ض' to 800,

    'ظ' to 900,

    'غ' to 1000
)


// ================================
// محاسبه ابجد
// ================================

fun calculateAbjad(text: String): Int {

    return text.sumOf { char ->
        abjadValues[char] ?: 0
    }
}


// ================================
// دسته بندی تقسیم بر 4
// ================================

fun categoryByFour(number: Int): String {

    return when (number % 4) {

        0 -> "اشرافی"

        3 -> "عرفانی"

        2 -> "کم‌درآمد"

        1 -> "حادثه"

        else -> "-"
    }
}


// ================================
// دسته بندی تقسیم بر 3
// ================================

fun categoryByThree(number: Int): String {

    return when (number % 3) {

        0 -> "ثابت"

        2 -> "صعودی"

        1 -> "نزولی"

        else -> "-"
    }
}


// ================================
// صفحه اصلی برنامه
// ================================

@Composable
fun AbjadApp() {

    var personName by remember { mutableStateOf("") }

    var motherName by remember { mutableStateOf("") }

    var totalAbjad by remember { mutableStateOf(0) }

    var resultFour by remember { mutableStateOf("") }

    var resultThree by remember { mutableStateOf("") }

    var calculated by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(modifier = Modifier.height(20.dp))


        // عنوان

        Text(
            text = "محاسبه‌گر ابجد کبیر",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color(0xFF4527A0)
        )


        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = "محاسبه ابجد نام شخص و نام مادر",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )


        Spacer(modifier = Modifier.height(30.dp))


        // نام شخص

        OutlinedTextField(

            value = personName,

            onValueChange = {
                personName = it
                calculated = false
            },

            label = {
                Text("نام شخص")
            },

            placeholder = {
                Text("مثال: محمدحسن")
            },

            singleLine = true,

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(14.dp)
        )


        Spacer(modifier = Modifier.height(16.dp))


        // نام مادر

        OutlinedTextField(

            value = motherName,

            onValueChange = {
                motherName = it
                calculated = false
            },

            label = {
                Text("نام مادر")
            },

            placeholder = {
                Text("مثال: معصومه")
            },

            singleLine = true,

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(14.dp)
        )


        Spacer(modifier = Modifier.height(24.dp))


        // دکمه محاسبه

        Button(

            onClick = {

                val personAbjad =
                    calculateAbjad(personName.trim())

                val motherAbjad =
                    calculateAbjad(motherName.trim())

                totalAbjad =
                    personAbjad + motherAbjad

                resultFour =
                    categoryByFour(totalAbjad)

                resultThree =
                    categoryByThree(totalAbjad)

                calculated = true
            },

            enabled =
                personName.isNotBlank() &&
                motherName.isNotBlank(),

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(16.dp)
        ) {

            Text(
                text = "محاسبه",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(modifier = Modifier.height(30.dp))


        // ================================
        // نمایش نتیجه
        // ================================

        if (calculated) {

            Card(
                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(24.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF8F5FF)
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {


                    Text(
                        text = "نتیجه محاسبات",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF311B92)
                    )


                    Spacer(
                        modifier = Modifier.height(25.dp)
                    )


                    Text(
                        text = "مجموع ابجد",
                        fontSize = 16.sp
                    )


                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )


                    Text(
                        text = "$totalAbjad",
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6D4FB3)
                    )


                    Spacer(
                        modifier = Modifier.height(25.dp)
                    )


                    // نتیجه تقسیم بر 4

                    Surface(
                        modifier = Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(18.dp),

                        color =
                            Color(0xFFEDE7F6)
                    ) {

                        Column(
                            modifier =
                                Modifier.padding(20.dp),

                            horizontalAlignment =
                                Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = resultFour,
                                fontSize = 26.sp,
                                fontWeight =
                                    FontWeight.Bold,
                                color =
                                    Color(0xFF4527A0)
                            )
                        }
                    }


                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )


                    // نتیجه تقسیم بر 3

                    Surface(
                        modifier = Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(18.dp),

                        color =
                            Color(0xFFE8F5E9)
                    ) {

                        Column(
                            modifier =
                                Modifier.padding(20.dp),

                            horizontalAlignment =
                                Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = resultThree,
                                fontSize = 26.sp,
                                fontWeight =
                                    FontWeight.Bold,
                                color =
                                    Color(0xFF2E7D32)
                            )
                        }
                    }


                    Spacer(
                        modifier = Modifier.height(25.dp)
                    )


                    HorizontalDivider()


                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )


                    Text(
                        text = "سلامت کده دکتر لیلا هیزجی",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF6D4FB3)
                    )
                }
            }
        }


        Spacer(
            modifier = Modifier.height(30.dp)
        )
    }
}
