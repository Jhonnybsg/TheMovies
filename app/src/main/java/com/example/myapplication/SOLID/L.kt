package com.example.myapplication.SOLID

import com.example.myapplication.SOLID.L.Rectangle
import com.example.myapplication.SOLID.L.Shape
import com.example.myapplication.SOLID.L.Square

class L {
    open class Shape {
        open fun calculateArea(): Double {
            return 0.0
        }
    }

    class Rectangle(private val width: Double, private val height: Double) : Shape() {
        override fun calculateArea(): Double {
            return width * height
        }
    }

    class Square(private val side: Double) : Shape() {
        override fun calculateArea(): Double {
            return side * side
        }
    }
}

private fun printArea(shape: Shape) {
    println("Area: ${shape.calculateArea()}")
}

fun main() {
    val rectangle = Rectangle(10.0, 5.0)
    val square = Square(4.0)

    printArea(rectangle)  // Substitutable
    printArea(square)     // Substitutable
}