@getRepos
Feature: get REPOS for a user from GITHUB

  Scenario Outline: get github REPOS
    When I triggered GET "getRepos" service endpoint with "valid" token
    Then I should get response code "200"
    And I should see below details in the response for get Repo service:
      | owner-login   | url   | clone_url   |
      | <owner_login> | <url> | <clone_url> |

    Examples:
      | owner_login    | url                                                    | clone_url                                              |
      | achi-ravikumar | https://api.github.com/repos/achi-ravikumar/mailTravel | https://github.com/achi-ravikumar/AmazonRegression.git |

  Scenario: Invalid Bearer Token
    When I triggered GET "getRepos" service endpoint with "invalid" token
    Then I should get response code "500"
    And I should see response error message "message" "Something went wrong in the application. Please check"

  Scenario: Empty Bearer Token
    When I triggered GET "getRepos" service endpoint with "empty" token
    Then I should get response code "500"
    And I should see response error message "message" "Something went wrong in the application. Please check"

  Scenario: Method not found error
    When I triggered POST "getRepos1" service endpoint with
      | name        | description               |
      | Repository8 | This is Public Repository |
    Then I should get response code "404"
    And I should see response error message "message" "Get Method Or post Method Not found"
