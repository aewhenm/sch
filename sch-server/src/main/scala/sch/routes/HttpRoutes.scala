package sch.routes

import akka.stream.ActorMaterializer
import akka.actor.{ActorRef, ActorRefFactory}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

class HttpRoutes()(implicit val actorMaterializer: ActorMaterializer){

  val routes: Route = {
    path("healthcheck") {
      get {
        complete("OK")
      }
    }
  }

}
