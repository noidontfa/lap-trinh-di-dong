
object SumOfMultiples {

    fun sum(factors: Set<Int>, limit: Int): Int {
        val multiples = mutableSetOf<Int>()

        factors.filter { i -> i > 0 }
                .forEach { num: Int ->
                    for (i in 1..(limit / num)) {
                        if (i * num < limit) {
                            multiples.add(i * num)
                        }
                    }
                }

        return multiples.sum()
    }
}
