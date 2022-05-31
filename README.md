# Distributed System Demos

## [Java RMI](JavaRMI/src/main/java)

The [Server](JavaRMI/src/main/java/server_module/Server.java) provides a __skeleton__ with an
implemented [RMI Interface](JavaRMI/src/main/java/interface_module). This interface can be used from
the [Client](JavaRMI/src/main/java/client_module/Client.java). To use a bidirectional communication, the client provides
a __stub__ with the callback interface. With this stub the server can send its answer to the client.

__Note:__ Watch out for the extended classes, because they are the key for RMI.

<details>
  <summary>Class Diagram</summary>

__Note:__ Modules can be independent projects where the client and server each have the interface dependency.

![RMI class diagram](.readme-images/rmi-classes.png)
</details>

<details>
  <summary>Usage and Output</summary>

1. Start [Server main()](JavaRMI/src/main/java/server_module/Server.java)
2. Start [Client main()](JavaRMI/src/main/java/client_module/Client.java)

__Server Output:__

```log
INFORMATION: Server is started!

Received message: Greetings from Client!
Received message: Greetings from Client!
Received message: Greetings from Client!
```

__Client Output:__

```log
Received callback: Client is registered for callback!
Received callback: Server received the message!
Received callback: Server received the message!
Received callback: Server received the message!
```

</details>

__System Requirements:__

* Java
* Maven

## [MQTT Controller](MqttController/src/main/java)

The controller show a small usage of the [Publisher](MqttController/src/main/java/server_module/Publisher.java) and
[Subscriber](MqttController/src/main/java/client_module/Subscriber.java) concept of MQTT. The communication goes over a
topic on a ActiveMQ Broker.

<details>
  <summary>Class Diagram</summary>

__Note:__ Modules can be independent projects.

![RMI class diagram](.readme-images/mqtt-classes.png)
</details>

<details>
  <summary>Usage and Output</summary>

1. Run `activemq start`
2. Start [Subscriber main()](MqttController/src/main/java/client_module/Subscriber.java)
3. Start [Publisher main()](MqttController/src/main/java/server_module/Publisher.java)

__Publisher Output:__

```log
Log Date Time server_module.ProducerConnection start
INFO: Started Connection.
Sent message: Hello! at: 1653930520109
Sent message: Hello! at: 1653930521130
Sent message: Hello! at: 1653930522132
Sent message: Hello! at: 1653930523147
Sent message: Hello! at: 1653930524159
Sent message: Hello! at: 1653930525170
Sent message: Hello! at: 1653930526177
Sent message: Hello! at: 1653930527186
Sent message: Hello! at: 1653930528190
Sent message: Hello! at: 1653930529191
Log Date Time server_module.ProducerConnection stop
INFO: Stopped Connection.
```

__Subscriber Output:__

```log
Log Date Time client_module.ConsumerConnection start
INFO: Started Connection.
Received: Hello! at: 1653930520109
Received: Hello! at: 1653930521130
Received: Hello! at: 1653930522132
Received: Hello! at: 1653930523147
Received: Hello! at: 1653930524159
Received: Hello! at: 1653930525170
Received: Hello! at: 1653930526177
Received: Hello! at: 1653930527186
Received: Hello! at: 1653930528190
Received: Hello! at: 1653930529191
Received: null
Log Date Time client_module.ConsumerConnection stop
INFO: Stopped Connection.
```

</details>

__System Requirements:__

* Java
* Maven
* ActiveMQ

## [Multi Threading](MultiThread_MonteCarlo/src/main/java/app)

A small example for multi threading is the calculation of Pi in the context of the __Monte Carlo__ problem. New issues
like __deadlocks and race conditions__ need to be addressed in manual multi threading.

## System Requirements

### Java 11

1. Download: [Java 11+](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
2. Install the executable
3. Set [System Environment Variables](.readme-images/SystemVariables.png):
4. New: `JAVA_HOME` = `C:\Program Files\Java\jdk-11`
5. Edit > `PATH` > New: `%JAVA_HOME%\bin`
6. Test command: `java -version`

### Maven

1. Download: [Maven 3.6.3+](https://maven.apache.org/download.cgi)
2. Unzip it to: `C:\Program Files\maven`
3. Set [System Environment Variables](.readme-images/SystemVariables.png):
4. New: `MAVEN_HOME` = `C:\Program Files\maven`
5. Edit > `PATH` > New: `%MAVEN_HOME%\bin`
6. Test: `$ mvn -v`

### ActiveMQ

1. Download: [ActiveMQ 5.17+](https://activemq.apache.org/components/classic/download/)
2. Unzip it to: `C:\Program Files\activemq`
3. Set [System Environment Variables](.readme-images/SystemVariables.png):
4. Edit > `PATH` > New: `C:\Program Files\activemq\bin`
5. Test command: `activemq start`
6. Admin page: [http://localhost:8161/admin](http://localhost:8161/admin) with `admin`, `admin` credentials.

