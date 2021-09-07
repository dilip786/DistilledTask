package com.android.distilled.database

import android.text.TextUtils

fun List<String>.convertListToString(): String {
    return TextUtils.join(",", this)
}

fun List<Int>.convertIntegerListToString(): String {
    return TextUtils.join(",", this)
}

fun String.convertStringToList(): List<String> {

    if (this.isEmpty()) {
        return emptyList()
    }

    val splitArray = this.split(",").toTypedArray()
    return listOf(*splitArray)
}

fun String.convertStringToIntegerList(): List<Int> {

    if (this.isEmpty()) {
        return emptyList()
    }

    val splitArray = this.split(",").toTypedArray()
    val numberList: ArrayList<Int> = arrayListOf()
    for (num in splitArray) {
        numberList.add(num.toInt())
    }
    return numberList
}
