package io.sandstorm.gatling

import java.io.{PrintWriter, Writer}

import io.sandstorm.gatling.simulate.LoadProfile
import org.scalatest.{FlatSpec, Matchers}

import scala.io.{BufferedSource, Source}

class LoadProfileTest extends FlatSpec with Matchers {

  "Resource 'LoadProfile-1.json' on class path" should "be successfully serialized to a LoadProfile instance" in {
    setLoadProfileConfFile("LoadProfile-1.json")
    LoadProfile.scenarioRepeatTimes() should be(1000)
    checkUserInjectSteps()
    checkRpsThrotSteps()
  }

  "Resource 'LoadProfile-2.json' on class path" should "be successfully serialized to a LoadProfile instance" in {
    setLoadProfileConfFile("LoadProfile-2.json")
    LoadProfile.scenarioRepeatTimes() should be(1000)

    checkUserInjectSteps()

    LoadProfile.rpsThrotSteps() should not be (null)
    LoadProfile.rpsThrotSteps().size should be (0)
  }

  "Resource 'LoadProfile-3.json' on class path" should "be successfully serialized to a LoadProfile instance" in {
    setLoadProfileConfFile("LoadProfile-3.json")
    LoadProfile.scenarioRepeatTimes() should be(1000)

    LoadProfile.userInjectSteps() should not be (null)
    LoadProfile.userInjectSteps().size should be (0)

    checkRpsThrotSteps()
  }

  def checkUserInjectSteps() = {
    LoadProfile.userInjectSteps() should  not be (null)
    LoadProfile.userInjectSteps().size should be (1)
    LoadProfile.userInjectSteps().foreach { step =>
      step.totalUsers should be (6000)
      step.rateUps should be (200)
      step.duration should be (30)
    }
  }

  def checkRpsThrotSteps() = {
    LoadProfile.rpsThrotSteps() should not be (null)
    LoadProfile.rpsThrotSteps().size should be (2)
    val throtSteps = LoadProfile.rpsThrotSteps()
    throtSteps(0).rampTime should be (10)
    throtSteps(0).toRps should be (10000)
    throtSteps(0).holdTime should be (10)
    throtSteps(1).rampTime should be (20)
    throtSteps(1).toRps should be (20000)
    throtSteps(1).holdTime should be (10)
  }

  def setLoadProfileConfFile(resource: String): Unit = {
    val file = this.getClass.getClassLoader.getResource(resource).getFile
    val configWriter = new PrintWriter(this.getClass.getClassLoader.getResource("application.properties").getFile)
    doIOAction(configWriter) { writer =>
      writer.println(s"load-profile.conf.file=$file")
    }
  }

  def copy(srcResource: String, destResource: String): Unit = {
    val src = Source.fromResource(srcResource)
    val dest = new PrintWriter(this.getClass.getClassLoader.getResource(destResource).getFile)
    doIOAction(src, dest) { (src, dest) => {
      src.getLines().foreach {
        line => {
          println(line)
          dest.println(line)
        }
      }
    }
    }
  }

  def doIOAction[A <: BufferedSource, B <: Writer](src: A, dest: B)(f: (A, B) => Unit): Unit =
    try {
      f(src, dest)
    } finally {
      close(src)
      close(dest)
    }

  def doIOAction[S <: {def close() : Unit}](stream: S)(f: S => Unit): Unit =
    try {
      f(stream)
    } finally {
      close(stream)
    }

  def close[C <: {def close() : Unit}](closableObj: C): Unit = {
    try {
      closableObj.close()
    } catch {
      case e: Exception => println(s"Error while closing object: ${e.getClass.getName}: ${e.getMessage}")
    }
  }
}
