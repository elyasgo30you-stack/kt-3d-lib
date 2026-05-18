# KT 3D Lib

KT 3D Lib is a simple Android Kotlin 3D library with native C++ support.

It provides:
- Basic 3D engine startup
- Native C++ rendering bridge
- Player setup
- Default player support
- GLB model path support
- Joystick movement
- Camera touch rotation
- Jump button
- Player speed settings
- Jump power settings
- Basic keyframe animation setup

---

## Library Package

The library package is:

```kotlin
com.lib.elyasabdo3d
```

You do not need to change your app package name.

Your app package can be anything, for example:

```kotlin
com.my.game
com.test.app
com.company.project
```

Use this import in your Kotlin files:

```kotlin
import com.lib.elyasabdo3d.world3d
```

---

## Installation

### Step 1: Add JitPack

Open `settings.gradle.kts` in your Android project and add JitPack:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
```

### Step 2: Add the Library

Open `app/build.gradle.kts` and add:

```kotlin
dependencies {
    implementation("com.github.elyasgo30you-stack:kt-3d-lib:1.0.0")
}
```

Then press **Sync Now**.

---

## Basic Usage

In `MainActivity.kt`:

```kotlin
package com.my.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lib.elyasabdo3d.world3d

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        world3d.start(this)
    }
}
```

---

## Start the 3D Engine

To start the engine:

```kotlin
world3d.start(this)
```

This creates the 3D view, starts the native renderer, adds the default player, and enables basic controls.

---

## Default Player

The default player file path is:

```text
src/main/assets/characters/player.glb
```

To use the default player:

```kotlin
world3d.player()
```

To use a custom player file:

```kotlin
world3d.player("characters/player.glb")
```

If the player GLB file does not exist, the library still creates a default player object.

---

## Add a 3D Model

Model files should be placed inside:

```text
src/main/assets/charcters/models/
```

Example:

```kotlin
world3d.new.model(
    name = "enemy",
    path = "charcters/models/enemy.glb",
    x = 5f,
    y = 0f,
    z = 10f,
    scale = 1f
)
```

---

## Add a Keyframe Animation

Keyframe files should be placed inside:

```text
src/main/assets/characters/keyframes/
```

Example:

```kotlin
world3d.new.keyframe(
    modelName = "player",
    fromSec = 2f,
    toSec = 5f,
    action = "walk",
    file = "characters/keyframes/player.glb"
)
```

Example action names:

```text
idle
walk
run
jump
```

---

## Player Speed

The player speed file path is:

```text
src/main/assets/characters/setings/speed.txt
```

Write only one number inside the file.

Example:

```text
20
```

If the file does not exist, the default player speed is:

```text
20
```

---

## Jump Power

The default jump power file path is:

```text
src/main/assets/charecters/seting/jump.txt
```

Write only one number inside the file.

Example:

```text
200
```

If the file does not exist, the default jump power is:

```text
200
```

For a model-specific jump power, create a file like this:

```text
src/main/assets/charecters/seting/jump_player.txt
```

Example content:

```text
300
```

---

## Jump Button

To make the player jump from Kotlin:

```kotlin
world3d.jump.button()
```

If the player is on the ground, it will jump.

---

## Auto Jump Point

You can make a model jump when it reaches a specific position.

Example:

```kotlin
world3d.set.jump(
    modelName = "player",
    z = 10f,
    x = 5f,
    y = 0f
)
```

---

## Controls

Left side of the screen:

```text
Joystick movement
```

Right side of the screen:

```text
Camera rotation by finger touch
```

---

## Full Example

```kotlin
package com.my.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lib.elyasabdo3d.world3d

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        world3d.start(this)

        world3d.player("characters/player.glb")

        world3d.new.model(
            name = "enemy",
            path = "charcters/models/enemy.glb",
            x = 5f,
            y = 0f,
            z = 10f,
            scale = 1f
        )

        world3d.new.keyframe(
            modelName = "player",
            fromSec = 2f,
            toSec = 5f,
            action = "walk",
            file = "characters/keyframes/player.glb"
        )

        world3d.set.jump(
            modelName = "player",
            z = 10f,
            x = 5f,
            y = 0f
        )
    }
}
```

---

## Important File Paths

Player:

```text
src/main/assets/characters/player.glb
```

Speed:

```text
src/main/assets/characters/setings/speed.txt
```

Jump:

```text
src/main/assets/charecters/seting/jump.txt
```

Model-specific jump:

```text
src/main/assets/charecters/seting/jump_player.txt
```

Models:

```text
src/main/assets/charcters/models/
```

Keyframes:

```text
src/main/assets/characters/keyframes/
```

---

## Notes

- Your app package name can be anything.
- You do not need to change your app package name.
- The library package is always:

```kotlin
com.lib.elyasabdo3d
```

- Use this import:

```kotlin
import com.lib.elyasabdo3d.world3d
```

- This library uses Android NDK and native C++.
- If the project fails to build, make sure NDK and CMake are installed.
- If the library is used from JitPack, make sure the GitHub release/tag exists.
- Recommended version tag format:

```text
1.0.0
```

---

## License

This project is open for personal and educational use.
