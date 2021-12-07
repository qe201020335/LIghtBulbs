package org.codechallenge.lightbulbsimulation.utils

class Simulation {
    companion object {
        private var instance : Simulation? = null
        fun getInstance() : Simulation {
            if (instance == null) {
                instance = Simulation()
            }
            return instance as Simulation
        }
    }

    fun pullOnce(numToPull : Int) : Int{
        if (numToPull <= 0) {
            return 0
        }
        if (numToPull == 1) {
            return 1
        }
        // randomly select numbers from a list of 0-69
        val randomSelections = sequenceOf(0.. BulbCosntants.TOTLE_NUMBER_BULB).flatten().shuffled().take(numToPull)

        // println(randomSelections)

        val resultSet = mutableSetOf<Int>()

        for (number in randomSelections) {
            resultSet.add(number / BulbCosntants.NUMBER_OF_BULB_EACH_COLOR)
            // use integer division to decide which color
            // add to a set to deduplicate
        }

        print(resultSet)

        return resultSet.size
    }

}