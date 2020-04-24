object Hamming {

    fun compute(leftStrand: String, rightStrand: String): Int {
        //TODO("Implement this function to complete the task")
        var count: Int =0;
        for (s in 1..(rightStrand.length)) {
            if(leftStrand.get(s) != rightStrand.get(s)){
                count++;
            }
        }
        return count;
    }
}
