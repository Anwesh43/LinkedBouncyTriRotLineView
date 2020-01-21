package com.anwesh.uiprojects.bouncytrirotlineview

/**
 * Created by anweshmishra on 22/01/20.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color

val nodes : Int = 5
val lines : Int = 2
val strokeFactor : Float = 90f
val deg : Float = 60f
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#3F51B5")
val backColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 20

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawTriRotLine(i : Int, scale : Float, size : Float, paint : Paint) {
    val sf : Float = scale.sinify().divideScale(i, lines)
    val sj : Float = 1f - 2 * i
    save()
    translate(-size, 0f)
    rotate(-deg * sf * sj)
    drawLine(0f, 0f, 2 * size * sj, 0f, paint)
    restore()
}

fun Canvas.drawTriRotLines(scale : Float, size : Float, paint : Paint) {
    for (j in 0..(lines - 1)) {
        drawTriRotLine(j, scale, size, paint)
    }
}

fun Canvas.drawBTRLNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = w / (nodes + 1)
    val size : Float = gap / sizeFactor
    paint.color = foreColor
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    save()
    translate(gap * (i + 1), h / 2)
    drawTriRotLines(scale, size, paint)
    restore()
}

