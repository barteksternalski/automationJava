_List of content_:

1. [Manual Tests](#manual-tests)
2. [Automation Introduction](#introduction)
3. [Dockerized setup](#docker-setup)
4. [Assertions](#assertions)
5. [How to run tests](#how-to-run-tests)
6. [Reporting](#reporting)

## Manual Tests

You can find sets of manual tests for two functionalities in `src/test/resources/testscenarios`

## Introduction

Framework contains both UI and API testing Below you can find main dependencies:

| Name   | Description   |
| ------------- |-------------|
| [JUnit 5](https://junit.org/junit5/) | Main test runner |
| [Selenide](https://selenide.org/) | This is to make our life with Selenium less painful |
| [Rest assured](https://rest-assured.io/) | For API testing |
| [Allure](http://allure.qatools.ru/) | For nice reporting test execution results |
| [AssertJ](https://joel-costigliola.github.io/assertj/) | Library for assertions |
| [Awaitility](https://github.com/awaitility/awaitility) | DSL for wait for some event to happened |

### Web drivers

You can use any web driver/browser which the Selenide library is giving out of the box. More
information: [here](https://github.com/selenide/selenide/wiki/How-Selenide-creates-WebDriver)

The Selenide is taking care of downloading needed binaries etc.

To specify browser you should use this property: **selenide.browser**, for example:

```bash
java -Dselenide.browser=chrome

```

For headless execution change `config.properties` file or just use this param: **selenide.headless**

```bash
java -Dselenide.headless=true
```

And if you want to connect to remote webriver:

```bash
java -Dselenide.remote=http://localhost:4445/wd/hub
```

## Docker setup

Install needed images

```bash
docker pull elgalu/selenium
docker pull dosel/zalenium
```

Run selenium grid

```bash
docker run --rm -ti --name zalenium -p 4444:4444 -p 5555:5555 -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos dosel/zalenium start
```

By default, 2 browser instances are created, you can change it with the parameter

```bash
--desiredContainers <count>
```

When setup properly, you can watch live view from test
run [Here](http://localhost:4444/grid/admin/live)

## Assertions

Library used for assertion is [AssertJ](https://joel-costigliola.github.io/assertj/). It's a Java
library that provides a fluent interface for writing assertions.

You can easily create custom assertion like it's
described [here](https://joel-costigliola.github.io/assertj/assertj-core-custom-assertions.html)

## How to run tests

To run tests update chrome to the latest version and execute one of the following commands

```bash
mvn clean test -Dsuite.name=uiTests
mvn clean test -Dsuite.name=apiTests
```

## Reporting

Library used for reporting test results execution: [allure](http://allure.qatools.ru/)
To generate report first install allure package and then run the following command to generate report:

```bash
allure generate target/allure-results --clean

```

You can open html report from `allure-report` folder.
