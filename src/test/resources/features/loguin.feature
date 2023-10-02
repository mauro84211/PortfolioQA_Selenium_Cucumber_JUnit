Feature: Login Functionality
  In order to do internet banking
  As a valid Para Bank customer
  I want to login successfully

  Scenario Outline: Login Successful
    Given User is in LogIn Page
    And  User enters <username> and <password>
    When User clicks Login
    Then User should be logged in successfully
    Examples:
      | username        | password       |
      | "standard_user" | "secret_sauce" |

  Scenario Outline: Login locked user
    Given User is in LogIn Page
    And  User enters <username> and <password>
    When User clicks Login
    Then The User Locked Message Show Up
    Examples:
      | username          | password       |
      | "locked_out_user" | "secret_sauce" |