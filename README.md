# Bean-Builder

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.toolisticon.beanbuilder/beanbuilder-processor/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.toolisticon.beanbuilder/beanbuilder-processor)
[![Build Status](https://travis-ci.org/toolisticon/bean-builder.svg?branch=master)](https://travis-ci.org/toolisticon/bean-builder)
[![codecov](https://codecov.io/gh/toolisticon/bean-builder/branch/master/graph/badge.svg)](https://codecov.io/gh/toolisticon/bean-builder)

This is an example project that demonstrates the usage of the [Annotation-Processor-Toolkit](https://github.com/holisticon/annotation-processor-toolkit) and the [Compile-Testing](https://github.com/toolisticon/compile-testing) libraries.

It provides an annotation processor that creates bean builder classes that allows you to create bean instances via an immutable fluent API.

# Why you should use this project?

During the implementation of unit or integration test it's a common task to setup bean instances used for testing. 

Usually it's more convenient and far more readable to use a fluent builder api to create the bean instances than to create a bean instance and to use it's setter methods.

This project provides an annotation processor which allows you to generate a bean builder class for your own and 3rd party bean classes. It even supports setting of inherited attributes.

# Features
Annotation processor that

- creates a bean builder class for your own and 3rd party bean classes that allows you to create bean instances via a fluent API.
- is fully lombok compatible

# How does it work?


First you need to add the bean builder annotation processor api dependency to your project.

```xml
<dependencies>
    <!-- must be on provided scope since it is just needed at compile time -->
    <dependency>
	<groupId>io.toolisticon.beanbuilder</groupId>
	<artifactId>beanbuilder-api</artifactId>
    <version>${CURRENT_VERSION}</version>
    </dependency>
</dependencies>
```

The annotation processor should be applied by defining it in annotation processor path of maven-compile-plugin:
```xml
<plugin>
   <artifactId>maven-compiler-plugin</artifactId>
   <configuration combine.self="append">
       <annotationProcessorPaths>
           <path>
               <groupId>io.toolisticon.beanbuilder</groupId>
               <artifactId>beanbuilder-processor</artifactId>
               <version>${CURRENT_VERSION}</version>
           </path>
       </annotationProcessorPaths>
   </configuration>
</plugin>
```

Bean builder classes can be created by annotating your bean class with the _Builder_ annotation or by using the _ThirdPartyBeanBuilder_ annoation in a packacke-info.java file.

For further information about usage check Examples section down below.

## Preconditions

Builder classes can be created for classes that 

- provide an accessible NoArg constructor
- provide getters and setter for fields (explicitely implemeted or via lombok)


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

It's also possible to generate bean builder classes for existing classes by using the ThirdPartyBeanBuilder annotation in a package-info.java file.
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
A simple import of the pom in your IDE should get you up and running. To build the bean-builder on the commandline, just run `mvn` or `mvn clean install`

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
