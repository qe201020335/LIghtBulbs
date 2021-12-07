package org.codechallenge.lightbulbsimulation.model

class SimulationResult(notEnoughSims: Boolean, sampleMean: Double, numSims: Int) {

    private val notEnoughSims = notEnoughSims
    private val sampleMean = sampleMean
    private val numSims = numSims


    override fun toString(): String {
        return "$notEnoughSims, $sampleMean, $numSims"
    }
}