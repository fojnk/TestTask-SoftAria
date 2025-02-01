# Softaria Test Task

## Monitoring System
This project is a simple command line interface that will allow you to track the status of a selected list of pages. 
### Basic functionality:

| Option |  Description | Type |
| --- | --- | --- |
| today | path to the file where the list of pages to be monitored today is stored. There is exactly one page on each line | required |
| yesterday | path to the file where the previous state of pages (yesterday) was stored | required |
| update | this flag stores the current state of the pages on the path from the [--yestarday] flag | optional |
| parallel | parallel search for page state differences | optional |
| name | username | required |
| recipients | if this flag is specified, the result will be sent to mail, not to the console. Represents a comma-separated list of addresses | optional |


## Usage

### Install
```
git clone git@github.com:fojnk/TestTask-SoftAria.git
```

### Env
If you want to use mailing, you need to fill the fields in the **mail.properties** file

### Build

``` java
./gradlew shadowJar
```

### Run
``` java
java -jar build/libs/site-info-1.0-SNAPSHOT-all.jar  --C --name "Vova Petrov" --today "src/main/resources/todayData.txt" --yesterday "src/main/resources/yesterdayData
```

### Example
``` java
// run without yesterday data
java -jar build/libs/site-info-1.0-SNAPSHOT-all.jar  --C --name "Vova Petrov" --today "src/main/resources/todayData.txt" --yesterday "src/main/resources/yesterdayData

// update yesterday data
java -jar build/libs/site-info-1.0-SNAPSHOT-all.jar  --C --name "Vova Petrov" --today "src/main/resources/todayData.txt" --yesterday "src/main/resources/yesterdayData --update

// run again without updating and check the result
java -jar build/libs/site-info-1.0-SNAPSHOT-all.jar  --C --name "Vova Petrov" --today "src/main/resources/todayData.txt" --yesterday "src/main/resources/yesterdayData
```

## Dependencies

* OpenJDK 17.0.8
* Gradle  8.10.2
* Log4j 2.16.0
* JCommander 1.69
* Angus 2.0
* Junit 5.9
* Lombok 1.18
