package org.codechallenge.lightbulbsimulation.model

class SimulationResult(notEnoughSims: Boolean, sampleMean: Double, numSims: Int) {

    private val notEnoughSims = notEnoughSims
    private val sampleMean = sampleMean
    private val numSims = numSims


    override fun toString(): String {
        if (notEnoughSims) {
            return "Can't reach 99% confident level with $numSims simulations.\nThe simulation expected value is ${"%.2f".format(sampleMean)}"
        } else {
            return "The simulation expected value is ${"%.2f".format(sampleMean)} \n after $numSims simulations."
        }
    }
}