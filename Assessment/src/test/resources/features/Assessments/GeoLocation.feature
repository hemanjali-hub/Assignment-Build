@GeoLocation
Feature: Fetch User GeoLocation

  @Location @assessment
  Scenario Outline: Verify user is able to fetch the valid location for given cordinates
    Given set the loation cordinates "<latitude>" "<longitude>" using dev tools
    When Navigate to the geoLocation website
    Then fetch the user cordinates and verify the given "<latitude>" "<longitude>" location cordinates

    Examples: 
      | latitude | longitude |
      |  40.7128 | -74.0060 |
