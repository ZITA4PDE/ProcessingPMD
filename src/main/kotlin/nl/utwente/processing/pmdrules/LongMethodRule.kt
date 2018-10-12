package nl.utwente.processing.pmdrules

import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration
import net.sourceforge.pmd.lang.java.rule.codesize.AbstractNcssCountRule
import net.sourceforge.pmd.lang.rule.stat.StatisticalRule
import net.sourceforge.pmd.stat.DataPoint

/**
 * Class which implements the long method smell as PMD rule. Based on the NcssMethodCountRule in PMD.
 */
class LongMethodRule : AbstractNcssCountRule(ASTMethodDeclaration::class.java) {

    init {
        setProperty(StatisticalRule.MINIMUM_DESCRIPTOR, 50.0)
    }

    override fun visit(node: ASTMethodDeclaration, data: Any): Any {
        return super.visit(node, data)
    }

    override fun getViolationParameters(point: DataPoint?): Array<Any> {
        val node = point?.node as? ASTMethodDeclaration
        val score = point?.score?.toInt()
        return arrayOf(node?.methodName ?: "null", score.toString())
    }
}