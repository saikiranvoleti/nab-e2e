@core_product
Feature: Core Product
 @search
  Scenario: Search and Store product information in a file
    Given User is on the Home page
    And Navigate to menu "shop" and submenu "men's"
    And User is on Search Page
    And Select filter "jackets" on Search Page
    And Store Products information in a file "product_info"

  @news
    Scenario: news & features
      Given User is on the Home page
      And Navigate to menu "..." and submenu "News & Features"
      And user is on the News and Features Page
      Then Verify the video feed count is 22
      Then Verify the video feed count greater than 3 days








