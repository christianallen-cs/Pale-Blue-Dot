package pbd

import java.awt.Desktop
import java.net.URI
import scala.io.{BufferedSource, Source}

object PaleBlueDot {


  /**
   * Task 1
   *
   * Given a country name using a mix of case (upper/lower), return the country code in all lowercase letters
   *
   * Ex. If "Heard Island and McDonald Islands#HM" is a line countriesFilename and the countryName input
   * of your method is "hEaRd IsLaNd AnD mCdOnAlD iSlAnDs" the returned value is "hm"
   *
   * If countryName is not in the file, return the empty String: ""
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param countryName       The name of the country to lookup in the file with any mix of upper/lower-case
   * @return The two letter country code for countryName in lowercase letters
   */

  def getCountryCode(countriesFilename: String, countryName: String): String = {
    val countriesFile: BufferedSource = Source.fromFile(countriesFilename)
    var contents: String = ""
    for (line <- countriesFile.getLines()) {
      val split: Array[String] = line.split("#")
      val firstElement: String = split(0)
      val secondElement: String = split(1)
      if (countryName.toLowerCase() == firstElement.toLowerCase()) {
        return (secondElement.toLowerCase())
      }
    }
    ""
  }


  /**
   * Task 2
   *
   * Find the average population of cities in a country
   * regardless.
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param citiesFilename    Name of the file containing city name, population, and location data
   * @param countryName       The name of the country with any mix of upper/lower-case
   * @return The average population of cities in the given country
   */
  def averagePopulation(countriesFilename: String, citiesFilename: String, countryName: String): Double = {
    val countriesFile: BufferedSource = Source.fromFile(countriesFilename)
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    var totalCities: Double = 0
    var total: Double = 0
    for (line <- citiesFile.getLines().drop(1)) {
      val split: Array[String] = line.split(",")
      val population: Double = split(3).toInt
      if (getCountryCode(countriesFilename, countryName) == split(0)) {
        total += population
        totalCities += 1
      }
    }
    total / totalCities
    }


  /**
   * Task 3
   */

  /**
   * Returns a Map[cityName -> population] for all cities in the given county. The name of each
   * city should match exactly how it appears in citiesFilename and the population is read from the file
   * and converted to an Int. The country name may contain any mix of upper/lower-case letters.
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param citiesFilename    Name of the file containing city name, population, and location data
   * @param countryName       The name of the country with any mix of upper/lower-case
   * @return A Map containing the name and population of every city in the given country
   */
  def cityPopulations(countriesFilename: String, citiesFilename: String, countryName: String): Map[String, Int] = {
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    var value: Map[String, Int] = Map()
    for (line <- citiesFile.getLines()) {
      val splits: Array[String] = line.split(",")
      val cityName: String = splits(1)
      val pop: String = splits(3)
        if (getCountryCode(countriesFilename, countryName) == splits(0)) {
          value += (cityName -> pop.toInt)
        }
      }
      value
    }


  /**
   * Returns a List of city names in the given county and with above average population for that country
   *
   * @param countriesFilename Name of the file containing country names and codes
   * @param citiesFilename    Name of the file containing city name, population, and location data
   * @param countryName       The name of the country with any mix of upper/lower-case
   * @return All city names in given country with a population > the average populations of cities in that country
   */
  def aboveAverageCities(countriesFilename: String, citiesFilename: String, countryName: String): List[String] = {
    List()
  }


  /**
   * Application Objective
   *
   * You find yourself stranded in an unfamiliar place with no signs of civilization. You don't have much with you,
   * but you do have a locator that gives your current latitude/longitude, a csv file of cities, and your final
   * submission to the PaleBlueDot assignment from CSE116 (What luck!). You decide that finding and walking
   * directly to the closest city will give you the best chance to survive.
   *
   * Return the closest city to the given location in terms of greater circle distance which is the shortest distance
   * needed to walk along the surface of the Earth to reach a city.
   *
   * @param citiesFilename Name of the file containing city name, population, and location data
   * @param location       A location on Earth given as a List containing latitude and longitude coordinates
   * @return The city closest to the given location as a List containing country code, city name, and region
   *         exactly as they appear in the cities file (ie. the List should have exactly 3 values to return
   *         a single city
   */
  def closestCity(citiesFilename: String, location: List[Double]): List[String] = {
    val citiesFile: BufferedSource = Source.fromFile(citiesFilename)
    var value: List[String] = List[String]()
    var minDistance: Double = Double.MaxValue
    for (line <- citiesFile.getLines().drop(1)) {
      val split: Array[String] = line.split(",")
      val cityLat: Double = split(4).toDouble
      val cityLon: Double = split(5).toDouble
      val currentCityLocation: List[Double] = List(cityLat, cityLon)
      val currentCityDistance: Double = greaterCircleDistance(currentCityLocation, location)
      if (currentCityDistance <= minDistance) {
        minDistance = currentCityDistance
        value = split.toList
      }
    }
    value.take(3)
  }

  def degreesToRadians(degrees: Double): Double = {
    val a: Double = math.Pi
    val b: Double = 180
    return (degrees * a) / b
  }

  def greaterCircleDistance(location1: List[Double], location2: List[Double]): Double = {
    val earthRadius: Double = 6371.0 // km
    val lat1: Double = degreesToRadians(location1.head)
    val lon1: Double = degreesToRadians(location1(1));
    val lat2: Double = degreesToRadians(location2.head)
    val lon2: Double = degreesToRadians(location2(1));
    val totLat: Double = (lat2 - lat1)
    val totLon: Double = (lon2 - lon1)
    val a: Double = (Math.sin(totLat / 2) * Math.sin(totLat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(totLon / 2) * Math.sin(totLon / 2))
    val c: Double = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
    val d: Double = earthRadius * c
    return d
  }

  /**
   * Helper Method
   *
   * Opens Google Maps at a specific location. The location is a List containing the latitude then longitude as Doubles
   *
   * @param location The location to open in the format List(Latitude, Longitude)
   */
  def openMap(location: List[Double]): Unit = {
    if (Desktop.isDesktopSupported && Desktop.getDesktop.isSupported(Desktop.Action.BROWSE)) {
      val url: String = "http://maps.google.com/maps?t=m&q=loc:" + location.head.toString + "+" + location(1).toString
      Desktop.getDesktop.browse(new URI(url))
    } else {
      println("Opening the browser not supported")
    }
  }


  def main(args: Array[String]): Unit = {
    openMap(List(43.002743, -78.7874136))
  }

}
