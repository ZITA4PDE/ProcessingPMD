package nl.utwente.processing.pmdrules.utils

import net.sourceforge.pmd.lang.java.ast.*
import nl.utwente.processing.pmdrules.symbols.ProcessingAppletMethod
import java.util.*

val ASTPrimaryExpression.isMethodCall : Boolean
    get() {
        return this.findChildrenOfType(ASTPrimarySuffix::class.java).stream().filter { s -> s.isArguments }.count() > 0
    }

fun ASTPrimaryExpression.hasLiteralArguments(method: ProcessingAppletMethod) : Boolean {
    val argumentNode = this.findChildrenOfType(ASTPrimarySuffix::class.java).stream()
            .filter { s -> s.isArguments }.findFirst().orElse(null) ?: return false
    val argumentList = argumentNode.getFirstDescendantOfType(ASTArgumentList::class.java)
    return (0..argumentList.jjtGetNumChildren()-1)
            .filter { method.parameters[it].pixels }
            .map {
                argumentList.jjtGetChild(it)?.
                        getFirstChildOfType(ASTPrimaryExpression::class.java)?.
                        getFirstChildOfType(ASTPrimaryPrefix::class.java)?.
                        getFirstChildOfType(ASTLiteral::class.java)
            }
            .any { it != null };
}

fun ASTPrimaryExpression.matches(method: ProcessingAppletMethod) : Boolean {
    var result = false;
    check@ for (i in 0..this.jjtGetNumChildren()-1) {
        val node = this.jjtGetChild(i)
        when (node) {
            is ASTPrimaryPrefix -> {
                if (node.usesThisModifier() || node.usesSuperModifier()) {
                    if (!this.scope.isPartOfTopClassScope) {
                        break@check
                    }
                } else if(node.getFirstChildOfType(ASTName::class.java)?.image != method.name) {
                    break@check
                } else if(node.getFirstChildOfType(ASTName::class.java)?.nameDeclaration != null) {
                    break@check
                }
            }
            is ASTPrimarySuffix -> {
                result = node.isArguments && node.argumentCount == method.parameters.size;
                break@check
            }
        }
    }
    return result
}

fun ASTPrimaryExpression.matches(variable: String) : Boolean {
    var result = false
    check@ for (i in 0..this.jjtGetNumChildren()-1) {
        val node = this.jjtGetChild(i)
        when (node) {
            is ASTPrimaryPrefix -> {
                if (node.usesThisModifier() || node.usesSuperModifier()) {
                    if (!this.scope.isPartOfTopClassScope) {
                        break@check
                    }
                } else if(node.getFirstChildOfType(ASTName::class.java) == null) {
                    break@check
                } else if(node.getFirstChildOfType(ASTName::class.java).image != variable) {
                    break@check
                } else if(node.getFirstChildOfType(ASTName::class.java).nameDeclaration != null) {
                    break@check
                } else {
                    result = true
                }
            }
        }
    }
    return result
}

fun ASTPrimaryExpression.matches(vararg methods: ProcessingAppletMethod) : ProcessingAppletMethod? {
    return Arrays.stream(methods).filter { m -> this.matches(m) }.findFirst().orElse(null)
}

fun ASTPrimaryExpression.matches(vararg variables: String) : String? {
    return Arrays.stream(variables).filter { m -> this.matches(m) }.findFirst().orElse(null)
}