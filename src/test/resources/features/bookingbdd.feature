Feature: Hotel rating control


  Scenario Outline: User searches for hotel
    Given User is on main screen
    When User enters <hotel>
    And User clicks on the "Search" button
    Then The page shows <hotel> and its rating
    Examples: | hotel  |
              | "Hilton Hurghada Plaza Hotel" |

