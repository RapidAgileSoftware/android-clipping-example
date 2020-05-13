package com.example.clippingexample

import android.content.Context
import android.graphics.Canvas
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

    private val columnOne = rectInset
    private val columnTwo = columnOne + rectInset + clipRectRight

    private val rowOne = rectInset
    private val rowTwo = rowOne + rectInset + clipRectBottom
    private val rowThree = rowTwo + rectInset + clipRectBottom
    private val rowFour = rowThree + rectInset + clipRectBottom
    private val textRow = rowFour + (1.5 * clipRectBottom)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawBackAndUnclippedRectangle(canvas)
        drawDifferenceClippingExample(canvas)
        drawCircularClippingExample(canvas)
        drawIntersectionClippingExample(canvas)
        drawCombinedClippingExample(canvas)
        drawRoundedRectangleClippingExample(canvas)
        drawOutsideClippingExample(canvas)
        drawSkewedTextExample(canvas)
        drawTranslatedTextExample(canvas)
        drawQuickRejectExample(canvas)
    }
    private fun drawBackAndUnclippedRectangle(canvas:Canvas){

    }
    private fun drawDifferenceClippingExample(canvas: Canvas){

    }
    private fun drawCircularClippingExample(canvas: Canvas){

    }
    private fun drawIntersectionClippingExample(canvas: Canvas){

    }
    private fun drawCombinedClippingExample(canvas: Canvas){

    }
    private fun drawRoundedRectangleClippingExample(canvas: Canvas){

    }
    private fun drawOutsideClippingExample(canvas: Canvas){

    }
    private fun drawSkewedTextExample(canvas: Canvas){

    }
    private fun drawTranslatedTextExample(canvas: Canvas){

    }
    private fun drawQuickRejectExample(canvas: Canvas){
        
    }
}