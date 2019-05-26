Silver Bars Marketplace
=======================
Coding recruitment task.


Building and testing
--------------------
Build tool: Gradle Wrapper 4.1 embedded in project

command line: `./gradlew clean test` run from project root level

IntelliJ: built-in test runner


Production code
---------------
Production code is written in Java supported by:
* clean code
* software craftsmanship
* TDD 


Tests
-----
Test code written in Groovy and Spock.
Spock was chosen due to Groovy's expressiveness, conciseness and neat interoperability with Java.


Design decisions:
-----------------
1. `Order` and `OrderSummary` classes are immutable
2. `equalsverifier` library is used to test correctness of: 
`equals()` and `hashCode()` methods, as well as the contract between these two method
 http://jqno.nl/equalsverifier/
3. `mutabilitydetector` library is used to test immutability of classes
https://github.com/MutabilityDetector/MutabilityDetector
4. `Order` and `OrderSummary` quantity should be given in same unit (e.g. kilograms)
5. `Order` and `OrderSummary` price should be given in same currency (e.g. sterling)
6. `LiveOrderBoard` is the entry point for to application and place where main logic happens
