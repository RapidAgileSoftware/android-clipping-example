package com.example.clippingexample

import android.content.Context
import android.graphics.*
import android.os.Build
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
        drawPictureFrameExample(canvas)
        drawCircularClippingExample(canvas)
        drawIntersectionClippingExample(canvas)
        drawCombinedClippingExample(canvas)
        drawRoundedRectangleClippingExample(canvas)
        drawOutsideClippingExample(canvas)
        drawSkewedTextExample(canvas)
        drawTranslatedTextExample(canvas)
        drawQuickRejectExample(canvas)
    }
    // the app draws the same Rectangle 7 times
    private fun drawClippedRectangle(canvas: Canvas){
        // set boundaries for the whole shape
        // reduces region in which future operations can write to
        canvas.clipRect(
            clipRectLeft,
            clipRectTop,
            clipRectRight,
            clipRectBottom
        )
        // we apply to entire canvas, but only clipped area is filled -> creates white rectangle
        canvas.drawColor(Color.GREEN)
        // lets draw a red diagonal line
        paint.color = Color.YELLOW
        canvas.drawLine(
            clipRectLeft, clipRectTop,
            clipRectRight, clipRectBottom,
            paint
        )
        canvas.drawLine(
            clipRectLeft,  clipRectBottom,
            clipRectRight,clipRectTop,
            paint
        )
        // draw green circle
        paint.color = Color.BLACK
        canvas.drawCircle(
            circleRadius, clipRectBottom - circleRadius,
            circleRadius,
            paint
        )
        // set blue text aligned with the right edge of the rectangle
        paint.color = Color.RED
        paint.textAlign = Paint.Align.RIGHT
        canvas.drawText(
            context.getString(R.string.clipping),
            clipRectRight, textOffset,paint
        )
    }

    private fun drawBackAndUnclippedRectangle(canvas:Canvas){
        //set entire background to gray
        canvas.drawColor(Color.GRAY)
        // save the canvas
        canvas.save()
        // translate to first column and row position
        canvas.translate(columnOne,rowOne)
        // draw our shape
        drawClippedRectangle(canvas)
        // restore canvas to its previous state
        canvas.restore()

    }
    // puts a second clipping rectangle in the middle so our shape gets the effect oof a picture frame
    private fun drawPictureFrameExample(canvas: Canvas){
        // save the canvas so we can restore the state before the following transformations
        canvas.save()
        // put it in the second column of the first row
        canvas.translate(columnTwo, rowOne)
        // use the subtraction of two clipping rectangles to create frame effect
        canvas.clipRect(
            2*rectInset, 2*rectInset,
            clipRectRight - 2*rectInset,
            clipRectBottom - 2*rectInset
        )
        // The method clipRect(float, float, float, float, Region.Op
        // .DIFFERENCE) was deprecated in API level 26. The recommended
        // alternative method is clipOutRect(float, float, float, float),
        // which is currently available in API level 26 and higher.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            canvas.clipRect(
                4 * rectInset,4 * rectInset,
                clipRectRight - 4 * rectInset,
                clipRectBottom - 4 * rectInset,
                Region.Op.DIFFERENCE
            )
        else {
            canvas.clipOutRect(
                4 * rectInset,4 * rectInset,
                clipRectRight - 4 * rectInset,
                clipRectBottom - 4 * rectInset
            )
        }
        drawClippedRectangle(canvas)
        canvas.restore()

    }
    // clips a circular out
    private fun drawCircularClippingExample(canvas: Canvas){
        // save the canvas so we can restore the state before the following transformations
        canvas.save()
        // put it in the first column of the second row
        canvas.translate(columnOne, rowTwo)
        // Clears any lines and curves from the path but unlike reset(),
        // keeps the internal data structure for faster reuse.
        path.rewind()
        path.addCircle(
            circleRadius,clipRectBottom - circleRadius,
            circleRadius,Path.Direction.CCW
        )
        // The method clipPath(path, Region.Op.DIFFERENCE) was deprecated in
        // API level 26. The recommended alternative method is
        // clipOutPath(Path), which is currently available in
        // API level 26 and higher.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            canvas.clipPath(path, Region.Op.DIFFERENCE)
        } else {
            canvas.clipOutPath(path)
        }
        drawClippedRectangle(canvas)
        canvas.restore()

    }
    private fun drawIntersectionClippingExample(canvas: Canvas){
        canvas.save()
        canvas.translate(columnTwo,rowTwo)
        canvas.clipRect(
            clipRectLeft,clipRectTop,
            clipRectRight - smallRectOffset,
            clipRectBottom - smallRectOffset
        )
        // The method clipRect(float, float, float, float, Region.Op
        // .INTERSECT) was deprecated in API level 26. The recommended
        // alternative method is clipRect(float, float, float, float), which
        // is currently available in API level 26 and higher.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            canvas.clipRect(
                clipRectLeft + smallRectOffset,
                clipRectTop + smallRectOffset,
                clipRectRight,clipRectBottom,
                Region.Op.INTERSECT
            )
        } else {
            canvas.clipRect(
                clipRectLeft + smallRectOffset,
                clipRectTop + smallRectOffset,
                clipRectRight,clipRectBottom
            )
        }
        drawClippedRectangle(canvas)
        canvas.restore()

    }
    // combine a circle and rectangle to clip out the path
    private fun drawCombinedClippingExample(canvas: Canvas){
        canvas.save()
        canvas.translate(columnOne, rowThree)
        path.rewind()
        path.addCircle(
            clipRectLeft + rectInset + circleRadius,
            clipRectTop + circleRadius + rectInset,
            circleRadius,Path.Direction.CCW
        )
        path.addRect(
            clipRectRight / 2 - circleRadius,
            clipRectTop + circleRadius + rectInset,
            clipRectRight / 2 + circleRadius,
            clipRectBottom - rectInset,Path.Direction.CCW
        )
        canvas.clipPath(path)
        drawClippedRectangle(canvas)
        canvas.restore()
    }
    // RectF is a class that holds rectangle coordinates in floating point
    private var rectF = RectF(
        rectInset,
        rectInset,
        clipRectRight - rectInset,
        clipRectBottom - rectInset
    )
    // rectangle with rounded corners
    private fun drawRoundedRectangleClippingExample(canvas: Canvas){
        canvas.save()
        canvas.translate(columnTwo,rowThree)
        path.rewind()
        path.addRoundRect(
            rectF,clipRectRight / 4,
            clipRectRight / 4, Path.Direction.CCW
        )
        canvas.clipPath(path)
        drawClippedRectangle(canvas)
        canvas.restore()
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