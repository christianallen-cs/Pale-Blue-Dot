package tests

import org.scalatest._
import pbd.PaleBlueDot.closestCity

class ApplicationObjective extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

  test("test 1") {
    val actualResult = closestCity(citiesFilename, List(42.50, 72.53))
    val expectedResult: List[String] = List("kg", "talas", "06")
    assert(actualResult == expectedResult)
  }
  test("test 2") {
    val actualResult = closestCity(citiesFilename, List(42.00, 71.50))
    val expectedResult: List[String] = List("uz", "kosonsoy", "06")
    assert(actualResult == expectedResult)
  }
  test("test 3") {
    val actualResult = closestCity(citiesFilename, List(90.00, 135.00))
    val expectedResult: List[String] = List("sj", "ny-alesund", "00")
    assert(actualResult == expectedResult)
  }
  test("test 4") {
    val actualResult = closestCity(citiesFilename, List(90.00, 45.00))
    val expectedResult: List[String] = List("sj", "ny-alesund", "00")
    assert(actualResult == expectedResult)
  }
  test("test 5") {
    val actualResult = closestCity(citiesFilename, List(10.00, 120.00))
    val expectedResult: List[String] = List("ph", "osmena", "49")
    assert(actualResult == expectedResult)
  }

}
