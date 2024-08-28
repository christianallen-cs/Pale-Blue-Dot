# City Location and Population Data Analysis

## Introduction

This project involves using city location and population data to answer various questions about cities around the world.

## Data

We will work with two data files containing information about countries and cities:

### `countries.txt`

- **Format**: `Country Name#Country Code`
- **Sample Line**: `United States of America#US`
- **Description**: This file provides a 2-character code for each country. This file will be used to look up the country code for a given country name.
- **Reference**: [ISO 3166-1 alpha-2 codes](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2)

### `cities.csv`

- **Header Line**: `Country,City,Region,Population,Latitude,Longitude`
- **Sample Data**: `us,buffalo,NY,279557,42.8863889,-78.8786111`
- **Description**: This file contains population and location data for many cities around the world. The country is represented by a 2-digit code in lowercase letters. Population is an integer, and latitude and longitude are represented as doubles.

## Programming Tasks

### Task 1

- **Testing**: A test suite named `Task1` is provided.
- **Functionality**: Implement the `getCountryCode` method in the `PaleBlueDot` object. This method takes two strings (filename and country name) and returns the country code. The country name may have any case, and the code should be returned in lowercase.

### Task 2

- **Testing**: Complete the test suite named `Task2`.
- **Functionality**: Implement the `averagePopulation` method. This method takes three strings (countries filename, cities filename, and country name) and returns the average population of cities in the specified country.

### Task 3

- **Testing**: Complete the test suite named `Task3`.
- **Functionality**:
  - `cityPopulations`: Returns a map of city names to populations for a given country.
  - `aboveAverageCities`: Returns a list of city names with populations greater than the average in the specified country.
