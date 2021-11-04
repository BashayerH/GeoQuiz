package com.example.geoquiz

import androidx.annotation.StringRes

// الداتا لتخزين البيانات
// تتاكد من اليوزر انو يعطيني سترنق
data class Question (@StringRes val textResId:Int ,val answer:Boolean )