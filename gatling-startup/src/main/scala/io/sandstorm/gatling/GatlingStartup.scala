package io.sandstorm.gatling

import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

object GatlingStartup {

  def main(args: Array[String]): Unit = {
    //    SysOutOverSLF4J.sendSystemOutAndErrToSLF4J
    if (args == null) {
      showUsageThenExit()
    }

    val props = new GatlingPropertiesBuilder
    args.length match {
      case 2 => {
        props.simulationClass(args(0).trim)
        props.simulationId(args(1).trim)
      }
      case 3 => {
        props.simulationClass(args(0).trim)
        props.simulationId(args(1).trim)
        props.dataDirectory(args(2).trim)
      }
      case 4 => {
        props.simulationClass(args(0).trim)
        props.simulationId(args(1).trim)
        props.dataDirectory(args(2).trim)
        props.resultsDirectory(args(3).trim)
      }
      case _ => showUsageThenExit()
    }
    props.noReports()
    Gatling.fromMap(props.build)
  }

  def showUsageThenExit(): Unit = {
    println(s"Usage: ${GatlingStartup.getClass.getName} <simulation class name> <simulation execution id> [<data directory> <results directory>]")
    println(s"Example 1: ${GatlingStartup.getClass.getName} a.b.c.script.NormalPurchase 10000")
    println(s"Example 2: ${GatlingStartup.getClass.getName} a.b.c.script.NormalPurchase 10000 /gatling/user-files/data")
    println(s"Example 2: ${GatlingStartup.getClass.getName} a.b.c.script.NormalPurchase 10000 /gatling/user-files/data /gatling/user-files/results")
    System.exit(0)
  }

}

