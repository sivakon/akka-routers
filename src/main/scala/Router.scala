import akka.actor.{Actor, ActorRef, ActorSystem, Props}

class RouterPool extends Actor {

  import Worker._
  // this actor should contain list of actors
  // initialize
  var routees: List[ActorRef] = _

  override def preStart(): Unit = {
    // create 5 instances, because of this, router actor is parent of routees
    routees = List.fill(10) (
      context.actorOf(Props[Worker])
    )

  }

  def receive = {
    // when the router receives a work message
    case msg: Work =>
      println("I'm a router and I received a message")
      // forward message to a random actor from the list of routees
      // forward -> original sender ref is maintained
      routees(util.Random.nextInt(routees.size)) forward msg
  }

}

class RouterGroup(routess: List[String]) extends Actor {

  import Worker._

  def receive = {
    case msg: Work =>
      println("I'm a router group and I receive a work message")
      context.actorSelection(routess(util.Random.nextInt(routess.size))) forward msg
  }
}
