import akka.actor.{Actor, ActorSystem, ActorRef, Props}
import akka.routing.{RoundRobinPool, FromConfig}
import Worker._

object RoundRobin extends App {
  val system = ActorSystem("Round-robin-router")

//  val routerPool = system.actorOf(RoundRobinPool(3).props(Props[Worker]), "round-robin-pool") // without config

  val routerPool = system.actorOf(FromConfig.props(Props[Worker]), "round-robin-pool")

  routerPool ! Work()
  routerPool ! Work()
  routerPool ! Work()
  routerPool ! Work()
  routerPool ! Work()
  routerPool ! Work()


  // add three workers by labels first
  system.actorOf(Props[Worker], "w1")
  system.actorOf(Props[Worker], "w2")
  system.actorOf(Props[Worker], "w3")

  // create routerGroup from the config

  val routerGroup = system.actorOf(FromConfig.props(), "round-robin-group")

  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()

  /**
    * There are different routers, best way to create multiple actors to
    */

  system.terminate()
}
