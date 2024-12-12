
@tag
Feature: Error Validation

  @ErrorValidation  
  Scenario Outline: 
    Given I Landed On Ecommerce Page
    When I Logged In with UserName <userName> and Password <password>
    Then "Incorrect email or password." message is displayed
    Examples: 
      | userName  							| password 				| 
      | karthiame@gmail.com		 	| Cvmad135	  		|       