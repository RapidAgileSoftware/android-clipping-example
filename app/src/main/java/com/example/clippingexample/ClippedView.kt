package com.example.clippingexample

import android.content.Context
import android.util.AttributeSet
import android.view.View


//@JvmOverloads annotation instructs the Kotlin compiler to generate overloads for this function
// that substitute default parameter values
class ClippedView @JvmOverloads constructor(
    context: Context,
    attrs:AttributeSet?=null,
    defStyleAttr: Int=0
):View(context,attrs,defStyleAttr){

}