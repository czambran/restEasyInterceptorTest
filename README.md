# Empty Arquillian Maven project

By default it uses a managed container. Update the POM variable "container.type"  to remote 
if you would like arquillian to run against an already running JBoss EAP container.

See the arquillian.xml file for additional container configurations


## How to run it

The sample test can be run with the following command:

Managed - Letting Arquillian download a JBoss EAP 6.4 installation:
```
mvn clean verify
```

Managed - Using an existing JBoss EAP 6.4 installation:

First edit arquillian.xml and uncomment the jbossHome property. The run it with:
```
mvn -DJBOSS_HOME=/path/to/jboss-eap-6.4 clean verify
```

Remote:
Make sure to start the container before running the following command. 
Update the value of "container.type" in the pom file to remote. You can override the defaults
used to connect to the container by editing the values commented out in arquillian.xml:

```
mvn clean verify
``` 
