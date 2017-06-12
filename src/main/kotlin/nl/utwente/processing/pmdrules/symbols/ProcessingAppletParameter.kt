package nl.utwente.processing.pmdrules.symbols

/**
 * Simple data class which defines an processing applet method parameter.
 */
data class ProcessingAppletParameter(val type: String, val pixels: Boolean) {

    override fun toString(): String {
        val sb = StringBuilder(type)
        if (this.pixels) {
            sb.append('*')
        }
        return sb.toString()
    }
}