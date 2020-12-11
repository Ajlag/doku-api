# doku-api
* * *

Online software documentation management system.

## Prerequisites

- Git
- Java SE Development Kit 8
- Gradle 6.6
- MySQL 5.7

## Cloning

Once the environment is set up with the required dependencies, you can clone the source code:

```sh
$ git clone git@github.com:i4di/doku-api.git
```

When the cloning completes, you can navigate into the directory where the source code is cloned:

```sh
$ cd doku-api
```

## Building and testing

Before building the project, you need to adjust the configuration files' parameters in the `src/main/resources/application.yml` file:

- `development document`: Contains development profile parameters (ie. local database connection URL, credentials, etc.).
- `test document`: Contains testing profile parameters. All tests are executed under this profile.
- `release document`: Contains release profile parameters.  

With the proper profile and configuration parameters adjusted as per the local environment, you can build the application using one of the following commands:

```sh
$ gradle clean build                  # Execute all tests and build project

$ gradle clean build -x test          # Build project without executing tests
```

In case you only want to execute tests:

```sh
$ gradle test
```

## Running

After the application has been successfully built, the application's executable JAR will be placed in the `build/libs` directory of the application directory. Application can be started just like any other JAR, using the following:

```sh
$ java -jar build/libs/doku-api-VERSION_TAG.jar
```
