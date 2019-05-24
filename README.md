# Bean-Builder

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.toolisticon.beanbuilder/beanbuilder-processor/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.toolisticon.beanbuilder/beanbuilder-processor)
[![Build Status](https://travis-ci.org/toolisticon/bean-builder.svg?branch=master)](https://travis-ci.org/toolisticon/bean-builder)
[![codecov](https://codecov.io/gh/toolisticon/bean-builder/branch/master/graph/badge.svg)](https://codecov.io/gh/toolisticon/bean-builder)

This is an example project that demonstrates the usage of the [Annotation-Processor-Toolkit](https://github.com/holisticon/annotation-processor-toolkit).

# Why you should use this project?

Usually using a builder fluent api to create POJO instances is far more readable than creating an instance and using the setter methods.

# Features
Annotation processor that

- creates a bean builder class for POJO classes that allows you to create instances via a fluent API.
- is fully lombok compatible

# How does it work?

Just add the bean builder annotation processor dependency to your
	<dependencies>
	    <!-- must be on provided scope since it is just needed at compile time -->
	    <dependency>
	        <groupId>io.toolisticon.beanbuilder</groupId>
	        <artifactId>beanbuilder-processor</artifactId>
	        <scope>provided</scope>
	    </dependency>
	</dependencies>

Pojo classes you want to create a builder for must be annotated with the Builder annotation.

## Preconditions

POJOs must

- Provide an accessible NoArg constructor
- must provide getters and setter of fields (explicitely implemeted or via lombok)


## Example

POJO:

```java
@BeanBuilder
public class TestBean {

    private String stringField;
    private Long longField;
    
    public void setStringField(String stringField) {
        this.stringField = stringField;
    }
    
    public String getStringField() {
        return this.stringField;
    }
    
    public void setLongField(String longField) {
        this.longField = longField;
    }
    
    public Long getLongField() {
        return this.longField;
    }

}
```
    
POJO with lombok:

```java
@BeanBuilder
@Data
public class TestBean {

    private String stringField;
    private Long longField;

}
```
    
Usage of builder:
   
```java
TestBean testBean = TestBeanBuilder.createBuilder()
   .withLongField(5L)
   .withStringField("TEST")
   .build(); 
```

It's also possible to generate bean builder for existing classes by using the ThirdPartyBeanBuilder annotation in a package-info.java file.
In this case the Builder will be created inside the annotated package with package private visibility.

```java
@ThirdPartyBeanBuilder({TestBean.class})
package io.toolisticon.beanbuilder.integrationtest;

import io.toolisticon.beanbuilder.api.ThirdPartyBeanBuilder;
```
    
# Contributing

We welcome any kind of suggestions and pull requests.

## Building and developing the Bean-Builder

The Bean-Builder is built using Maven.
A simple import of the pom in your IDE should get you up and running. To build the annotation-processor-toolkit on the commandline, just run `mvn` or `mvn clean install`

## Requirements

The likelihood of a pull request being used rises with the following properties:

- You have used a feature branch.
- You have included a test that demonstrates the functionality added or fixed.
- You adhered to the [code conventions](http://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html).

## Contributions

- (2018) Tobias Stamann (Holisticon AG)

# License

This project is released under the revised [MIT License](LICENSE).

This project includes and repackages the [Annotation-Processor-Toolkit](https://github.com/holisticon/annotation-processor-toolkit) released under the  [MIT License](/3rdPartyLicenses/annotation-processor-toolkit/LICENSE.txt).
