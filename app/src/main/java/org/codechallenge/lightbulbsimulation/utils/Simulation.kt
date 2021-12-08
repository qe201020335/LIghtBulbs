package org.codechallenge.lightbulbsimulation.utils

import org.codechallenge.lightbulbsimulation.model.SimulationResult
import kotlin.math.pow
import kotlin.math.sqrt

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
        val randomSelections = sequenceOf(0 until BulbCosntants.TOTLE_NUMBER_BULB).flatten().shuffled().take(numToPull)

        // println(randomSelections)

        val resultSet = mutableSetOf<Int>()

        for (number in randomSelections) {
            resultSet.add(number / BulbCosntants.NUMBER_OF_BULB_EACH_COLOR)
            // use integer division to decide which color
            // add to a set to deduplicate
        }

        // print(resultSet)

        return resultSet.size
    }

    fun calculateTheoryMean(numToPull: Int) : Double {

        if (numToPull <= 0) {
            return 0.0
        }
        if (numToPull == 1) {
            // You can only get 1 color if you pulled only 1 bulb
            return 1.0
        }
        if (numToPull == BulbCosntants.TOTLE_NUMBER_BULB) {
            // You get all the colors if you pulled all bulbs out
            return BulbCosntants.NUMBER_OF_COLORS.toDouble()
        }
        // formula from http://www.adellera.it/static_html/investigations/distinct_balls/distinct_balls.pdf
        // Section 2.2
        var product = 1.0
        for (i in 0 until BulbCosntants.NUMBER_OF_BULB_EACH_COLOR ) {
            product *= 1 - (numToPull.toDouble() / (BulbCosntants.TOTLE_NUMBER_BULB - i).toDouble())
        }

        return BulbCosntants.NUMBER_OF_COLORS * (1 - product)
    }

    fun runSimulations(numToPull: Int, theoryMean: Double, maxSims: Int) : SimulationResult {

        val z99 = 0.1257 // z-score for 1% confidence interval i.e 99% confident level

        var sampleMean = 0.0
        for (i in 1 .. maxSims) {
            val sample = List(i) {
                pullOnce(numToPull)
            }
            sampleMean = sample.sum().toDouble() / sample.size
//            println("Sample Mean: $sampleMean")

            var sampleStdDeviation = 0.0
            for (eachSim in sample) {
                sampleStdDeviation += (eachSim - sampleMean).pow(2.0)
            }
            sampleStdDeviation = sqrt(sampleStdDeviation / sample.size )
//            println("Sample Standard Deviation: $sampleStdDeviation")

            val sampleStdErr = sampleStdDeviation / sqrt(sample.size.toDouble())
//            println("Sample Standard Error: $sampleStdErr")

            // then we have a confidence interval
            val intervalUp = sampleMean + z99 * sampleStdErr
            val intervalDown = sampleMean - z99 * sampleStdErr
//            println("Interval $intervalDown $intervalUp")

            if (theoryMean in intervalDown..intervalUp) {
                // We have finished!
                return SimulationResult(false, sampleMean, i)
            }
        }
        // we didn't finish in maxSims times of simulation
        return SimulationResult(true, sampleMean, maxSims)
    }

}