package nl.utwente.processing.pmdrules.symbols

/**
 * Object which defines Processing Applet definitions.
 */
object ProcessingApplet {

    val PARAM_FLOAT_PIXEL = ProcessingAppletParameter("float", true)
    val PARAM_FLOAT_NON_PIXEL = ProcessingAppletParameter("float", false)
    val PARAM_INT_NON_PIXEL = ProcessingAppletParameter("int", false)
    val PARAM_PSHAPE = ProcessingAppletParameter("PShape", false)
    val PARAM_PIMAGE = ProcessingAppletParameter("PImage", false)
    val PARAM_STRING = ProcessingAppletParameter("String", false)
    val PARAM_CHAR = ProcessingAppletParameter("char", false)
    val PARAM_CHAR_ARRAY = ProcessingAppletParameter("char[]", false)

    val SETUP_METHOD_SIGNATURE = "setup()"

    val DRAW_METHOD_SIGNATURE = "draw()"
    val DRAW_METHODS = setOf(
            //Arc method: https://processing.org/reference/arc_.html
            ProcessingAppletMethod("arc", repeatedFloatParam(4,2)),
            ProcessingAppletMethod("arc", repeatedFloatParam(6, 2)),

            //Ellipse method: https://processing.org/reference/ellipse_.html
            ProcessingAppletMethod("ellipse", repeatedFloatParam(4, 2)),

            //Line method: https://processing.org/reference/line_.html
            ProcessingAppletMethod("line", repeatedFloatParam(4)),
            ProcessingAppletMethod("line", repeatedFloatParam(6)),

            //Point method: https://processing.org/reference/point_.html
            ProcessingAppletMethod("point", repeatedFloatParam(2)),
            ProcessingAppletMethod("point", repeatedFloatParam(3)),

            //Quad method: https://processing.org/reference/quad_.html
            ProcessingAppletMethod("quad", repeatedFloatParam(8)),

            //Rect method: https://processing.org/reference/rect_.html
            ProcessingAppletMethod("rect", repeatedFloatParam(4, 2)),
            ProcessingAppletMethod("rect", repeatedFloatParam(5, 2)),
            ProcessingAppletMethod("rect", repeatedFloatParam(8, 2)),

            //Triangle method: https://processing.org/reference/triangle_.html
            ProcessingAppletMethod("triangle", repeatedFloatParam(6, 6)),

            //Bezier method: https://processing.org/reference/bezier_.html
            ProcessingAppletMethod("bezier", repeatedFloatParam(8)),
            ProcessingAppletMethod("bezier", repeatedFloatParam(12)),

            //BezierPoint method: https://processing.org/reference/bezierPoint_.html
            ProcessingAppletMethod("bezierPoint", repeatedFloatParam(5, 4)),

            //BezierTangent method: https://processing.org/reference/bezierTangent_.html
            ProcessingAppletMethod("bezierTangent", repeatedFloatParam(5, 4)),

            //Curve method: https://processing.org/reference/curve_.html
            ProcessingAppletMethod("curve", repeatedFloatParam(8)),
            ProcessingAppletMethod("curve", repeatedFloatParam(12)),

            //CurvePoint method: https://processing.org/reference/curvePoint_.html
            ProcessingAppletMethod("curvePoint", repeatedFloatParam(5, 4)),

            //CurveTangent method: https://processing.org/reference/curveTangent_.html
            ProcessingAppletMethod("curveTangent", repeatedFloatParam(5, 4)),

            //Box method: https://processing.org/reference/box_.html
            ProcessingAppletMethod("box", repeatedFloatParam(1)),
            ProcessingAppletMethod("box", repeatedFloatParam(3)),

            //Sphere method: https://processing.org/reference/sphere_.html
            ProcessingAppletMethod("sphere", repeatedFloatParam(1)),

            //Shape method: https://processing.org/reference/shape_.html
            ProcessingAppletMethod("sphere", listOf(PARAM_PSHAPE)),
            ProcessingAppletMethod("sphere", listOf(PARAM_PSHAPE, *repeatedFloatParam(2).toTypedArray())),
            ProcessingAppletMethod("sphere", listOf(PARAM_PSHAPE, *repeatedFloatParam(4).toTypedArray())),

            //Image method: https://processing.org/reference/image_.html
            ProcessingAppletMethod("img", listOf(PARAM_PIMAGE, *repeatedFloatParam(2).toTypedArray())),
            ProcessingAppletMethod("img", listOf(PARAM_PIMAGE, *repeatedFloatParam(4).toTypedArray())),

            //Text method: https://processing.org/reference/text_.html
            ProcessingAppletMethod("text", listOf(PARAM_CHAR, *repeatedFloatParam(2).toTypedArray())),
            ProcessingAppletMethod("text", listOf(PARAM_CHAR, *repeatedFloatParam(3).toTypedArray())),
            ProcessingAppletMethod("text", listOf(PARAM_STRING, *repeatedFloatParam(2).toTypedArray())),
            ProcessingAppletMethod("text", listOf(PARAM_CHAR_ARRAY, PARAM_INT_NON_PIXEL, PARAM_INT_NON_PIXEL,
                    *repeatedFloatParam(2).toTypedArray())),
            ProcessingAppletMethod("text", listOf(PARAM_STRING, *repeatedFloatParam(3).toTypedArray())),
            ProcessingAppletMethod("text", listOf(PARAM_CHAR_ARRAY, PARAM_INT_NON_PIXEL, PARAM_INT_NON_PIXEL,
                    *repeatedFloatParam(3).toTypedArray())),
            ProcessingAppletMethod("text", listOf(PARAM_STRING, *repeatedFloatParam(4).toTypedArray())),
            ProcessingAppletMethod("text", listOf(PARAM_INT_NON_PIXEL, *repeatedFloatParam(2).toTypedArray())),
            ProcessingAppletMethod("text", listOf(PARAM_INT_NON_PIXEL, *repeatedFloatParam(3).toTypedArray())),
            ProcessingAppletMethod("text", listOf(PARAM_FLOAT_NON_PIXEL, *repeatedFloatParam(2).toTypedArray())),
            ProcessingAppletMethod("text", listOf(PARAM_FLOAT_NON_PIXEL, *repeatedFloatParam(3).toTypedArray()))
    )

    val MATRIX_METHOD_SIGNATURES = setOf(
            //MouseClicked handler: https://processing.org/reference/mouseClicked_.html
            "pushMatrix",
            "popMatrix"
    )

    val EVENT_METHOD_SIGNATURES = setOf(
            //MouseClicked handler: https://processing.org/reference/mouseClicked_.html
            "mouseClicked()",
            "mouseClicked(MouseEvent)",

            //MouseDragged handler: https://processing.org/reference/mouseDragged_.html
            "mouseDragged()",
            "mouseDragged(MouseEvent)",

            //MouseMoved handler: https://processing.org/reference/mouseMoved_.html
            "mouseMoved()",
            "mouseMoved(MouseEvent)",

            //MousePressed handler: https://processing.org/reference/mousePressed_.html
            "mousePressed()",
            "mousePressed(MouseEvent)",

            //MouseReleased handler: https://processing.org/reference/mouseReleased_.html
            "mouseReleased()",
            "mouseReleased(MouseEvent)",

            //MouseWheel handler: https://processing.org/reference/mouseWheel_.html
            "mouseWheel()",
            "mouseWheel(MouseEvent)",

            //KeyPressed handler: https://processing.org/reference/keyPressed_.html
            "keyPressed()",
            "keyPressed(KeyEvent)",

            //KeyReleased handler: https://processing.org/reference/keyReleased_.html
            "keyReleased()",
            "keyReleased(KeyEvent)",

            //KeyTyped handler: https://processing.org/reference/keyTyped_.html
            "keyTyped()",
            "keyTyped(KeyEvent)"
    )
    val EVENT_GLOBALS = setOf(
            "mouseButton", //https://processing.org/reference/mouseButton.html
            "mousePressed", //https://processing.org/reference/mousePressed.html
            "mouseX", //https://processing.org/reference/mouseX.html
            "mouseY", //https://processing.org/reference/mouseY.html
            "pmouseX", //https://processing.org/reference/pmouseX.html
            "pmouseY", //https://processing.org/reference/pmouseY.html

            "key", //https://processing.org/reference/key.html
            "keyCode", //https://processing.org/reference/keyCode.html
            "keyPressed" //https://processing.org/reference/keyPressed.html
    )

    private fun repeatedFloatParam(amount: Int, amountPixels: Int) : List<ProcessingAppletParameter> {
        val result = ArrayList<ProcessingAppletParameter>(amount)
        kotlin.repeat(amount, { i -> result.add(if (i < amountPixels) PARAM_FLOAT_PIXEL else PARAM_FLOAT_NON_PIXEL) })
        return result
    }

    private fun repeatedFloatParam(amount: Int) : List<ProcessingAppletParameter> {
        return this.repeatedFloatParam(amount, amount)
    }

}