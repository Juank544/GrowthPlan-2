# Project 3 - Growth Plan 2

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/Juank544/GrowthPlan-2/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/Juank544/GrowthPlan-2/tree/master)

<!-- TOC -->
* [Project 3 - Growth Plan 2](#project-3---growth-plan-2)
  * [Abstract](#abstract)
  * [Technologies and Stuff](#technologies-and-stuff)
  * [Class Diagram](#class-diagram)
<!-- TOC -->

## Abstract

This repo provides a REST API application that manage all tasks related to soccer leagues. Implements the basic CRUD
operations with the different entities and allows to manage process like set up matches between teams and its owns
standings, all of this covered with a security layer of authentication and authorization as well as handling common
errors that provides a better experience.

## Technologies and Stuff

* Spring Boot
* Lombok
* Mapstruct
* Postgres - H2
* Hibernate - JPA
* QueryDSL
* JUnit 5
* Mockito
* MockMVC
* Spring Security
* JWT
* Validations constraints
* HATEOAS
* Exception Handling

## Class Diagram

![](/img/ClassDiagram.png)