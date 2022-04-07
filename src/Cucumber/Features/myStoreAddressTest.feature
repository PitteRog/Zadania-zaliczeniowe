Feature: adding an address in myStore
  Scenario Outline: user can add an address to their myStore account

    Given opened myStore webpage
    When user is signed in
    And add first address button is clicked or addresses button is clicked
    And address form is filled with <address> <city> <postalCode> <phone>
    And save button is clicked
    Then "Address successfully added!" is displayed
    And address is deleted
    And user sees "Address successfully deleted!"



    Examples:
    |address          |postalCode    |city           |phone      |
    |Maiden Lane      |000000        |New York       |333444555  |
    |Beach Drive      |111111        |Washington     |777999222  |
    |Union Street     |222222        |New Jersey     |444888000  |
    |Mulholland Drive |333333        |Los Angeles    |999222888  |
    |Marszałkowska 1  |444444        |Warsaw         |123456789  |
    |Schwedter Straße |555555        |Berlin         |555000111  |
