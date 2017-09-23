package de.erna.storage

import com.mongodb.DBObject
import com.mongodb.util.JSON
import org.springframework.stereotype.Component

@Component
class MongoDBTranslator extends RecordTranslator[ String, Object ] {
  override def toInternal( record: String ): DBObject = {
    JSON.parse( record ).asInstanceOf[ DBObject ]
  }

  override def toExternal( record: Object ): String = {
    record match {
      case dbObject: DBObject =>
        renameIdField( dbObject )
        JSON.serialize( dbObject )
      case _ =>
        throw new IllegalArgumentException( s"Invalid format for record object: ${record.getClass.getCanonicalName}" )
    }
  }

  private def renameIdField( record: DBObject ) = {
    record.put( "id", record.get( "_id" ).toString )
    record.removeField( "_id" )
  }
}
