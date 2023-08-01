@createRepo
Feature: Create Repo

  Scenario Outline: Create Repositories
    When I triggered POST "createRepo" service endpoint with
      | name   | description   |
      | <name> | <description> |
    Then I should get response code "201"
    And I should see below details in the response:
      | name   | full_name          | owner_url   | description   | clone_url   |
      | <name> | <full_name>+<name> | <owner_url> | <description> | <clone_url> |

    Examples:
      | name            | description               | full_name      | owner_url                                   | clone_url                                          |
      | RepositoryJuly | This is Public Repository | achi-ravikumar | https://api.github.com/users/achi-ravikumar | https://github.com/achi-ravikumar/Hello-World1.git |

  Scenario: Method not found error
    When I triggered POST "createRepo1" service endpoint with
      | name        | description               |
      | Repository8 | This is Public Repository |
    Then I should get response code "404"
    And I should see response error message "message" "Get Method Or post Method Not found"

  Scenario: Create a Repo without Name
    When I triggered POST "createRepo" service endpoint with
      | description               |
      | This is Public Repository |
    Then I should get response code "500"
    And I should see response error message "message" "Something went wrong in the application. Please check"
