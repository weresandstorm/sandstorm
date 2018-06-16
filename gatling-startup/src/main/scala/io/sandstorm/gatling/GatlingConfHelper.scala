package io.sandstorm.gatling

import java.nio.file.Path

import io.gatling.commons.util.PathHelper._

trait GatlingConfHelper {

  val projectRootDir: Path = getProjectRootDir
  var dataDirectory: Path = projectRootDir / "user-files" / "data"
  var resultsDirectory: Path = projectRootDir / "user-files" / "results"

  private def getProjectRootDir(): Path = {
    val classesPath: Path = getClass.getClassLoader.getResource("").toURI
    classesPath.ancestor(2)
  }

}
