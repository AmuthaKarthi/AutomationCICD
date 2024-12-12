@tag
Feature: Purchase Order from ECommerce Site
  I want to use this template for my feature file

  Background:
  Given I Landed On Ecommerce Page
  
  
  @Regression 
  Scenario Outline: Possitive Test of Submitting Order
    Given I Logged In with UserName <userName> and Password <password>
    When I add the Product <productName> to cart
    And Checkout <productName> and Submit Order
    Then "THANKYOU FOR THE ORDER." message displayed in Confirmation Page

    Examples: 
      | userName  							| password 				| productName  		|
      | karthiame@gmail.com		 	| Cvmad1350	  		| ZARA COAT 3 		|
      