package de.erna

import com.typesafe.scalalogging.LazyLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.{Banner, SpringApplication}

@SpringBootApplication
class Main extends LazyLogging {
  logger.info( "ernadoc meta data store starting" )
}

object Main extends App {
  val app = new SpringApplication( classOf[ Main ] )
  app.setBannerMode( Banner.Mode.OFF )
  app.run( args: _* )
}
