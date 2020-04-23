object Hamming {

    fun compute(leftStrand: String, rightStrand: String): Int {
        TODO("Implement this function to complete the task")
	   var index : Int = 0;
    	   var count : Int = 0;
    	while (index < rightStrand.length){
        if(leftStrand.get(index) != rightStrand.get(index))
        {
            count ++;
        }
        index++;
    }
    return count;		
    }
}
