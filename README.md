# Writting Datatorrent Application using Scala.

## Datatorrent
[Datatorrent][dt] is platform for writing real-time application on Hadoop
It scales linearly with number of resources. It provides a simple programing
model to write scalable application using java, and comes with more that 400
[open source operators][malhar]


## Scala
Scala is functional/object-oriented language targeted toward
jvm. As the code is targeted for JVM, scala program can use
existing Java objects, which make it easier to integrate with
existing Java applications/Frameworks.

## Sbt
SBT is the build tool for Scala, This project is using SBT to
managing dependency, compilation and creation of package.

### compile

    sbt compile

### Create package
    
    sbt package

## Running on Datatorrent Platform.

The application requires malhar and scala runtime libraries to run.  These can be installed on launch machine, or packaged with AppBundles feature.  Example below uses locally installed scala libraries.

    $ dtcli 
    dt> launch -libjars /usr/share/scala/lib/* dtscalatest_2.10-1.0.jar


## Links

DataTorrent - https://www.datatorrent.com/
DataTorrent Docs - https://www.datatorrent.com/docs/
Malhar - https://github.com/DataTorrent/Malhar
