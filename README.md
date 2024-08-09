# Game Project

This is a simple 2D game built in Java. The project features player movement, sound effects, collision detection, and object interaction.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)

## Introduction

This project demonstrates a basic 2D game where a player can move around the screen, interact with objects, and navigate through the game world. It is built using Java and includes basic features like collision detection and sprite animation.

## Features

- Player movement in four directions (up, down, left, right)
- Collision detection with tiles and objects
- Object interaction (e.g., picking up items)
- Sprite animation for different player states
- Simple game panel for rendering graphics

## Getting Started

### Prerequisites

To run this project, you need to have Java Development Kit (JDK) installed on your machine.

- [JDK Download](https://www.oracle.com/java/technologies/javase-downloads.html)

### Installation

1. Clone the repository:

    ```sh
    git clone https://github.com/Stancoschi/2D-javaGame.git
    ```

2. Navigate to the project directory:

    ```sh
    cd 2D-javaGame.git
    ```

3. Compile the project:

    ```sh
    javac -d bin src/main/*.java src/entity/*.java
    ```

4. Run the project:

    ```sh
    java -cp bin main.GamePanel
    ```

## Usage

- Use the WASD keys to move the player character around the screen.
- The player can pick up objects by walking over them.
- The player's sprite will change based on the direction of movement and animation frame.

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Open a Pull Request.

