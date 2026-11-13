# JSON CSV Converter

## Description

JSON-CSV Converter is a Java command-line application that converts files between JSON and CSV formats.

This project was developed to demonstrate the use of Apache Maven as a modern build automation tool.

The project demonstrates the following Maven functionalities:
- Dependency management through Maven Central
- Java source code compilation
- Project version management
- Packaging into executable JAR files
- Reproducible builds using Maven commands

## Technologies

- Java 21
- Apache Maven
- Jackson Databind
- OpenCSV

## Project Structure

```text
json-csv-converter
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── lea
│   │               └── converter
│   │                   ├── Main.java
│   │                   └── Converter.java
│   └── test
│       └── java
```

## Features

- Convert JSON files to CSV
- Convert CSV files to JSON
- Simple command-line interface
- Automatic dependency management with Maven

## Dependencies

The project uses the following external libraries:

Jackson Databind for JSON parsing and generation 
OpenCSV for CSV reading and writing 

Dependencies are automatically downloaded by Maven. 

## Build Instructions

Compile the project:

```bash
mvn compile
```

Clean and rebuild the project:

```bash
mvn clean package
```

Install into the local Maven repository:

```bash
mvn install
```

## Generated Artifacts

Running:

```bash
mvn package
```

creates:

```text
target/json-csv-converter-1.0.0.jar
target/json-csv-converter-1.0.0-jar-with-dependencies.jar
```

The second artifact contains all required dependencies and can be executed directly.

## Usage

### JSON to CSV

```bash
java -jar target/json-csv-converter-1.0.0-jar-with-dependencies.jar \
json2csv input.json output.csv
```

### CSV to JSON

```bash
java -jar target/json-csv-converter-1.0.0-jar-with-dependencies.jar \
csv2json input.csv output.json
```

## Example

### Input JSON

```json
[
  {
    "name": "Alice",
    "age": 20
  },
  {
    "name": "Bob",
    "age": 25
  }
]
```

### Output CSV

```csv
name,age
Alice,20
Bob,25
```

## Version

Current version:

```text
1.0.0
```

Managed in the Maven configuration file:

```xml
<version>1.0.0</version>
```

## Build Tool Demonstration

Typical build command:

```bash
mvn clean install
```

This command allows any developer to rebuild the project from scratch in a reproducible way.