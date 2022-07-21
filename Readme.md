# Automationpractice.com
The project is ready to run from a jenkins pipeline.

TestNG Integration with BrowserStack and Jenkins.

## Using Maven

### Setup

* Clone the repo
* Install dependencies `mvn compile`
* Update `*.conf.json` files inside the `src/main/resources/conf` directory with your credentials of Browserstack 

### Running your tests

- To run a single test, run `mvn test`
- To run Smoke tests, run `mvn test -P SmokeSuite`
- To run Bug tests, run `mvn test -P BugSuite`
- To run the Browserstack test, run `mvn test -P Browserstack`

## Running on Jenkins

### Setup
* Install the Slack plugin for jenkins and configure it with your credentials and channel.
* Configure the desired jdk in Jenkins.
* Configure in Jenkins the version of Maven under the name of mvn.

![178849235-56efb5c6-93a2-4d9c-97f6-9085171425ed](https://user-images.githubusercontent.com/107224306/180321498-2348f7ee-9b48-4e79-9b90-8d90011ed395.png)

### Job configuration

* Add job pipeline
* On pipeline select Definition -> Pipeline script SCM
* Add the repository 
* Use `Jenkinsfile` for windows and `Jenkinsfile-linux` for linux or mac

#### Notes:
<p stile="font-size: 8"> In case of execution error disable `Lightweight checkout` </p>


