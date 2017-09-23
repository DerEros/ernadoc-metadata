package de.erna.storage

import de.erna.config.DatabaseSettings
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component

@Component
class MetaDataDAO @Autowired()( val databaseSettings: DatabaseSettings, val mongoTemplate: MongoTemplate ) {

  def store( record: Object ): Object = {
    val collection = findCollection( record )
    mongoTemplate.insert( record, collection )

    record
  }

  def findCollection( record: Object ) = "users"

}
