package com.nasehi.abjad
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun AbjadApplication() {

    var showSplash by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000)
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
                text = "بر اساس آموزش‌های",
                fontSize = 16.sp,
                color = Color(0xFFD1D5DB)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "دکتر لیدا هیزجی",
                fontSize = 24.sp,
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

// جدول ابجد کبیر
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

fun calculateAbjad(text: String): Int {
    return text.sumOf { char ->
        abjadValues[char] ?: 0
    }
}

fun categoryByFour(number: Int): String {
    return when (number % 4) {
        0 -> "اشرافی"
        3 -> "عرفانی"
        2 -> "کم‌درآمد"
        1 -> "حادثه"
        else -> "-"
    }
}

fun categoryByThree(number: Int): String {
    return when (number % 3) {
        0 -> "ثابت"
        2 -> "صعودی"
        1 -> "نزولی"
        else -> "-"
    }
}

@Composable
fun AbjadApp() {

    var personName by remember { mutableStateOf("") }
    var motherName by remember { mutableStateOf("") }

    var personAbjad by remember { mutableStateOf(0) }
    var motherAbjad by remember { mutableStateOf(0) }
    var totalAbjad by remember { mutableStateOf(0) }

    var resultFour by remember { mutableStateOf("") }
    var resultThree by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "محاسبه‌گر ابجد کبیر",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "محاسبه ابجد نام شخص و نام مادر",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = personName,
            onValueChange = { personName = it },
            label = { Text("نام شخص") },
            placeholder = { Text("مثال: محمدحسن") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = motherName,
            onValueChange = { motherName = it },
            label = { Text("نام مادر") },
            placeholder = { Text("مثال: معصومه") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                personAbjad = calculateAbjad(personName)
                motherAbjad = calculateAbjad(motherName)

                totalAbjad = personAbjad + motherAbjad

                resultFour = categoryByFour(totalAbjad)
                resultThree = categoryByThree(totalAbjad)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(
                text = "محاسبه",
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        if (totalAbjad > 0) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "نتیجه محاسبه",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "ابجد نام شخص: $personAbjad",
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "ابجد نام مادر: $motherAbjad",
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "مجموع ابجد: $totalAbjad",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 18.dp)
                    )

                    Text(
                        text = "تقسیم بر ۴",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "باقیمانده: ${totalAbjad % 4}",
                        fontSize = 17.sp
                    )

                    Text(
                        text = "نتیجه: $resultFour",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 18.dp)
                    )

                    Text(
                        text = "تقسیم بر ۳",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "باقیمانده: ${totalAbjad % 3}",
                        fontSize = 17.sp
                    )

                    Text(
                        text = "نتیجه: $resultThree",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "این محاسبات بر اساس روش ابجد و دسته‌بندی تعریف‌شده در برنامه انجام می‌شود.",
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}
