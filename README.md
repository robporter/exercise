# Checkout Kata

To build the project and run all tests run Gradle from the root of the project directory using the supplied wrapper files. i.e.

```./gradlew build```

The main pricing logic tests can be found in:<br>

```PricingCalculatorServiceIntegrationTest```
and
```PricingRuleTest```

### TODO

- Wrote the PricingCalculatorService from integration test to demonstrate more of the application up and working, than
from unit tests. Would like to have a unit test for this class.
- Not implemented an API or UI on top of this, would if I had more time.

### Other things to note:

- The calculation attempts to handle multiple pricing rules as I wasn't sure if this was a requirement, and I didn't want 
to box myself into not being able to provide that.
- There's an interface for CalculatePricing, this would be to allow other pricing rule implementations
- Tried to demonstrate separation of logic and presentation, with domain, entities, and felt this was a distraction 
  from the calculation logic.
- Target JDK is 1.8 as the PDF asks for a demonstration of Java 8 streams.


### Notes for exercise as supplied
- Do NOT put everything in one class!!!
- Keep your solution very simple and easy to read. Do NOT over-engineer or over-complicate your solution
- Do NOT use frameworks such as Spring, just use core Java.
- Show extensive test coverage and a test-driven approach.
- Include a READme file which outlines any assumptions made and what you would have done with more time
- Ensure that test methods reflect only upon behaviour and be parameterised to handle various sorts of data. Avoid tight coupling between the tests method names and the test data.
- For the price rules, AVOID the temptation to hardcode within the strategy as it makes it hard to change or inject from within the test.
- Your solution must show good design (i.e. include packages, good modelling to prevent continual copying/duplication, relevant methods and good logic)
- Have an appreciation for separation of concerns
- Do not include redundant code (constructors, getters, setters, even method with logic!)
- Ensure the main function is well documented,
- Ensure you deal with error handling
- Demonstrate sufficient knowledge of the Java 8 Streaming API
- Ensure there is a mechanism to load skus
- Ensure there is a mechanism to prevent duplicate Skus with the same code being inserted
- Ensure the checkout requires the caller to know code, not the Sku ID
- Implement discounting rules and apply discount rules to the entire basket
- Separate the logic and presentation with DTOs, entities and views