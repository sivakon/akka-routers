import Worker.Work
import akka.actor.{Actor, ActorSystem, Props}
import akka.routing.{FromConfig, RandomGroup}

object Random extends App {

  val system = ActorSystem("Random-Router")

  // create a router instance instead of props

  // with just a config flag and FromConfig, we can create a random router pool
  // create router pool from config
  val routerPool = system.actorOf(FromConfig.props(Props[Worker]), "random-router-pool")

  (1 to 10) foreach { _ =>
    routerPool ! Work()
  }

  val giveWorkToRouter: Unit = routerPool ! Work()

//  List.fill(10)(giveWorkToRouter)

//
//  routerPool ! Work()
//
//  routerPool ! Work()
//
//  routerPool ! Work()
//
//  routerPool ! Work()
//
//  routerPool ! Work()
//
//  routerPool ! Work()
//
//  routerPool ! Work()

  /**
    * Create a random group router
    */

  system.actorOf(Props[Worker], "w1")
  system.actorOf(Props[Worker], "w2")
  system.actorOf(Props[Worker], "w3")

  val paths = List("/user/w1", "/user/w2", "/user/w3")

  val routerGroup = system.actorOf(RandomGroup(paths).props(), "random-router-group")

  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()
  routerGroup ! Work()

  Thread.sleep(100)

  system.terminate()

}
