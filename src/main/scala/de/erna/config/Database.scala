package de.erna.config

import com.mongodb.Mongo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.mongodb.core.{MongoClientFactoryBean, MongoTemplate}

@Configuration
class Database @Autowired()( databaseSettings: DatabaseSettings ) {
  @Bean
  def mongo( ): MongoClientFactoryBean = {
    val mongo = new MongoClientFactoryBean()
    mongo.setHost( databaseSettings.host )
    mongo
  }

  @Bean
  def mongoTemplate( @Autowired mongo: Mongo ): MongoTemplate = {
    new MongoTemplate( mongo, databaseSettings.name )
  }
}
