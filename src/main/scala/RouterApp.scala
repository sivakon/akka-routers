import Worker.Work
import akka.actor.{ActorSystem, Props}

object RouterApp extends App {

  val system = ActorSystem("router")

  val routerPool = system.actorOf(Props[RouterPool], "router-actor")

  routerPool ! Work()

  routerPool ! Work()

  routerPool ! Work()

  // for routerGroup
  system.actorOf(Props[Worker], "w1")
  system.actorOf(Props[Worker], "w2")
  system.actorOf(Props[Worker], "w3")
  system.actorOf(Props[Worker], "w4")
  system.actorOf(Props[Worker], "w5")

  val workers: List[String] = List(
    "/user/w1",
    "/user/w2",
    "/user/w3",
    "/user/w4",
    "/user/w5"
  )

  val routerGroup = system.actorOf(Props(classOf[RouterGroup], workers)) // props can also be created inside routerGroup

  println("---------------------------")

  routerGroup ! Work()

  routerGroup ! Work()

  routerGroup ! Work()

  routerGroup ! Work()

  Thread.sleep(100)

  system.terminate()

}
