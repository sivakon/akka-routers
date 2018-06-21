import akka.actor.Actor

class Worker extends Actor {
  import Worker.Work

  def receive = {
    case msg: Work =>
      println(s"I received a work message and my actorref: ${self}")
  }

}

object Worker {
  case class Work()
}
