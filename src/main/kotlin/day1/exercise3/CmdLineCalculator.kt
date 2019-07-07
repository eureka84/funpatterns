package day1.exercise3

import day1.example3.Outcome
import java.lang.UnsupportedOperationException

class CmdLineCalculator(
    val cout: (String) -> Console<Unit>,
    val cin: () -> Console<String>
) {
    fun enter() {
        cin().exec().let { input ->
            input.split(" ")
                .let { (operator, operand1, operand2) ->
                    Outcome.tryThis{ execOperation(operator, operand1, operand2) }
                }.fold(
                    { cout("Error: cannot process $input").exec()},
                    { v -> cout(v.toString()).exec()}
                )
        }
    }

    private fun execOperation(operator: String, operand1: String, operand2: String): Int {
        return when (operator) {
            "+" -> operand1.toInt() + operand2.toInt()
            "*" -> operand1.toInt() * operand2.toInt()
            else -> throw UnsupportedOperationException(operator)
        }
    }

}

fun main() {
    val calculator = CmdLineCalculator(::printIO, ::readlineIO)

    calculator.enter()
}