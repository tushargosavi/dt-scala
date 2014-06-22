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
```
sbt compile
```

### Create package
```
sbt package
```

## Running on Datatorrent Platform.

The application requires malhar and scala runtime libraries
to run, I do not know how to make a simple jar with just these
dependency included.

Finally copied these two jars in a lib directory and provide its
path to Datatorrent application launcher (dtcli)

```
dtadmin@dtbox1:~$ dtcli 
DT CLI 0.9.5-RC1 02.05.2014 @ 21:51:54 PDT rev: 7fb9d4b branch: 7fb9d4b34bd5b4af84d7f19cda31f652a844d593
dt> launch -libjars /home/tushar/devel/scala/dtscalatest/target/lib/* /home/tushar/devel/scala/dtscalatest/target/scala-2.10/dtscalatest_2.10-1.0.jar 
Waiting for license agent to start...
License agent started.
{"appId": "application_1403440284204_0002"}
dt (application_1403440284204_0002) > 
```

[dt]: https://www.datatorrent.com/
[malhar]: https://github.com/DataTorrent/Malhar