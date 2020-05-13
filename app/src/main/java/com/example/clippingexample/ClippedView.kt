package com.example.clippingexample

import android.content.Context
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View


//@JvmOverloads annotation instructs the Kotlin compiler to generate overloads for this function
// that substitute default parameter values
class ClippedView @JvmOverloads constructor(
    context: Context,
    attrs:AttributeSet?=null,
    defStyleAttr: Int=0
):View(context,attrs,defStyleAttr){

    private val paint = Paint().apply{
        isAntiAlias=true
        strokeWidth =resources.getDimension(R.dimen.strokeWidth)
        textSize = resources.getDimension(R.dimen.textSize)
    }

    private var path = Path()

    private val clipRectRight= resources.getDimension(R.dimen.clipRectRight)
    private val clipRectLeft= resources.getDimension(R.dimen.clipRectLeft)
    private val clipRectTop= resources.getDimension(R.dimen.clipRectTop)
    private val clipRectBottom= resources.getDimension(R.dimen.clipRectBottom)

    private val rectInset= resources.getDimension(R.dimen.rectInset)
    private val smallRectOffset= resources.getDimension(R.dimen.smallRctOffset)

    private val circleRadius= resources.getDimension(R.dimen.circleRadius)

    private val textOffset= resources.getDimension(R.dimen.textOffset)
    private val textSize= resources.getDimension(R.dimen.textSize)


}