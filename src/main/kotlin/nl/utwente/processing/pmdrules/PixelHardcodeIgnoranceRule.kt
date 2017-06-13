package nl.utwente.processing.pmdrules

import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration
import net.sourceforge.pmd.lang.java.ast.ASTPrimaryExpression
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule
import nl.utwente.processing.pmdrules.symbols.ProcessingApplet
import nl.utwente.processing.pmdrules.utils.hasLiteralArguments
import nl.utwente.processing.pmdrules.utils.isMethodCall
import nl.utwente.processing.pmdrules.utils.matches

/**
 * Class which implements the pixel hardcode ignorance smell as PMD rule.
 */
class PixelHardcodeIgnoranceRule : AbstractJavaRule() {

    override fun visit(node: ASTPrimaryExpression, data: Any): Any? {
        val method = node.getFirstParentOfType(ASTMethodDeclaration::class.java);
        if (node.isMethodCall) {
            val match = node.matches(*ProcessingApplet.DRAW_METHODS.toTypedArray())
            match?.let {
                if (node.hasLiteralArguments(match)) {
                    this.addViolationWithMessage(data, node, message, kotlin.arrayOf(match, method.methodName))
                }
            }
        }
        return super.visit(node, data)
    }

}