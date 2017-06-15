package nl.utwente.processing.pmdrules

import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule
import net.sourceforge.pmd.lang.java.symboltable.ClassScope

/**
 * Class which implements the stateless class smell as PMD rule.
 */
class StatelessClassRule : AbstractJavaRule() {

    override fun visit(node: ASTClassOrInterfaceDeclaration, data: Any): Any? {
        //Check if this is a top node, not a inner class.
        if (node.isNested && !node.isInterface && !node.isAbstract) {
            val scope = node.scope as? ClassScope
            val vars = scope?.variableDeclarations?.size ?: 0
            if (vars == 0) {
                this.addViolationWithMessage(data, node, message, kotlin.arrayOf(scope?.className))
            }
        }
        return super.visit(node, data)
    }

}