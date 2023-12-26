@OrangeHRM
Feature: Automate the login process for the OrangeHRM open-source demo site

  @Login @assessment
  Scenario Outline: Login to orangeHRM demo site and validate valid and invalid credentials
    Given Navigate to the OrangeHrm website
    When user enters "<username>" and "<password>"
    Then verify user is at hrm main page when valid credentails are provided else dispaly the error message "<ErrorMessage>"

    Examples: 
      | username | password |ErrorMessage|
      | Admin    | admin123 |Invalid credentials|
      | Admin    | Admin123 |Invalid credentials|
