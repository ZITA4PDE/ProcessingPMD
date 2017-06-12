package nl.utwente.processing.pmdrules

import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule
import net.sourceforge.pmd.lang.java.symboltable.ClassScope
import net.sourceforge.pmd.lang.java.symboltable.JavaNameOccurrence
import nl.utwente.processing.pmdrules.symbols.ProcessingApplet
import nl.utwente.processing.pmdrules.utils.callStack
import nl.utwente.processing.pmdrules.utils.findMethod

/**
 * Class which implements the drawing state change smell as PMD rule.
 */
class DrawingStateChangeRule: AbstractJavaRule() {

    override fun visit(node: ASTClassOrInterfaceDeclaration, data: Any): Any? {
        //Check if this is a top node, not a inner class.
        if (!node.isNested) {
            val scope = node.scope as? ClassScope
            val methodDecl = scope?.findMethod(ProcessingApplet.DRAW_METHOD_SIGNATURE) ?: return super.visit(node, data)
            val drawStack = scope.callStack(methodDecl)
            for ((variable, occurrences) in scope.variableDeclarations) {
                for (occurrence in occurrences) {
                    val method = occurrence.location.getFirstParentOfType(ASTMethodDeclaration::class.java)
                    if (occurrence is JavaNameOccurrence &&
                            (occurrence.isOnLeftHandSide || occurrence.isSelfAssignment) && method in drawStack) {
                        this.addViolationWithMessage(data, occurrence.location, message,
                                kotlin.arrayOf(variable.name, method.methodName))
                    }
                }
            }
        }
        return super.visit(node, data)
    }

}