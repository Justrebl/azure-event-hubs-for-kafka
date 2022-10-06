**Application Insights not tracing Kafka Dependencies in a Java JDK 11 local App**

### Expected behavior
Having a Java Kafka Consumer App instrumented with the application-insights agent running locally, i am expecting to see the  tracked dependencies (application insights logs and application map) while the Java consumer retrieves events from a Kafka enabled Event Hub.

### Actual behavior
Currently, all i can see being traced with the java app insight agent are the requests logs from my java client app to the Kafka (event-hub) source, but not the dependancy logs (thus application map is not showing the link between my java client/consumer app 

### To Reproduce
Steps to reproduce the behavior:
- Clone the following Microsoft Kafka consumer example here : https://github.com/Azure/azure-event-hubs-for-kafka/tree/master/quickstart/java and configure the resources/consumer.config as detailed in the consumer/readme.md
- Instrument the Java consumer app with the application-insight java agent 3.4.1
- Run the Java application to consume the Event Hub/Kafka endpoint
- Check the application insights logs (table : requests & dependencies)
- Run the following queries in Log Analytics :  
  - requests | where cloud_RoleName contains "consumer" => 1 Line per event consumed showing in the KQL query results 
  - dependencies | where cloud_RoleName contains "consumer" => 0 line result

#### Sample Application
This Microsoft provided Java Kafka Consumer reproduces the behavior : https://github.com/Azure/azure-event-hubs-for-kafka/tree/master/quickstart/java 

##### .mvn/jvm.config :  
-javaagent:./applicationinsights-agent-3.4.1.jar

##### applicationinsights.js :  
{
    "connectionString": "InstrumentationKey=0000;IngestionEndpoint=https://00000.in.applicationinsights.azure.com/;LiveEndpoint=https://0000.livediagnostics.monitor.azure.com/",
    "role":{
        "name":"Java Kafka Consumer",
        "instance":"OnPremise"
    },
    "instrumentation": {
        "logging": {
          "level": "TRACE"
        }
      },
      "sampling":{
        "percentage":100
      },
      "preview":{
        "captureControllerSpans": true
      }
    }

### System information
Please provide the following information:
 - SDK Version: 
     - jdk-11.0.16
     - App insight java agent 3.4.1
 - OS type and version: Windows 11 (same behavior on Linux WSL2 - Ubuntu 20.04 LTS)
 - Application Server type and version (if applicable):
 - Using spring-boot? No
 - Additional relevant libraries (with version, if applicable):

### Logs
[Turn on SDK logs](https://docs.microsoft.com/en-us/azure/application-insights/app-insights-java-troubleshoot#debug-data-from-the-sdk) and attach/paste them to the issue. If using an application server, also attach any relevant server logs.

_Be sure to remove any private information from the logs before posting!_

### Screenshots
If applicable, add screenshots to help explain your problem.
