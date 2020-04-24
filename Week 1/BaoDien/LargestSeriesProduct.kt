import java.lang.IllegalArgumentException

class Series(val input: String){
    init {
        require(input.all{ '0' <= it && it <= '9'})
    }

    fun getLargestProduct(span: Int): Long {
        if ( input.length < span || span < 0 ) throw IllegalArgumentException()
        require(span >= 0 && span <= input.length)
        val max_value =
                (0..input.length - span).map {
                    input.slice(it until it + span).let {
                        if (it.isEmpty()) {
                            1
                        } else {
                            it.map{ (it - '0').toInt().toLong()}.reduce { acc, x -> acc * x }
                        }
                    }
                }.max()
        return max_value ?: 0
    }
}