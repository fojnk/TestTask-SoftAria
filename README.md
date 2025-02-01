# Softaria Test Task

## Monitoring System
This project is a simple command line interface that will allow you to track the status of a selected list of pages. 
### Basic functionality:
**[ --today ]** - path to the file where the list of pages to be monitored today is stored. There is exactly one page on each line.

**[ --yesterday ]** - path to the file where the previous state of pages (yesterday) was stored.
 
**[ --update ]** - this flag stores the current state of the pages on the path from the [--yestarday] flag.

**[ --parallel ]** - parallel search for page state differences.

**[ --name ]** - username

**[ --recipients ]** - if this flag is specified, the result will be sent to mail, not to the console. Represents a comma-separated list of addresses.



## Usage

### Install
```
git clone git@github.com:fojnk/TestTask-SoftAria.git
```

### Env
If you want to use mailing, you need to fill in the fields in the **mail.properties** file

### Build

``` java
./gradlew shadowJar
```

### Run
```
java -jar build/libs/site-info-1.0-SNAPSHOT-all.jar  --C --name "Vova Petrov" --today "src/main/resources/todayData.txt" --yesterday "src/main/resources/yesterdayData
```

## Dependencies
