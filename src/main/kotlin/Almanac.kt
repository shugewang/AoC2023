// Day 5 Solution
class Almanac(var input: MutableList<MutableList<String>>) {
    lateinit var seeds: List<Long>
    private lateinit var seedsMapping: MutableList<List<Long>>
    private lateinit var seedToSoilMapping: MutableList<Mapping>
    private lateinit var humidityToLocationMapping: MutableList<Mapping>
    private lateinit var temperatureToHumidityMapping: MutableList<Mapping>
    private lateinit var lightToTemperatureMapping: MutableList<Mapping>
    private lateinit var waterToLightMapping: MutableList<Mapping>
    private lateinit var fertiliserToWaterMapping: MutableList<Mapping>
    private lateinit var soilToFertiliserMapping: MutableList<Mapping>

    init {
        populateData()
        lookUp(seedToSoilMapping)
        lookUp(soilToFertiliserMapping)
        lookUp(fertiliserToWaterMapping)
        lookUp(waterToLightMapping)
        lookUp(lightToTemperatureMapping)
        lookUp(temperatureToHumidityMapping)
        lookUp(humidityToLocationMapping)
    }

    private fun populateData() {
        seeds = input[0][0].split(":")[1].split(" ").drop(1).map { it.toLong() }
        seedToSoilMapping = convertMapping(input[1])
        soilToFertiliserMapping = convertMapping(input[2])
        fertiliserToWaterMapping = convertMapping(input[3])
        waterToLightMapping = convertMapping(input[4])
        lightToTemperatureMapping = convertMapping(input[5])
        temperatureToHumidityMapping = convertMapping(input[6])
        humidityToLocationMapping = convertMapping(input[7])
//        var actualSeeds = mutableListOf<Long>()
//        for (i in seeds.indices step 2) {
//            for (value in 0..<seeds[i+1]) {
//                actualSeeds.add(seeds[i]+value)
//            }
//        }
        seedsMapping = mutableListOf(seeds)
    }

    private fun convertMapping(information: List<String>): MutableList<Mapping> {
        val sourceCategory = information[0].split("-")[0]
        val destinationCategory = information[0].split("-")[2].split(" ")[0]
        val rangeMappings = information.drop(1)
        var listOfMappings = mutableListOf<Mapping>()

        for (line in rangeMappings) {
            val listOfNumbers = line.split(" ").map {it.toLong()}
            listOfMappings.add(Mapping(sourceCategory, destinationCategory, listOfNumbers[0], listOfNumbers[1], listOfNumbers[2]))
        }
        return listOfMappings
    }

    fun lookUp(mapping: MutableList<Mapping>) {
        var mappedTo = mutableListOf<Long>()
        for (seed in seedsMapping.last()) {
            val itsMapping = mapping.filter { it.sourceRangeStart <= seed && seed < it.sourceRangeStart+it.rangeLength}
            if (itsMapping.isNotEmpty()) {
                mappedTo.add(itsMapping[0].destinationRangeStart-itsMapping[0].sourceRangeStart+seed)
            } else {
                mappedTo.add(seed)
            }
        }
        seedsMapping.add(mappedTo)
    }

    fun findLowestLocation(): Long {
        return seedsMapping.last().min()
    }

    override fun toString(): String {
        return "Almanac(input=$input, seeds=$seeds, seedToSoilMapping=$seedToSoilMapping, humidityToLocationMapping=$humidityToLocationMapping, temperatureToHumidityMapping=$temperatureToHumidityMapping, lightToTemperatureMapping=$lightToTemperatureMapping, waterToLightMapping=$waterToLightMapping, fertiliserToWaterMapping=$fertiliserToWaterMapping, soilToFertiliserMapping=$soilToFertiliserMapping)"
    }
}

class Seed(val seedNumber: Int) {
    var soilNumber = 0
    var fertiliserNumber = 0
    var waterNumber = 0
    var lightNumber = 0
    var temperatureNumber = 0
    var humidityNumber = 0
    var locationNumber = 0
}

class Mapping(val sourceCategory: String, val destinationCategory: String, var destinationRangeStart: Long, var sourceRangeStart: Long, var rangeLength: Long) {
    override fun toString(): String {
        return "Mapping(sourceCategory='$sourceCategory', destinationCategory='$destinationCategory', destinationRangeStart=$destinationRangeStart, sourceRangeStart=$sourceRangeStart, rangeLength=$rangeLength)"
    }
}