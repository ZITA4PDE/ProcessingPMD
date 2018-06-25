package nl.utwente.processing.pmdrules

import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration
import net.sourceforge.pmd.lang.java.ast.ASTCompilationUnit
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration
import net.sourceforge.pmd.lang.java.ast.ASTPrimaryExpression
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule
import net.sourceforge.pmd.lang.java.symboltable.ClassScope
import net.sourceforge.pmd.lang.java.symboltable.MethodScope
import nl.utwente.processing.pmdrules.symbols.ProcessingApplet
import nl.utwente.processing.pmdrules.symbols.ProcessingAppletMethod
import nl.utwente.processing.pmdrules.utils.*

/**
 * Class which implements the pixel hardcode ignorance smell as PMD rule.
 */

class PixelHardcodeIgnoranceRule : AbstractJavaRule() {

    private var pushesMatrix = false

    override fun visit(node: ASTCompilationUnit?, data: Any?): Any? {
        this.pushesMatrix=false
        return super.visit(node, data)
    }


    override fun visit(node: ASTPrimaryExpression, data: Any): Any? {
        val method = node.getFirstParentOfType(ASTMethodDeclaration::class.java)

        if (node.isMethodCall) {
            val matchPushMatrix = node.matches(*ProcessingApplet.MATRIX_METHOD_SIGNATURES.toTypedArray())
            matchPushMatrix?.let {
                pushesMatrix=true
            }

            val match = node.matches(*ProcessingApplet.DRAW_METHODS.toTypedArray())
            match?.let {
                if (node.hasLiteralArguments(match)  &&!pushesMatrix ) {
                    this.addViolationWithMessage(data, node, message, kotlin.arrayOf(match, method.methodName))
                }

            }

        }
        return super.visit(node, data)
    }

}