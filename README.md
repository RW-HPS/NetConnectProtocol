## Language

[跳转中文](https://github.com/RW-HPS/RW-HPS-ExampleJavaPlugin/blob/master/README-zh_CN.md)  
[TO EN](https://github.com/RW-HPS/RW-HPS-ExampleJavaPlugin/blob/master/README.md)

## Setup
Clone this repository first. To edit the plugin display name and other data, take a look at plugin.json. Edit the name of the project itself by going into settings.gradle.

## Basic Usage
See src/example/ExamplePlugin.java for some basic commands and event handlers.
Every main plugin class must extend Plugin. Make sure that plugin.json points to the correct main plugin class.

Please note that the plugin system is in beta, and as such is subject to changes.

## Building a Jar
gradlew jar or ./gradlew jar

Output jar should be in build/libs.

## Installing
Simply place the output jar from the step above in your server's data/plugins directory and restart the server. List your currently installed plugins by running the mods command.