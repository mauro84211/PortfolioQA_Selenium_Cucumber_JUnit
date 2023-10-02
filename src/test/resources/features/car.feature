Feature: Shopping Cart

 Background:
   Given User is logged with valid credentials

  Scenario Outline: Product in Shopping Cart
    And User add to cart a <product>
    When User go to the Shopping Cart page
    Then User can see a list with the <product> and <price> selected
    Examples:
      | product                   | price   |
      | "Sauce Labs Backpack"     | "29.99" |
      | "Sauce Labs Bolt T-Shirt" | "15.99" |

  Scenario Outline: Shopping Checkout
    And User add to cart a <product>
    When User go to the Shopping Cart page
    And User go to Checkout
    When User fill the form
    Then User can see their facture with <product> and <price>
    Examples:
      | product                   | price   |
      | "Sauce Labs Backpack"     | "29.99" |
      | "Sauce Labs Bolt T-Shirt" | "15.99" |