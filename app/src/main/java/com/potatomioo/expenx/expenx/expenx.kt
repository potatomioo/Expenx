package com.potatomioo.expenx.expenx

data class expenx(
    val id : String,
    val amount : Double,
    val description : String,
    val timeStamp : Long = System.currentTimeMillis()
)