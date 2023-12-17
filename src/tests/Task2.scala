package tests

import org.scalatest._
import pbd.PaleBlueDot
import pbd.PaleBlueDot.averagePopulation

class Task2 extends FunSuite {

  val countriesFile: String = "data/countries.txt"
  val citiesFilename: String = "data/cities.csv"

  val EPSILON: Double = 0.001
  def compareDoubles(d1: Double, d2: Double): Boolean = {
    Math.abs(d1 - d2) < EPSILON
  }

    test("test 1") {
      val actualResult: Double = averagePopulation(countriesFile, citiesFilename, "Andorra")
      val expectedResult: Double = 8409.5
      assert(compareDoubles(actualResult, expectedResult))
    }

    test("test 2") {
      val actualResult: Double = averagePopulation(countriesFile, citiesFilename, "United Arab Emirates")
      val expectedResult: Double = 761668.3333333334
      assert(compareDoubles(actualResult, expectedResult),actualResult + " " + expectedResult)

    }

    test("test 3") {
      val actualResult: Double = averagePopulation(countriesFile,citiesFilename, "aruba")
      val expectedResult: Double = 29998
      assert(compareDoubles(actualResult, expectedResult),actualResult + " " + expectedResult)
    }
}
