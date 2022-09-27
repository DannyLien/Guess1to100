package com.tom.guess1to100

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.math.absoluteValue

class GuessViewModel : ViewModel() {
    private val TAG: String? = GuessViewModel::class.java.simpleName
    var min = MutableLiveData<Int>()
    var max = MutableLiveData<Int>()
    val secret = Random().nextInt(100) - 1
    var result = MutableLiveData<Int>()

    companion object {
        val INIT = 9
        val BIGGER = 1
        val SMALLER = 2
        val BINGO = 3
    }

    init {
        min.value = 1
        max.value = 100
        Log.d(TAG, "secret: $secret")
        result.value = INIT
    }

    fun guessNumber(num: Int) {
        var r:Int
        if (num > secret) {
            max.value = num
            r = SMALLER
        } else if (num < secret) {
            min.value = num
            r = BIGGER
        } else {
            //bingo
            r = BINGO
        }
        result.value = r
    }


}