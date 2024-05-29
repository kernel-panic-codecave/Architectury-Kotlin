# Architectury Kotlin

[![Pre Merge Checks](https://github.com/kernel-panic-codecave/Architectury-Kotlin/workflows/Pre%20Merge%20Checks/badge.svg)](https://github.com/kernel-panic-codecave/Architectury-Kotlin/actions/workflows/pre-merge.yaml)  [![License](https://img.shields.io/github/license/kernel-panic-codecave/Architectury-Kotlin.svg)](LICENSE) ![Language](https://img.shields.io/github/languages/top/kernel-panic-codecave/Architectury-Kotlin?color=blue&logo=kotlin)

A simple gradle plugin that enables the use of Kotlin Multiplatform's expect/actual declarations in Architectury projects, instead of using Architectury Transformer and the @ExpectPlatform annotation. 

## How to use 
Put this in your root `build.gradle.kts`
and ***make sure you have Architectury Loom configured in compile only mode, or forge like projects will not work***. Look on the Architectury wiki for how to do that. 

```kotlin
plugins {
    id("com.withertech.architectury.kotlin.plugin") version "<version>" apply false
}

subprojects {
    apply(plugin = "com.withertech.architectury.kotlin.plugin")
}
```
Then you should be able to use kotlin multiplatform expect/actual syntax with Architectury! Note that because you must put loom in compile only mode, you will not have access to Architectury Injectables, so you will have to implement ArchitecturyTarget yourself if you want to be able to get the current platform string, but that should be easy with expect/actual.

## Contributing 

Feel free to open a issue or submit a pull request for any bugs/improvements.

## License 

This plugin is licensed under the MIT License - see the [License](LICENSE) file for details
