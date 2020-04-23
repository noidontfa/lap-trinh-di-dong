object SumOfMultiples {
    var index: Int = 0;
    var s: Int = 0
    fun sum(factors: Set<Int>, limit: Int): Int {
        //TODO("Implement this function to complete the task")
        while (index < limit) {
            if (factors.elementAt(index) % 3 == 0 || factors.elementAt(index) % 5 == 0) {
                if (factors.elementAt(index) != 3 && factors.elementAt(index) != 5) {
                    s += factors.elementAt(index);

                }
            }
            index++;
        }
        return s;
    }
}

