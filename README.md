# Traffic Light System

This repository contains a Traffic Light System application implemented in two different modules - 
one with Jetpack Compose based UI and one with XML-based layouts.

## Project Structure
Two modules:
- **trafficlightsystemxml**: The implementation using XML layouts, Fragment-based navigation, and simpler clean architecture
- **app**: Jetpack Compose with Clean Architecture, MVVM, Hilt for DI, and Kotlin Coroutines for asynchronous operations

## Module Details

### 1. Jetpack Compose Module (`app`)

This module showcases UI using Jetpack Compose with Clean Architecture.

#### Features:
- **Car Input Screen**: Allows users to enter their car model with validation
- **Traffic Light Simulation**: Displays an animated traffic light with color transitions
- **Single-Activity Architecture**: Uses Jetpack Navigation with Compose

#### Technical Details:
- **UI Framework**: Jetpack Compose with Material Design 3
- **Architecture**: Clean Architecture with separation of layers:
  - **Domain Layer**: Models (TrafficLightState, CarModel) and use cases independent of Android framework
  - **Data Layer**: Repository implementations and data sources
  - **Presentation Layer**: ViewModels, UI States, and Composables
- **State Management**: 
  - Immutable UI state with StateFlow
  - BaseViewModel pattern for consistent state handling
  - Action-based state updates
- **Dependency Injection**: Hilt with proper module organization
  - Repository bindings
  - Use case provision
  - ViewModel injection
- **Navigation**: Jetpack Navigation Component with type-safe arguments
- **Resource Management**:
  - Centralized string resources in strings.xml
  - Color constants in theme package
- **Unit Testing**:
  - ViewModel tests with MainDispatcherRule
  - Mockito for mocking dependencies
  - Coroutines testing utilities

### 2. XML-based Module (`trafficlightsystemxml`)

This module demonstrates a traditional Android development approach using single Activity architectural strategy, XML layouts and Fragment-based navigation.

#### Features:
- **Car Input Fragment**: Allows users to enter their car model with validation
- **Traffic Light Fragment**: Displays an animated traffic light simulation
- **Navigation**: Uses the Navigation Component with XML-defined navigation graph

#### Technical Details:
- **UI**: XML layouts with ConstraintLayout and traditional View components
- **Architecture**: MVVM with lifecycle components
- **Data Binding**: ViewBinding for type-safe view access
- **State Management**: LiveData for observable UI states
- **Navigation**: Jetpack Navigation Component with SafeArgs

## Common Components

Both modules share:
- **Domain Models**: Common models like TrafficLightState and CarModel
- **Use Cases**: Business logic encapsulated in use cases
- **Testing Framework**: JUnit, Mockito, and coroutines testing utilities

## Getting Started
Run either the `app` module or the `trafficlightsystemxml` module on an emulator or physical device.

## Requirements
- Minimum SDK: 29
- Target SDK: 35
- Kotlin: 2.0.0
- Gradle: 8.8.2 