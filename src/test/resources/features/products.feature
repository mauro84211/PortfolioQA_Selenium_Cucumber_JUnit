Feature: Product Details

  Scenario Outline: Verify that product details
    Given User logs in with valid credentials <username> and <password>
    Then User sees the products
    Examples:
      | username        | password       |
      | "standard_user" | "secret_sauce" |

  Scenario Outline: See product details
    Given User logs in with valid credentials <username> and <password>
    When User clicks on a <name> image
    Then User sees the product <name> and product <price>
    Examples:
      | username        | password       | name                 | price  |
      | "standard_user" | "secret_sauce" |"Sauce Labs Backpack" | "29.99" |
      | "standard_user" | "secret_sauce" |"Sauce Labs Bolt T-Shirt" | "15.99" |

  Scenario Outline: Add product to cart
    Given User logs in with valid credentials <username> and <password>
    When User add to cart a <product>
    Then Increase the number of products in the shopping cart
    Then The option to remove the <product> from the shopping cart is activated
    Examples:
      | username        | password      | product               |
      | "standard_user" | "secret_sauce"| "Sauce Labs Backpack" |
      | "standard_user" | "secret_sauce"| "Sauce Labs Bolt T-Shirt"|