package io.sandstorm.gatling.simulate

import io.gatling.core.session.Session

trait SessionHelper {

  private val one_cycle_attrs = "one_cycle_attrs"

  def setOneCycleAttr(session: Session, key: String, value: Any): Session = {
    var one_cycle_attrs_names: Set[String] = null
    if (session(one_cycle_attrs).asOption[Map[String, Any]].isEmpty) {
      one_cycle_attrs_names = session(one_cycle_attrs).asInstanceOf[Set[String]]
      one_cycle_attrs_names += key
    } else {
      one_cycle_attrs_names = Set(key)
    }
    session.setAll((one_cycle_attrs, one_cycle_attrs_names), (key, value))
  }

  def clearAllOneCycleAttrs(session: Session): Session = {
    if (session(one_cycle_attrs).asOption[Map[String, Any]].isDefined) {
      val one_cycle_attrs_names = session(one_cycle_attrs).asInstanceOf[Set[String]]
      // TODO: remove all one_cycle_attrs
      session
    } else {
      // TODO: keep unchanged
      session
    }
  }

}
