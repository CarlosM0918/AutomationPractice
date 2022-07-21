The project is ready to run from a jenkins pipeline.

# pre steps

Add your own credentials in `src/main/resources/conf/browserstack.conf.json`

Install the Slack plugin for jenkins and configure it

Configure the desired jdk in Jenkins

Configure in Jenkins the version of Maven under the name of mvn

![image](https://user-images.githubusercontent.com/107224306/178849235-56efb5c6-93a2-4d9c-97f6-9085171425ed.png)

#Grades:

Use `Jenkinsfile` for windows and `Jenkinsfile-linux` for linux or mac

In case of execution error disable `Lightweight checkout`


